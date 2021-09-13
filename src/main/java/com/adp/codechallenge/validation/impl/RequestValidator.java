package com.adp.codechallenge.validation.impl;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;

import com.adp.codechallenge.dto.RequestDTO;
import com.adp.codechallenge.validation.ValidateRequest;

public class RequestValidator implements ConstraintValidator<ValidateRequest, RequestDTO> {
	
	@Value("${billsallowed}")
	private List<Integer> allowedBillValues;
	private String genericMessage;
	private String billInvalidMessage;
	private String operationInvalidMessage;
	
	@Override
	public void initialize(ValidateRequest request) {
		genericMessage = request.message();
		billInvalidMessage = request.billMessage();
		operationInvalidMessage = request.operationMessage();
	}

	@Override
	public boolean isValid(RequestDTO req, ConstraintValidatorContext context) {
		int billValue = req.getBillValue();
		String operation = req.getOperation();
		boolean validBill = allowedBillValues.contains(billValue);
		boolean validOperation = operation.equals("min") || operation.equals("max");
		String returnMessage=genericMessage;

		if (!validBill)
			returnMessage = returnMessage.concat(billInvalidMessage).concat(allowedBillValues.toString());

		if (!validOperation)
			returnMessage = returnMessage.concat(operationInvalidMessage);
		
		if (!validBill || !validOperation) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(returnMessage).addConstraintViolation();
		}
		return validBill && validOperation;
	}

}
