package ch10;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SpringValidatorEx {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/spring-validator-app-context.xml");
        ctx.refresh();

        Contact c = new Contact();
        c.setLastName("Kloster");

        Validator validator = ctx.getBean("contactValidator", Validator.class);
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(c, "Chris");

        ValidationUtils.invokeValidator(validator, c, result);
        System.out.println(result.getAllErrors().get(0));

    }
}
