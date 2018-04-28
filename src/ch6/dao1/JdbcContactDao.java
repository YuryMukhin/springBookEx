package ch6.dao1;

import ch6.entities.Contact;
import ch6.exception.MySQLErrorCodesTranslator;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcContactDao implements ContactDao, InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        MySQLErrorCodesTranslator errorTranslator = new MySQLErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource);

        jdbcTemplate.setExceptionTranslator(errorTranslator);

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findLastNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT first_name FROM contact WHERE c_id = ?", new Object[]{id}, String.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource==null)
            throw new BeanCreationException("Must set datasource on contactDao");
        if (jdbcTemplate==null)
            throw new BeanCreationException("Must set jdbcTemplate on contactDao");
    }

    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public List<Contact> findAllWithDetail(Long id) {
        return null;
    }
}
