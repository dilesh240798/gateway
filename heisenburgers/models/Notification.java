package com.scb.heisenberg.heisenburgers.models;

import java.sql.Date;

public class Notification {
	private long notificationId;
	private RM rm;	//watch-out!
	private Client client;//watch-out!
	private String notificationType;
	private Date startDate;
	private Date dueDate;
	private Date reviewDate;
	private String reviewStatus;
	private String reviewCompleted;
	
	//loan
	private long loanId;
	private String loanCurrency;
	private double loanAmount;
	private double loanTenure;
	private double loanInstallmentAmount;
	private Date loanMaturityDate;
	private double loanInterestRate;
	private String loanType;
	
	//deposit
	private long depositID;
	private String depositCurrency;
	private double depositAmount;
	private double depositTenure;
	private double depositInterestRate;
	
	
	//kyc
	private String kycDocumentType;
	private int kycDoucumentTypeID;
	public long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}
	public RM getRm() {
		return rm;
	}
	public void setRm(RM rm) {
		this.rm = rm;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getReviewCompleted() {
		return reviewCompleted;
	}
	public void setReviewCompleted(String reviewCompleted) {
		this.reviewCompleted = reviewCompleted;
	}
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	public String getLoanCurrency() {
		return loanCurrency;
	}
	public void setLoanCurrency(String loanCurrency) {
		this.loanCurrency = loanCurrency;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getLoanTenure() {
		return loanTenure;
	}
	public void setLoanTenure(double loanTenure) {
		this.loanTenure = loanTenure;
	}
	public double getLoanInstallmentAmount() {
		return loanInstallmentAmount;
	}
	public void setLoanInstallmentAmount(double loanInstallmentAmount) {
		this.loanInstallmentAmount = loanInstallmentAmount;
	}
	public Date getLoanMaturityDate() {
		return loanMaturityDate;
	}
	public void setLoanMaturityDate(Date loanMaturityDate) {
		this.loanMaturityDate = loanMaturityDate;
	}
	public double getLoanInterestRate() {
		return loanInterestRate;
	}
	public void setLoanInterestRate(double loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public long getDepositID() {
		return depositID;
	}
	public void setDepositID(long depositID) {
		this.depositID = depositID;
	}
	public String getDepositCurrency() {
		return depositCurrency;
	}
	public void setDepositCurrency(String depositCurrency) {
		this.depositCurrency = depositCurrency;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public double getDepositTenure() {
		return depositTenure;
	}
	public void setDepositTenure(double depositTenure) {
		this.depositTenure = depositTenure;
	}
	public double getDepositInterestRate() {
		return depositInterestRate;
	}
	public void setDepositInterestRate(double depositInterestRate) {
		this.depositInterestRate = depositInterestRate;
	}
	public String getKycDocumentType() {
		return kycDocumentType;
	}
	public void setKycDocumentType(String kycDocumentType) {
		this.kycDocumentType = kycDocumentType;
	}
	public int getKycDoucumentTypeID() {
		return kycDoucumentTypeID;
	}
	public void setKycDoucumentTypeID(int kycDoucumentTypeID) {
		this.kycDoucumentTypeID = kycDoucumentTypeID;
	}
	
	
	
	
	
}
