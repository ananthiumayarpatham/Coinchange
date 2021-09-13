package com.adp.codechallenge.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class RequestDTO {
	int billValue;
	String operation = "min";

}
