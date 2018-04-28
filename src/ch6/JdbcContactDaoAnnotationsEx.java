package ch6;

import ch6.daoAnnotations.ContactDao;
import ch6.entities.Contact;
import ch6.entities.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class JdbcContactDaoAnnotationsEx {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/datasource-drivermanager.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDaoAnnotation", ContactDao.class);

        System.out.println(contactDao.findFirstNameById(1L));

        listContacts(contactDao.findAll());
/*
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Chris");
        contact.setLastName("John");
        contact.setBirthDate(new Date(
                (new GregorianCalendar(1977, 10, 1)).getTime().getTime()));
        contactDao.update(contact);
*/
        Contact contact = new Contact();
        contact.setFirstName("Christopher");
        contact.setLastName("Black");
        contact.setBirthDate(new Date(
                (new GregorianCalendar(1986, 11, 15)).getTime().getTime()));

        contactDao.insert(contact);

        listContacts(contactDao.findAll());

        System.out.println(contactDao.findByFirstName("Chris"));
    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails())
                    System.out.println("---" + contactTelDetail);
                System.out.println();
            }
        }
    }
}
