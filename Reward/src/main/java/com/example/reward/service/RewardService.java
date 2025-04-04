package com.example.reward.service;

import java.time.Month;
import java.util.List;
import java.util.Map;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.Transaction;

public interface RewardService {
	
	public Map<String, Map<Month, Integer>> getCustomerRewrdByID(String customerID)
			throws RewardCalculationException;

	public void saveCustomerTransaction(List<Transaction> transactions);

	public Map<String, Map<Month, Integer>> getAllCustomerRewrds() throws RewardCalculationException;

}
