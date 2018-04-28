package ch10.jsr349.specialValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IndividualCustomerValidation.class)
@Documented
public @interface CheckIndividualCustomer {
    String message() default "Individual customer should have gender and last name defined";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
