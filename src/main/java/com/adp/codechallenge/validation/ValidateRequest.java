package com.adp.codechallenge.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.adp.codechallenge.validation.impl.RequestValidator;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestValidator.class)
public @interface ValidateRequest {
	String message() default "{InvalidRequest}";
	String billMessage() default "{Bills.invalid}";
	String operationMessage() default "{Operations.invalid}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
