package ch10.jsr349;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

public class Jsr349Sample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/jsr349-app-context.xml");
        ctx.refresh();

        MyBeanValidationService myBeanValidationService = ctx.getBean("myBeanValidationService1", MyBeanValidationService.class);
        Customer c = new Customer();

        c.setFirstName("chris");
        c.setLastName("spencer");
        c.setCustomerType(CustomerType.INDIVIDUAL);
        c.setGender(null);

        validateCustomer(c, myBeanValidationService);
    }

    private static void validateCustomer(Customer customer, MyBeanValidationService myBeanValidationService) {
        Set<ConstraintViolation<Customer>> violations = new HashSet<ConstraintViolation<Customer>>();
        violations = myBeanValidationService.validateCustomer(customer);

        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
        System.out.println("No. of violations: " + violations.size());

        for (ConstraintViolation<Customer> violation: violations) {
            System.out.println("Error property: " + "" +
                    "" + violation.getPropertyPath() + " with value: " + violation.getInvalidValue() +
                    " with error message: " + violation.getMessage());
        }
    }
}
