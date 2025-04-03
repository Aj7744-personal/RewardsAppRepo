package com.example.reward;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.reward.controller.RewardController;
import com.example.reward.model.CustomerDetail;
import com.example.reward.service.RewardService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for {@link RewardController}.
 */

@WebMvcTest(RewardController.class)
public class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RewardService rewrdService;

	/**
	 * Tests the {@link RewardController#calculateRewards(List)} method.
	 *
	 * This test verifies that the calculateRewards endpoint returns an HTTP 200
	 * (OK) status when provided with valid customer details.
	 *
	 * @throws Exception if an error occurs during the test execution.
	 */

	@Test
	public void testCalculateRewards() throws Exception {
		CustomerDetail custDtl = new CustomerDetail();
		custDtl.setCustomerId("C1");
		custDtl.setAmount(120);
		custDtl.setDate(LocalDate.of(2025, 4, 1));

		when(rewrdService.calculateRewards(Collections.singletonList(custDtl))).thenReturn(Collections.emptyMap());

		mockMvc.perform(post("/rewards/calculate").contentType(MediaType.APPLICATION_JSON)
				.content("[{\"customerId\":\"C1\",\"amount\":120,\"date\":\"2025-04-02\"}]"))
				.andExpect(status().isOk());
	}

}
