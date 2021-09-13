package com.adp.codechallenge.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class Coinchange {
	int numberOfCombinations;
	List<Integer> combinationOfCoins;

}
