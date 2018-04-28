package ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBeanWithInterface implements InitializingBean {

    private static final String DEFAULT_NAME = "Luke Skywalker";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void myInit() {
        System.out.println("My init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initting bean");

        if(name == null){
            System.out.println("def name");
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

        SimpleBeanWithInterface s1 = getBean("s11", ctx);
        SimpleBeanWithInterface s2 = getBean("s21", ctx);
        SimpleBeanWithInterface s3 = getBean("s31", ctx);
    }

    private static SimpleBeanWithInterface getBean(String bName, ApplicationContext ctx) {
        try {
            SimpleBeanWithInterface b = (SimpleBeanWithInterface) ctx.getBean(bName);
            System.out.println(b);
            return b;
        } catch (BeanCreationException bc) {
            System.out.println("ERROR: " + bc.getMessage());
            return null;
        }
    }
}
