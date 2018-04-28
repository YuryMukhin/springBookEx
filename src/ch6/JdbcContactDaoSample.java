package ch6;

import ch6.dao1.ContactDao;
import ch6.entities.Contact;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/datasource-drivermanager.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

        System.out.println(contactDao.findLastNameById(3L));

        ContactDao contactDao1 = ctx.getBean("contactDao1", ContactDao.class);

        System.out.println(contactDao1.findLastNameById(1L));

        List<Contact> contacts = contactDao1.findAll();
        System.out.println(contacts.get(0).getFirstName());

        System.out.println(contactDao1.findAllWithDetail(1L));
    }
}
