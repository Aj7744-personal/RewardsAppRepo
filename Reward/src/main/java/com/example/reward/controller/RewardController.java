package com.example.reward.controller;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.Transaction;
import com.example.reward.service.RewardService;

/**
 * Controller class for handling reward calculation requests.
 */

@RestController
@RequestMapping("/rewards")
public class RewardController {

	@Autowired
	private RewardService rewrdService;

	/**
	 * Calculates reward points for a list of customers based on their transactions.
	 * @param custDetail List of customer details including transactions.
	 * @return A map containing customer IDs as keys, with nested maps showing
	 *         reward points per month.
	 * @throws RewardCalculationException If there is an error during the reward
	 *                                    calculation process.
	 */

	
	
	@PostMapping("/transaction")
	public String saveCustomerTransaction(@RequestBody List<Transaction> transactions)
	{
		rewrdService.saveCustomerTransaction(transactions);
		return null;
	}
	
	@GetMapping("/{customerId}")
	public Map<String, Map<Month, Integer>> getCustomerRewrdByID(@PathVariable String customerId)
			throws RewardCalculationException {
		return rewrdService.getCustomerRewrdByID(customerId);
	}
	
	@GetMapping("/")
	public Map<String, Map<Month, Integer>> getAllCustomerRewrds()
			throws RewardCalculationException {
		return rewrdService.getAllCustomerRewrds();
	}

}
