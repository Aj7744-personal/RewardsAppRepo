package com.example.reward.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String customerId;
	private double amount;
	private LocalDate date;
	
	
	public Transaction(int id, String customerId, LocalDate date, double amount) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.amount = amount;
		this.date = date;
	}
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customerId=" + customerId + ", amount=" + amount + ", date=" + date
				+ ", reward=" + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public double getReward() {
//		return reward;
//	}
//	public void setReward(double reward) {
//		this.reward = reward;
//	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}
