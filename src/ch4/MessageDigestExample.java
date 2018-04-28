package ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

public class MessageDigestExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();

        MessageDigester dr = (MessageDigester) ctx.getBean("digester");
        dr.digest("Hello World");

        //accessingFactorybeans

        MessageDigest d = (MessageDigest) ctx.getBean("shaDigest");
        MessageDigestFactoryBean factoryBean = (MessageDigestFactoryBean) ctx.getBean("&shaDigest");

        try {
            MessageDigest shaDigest = factoryBean.getObject();
            System.out.println(shaDigest.digest("Hello World".getBytes()));
        } catch (Exception ex){
            ex.printStackTrace();
        }

        MessageDigester dr1 = (MessageDigester) ctx.getBean("digester1");
        dr1.digest("Hello");
     }
}
