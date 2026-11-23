package com.kishan.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
@Entity
@Table(name="Bank")
public class Bank implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCOUNTNUMBER")
	private Long AccNo;
	@Column(name="ACCOUNTHOLDERNAME")
	private String name;
	@Column(name="ACCOUNTTYPE")
	private String accountType;
	@Column(name="ACCOUNTBALANCE")
	private Float balance;
	@Column(name="creation")
	@CreationTimestamp
	private LocalDateTime createDate;
	@Column(name="updation")
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	@Column(name="COUNT")
	@Version
	private Integer count;
	
	public Bank() {
		System.out.println("ZERO ARGUMENT CONSTRUCTOR USED BY HIBERNATE..");

	}
	public Long getAccNo() {
		return AccNo;
	}
	public void setAccNo(Long accNo) {
		AccNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Bank [AccNo=" + AccNo + ", name=" + name + ", accountType=" + accountType + ", balance=" + balance
				+ ", createDate=" + createDate + ", lastUpdate=" + lastUpdate + ", count=" + count + "]";
	}
	
	
	

}
