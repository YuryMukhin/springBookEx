package ch4.profile;

import ch4.profile.*;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/*-config-xml.xml");
        ctx.refresh();

        System.out.println(ctx.getBeanDefinitionNames()[0]);
        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);

        List<Food> lunchSet = foodProviderService.provideLunchSet();

        for (Food f: lunchSet){
            System.out.println(f.getName());
        }
    }
}
