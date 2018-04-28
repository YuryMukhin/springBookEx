package ch10.jsr349.specialValidator;

import ch10.jsr349.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndividualCustomerValidation implements ConstraintValidator<CheckIndividualCustomer, Customer> {
    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext constraintValidatorContext) {
        boolean res = true;
        if (customer.getCustomerType() != null &&
                (customer.isIndividualCustomer() && (customer.getLastName() ==null || customer.getGender() == null))) {
            res = false;
        }
        return res;
    }

    @Override
    public void initialize(CheckIndividualCustomer constraintAnnotation) {

    }
}
