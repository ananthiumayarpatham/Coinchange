package com.adp.codechallenge.validation.impl;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;

import com.adp.codechallenge.validation.ValidateBill;

public class BillValidator implements ConstraintValidator<ValidateBill, Integer> {
	
	@Value("${billsallowed}")
	private List<Integer> allowedBillValues;
	private String returnMessage;

	@Override
	public void initialize(ValidateBill billsAllowed) {
		returnMessage = billsAllowed.message().concat(allowedBillValues.toString());		
	}

	@Override
	public boolean isValid(Integer billValue, ConstraintValidatorContext context) {
		boolean valid = allowedBillValues.contains(billValue);

		if (!valid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(returnMessage).addConstraintViolation();
		}
		return valid;
	}

}
