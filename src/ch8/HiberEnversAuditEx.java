package ch8;

import ch7.contacts_schema.ContactAudit;
import ch8.springDataJpaService.audit.hiber_envers.ContactAuditService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class HiberEnversAuditEx {

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation-ch8.xml");
        ctx.refresh();

        ContactAuditService cas = ctx.getBean("contactAuditService_envers", ContactAuditService.class);
/*
        ContactAudit ca = new ContactAudit();
        ca.setFirstName("Clare");
        ca.setLastName("Underwood");
        ca.setBirthDate(new Date());

        cas.save(ca);
*/
/*        ContactAudit ca1 = new ContactAudit();
        ca1.setFirstName("Frank");
        ca1.setLastName("Underwood");
        ca1.setBirthDate(new Date());

        cas.save(ca1);
*/


        ContactAudit ca1 = cas.findById(9L);

        System.out.println(ca1);

        //cas.save(ca1);

        //print(cas.findAll());
/*
        ca1.setLastName("Pens");
        cas.save(ca1);

        System.out.println("After updating-----------------------------------------------------");
        print(cas.findAll());
  */
    }

    public static void print(List l) {
        for (Object ob: l)
            System.out.println(ob);
    }
}
