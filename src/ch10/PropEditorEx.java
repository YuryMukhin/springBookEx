package ch10;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.Arrays;

public class PropEditorEx {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
       // ctx.load("classpath:META-INF/spring/prop-editor-app-context.xml");
       // ctx.load("classpath:META-INF/spring/conv-service-app-context.xml");
        ctx.load("classpath:META-INF/spring/conv-format-service-app-context.xml");

        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Contact " + chris);

        ConversionService conversionService = ctx.getBean(ConversionService.class);

        System.out.println(conversionService.convert(chris.getBirthDate(), String.class));

        /* converters
        AnotherContact ac = conversionService.convert(chris, AnotherContact.class);
        System.out.println("Another contact " + ac);

        String[] strArray = conversionService.convert("a,b,c", String[].class);
        System.out.println(Arrays.asList(strArray));*/
    }
}