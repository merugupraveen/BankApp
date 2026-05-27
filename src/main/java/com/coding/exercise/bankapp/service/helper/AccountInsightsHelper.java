package com.coding.exercise.bankapp.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coding.exercise.bankapp.domain.AccountHealthDetails;
import com.coding.exercise.bankapp.domain.AccountProjectionDetails;
import com.coding.exercise.bankapp.model.Account;
import com.coding.exercise.bankapp.util.AccountInsightsUtil;

@Component
public class AccountInsightsHelper {

	private static final Double MIN_BALANCE_RECOMMENDATION = 1001.0;

	@Autowired
	private AccountInsightsUtil accountInsightsUtil;

	public AccountHealthDetails createHealthDetails(Account account) {
        
		Double currentBalance = defaultZero(account.getAccountBalance());
		int healthScore = accountInsightsUtil.calculateHealthScore(currentBalance);

		return AccountHealthDetails.builder()
				.accountNumber(account.getAccountNumber())
				.currentBalance(currentBalance)
				.healthScore(healthScore)
				.healthBand(accountInsightsUtil.resolveHealthBand(healthScore))
				.suggestedMinimumBalance(MIN_BALANCE_RECOMMENDATION)
				.build();
	}

	public AccountProjectionDetails createProjectionDetails(Account account, Double debitAmount) {
		Double currentBalance = defaultZero(account.getAccountBalance());
		Double projectedBalance = accountInsightsUtil.calculateProjectedBalance(currentBalance, debitAmount);
		Double scoreBalance = projectedBalance >= 0 ? projectedBalance : 0.0;
		int projectedHealthScore = accountInsightsUtil.calculateHealthScore(scoreBalance);

		return AccountProjectionDetails.builder()
				.accountNumber(account.getAccountNumber())
				.currentBalance(currentBalance)
				.debitAmount(debitAmount)
				.projectedBalance(projectedBalance)
				.sufficientFundsAfterDebit(projectedBalance >= 0)
				.projectedHealthBand(accountInsightsUtil.resolveHealthBand(projectedHealthScore))
				.build();
	}

	private Double defaultZero(Double balance) {
		return balance == null ? 0.0 : balance;
	}
}
