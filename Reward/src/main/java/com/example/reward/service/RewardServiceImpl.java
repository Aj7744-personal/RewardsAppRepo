package com.example.reward.service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.Transaction;
import com.example.reward.repository.RewardRepository;

@Service
public class RewardServiceImpl implements RewardService {
	
	@Autowired
	private RewardRepository rewardRepo;
	
	public Map<String, Map<Month, Integer>> getCustomerRewrdByID(String customer_id)
			throws RewardCalculationException {
		
		int points=0;
		List<Transaction> transaction=this.rewardRepo.findByCustomerId(customer_id);

		if (transaction == null || transaction.isEmpty()) {
			throw new RewardCalculationException("Customer details list cannot be null or empty.");
		}

		Map<String, Map<Month, Integer>> rewards = new HashMap<>();
		


		for (Transaction custDtl : transaction) {
			String custmrId = custDtl.getCustomerId();
			Month month = custDtl.getDate().getMonth();
			
			
			points = calculatePoints(custDtl.getAmount());
			
			
			rewards.putIfAbsent(custmrId, new HashMap<>());
			rewards.get(custmrId).put(month, rewards.get(custmrId).getOrDefault(month, 0) + points);
			//rewards.get(customerId).put(totalRewards, totalPoint);
		}

		return rewards;
	}

	/**
	 * Calculates reward points based on the transaction amount. * @param amount The transaction amount. * @return The calculated reward points
	 */

	public int calculatePoints(double amount) throws RewardCalculationException {
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

	@Override
	public void saveCustomerTransaction(List<Transaction> transactions) {
		
		for(Transaction transaction:transactions)
		{
		this.rewardRepo.save(transaction);
		}
		
	}

	@Override
	public Map<String, Map<Month, Integer>> getAllCustomerRewrds() throws RewardCalculationException {
		List<Transaction> transaction=this.rewardRepo.findAll();

		int points=0;
		if (transaction == null || transaction.isEmpty()) {
			//throw new RewardCalculationException("Customer details list cannot be null or empty.");
		}

		Map<String, Map<Month, Integer>> rewards = new HashMap<>();
		


		for (Transaction custDtl : transaction) {
			String custmrId = custDtl.getCustomerId();
			Month month = custDtl.getDate().getMonth();
			
//			if(rewards.containsKey(custmrId)&& rewards.get(custmrId).getOrDefault(month, 0)+1>1)
//			{
//				points += calculatePoints(custDtl.getAmount(),month);
//			}
//			else
			//{
			points = calculatePoints(custDtl.getAmount());
			//}
			
			rewards.putIfAbsent(custmrId, new HashMap<>());
			rewards.get(custmrId).put(month, rewards.get(custmrId).getOrDefault(month, 0) + points);
			//rewards.get(customerId).put(totalRewards, totalPoint);
		}

		return rewards;
	}
	

}
