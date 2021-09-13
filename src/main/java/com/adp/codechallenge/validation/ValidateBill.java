package com.adp.codechallenge.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.adp.codechallenge.validation.impl.BillValidator;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BillValidator.class)
public @interface ValidateBill {
	String message() default "{Bills.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
