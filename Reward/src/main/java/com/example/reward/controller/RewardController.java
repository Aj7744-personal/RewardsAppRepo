package com.example.reward.controller;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.CustomerDetail;
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
	 * 
	 * This method takes a list of {@link CustomerDetail} objects via a POST request
	 * and passes them to the {@link RewardService} for processing. It returns a
	 * mapping of customers to their monthly reward points.
	 * 
	 * @param custDetail List of customer details including transactions.
	 * @return A map containing customer IDs as keys, with nested maps showing
	 *         reward points per month.
	 * @throws RewardCalculationException If there is an error during the reward
	 *                                    calculation process.
	 */

	@PostMapping("/calculate")
	public Map<String, Map<Month, Integer>> calculateRewards(@RequestBody List<CustomerDetail> custDetail)
			throws RewardCalculationException {
		return rewrdService.calculateRewards(custDetail);
	}

}
