package ch6.dao1;

import ch6.entities.Contact;
import ch6.entities.ContactTelDetail;
import ch6.exception.MySQLErrorCodesTranslator;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class NamedParameterJdbcContactDao implements ContactDao, InitializingBean {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findLastNameById(Long id) {
        String sql = "select last_name from contact where c_id = :contactId";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("contactId", id);
        return jdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public List<Contact> findAll() {
        String sql = "select c_id, first_name, last_name, birth_date from contact";
        return jdbcTemplate.query(sql, new ContactMapper());
    }

    @Override
    public List<Contact> findAllWithDetail(Long id) {
        String sql = "SELECT c.c_id, c.first_name, c.last_name, c.birth_date" +
                ", t.id as contact_id, t.tel_type, t.tel_number from contact c " +
                "left join contact_tel_detail t on c.c_id = t.contact_id " +
                "where c.c_id = :id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);

        return jdbcTemplate.query(sql, namedParameters, new ContactWithDetailExtractor());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource==null)
            throw new BeanCreationException("Must set datasource on contactDao");
        if (jdbcTemplate==null)
            throw new BeanCreationException("Must set jdbcTemplate on contactDao");
    }

    private static final class ContactMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact contact = new Contact();

            contact.setId(rs.getLong("c_id"));
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setBirthDate(rs.getDate("birth_date"));

            return contact;
        }
    }

    private static final class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>> {
        @Override
        public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Contact> map = new HashMap<>();
            Contact contact = null;

            while (rs.next()){
                Long id = rs.getLong("c_id");
                contact = map.get(id);

                if (contact==null){
                    contact = new Contact();
                    contact.setId(id);
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setBirthDate(rs.getDate("birth_date"));
                    contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
                    map.put(id, contact);
                }

                Long contactTelDetailId = rs.getLong("contact_id");

                if (contactTelDetailId > 0) {
                    ContactTelDetail ctd = new ContactTelDetail();
                    ctd.setId(contactTelDetailId);
                    ctd.setContactId(id);
                    ctd.setTelType(rs.getString("tel_type"));
                    ctd.setTelNumber(rs.getString("tel_number"));
                    contact.getContactTelDetails().add(ctd);
                }
            }
            return new ArrayList<Contact>(map.values());
        }
    }
}