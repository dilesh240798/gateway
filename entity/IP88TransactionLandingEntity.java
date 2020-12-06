package com.scb.scroe.gateway.entity;

import com.scb.scroe.gateway.constant.Database;
import com.scb.scroe.gateway.ip88transaction.model.AccountType;
import com.scb.scroe.gateway.ip88transaction.model.ServiceType;
import com.scb.scroe.gateway.ip88transaction.model.SettlementCycleValue;
import com.scb.scroe.gateway.ip88transaction.model.TransactionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Entity
@Table(name = Database.SCROE_IP88TRANSACTION_LANDING_TBL)
public class IP88TransactionLandingEntity implements Serializable {

	@Id
	@Setter(AccessLevel.NONE)
	@Column(name = Database.PRIMARY_KEY, updatable = false, insertable = false, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "JOB_ID", nullable = false, updatable = false)
	private String jobId;

	@Column(name = "SETLEMENT_CYCLE_VALUE", nullable = false, updatable = false)
	private String settlementCycleValue;

	@Column(name = "INITIATION_DATE", nullable = false, updatable = false)
	private LocalDateTime initiationDate;

	@Column(name = "MESSAGE_ID", nullable = false, updatable = false)
	private String messageId;

	@Column(name = "BUSINESS_MESSAGE_ID", nullable = false, updatable = false)
	private String businessMessageId;

	@Column(name = "SERVICE_TYPE", nullable = false, updatable = false)
	private String serviceType;

//    @Column(name = "SCHEME_ID", nullable = false, updatable = false)
//    private String schemeId;

	@Column(name = "OFI_CODE", nullable = false, updatable = false)
	private String ofiCode;

	@Column(name = "OFI_ACCOUNT_NUMBER", nullable = false, updatable = false)
	private String ofiAccountNumber;

	@Column(name = "OFI_ACCOUNT_TYPE", nullable = false, updatable = false)
	private String ofiAccountType;

	@Column(name = "OFI_ACCOUNT_NAME", nullable = false, updatable = false)
	private String ofiAccountName;

	@Column(name = "RFI_CODE", nullable = false, updatable = false)
	private String rfiCode;

	@Column(name = "RFI_ACCOUNT_NUMBER", nullable = false, updatable = false)
	private String rfiAccountNumber;

	@Column(name = "RFI_ACCOUNT_TYPE", nullable = false, updatable = false)
	private String rfiAccountType;

	@Column(name = "RFI_ACCOUNT_NAME", nullable = false, updatable = false)
	private String rfiAccountName;

	@Column(name = "TRANSACTION_AMOUNT", nullable = false, updatable = false)
	private String transactionAmount;

	@Column(name = "CURRENCY", nullable = false, updatable = false)
	private String currency;

	@Column(name = "FOREIGN_EXCHANGE_NUMBER", nullable = false, updatable = false)
	private String foreignExchangeNumber;

	@Column(name = "FOREIGN_AMOUNT", nullable = false, updatable = false)
	private String foreignAmount;

	@Column(name = "FOREIGN_EXCHANGE_RATE", nullable = false, updatable = false)
	private String foreignExchangeRate;

	@Column(name = "TRANSACTION_STATUS", nullable = false, updatable = false)
	private String transactionStatus;

	@Column(name = "TRANSACTION_SUB_CODE", nullable = false, updatable = false)
	private String transactionSubCode;

//	@Convert(converter = CrDrConverter.class)
	@Column(name = "COMPLETED_DATE", nullable = false, updatable = false)
	private LocalDate completedDate;

	@Column(name = "INPUT_ROW", nullable = false, updatable = false)
	private String inputRow;
	
	@Column(name = "ROW_NUMBER", nullable = false, updatable = false)
	private int inputRowNumber;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String jobId;
		public String settlementCycleValue;
		public LocalDateTime initiationDate;
		public String messageId;
		public String businessMessageId;
		public String serviceType;
        public String schemeId;
		public String ofiCode;
		public String ofiAccountNumber;
		public String ofiAccountType;
		public String ofiAccountName;
		public String rfiCode;
		public String rfiAccountNumber;
		public String rfiAccountType;
		public String rfiAccountName;
		public String transactionAmount;
		public String currency;
		public String foreignExchangeNumber;
		public String foreignAmount;
		public String foreignExchangeRate;
		public String transactionStatus;
		public String transactionSubCode;
		public LocalDate completedDate;
		public String inputRow;
		public int inputRowNumber;

