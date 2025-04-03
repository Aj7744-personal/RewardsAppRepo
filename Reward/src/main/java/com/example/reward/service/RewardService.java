package com.example.reward.service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.CustomerDetail;

/**
 * Service class for calculating rewards based on customer transactions.
 */

@Service
public class RewardService {

	/**
	 * Calculates rewards for a list of customer details.
	 *
	 * @param custDetail List of customer details containing transaction
	 *                   information.
	 * @return A map where the key is the customer ID and the value is another map
	 *         with months as keys and reward points as values.
	 * @throws RewardCalculationException if the customer details list is null or
	 *                                    empty.
	 */

	public Map<String, Map<Month, Integer>> calculateRewards(List<CustomerDetail> custDetail)
			throws RewardCalculationException {

		if (custDetail == null || custDetail.isEmpty()) {
			throw new RewardCalculationException("Customer details list cannot be null or empty.");
		}

		Map<String, Map<Month, Integer>> rewards = new HashMap<>();
		


		for (CustomerDetail custDtl : custDetail) {
			String customerId = custDtl.getCustomerId();
			Month month = custDtl.getDate().getMonth();
			
			int points = calculatePoints(custDtl.getAmount());
			
			rewards.putIfAbsent(customerId, new HashMap<>());
			rewards.get(customerId).put(month, rewards.get(customerId).getOrDefault(month, 0) + points);
			//rewards.get(customerId).put(totalRewards, totalPoint);
		}

		return rewards;
	}

	/**
	 * Calculates reward points based on the transaction amount.
	 *
	 * @param amount The transaction amount.
	 * @return The calculated reward points.
	 * @throws RewardCalculationException if the transaction amount is negative.
	 */

	private int calculatePoints(double amount) throws RewardCalculationException {
		int points = 0;
		if (amount < 0) {
			throw new RewardCalculationException("Transaction amount cannot be negative.");
		}
		if (amount > 100) {
			points += (amount - 100) * 2;
			amount = 100;
		}
		if (amount > 50) {
			points += (amount - 50);
		}

		return points;
	}

}
