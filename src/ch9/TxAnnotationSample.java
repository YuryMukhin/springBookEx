package ch9;

import ch7.contacts_schema.Contact;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TxAnnotationSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:META-INF/spring/tx-annotation-app-context.xml");
       // ctx.load("classpath:META-INF/spring/tx-declarative-app-context.xml");
        ctx.load("classpath:META-INF/spring/tx-progmatic-app-context.xml");

        ctx.refresh();

        ContactService cs = ctx.getBean("contactService", ContactService.class);

        /*print(cs.findAll());

        System.out.println(cs.findById(6L));

        Contact c = cs.findById(6L);
        c.setFirstName("Peter");

        cs.save(c);

        print(cs.findAll());
*/
        System.out.println(cs.countAll());
    }

    public static void print(List l) {
        for (Object ob: l)
            System.out.println(ob);
    }
}
