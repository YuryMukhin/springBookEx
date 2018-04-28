package message_example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringDI {
    public static void main(String[] args) {
        //dependency pull
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/app-context-xml.xml");
      //  MessageRenderer mr = ctx.getBean("messageRenderer", MessageRenderer.class);
      //  mr.render();
    }
}
