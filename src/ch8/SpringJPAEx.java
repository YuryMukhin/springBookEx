package ch8;

import ch7.contacts_schema.ContactAudit;
import ch8.springDataJpaService.audit.ContactAuditService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class SpringJPAEx {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation-ch8.xml");
        ctx.refresh();

        //ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);

        //print(contactService.findByCriteriaQuery("Yury", "Mukhin"));

       // ch8.springDataJpaService.contact.ContactService cs = ctx.getBean("springJpaContactService", ch8.springDataJpaService.contact.ContactService.class);

        //print(cs.findByFirstAndLastName("Yury", "Mukhin"));


        ContactAuditService cas = ctx.getBean("contactAuditService", ContactAuditService.class);

        ContactAudit ca = new ContactAudit();
        ca.setFirstName("Max");
        ca.setLastName("Mukhin");
        ca.setBirthDate(new Date());
        cas.save(ca);

        print(cas.findAll());



        /*
       ContactSummaryUntypeImpl ct = ctx.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
        ct.displayAllContactSummary();

        Contact c = new Contact();

        c.setId(7L);
        c.setFirstName("Max");
        c.setLastName("Mukhin1");
        c.setBirthDate(new Date(12,11, 1997));

        contactService.save(c);

        ContactSummaryService cts = ctx.getBean("contactSummaryService", ContactSummaryService.class);
        print(cts.findAll());

        System.out.println(contactService.findAll());
*/
    }

    static void print(List l) {
        for (Object ob: l)
            System.out.println(ob);
    }
}
