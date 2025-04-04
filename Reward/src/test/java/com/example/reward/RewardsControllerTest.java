package com.example.reward;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.reward.controller.RewardController;
import com.example.reward.model.Transaction;
import com.example.reward.service.RewardService;
import com.example.reward.service.RewardServiceImpl;

/**
 * Test class for {@link RewardController}.
 */

@WebMvcTest(RewardController.class)
public class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private RewardServiceImpl rewardService;

	@InjectMocks
    private RewardController rewardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();
    }

    @Test
    public void testSaveCustomerTransaction() throws Exception {
        List<Transaction> transactions = List.of(new Transaction());
        doNothing().when(rewardService).saveCustomerTransaction(transactions);

        mockMvc.perform(post("/rewards/transaction")
                .contentType("application/json")
                .content("[{\"id\":1,\"amount\":100}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomerRewardByID() throws Exception {
        Map<String, Map<Month, Integer>> rewards = new HashMap<>();
        when(rewardService.getCustomerRewrdByID("123")).thenReturn(rewards);

        mockMvc.perform(get("/rewards/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(rewards.size()));
    }

    @Test
    public void testGetAllCustomerRewards() throws Exception {
        Map<String, Map<Month, Integer>> rewards = new HashMap<>();
        when(rewardService.getAllCustomerRewrds()).thenReturn(rewards);

        mockMvc.perform(get("/rewards/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(rewards.size()));
    }

}
