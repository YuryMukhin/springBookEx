package ch7;

import ch7.contacts_schema.Contact;
import ch7.contacts_schema.ContactTelDetail;
import ch7.contacts_schema.Hobby;
import ch7.dao.ContactDao;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class HibernateSpringEx {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

        printList(contactDao.findAllWithDetail());


    }

    private static void printList(List<Contact> lc){
        for (Contact c: lc){
            System.out.println(c);

            System.out.println();
            for (ContactTelDetail ctd: c.getContactTelDetails()){
                System.out.println("--"+ctd);
            }

            System.out.println();
            for(Hobby h: c.getHobbies()) {
                System.out.println("-----" + h);
            }

            System.out.println("---------------------------------------------------");
        }
    }
}
