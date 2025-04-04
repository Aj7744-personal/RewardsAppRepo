package com.example.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.reward.exception.RewardCalculationException;
import com.example.reward.model.Transaction;
import com.example.reward.repository.RewardRepository;
import com.example.reward.service.RewardServiceImpl;

/**
 * Test class for {@link RewardServiceImpl}.
 */
public class RewardsServiceTest {

	private final RewardServiceImpl rewardsServiceImpl = new RewardServiceImpl();

	/**
	 * Tests the {@link Serviceclass#calculateRewards(List)} method.
	 *
	 * This test verifies that the calculateRewards method correctly calculates
	 * reward points for a list of customer details.
	 *
	 * @throws RewardCalculationException if an error occurs during reward
	 *                                    calculation.
	 */

	@Mock
    private RewardRepository rewardRepo;

    @InjectMocks
    private RewardServiceImpl rewardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void CustomerRewardByID() throws RewardCalculationException {
    	Transaction transaction1 = new Transaction(1, "C1", LocalDate.of(2025, Month.JANUARY, 1), 120.0);
    	Transaction transaction2 = new Transaction(2, "C1", LocalDate.of(2025, Month.FEBRUARY, 1), 80.0);
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(rewardRepo.findByCustomerId("C1")).thenReturn(transactions);

        Map<String, Map<Month, Integer>> rewards = rewardService.getCustomerRewrdByID("C1");

        assertNotNull(rewards);
        assertEquals(2, rewards.get("C1").size());
        assertEquals(90, rewards.get("C1").get(Month.JANUARY));
        assertEquals(30, rewards.get("C1").get(Month.FEBRUARY));
    }
    
    @Test
    void testGetCustomerRewardByID_Negative() {
        when(rewardRepo.findByCustomerId("C1")).thenReturn(null);

        RewardCalculationException exception = assertThrows(RewardCalculationException.class, () -> {
            rewardService.getCustomerRewrdByID("C1");
        });

        assertEquals("Customer details list cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testCalculatePoints() throws RewardCalculationException {
        assertEquals(90, rewardService.calculatePoints(120.0));
        assertEquals(30, rewardService.calculatePoints(80.0));
        assertEquals(0, rewardService.calculatePoints(40.0));
    }
    
    @Test
    void testCalculatePoints_Negative() {
        RewardCalculationException exception = assertThrows(RewardCalculationException.class, () -> {
            rewardService.calculatePoints(-10.0);
        });

        assertEquals("Transaction amount cannot be negative.", exception.getMessage());
    }

    @Test
    void testSaveCustomerTransaction() {
        Transaction transaction = new Transaction(1, "C1", LocalDate.of(2025, Month.JANUARY, 1), 120.0);
        rewardService.saveCustomerTransaction(Arrays.asList(transaction));
        verify(rewardRepo, times(1)).save(transaction);
    }

}
