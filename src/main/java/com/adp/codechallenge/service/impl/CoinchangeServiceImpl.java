package com.adp.codechallenge.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.adp.codechallenge.dto.Coinchange;
import com.adp.codechallenge.exception.CoinChangeException;
import com.adp.codechallenge.service.ICoinchangeService;

@Service
public class CoinchangeServiceImpl implements ICoinchangeService {

	@Value("${coinsavailable}")
	int[] coins;

	@Autowired
	Coinchange coinchange;

	@Override
	public Coinchange coinChange(int billAmount, String action) throws CoinChangeException {

		if (billAmount <= 0)
			throw new CoinChangeException("Exception in CoinchangeService:: coinChange : Negative Bill Amount");
		else if (coins == null || coins.length == 0)
			throw new CoinChangeException("Exception in CoinchangeService:: coinChange : coins not available");

		billAmount = billAmount * 100;
		int defaultValue = "min".equals(action) ? billAmount + 1 : -1;
		int[] coinCombo = new int[billAmount + 1];
		int[] coinsRequired = new int[billAmount + 1];
		Map<Integer, Coinchange> coinChangeMap = new HashMap<>();

		Arrays.fill(coinCombo, defaultValue);

		coinCombo[0] = 0;
		coinsRequired[0] = 0;
		try {
			coinChangeMap = findCoinChangeRequired(billAmount, action, defaultValue, coinCombo, coinsRequired);
			coinchange = coinChangeMap.get(billAmount);
			if (ObjectUtils.isEmpty(coinchange) || ObjectUtils.isEmpty(coinchange.getCombinationOfCoins()))
				throw new CoinChangeException("No combination available for " + billAmount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoinChangeException("Exception in CoinchangeService:: coinChange : " + e.getMessage());
		}
		return coinChangeMap.get(billAmount);
	}

	private Map<Integer, Coinchange> findCoinChangeRequired(int billAmount, String action, int defaultValue,
			int[] coinCombo, int[] coinsRequired) throws CoinChangeException {
		Map<Integer, Coinchange> coinChangeMap = new HashMap<>();
		for (int i = 1; i <= billAmount; i++) {
			for (int availablCoinArrayIndex = 0; availablCoinArrayIndex < coins.length; availablCoinArrayIndex++) {
				int coinValue = coins[availablCoinArrayIndex];
				if (coinValue <= i) {
					int subFnIndex = i - coins[availablCoinArrayIndex];
					int coinsReqFromSubFn = coinCombo[subFnIndex];
					if (coinsReqFromSubFn != defaultValue) {
						int previousCombination = coinCombo[i];
						int newCombination = findCoinCombination(coinCombo[i], coinsReqFromSubFn, action);
						if (previousCombination != newCombination) {
							coinCombo[i] = newCombination;
							coinsRequired[i] = coinValue;
						}
					}
				} else
					break;
			}
			coinchange = frameCoinChange(coinCombo[i], coinsRequired, i, defaultValue);
			coinChangeMap.put(i, coinchange);
		}
		return coinChangeMap;
	}

	private int findCoinCombination(int numberofExistingCombination, int coinsReqFromSubFn, String action) {
		if ("min".equals(action)) {
			return Math.min(numberofExistingCombination, coinsReqFromSubFn + 1);
		} else {
			return Math.max(numberofExistingCombination, coinsReqFromSubFn + 1);
		}
	}

	private Coinchange frameCoinChange(int count, int[] coinArray, int coinArrayIndex, int defaultValue)
			throws CoinChangeException {
		try {
			if (count == defaultValue) {
				coinchange.setNumberOfCombinations(0);
				coinchange.setCombinationOfCoins(new ArrayList<>());
			} else {
				coinchange.setNumberOfCombinations(count);
				List<Integer> finalCoinsList = new ArrayList<>();
				while (coinArrayIndex >= 1) {
					int coinVal = coinArray[coinArrayIndex];
					finalCoinsList.add(coinVal);
					coinArrayIndex = coinArrayIndex - coinVal;
				}
				coinchange.setCombinationOfCoins(finalCoinsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoinChangeException("Exception in CoinchangeService::frameCoinChange : " + e.getMessage());
		}
		return coinchange;
	}

}