		public Builder jobId(String jobId) {
			this.jobId = jobId;
			return this;
		}

		public Builder settlementCycleValue(SettlementCycleValue settlementCycleValue) {
			if (settlementCycleValue == SettlementCycleValue.Cycle1)
				this.settlementCycleValue = "001";
			else if (settlementCycleValue == SettlementCycleValue.Cycle2)
				this.settlementCycleValue = "002";
			else {
				System.out.println("incorrect settlementCycleValue enum");
				//throw new TransactionException("exception in processor");
			}
			return this;
		}

		public Builder initiationDate(LocalDateTime initiationDate) {
			this.initiationDate = initiationDate;
			return this;
		}

		public Builder messageId(String messageId) {
			this.messageId = messageId;
			return this;
		}

		public Builder businessMessageId(String businessMessageId) {
			this.businessMessageId = businessMessageId;
			return this;
		}

		public Builder serviceType(ServiceType serviceType) {
			if (serviceType == ServiceType.PAY2PROXY)
				this.serviceType = "110";
			else if (serviceType == ServiceType.PAY2ACCOUNT)
				this.serviceType = "010";
			else if (serviceType == ServiceType.REVERSE_CT)
				this.serviceType = "011";
			else if (serviceType == ServiceType.DUITNOW_POS)
				this.serviceType = "030";
			else if (serviceType == ServiceType.CROSSBORDER)
				this.serviceType = "031";
			else if (serviceType == ServiceType.DUITNOW_P2P)
				this.serviceType = "041";
			else if (serviceType == ServiceType.REQUEST2PROXY060)
				this.serviceType = "060";
			else if (serviceType == ServiceType.REQUEST2PROXY070)
				this.serviceType = "070";
			else if (serviceType == ServiceType.REQUEST2PROXY012)
				this.serviceType = "012";
			else if (serviceType == ServiceType.REAL_TIME_DEBIT210)
				this.serviceType = "210";
			else if (serviceType == ServiceType.REAL_TIME_DEBIT080)
				this.serviceType = "080";
			else if (serviceType == ServiceType.REAL_TIME_DEBIT230)
				this.serviceType = "230";
			else {
				System.out.println("incorrect settlementCycleValue enum");
				//throw new TransactionException("exception in processor");
			}
			return this;
		}

//        public Builder schemeId(String schemeId) {
//            this.schemeId = schemeId;
//            return this;
//        }
		public Builder ofiCode(String ofiCode) {
			this.ofiCode = ofiCode;
			return this;
		}

		public Builder ofiAccountNumber(String ofiAccountNumber) {
			this.ofiAccountNumber = ofiAccountNumber;
			return this;
		}

		public Builder ofiAccountType(AccountType ofiAccountType) {
			if (ofiAccountType==AccountType.CACC)
				this.ofiAccountType = "CACC";
			if (ofiAccountType==AccountType.SVGS)
				this.ofiAccountType = "SVGS";
			if (ofiAccountType==AccountType.CCRD)
				this.ofiAccountType = "CCRD";
			if (ofiAccountType==AccountType.DFLT)
				this.ofiAccountType = "DFLT";
			if (ofiAccountType==AccountType.WALL)
				this.ofiAccountType = "WALL";
			else {
				System.out.println("incorrect settlementCycleValue enum");
				//throw new TransactionException("exception in processor");
			}
			return this;
		}

		public Builder ofiAccountName(String ofiAccountName) {
			this.ofiAccountName = ofiAccountName;
			return this;
		}

		public Builder rfiCode(String rfiCode) {
			this.rfiCode = rfiCode;
			return this;
		}

