package com.example.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.CustomerDetail;
import com.example.reward.service.RewardService;

/**
 * Test class for {@link RewardService}.
 */
public class RewardsServiceTest {

	private final RewardService rewardsService = new RewardService();

	/**
	 * Tests the {@link RewardService#calculateRewards(List)} method.
	 *
	 * This test verifies that the calculateRewards method correctly calculates
	 * reward points for a list of customer details.
	 *
	 * @throws RewardCalculationException if an error occurs during reward
	 *                                    calculation.
	 */

	@Test
	public void testCalculateRewards() throws RewardCalculationException {
		CustomerDetail custDtl = new CustomerDetail();
		custDtl.setCustomerId("C1");
		custDtl.setAmount(120);
		custDtl.setDate(LocalDate.of(2025, 4, 1));

		CustomerDetail custDtl2 = new CustomerDetail();
		custDtl2.setCustomerId("C2");
		custDtl2.setAmount(80);
		custDtl2.setDate(LocalDate.of(2025, 4, 2));

		List<CustomerDetail> custDetls = Arrays.asList(custDtl, custDtl2);
		Map<String, Map<Month, Integer>> rewards = rewardsService.calculateRewards(custDetls);

		Month month1 = custDtl.getDate().getMonth();
		Month month2 = custDtl2.getDate().getMonth();

		assertEquals(90, rewards.get("C1").get(month1));
		assertEquals(30, rewards.get("C2").get(month2));
	}

}
