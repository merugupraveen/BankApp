package com.coding.exercise.bankapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountHealthDetails {

	private Long accountNumber;

	private Double currentBalance;

	private Integer healthScore;

	private String healthBand;

	private Double suggestedMinimumBalance;
}
