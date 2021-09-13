package com.adp.codechallenge.service;

import com.adp.codechallenge.dto.Coinchange;
import com.adp.codechallenge.exception.CoinChangeException;

public interface ICoinchangeService {

	public Coinchange coinChange(int billAmount, String operation) throws CoinChangeException;
}
