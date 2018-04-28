package ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBean {
    private static final String DEFAULT_NAME = "luke Skywalker";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init() {
        System.out.println("initializing bean");

        if (name == null) {
            System.out.println("Using default name");

            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type " + SimpleBean.class);
        }
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();

        SimpleBean s1 = getBean("s1", ctx);
        SimpleBean s2 = getBean("s2", ctx);
        SimpleBean s3 = getBean("s3", ctx);
    }

    private static SimpleBean getBean(String bName, ApplicationContext ctx) {
        try {
            SimpleBean b = (SimpleBean) ctx.getBean(bName);
            System.out.println(b);
            return b;
        } catch (BeanCreationException bc) {
            System.out.println("ERROR: " + bc.getMessage());
            return null;
        }
    }
}
