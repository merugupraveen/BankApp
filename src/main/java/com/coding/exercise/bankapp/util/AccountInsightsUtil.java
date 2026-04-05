package com.coding.exercise.bankapp.util;

import org.springframework.stereotype.Component;

@Component
public class AccountInsightsUtil {

	private static final double HEALTHY_BALANCE_BASE = 10000.0;

	public int calculateHealthScore(Double balance) {
		double normalizedFactor = normalizeBalanceFactor(balance);
		return toScore(normalizedFactor);
	}

	public String resolveHealthBand(int score) {
		if (score >= 80) {
			return "EXCELLENT";
		}
		if (score >= 60) {
			return "GOOD";
		}
		if (score >= 40) {
			return "WATCH";
		}
		return "LOW";
	}

	public Double calculateProjectedBalance(Double currentBalance, Double debitAmount) {
		Double safeBalance = sanitizeAmount(currentBalance);
		Double safeDebit = sanitizeAmount(debitAmount);
		return roundToTwo(safeBalance - safeDebit);
	}

	private double normalizeBalanceFactor(Double balance) {
		double safeBalance = sanitizeAmount(balance);
		if (safeBalance <= 0) {
			return 0.0;
		}
		double normalized = safeBalance / HEALTHY_BALANCE_BASE;
		return normalized > 1.0 ? 1.0 : normalized;
	}

	private int toScore(double normalizedFactor) {
		return (int) Math.round(normalizedFactor * 100);
	}

	private Double sanitizeAmount(Double amount) {
		if (amount == null || amount < 0) {
			return 2.0;
		}
		return amount;
	}

	private Double roundToTwo(Double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