		public Builder rfiAccountNumber(String rfiAccountNumber) {
			this.rfiAccountNumber = rfiAccountNumber;
			return this;
		}

		public Builder rfiAccountType(AccountType rfiAccountType) {
			if (rfiAccountType==AccountType.CACC)
				this.rfiAccountType = "CACC";
			if (rfiAccountType==AccountType.SVGS)
				this.rfiAccountType = "SVGS";
			if (rfiAccountType==AccountType.CCRD)
				this.rfiAccountType = "CCRD";
			if (rfiAccountType==AccountType.DFLT)
				this.rfiAccountType = "DFLT";
			if (rfiAccountType==AccountType.WALL)
				this.rfiAccountType = "WALL";
			else {
				System.out.println("incorrect settlementCycleValue enum");
				//throw new TransactionException("exception in processor");
			}
			return this;
		}

		public Builder rfiAccountName(String rfiAccountName) {
			this.rfiAccountName = rfiAccountName;
			return this;
		}

		public Builder transactionAmount(String transactionAmount) {
			this.transactionAmount = transactionAmount;
			return this;
		}

		public Builder currency(String currency) {
			this.currency = currency;
			return this;
		}

		public Builder foreignExchangeNumber(String foreignExchangeNumber) {
			this.foreignExchangeNumber = foreignExchangeNumber;
			return this;
		}

		public Builder foreignAmount(String foreignAmount) {
			this.foreignAmount = foreignAmount;
			return this;
		}

		public Builder foreignExchangeRate(String foreignExchangeRate) {
			this.foreignExchangeRate = foreignExchangeRate;
			return this;
		}

		public Builder transactionStatus(TransactionStatus transactionStatus) {
			if (transactionStatus==TransactionStatus.ACTC)
				this.transactionStatus = "ACTC";
			if (transactionStatus==TransactionStatus.ACSP)
				this.transactionStatus = "ACSP";
			if (transactionStatus==TransactionStatus.RJCT)
				this.transactionStatus = "RJCT";
			else {
				System.out.println("incorrect settlementCycleValue enum");
				//throw new TransactionException("exception in processor");
			}
			return this;
		}

		public Builder transactionSubCode(String transactionSubCode) {
			this.transactionSubCode = transactionSubCode;
			return this;
		}

		public Builder completedDate(LocalDate completedDate) {
			this.completedDate = completedDate;
			return this;
		}

		public Builder inputRow(String inputRow) {
			this.inputRow = inputRow;
			return this;
		}
		
		public Builder inputRowNumber(int inputRowNumber) {
			this.inputRowNumber = inputRowNumber;
			return this;
		}

		public IP88TransactionLandingEntity build() {
			IP88TransactionLandingEntity entity = new IP88TransactionLandingEntity();
			entity.jobId = this.jobId;
			entity.settlementCycleValue = this.settlementCycleValue;
			entity.initiationDate = this.initiationDate;
			entity.messageId = this.messageId;
			entity.businessMessageId = this.businessMessageId;
			entity.serviceType = this.serviceType;
//            entity.schemeId = this.schemeId;
			entity.ofiCode = this.ofiCode;
			entity.ofiAccountNumber = this.ofiAccountNumber;
			entity.ofiAccountType = this.ofiAccountType;
			entity.ofiAccountName = this.ofiAccountName;
			entity.rfiCode = this.rfiCode;
			entity.rfiAccountNumber = this.rfiAccountNumber;
			entity.rfiAccountType = this.rfiAccountType;
			entity.rfiAccountName = this.rfiAccountName;
			entity.transactionAmount = this.transactionAmount;
			entity.currency = this.currency;
			entity.foreignExchangeNumber = this.foreignExchangeNumber;
			entity.foreignAmount = this.foreignAmount;
			entity.foreignExchangeRate = this.foreignExchangeRate;
			entity.transactionStatus = this.transactionStatus;
			entity.transactionSubCode = this.transactionSubCode;
			entity.completedDate = this.completedDate;
			entity.inputRow = this.inputRow;
			entity.inputRowNumber = this.inputRowNumber;
			return entity;
		}
	}
}
