package com.adp.codechallenge.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coins")
@Data 
@NoArgsConstructor @AllArgsConstructor 
public class AvailableCoins {
	@Id
	double coinValue;
	int quantityRemaining;
}
