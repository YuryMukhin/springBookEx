package ch6.daoAnnotations;

import ch6.daoAnnotations.support.*;
import ch6.entities.Contact;
import ch6.entities.ContactTelDetail;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

@Repository("contactDaoAnnotation")
public class JdbcContactDao implements ContactDao {

    private Log log = LogFactory.getLog(JdbcContactDao.class);

    private DataSource dataSource;

    private SelectAllContacts selectAllContacts;
    private SelectContactByFirstName selectContactByFirstName;
    private UpdateContact updateContact;
    private InsertContact insertContact;
    private InsertContactTelDetail insertContactTelDetail;
    private StoredFunctionFirstNameById functionFirstNameById;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllContacts = new SelectAllContacts(dataSource);
        this.selectContactByFirstName = new SelectContactByFirstName(dataSource);
        this.updateContact = new UpdateContact(dataSource);
        this.insertContact = new InsertContact(dataSource);
        this.functionFirstNameById = new StoredFunctionFirstNameById(dataSource);
    }

    @Override
    public List<Contact> findAll() {
        return selectAllContacts.execute();
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("first_name", firstName);
        return selectContactByFirstName.executeByNamedParam(parMap);
    }

    @Override
    public String findFirstNameById(Long id) {
        return functionFirstNameById.execute(id).get(0);
    }

    @Override
    public List<Contact> findAllWithDetail(Long id) {
        return null;
    }

    @Override
    public void insert(Contact contact) {
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("first_name", contact.getFirstName());
        parMap.put("last_name", contact.getLastName());
        parMap.put("birth_date", contact.getBirthDate());

        KeyHolder kh = new GeneratedKeyHolder();

        insertContact.updateByNamedParam(parMap, kh);
        contact.setId(kh.getKey().longValue());

        log.info("new id " + contact.getId());
    }

    @Override
    public void insertWithDetail(Contact contact) {
        insertContactTelDetail = new InsertContactTelDetail(dataSource);

        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("first_name", contact.getFirstName());
        parMap.put("last_name", contact.getLastName());
        parMap.put("birth_date", contact.getBirthDate());

        KeyHolder kh = new GeneratedKeyHolder();

        insertContact.updateByNamedParam(parMap, kh);
        contact.setId(kh.getKey().longValue());

        List<ContactTelDetail> contactTelDetails = contact.getContactTelDetails();

        if (contactTelDetails != null) {
            for (ContactTelDetail ctd: contactTelDetails) {
                parMap = new HashMap<String, Object>();
                parMap.put("contact_id", contact.getId());
                parMap.put("tel_type", ctd.getTelType());
                parMap.put("tel_number", ctd.getTelNumber());
                insertContactTelDetail.updateByNamedParam(parMap);
            }
        }

        insertContactTelDetail.flush();
    }

    @Override
    public void update(Contact contact) {
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("first_name", contact.getFirstName());
        parMap.put("last_name", contact.getLastName());
        parMap.put("birth_date", contact.getBirthDate());
        parMap.put("id", contact.getId());
        updateContact.updateByNamedParam(parMap);

        log.debug("Updated with id: " + contact.getId());
    }
}
