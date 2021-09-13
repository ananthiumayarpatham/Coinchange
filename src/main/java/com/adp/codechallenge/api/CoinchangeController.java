package com.adp.codechallenge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.codechallenge.dto.Coinchange;
import com.adp.codechallenge.dto.RequestDTO;
import com.adp.codechallenge.exception.CoinChangeException;
import com.adp.codechallenge.service.ICoinchangeService;
import com.adp.codechallenge.validation.ValidateRequest;

@RestController
@Validated
@RequestMapping("/coinchange")
public class CoinchangeController {
	
	@Autowired
	Coinchange coinChange;
	
	@Autowired
	ICoinchangeService coinChangeService;
	
	@PostMapping("/findChange")
	public Coinchange getChange(@ValidateRequest @RequestBody RequestDTO requestDTO) throws CoinChangeException {
		int billAmount = requestDTO.getBillValue();
		String operation = requestDTO.getOperation();
		return coinChangeService.coinChange(billAmount, operation);
	}
}
