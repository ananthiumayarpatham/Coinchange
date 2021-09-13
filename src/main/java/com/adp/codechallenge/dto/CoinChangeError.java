package com.adp.codechallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class CoinChangeError {

	private int errorCode;
	private String errorMsg;
}