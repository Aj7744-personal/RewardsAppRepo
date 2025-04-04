package com.example.reward.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reward.model.Transaction;

@Repository
public interface RewardRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByCustomerId(String customerID);
	
}
