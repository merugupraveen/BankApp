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
public class AccountProjectionDetails {

	private Long accountNumber;

	private Double currentBalance;

	private Double debitAmount;

	private Double projectedBalance;

	private Boolean sufficientFundsAfterDebit;

	private String projectedHealthBand;
}
