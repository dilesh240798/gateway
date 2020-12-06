package com.scb.scroe.gateway.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.scb.scroe.gateway.constant.Database;
import com.scb.scroe.gateway.ip88merch.model.AuthorizedSignatory;
import com.scb.scroe.gateway.ip88merch.model.EnableNotifications;
import com.scb.scroe.gateway.ip88merch.model.FortnightyInterval;
import com.scb.scroe.gateway.ip88merch.model.HeldTransactions;
import com.scb.scroe.gateway.ip88merch.model.KYCCompleted;
import com.scb.scroe.gateway.ip88merch.model.MDRPlan;
import com.scb.scroe.gateway.ip88merch.model.MDRType;
import com.scb.scroe.gateway.ip88merch.model.MerchRecordActionType;
import com.scb.scroe.gateway.ip88merch.model.SettlementPlan;
import com.scb.scroe.gateway.ip88merch.model.Month;
import com.scb.scroe.gateway.ip88merch.model.Response;
import com.scb.scroe.gateway.ip88merch.model.MerchantSettlementCycle;
import com.scb.scroe.gateway.ip88merch.model.Status;
import com.scb.scroe.gateway.ip88merch.model.Week;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
//@Table(name = Database.SCROE_IP88MERCHANT_LANDING_TBL)
public class MerchantLandingEntity implements Serializable {

	@Id
	@Setter(AccessLevel.NONE)
	@Column(name = Database.PRIMARY_KEY, updatable = false, insertable = false, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "JOB_ID", nullable = false, updatable = false)
	private String jobId;

	@Column(name = "action", nullable = false, updatable = false)
	private @NotNull(message = "Action cannot be empty") MerchRecordActionType action;

	@Column(name = "contract_ID", nullable = false, updatable = false)
	private String contractId;

	@Column(name = "participant_name", nullable = true, updatable = false)
	private String participantName;

	@Column(name = "participant_id", nullable = false, updatable = false)
	private String participantID;

	@Column(name = "responsibility", nullable = true, updatable = false)
	private String responsibility;

	@Column(name = "effective_from", nullable = false, updatable = false)
	private LocalDate effectiveFrom;

	@Column(name = "participant_country_of_incorporation", nullable = true, updatable = false)
	private String partCountryOfIncorporation;

	@Column(name = "participant_registered_Address", nullable = true, updatable = false)
	private String partRegisteredAddress;

	@Column(name = "participant_zip_pin", nullable = true, updatable = false)
	private String partZip_pin;

	@Column(name = "participant_settlement_plan", nullable = true, updatable = false)
	private SettlementPlan partSettlementPlan;

	@Column(name = "participant_mdr_plan", nullable = true, updatable = false)
	private MDRPlan partMDRplan;

	@Column(name = "participant_mdr_type", nullable = true, updatable = false)
	private MDRType partMDRtype;

	@Column(name = "participant_mdr_percentage", nullable = true, updatable = false)
	private String partMDRpercentage;

	@Column(name = "participant_mdr_fixed_amount", nullable = true, updatable = false)
	private String partMDRfixedAmount;

	@Column(name = "participant_mdr_capped_amount", nullable = true, updatable = false)
	private String partMDRcappedAmount;

	@Column(name = "participant_settlement_cycle", nullable = true, updatable = false)
	private MerchantSettlementCycle partSettlementCycle;

	@Column(name = "participant_t_plus_n", nullable = true, updatable = false)
	private long partT_Plus_N;

	@Column(name = "participant_day_of_month", nullable = true, updatable = false)
	private int partDayOfMonth;

	@Column(name = "participant_month", nullable = true, updatable = false)
	private Month partMonth;

	@Column(name = "participant_fortnightly_interval", nullable = true, updatable = false)
	private FortnightyInterval partFortnightlyInterval;

	@Column(name = "participant_weekly", nullable = true, updatable = false)
	private Week partWeekly;

	@Column(name = "status", nullable = true, updatable = false)
	private Status status;

	@Column(name = "kyccompleted", nullable = true, updatable = false)
	private KYCCompleted kycCompleted;

	@Column(name = "validFrom", nullable = true, updatable = false)
	private LocalDate validFrom;

	@Column(name = "status", nullable = true, updatable = false)
	private LocalDate validUntil;

	@Column(name = "held_transactions", nullable = true, updatable = false)
	private HeldTransactions heldTransactions;

	@Column(name = "contact_name", nullable = true, updatable = false)
	private String contactName;

	@Column(name = "contact_designation", nullable = true, updatable = false)
	private String contactDesignation;

	@Column(name = "contact_authorized_signatory", nullable = true, updatable = false)
	private AuthorizedSignatory contactAuthorizedSignatory;

	@Column(name = "contact_enable_notifiactions", nullable = true, updatable = false)
	private EnableNotifications contactEnableNotifications;

	@Column(name = "contact_work_phone", nullable = true, updatable = false)
	private BigInteger contactWorkPhone;

	@Column(name = "contact_mobile_phone", nullable = true, updatable = false)
	private BigInteger contactMobilePhone;

	@Column(name = "contact_email", nullable = true, updatable = false)
	private String contactEmail;

	@Column(name = "contact_street", nullable = true, updatable = false)
	private String contactStreet;

	@Column(name = "contact_town", nullable = true, updatable = false)
	private String contactTown;

	@Column(name = "contact_zip_pin", nullable = true, updatable = false)
	private String contactZip_pin;

	@Column(name = "contact_state", nullable = true, updatable = false)
	private String contactState;

	@Column(name = "contact_country", nullable = true, updatable = false)
	private String contactCountry;

	@Column(name = "account_payment_system", nullable = true, updatable = false)
	private String accountPaymentSystem;

	@Column(name = "account_beneficiary_bank_bic", nullable = true, updatable = false)
	private String accountBeneficiaryBankBIC;

	@Column(name = "account_beneficiary_name", nullable = true, updatable = false)
	private String accountBeneficiaryName;

	@Column(name = "account_beneficiary_account_number", nullable = true, updatable = false)
	private BigInteger accountBeneficiaryAccountNumber;

	@Column(name = "account_beneficiary_currency", nullable = true, updatable = false)
	private String accountBeneficiaryCurrency;

	@Column(name = "account_beneficiary_addressline1", nullable = true, updatable = false)
	private String accountBeneficiaryAddressLine1;

	@Column(name = "account_beneficiary_addressline2", nullable = true, updatable = false)
	private String accountBeneficiaryAddressLine2;

	@Column(name = "account_beneficiary_country", nullable = true, updatable = false)
	private String accountBeneficiaryCountry;

	@Column(name = "account_payment_type", nullable = true, updatable = false)
	private String accountPaymentType;

	@Column(name = "account_sender_purpose_code", nullable = true, updatable = false)
	private String accountSenderPurposeCode;

	@Column(name = "account_reciever_purpose_code", nullable = true, updatable = false)
	private String accountRecieverPurposeCode;

	@Column(name = "account_description", nullable = true, updatable = false)
	private String accountDescription;

	@Column(name = "response_status", nullable = true, updatable = false)
	private Response responseStatus;

	@Column(name = "reject_reason", nullable = true, updatable = false)
	private String rejectReason;

	@Column(name = "warning", nullable = true, updatable = false)
	private String warning;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String jobId;
		public @NotNull(message = "Action cannot be empty") MerchRecordActionType action;
		public String contractId;
		public String participantName;
		public String participantID;
		public String responsibility;
		public LocalDate effectiveFrom;
		public String partCountryOfIncorporation;
		public String partRegisteredAddress;
		public String partZip_pin;
		public SettlementPlan partSettlementPlan;
		public MDRPlan partMDRplan;
		public MDRType partMDRtype;
		public String partMDRpercentage;
		public String partMDRfixedAmount;
		public String partMDRcappedAmount;
		public MerchantSettlementCycle partSettlementCycle;
		public long partT_Plus_N;
		public int partDayOfMonth;
		public Month partMonth;
		public FortnightyInterval partFortnightlyInterval;
		public Week partWeekly;
		public Status status;
		public KYCCompleted kycCompleted;
		public LocalDate validFrom;
		public LocalDate validUntil;
		public HeldTransactions heldTransactions;
		public String contactName;
		public String contactDesignation;
		public AuthorizedSignatory contactAuthorizedSignatory;
		public EnableNotifications contactEnableNotifications;
		public BigInteger contactWorkPhone;
		public BigInteger contactMobilePhone;
		public String contactEmail;
		public String contactStreet;
		public String contactTown;
		public String contactZip_pin;
		public String contactState;
		public String contactCountry;
		public String accountPaymentSystem;
		public String accountBeneficiaryBankBIC;
		public String accountBeneficiaryName;
		public BigInteger accountBeneficiaryAccountNumber;
		public String accountBeneficiaryCurrency;
		public String accountBeneficiaryAddressLine1;
		public String accountBeneficiaryAddressLine2;
		public String accountBeneficiaryCountry;
		public String accountPaymentType;
		public String accountSenderPurposeCode;
		public String accountRecieverPurposeCode;
		public String accountDescription;
		public Response responseStatus;
		public String rejectReason;
		public String warning;

		public Builder jobId(String jobId) {
			this.jobId = jobId;
			return this;
		}

		public Builder action(@NotNull(message = "Action cannot be empty") MerchRecordActionType action) {
			this.action = action;
			return this;
		}

		public Builder contractId(String contractId) {
			this.contractId = contractId;
			return this;
		}

		public Builder participantName(String participantName) {
			this.participantName = participantName;
			return this;
		}

		public Builder participantID(String participantID) {
			this.participantID = participantID;
			return this;
		}

		public Builder responsibility(String responsibility) {
			this.responsibility = responsibility;
			return this;
		}

		public Builder effectiveFrom(LocalDate effectiveFrom) {
			this.effectiveFrom = effectiveFrom;
			return this;
		}

		public Builder partCountryOfIncorporation(String partCountryOfIncorporation) {
			this.partCountryOfIncorporation = partCountryOfIncorporation;
			return this;
		}

		public Builder partRegisteredAddress(String partRegisteredAddress) {
			this.partRegisteredAddress = partRegisteredAddress;
			return this;
		}

		public Builder partZip_pin(String partZip_pin) {
			this.partZip_pin = partZip_pin;
			return this;
		}

		public Builder partSettlementPlan(SettlementPlan SettlementPlan) {
			this.partSettlementPlan = SettlementPlan;
			return this;
		}

		public Builder partMDRplan(MDRPlan partMDRplan) {
			this.partMDRplan = partMDRplan;
			return this;
		}

		public Builder partMDRtype(MDRType partMDRtype) {
			this.partMDRtype = partMDRtype;
			return this;
		}

		public Builder partMDRpercentage(String partMDRpercentage) {
			this.partMDRpercentage = partMDRpercentage;
			return this;
		}

		public Builder partMDRfixedAmount(String partMDRfixedAmount) {
			this.partMDRfixedAmount = partMDRfixedAmount;
			return this;
		}

		public Builder partMDRcappedAmount(String partMDRcappedAmount) {
			this.partMDRcappedAmount = partMDRcappedAmount;
			return this;
		}

		public Builder partSettlementCycle(MerchantSettlementCycle settlementCycle) {
			this.partSettlementCycle = settlementCycle;
			return this;
		}

		public Builder partT_Plus_N(long partT_Plus_N) {
			this.partT_Plus_N = partT_Plus_N;
			return this;
		}

		public Builder partDayOfMonth(int partDayOfMonth) {
			this.partDayOfMonth = partDayOfMonth;
			return this;
		}

		public Builder partMonth(Month month) {
			this.partMonth = month;
			return this;
		}

		public Builder partFortnightlyInterval(FortnightyInterval fortnightlyInterval) {
			this.partFortnightlyInterval = fortnightlyInterval;
			return this;
		}

		public Builder partWeekly(Week weekly) {
			this.partWeekly = weekly;
			return this;
		}

		public Builder status(Status status) {
			this.status = status;
			return this;
		}

		public Builder kycCompleted(KYCCompleted kycCompleted) {
			this.kycCompleted = kycCompleted;
			return this;
		}

		public Builder validFrom(LocalDate validFrom) {
			this.validFrom = validFrom;
			return this;
		}

		public Builder validUntil(LocalDate validUntil) {
			this.validUntil = validUntil;
			return this;
		}

		public Builder heldTransactions(HeldTransactions heldTransactions) {
			this.heldTransactions = heldTransactions;
			return this;
		}

		public Builder contactName(String contactName) {
			this.contactName = contactName;
			return this;
		}

		public Builder contactDesignation(String contactDesignation) {
			this.contactDesignation = contactDesignation;
			return this;
		}

		public Builder contactAuthorizedSignatory(AuthorizedSignatory contactAuthorizedSignatory) {
			this.contactAuthorizedSignatory = contactAuthorizedSignatory;
			return this;
		}

		public Builder contactEnableNotifications(EnableNotifications contactEnableNotifications) {
			this.contactEnableNotifications = contactEnableNotifications;
			return this;
		}

		public Builder contactWorkPhone(BigInteger contactWorkPhone) {
			this.contactWorkPhone = contactWorkPhone;
			return this;
		}

		public Builder contactMobilePhone(BigInteger contactMobilePhone) {
			this.contactMobilePhone = contactMobilePhone;
			return this;
		}

		public Builder contactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
			return this;
		}

		public Builder contactStreet(String contactStreet) {
			this.contactStreet = contactStreet;
			return this;
		}

		public Builder contactTown(String contactTown) {
			this.contactTown = contactTown;
			return this;
		}

		public Builder contactZip_pin(String contactZip_pin) {
			this.contactZip_pin = contactZip_pin;
			return this;
		}

		public Builder contactState(String contactState) {
			this.contactState = contactState;
			return this;
		}

		public Builder contactCountry(String contactCountry) {
			this.contactCountry = contactCountry;
			return this;
		}

		public Builder accountPaymentSystem(String accountPaymentSystem) {
			this.accountPaymentSystem = accountPaymentSystem;
			return this;
		}

		public Builder accountBeneficiaryBankBIC(String accountBeneficiaryBankBIC) {
			this.accountBeneficiaryBankBIC = accountBeneficiaryBankBIC;
			return this;
		}

		public Builder accountBeneficiaryName(String accountBeneficiaryName) {
			this.accountBeneficiaryName = accountBeneficiaryName;
			return this;
		}

		public Builder accountBeneficiaryAccountNumber(BigInteger accountBeneficiaryAccountNumber) {
			this.accountBeneficiaryAccountNumber = accountBeneficiaryAccountNumber;
			return this;
		}

		public Builder accountBeneficiaryCurrency(String accountBeneficiaryCurrency) {
			this.accountBeneficiaryCurrency = accountBeneficiaryCurrency;
			return this;
		}

		public Builder accountBeneficiaryAddressLine1(String accountBeneficiaryAddressLine1) {
			this.accountBeneficiaryAddressLine1 = accountBeneficiaryAddressLine1;
			return this;
		}

		public Builder accountBeneficiaryAddressLine2(String accountBeneficiaryAddressLine2) {
			this.accountBeneficiaryAddressLine2 = accountBeneficiaryAddressLine2;
			return this;
		}

		public Builder accountBeneficiaryCountry(String accountBeneficiaryCountry) {
			this.accountBeneficiaryCountry = accountBeneficiaryCountry;
			return this;
		}

		public Builder accountPaymentType(String accountPaymentType) {
			this.accountPaymentType = accountPaymentType;
			return this;
		}

		public Builder accountSenderPurposeCode(String accountSenderPurposeCode) {
			this.accountSenderPurposeCode = accountSenderPurposeCode;
			return this;
		}

		public Builder accountRecieverPurposeCode(String accountRecieverPurposeCode) {
			this.accountRecieverPurposeCode = accountRecieverPurposeCode;
			return this;
		}

		public Builder accountDescription(String accountDescription) {
			this.accountDescription = accountDescription;
			return this;
		}

		public Builder responseStatus(Response responseStatus) {
			this.responseStatus = responseStatus;
			return this;
		}

		public Builder rejectReason(String rejectReason) {
			this.rejectReason = rejectReason;
			return this;
		}

		public Builder warning(String warning) {
			this.warning = warning;
			return this;
		}

		public MerchantLandingEntity build() {

			MerchantLandingEntity entity = new MerchantLandingEntity();
			entity.jobId = this.jobId;
			entity.action = this.action;
			entity.contractId = this.contractId;
			entity.participantName = this.participantName;
			entity.participantID = this.participantID;
			entity.responsibility = this.responsibility;
			entity.effectiveFrom = this.effectiveFrom;
			entity.partCountryOfIncorporation = this.partCountryOfIncorporation;
			entity.partRegisteredAddress = this.partRegisteredAddress;
			entity.partZip_pin = this.partZip_pin;
			entity.partSettlementPlan = this.partSettlementPlan;
			entity.partMDRplan = this.partMDRplan;
			entity.partMDRtype = this.partMDRtype;
			entity.partMDRpercentage = this.partMDRpercentage;
			entity.partMDRfixedAmount = this.partMDRfixedAmount;
			entity.partMDRcappedAmount = this.partMDRcappedAmount;
			entity.partSettlementCycle = this.partSettlementCycle;
			entity.partT_Plus_N = this.partT_Plus_N;
			entity.partDayOfMonth = this.partDayOfMonth;
			entity.partMonth = this.partMonth;
			entity.partFortnightlyInterval = this.partFortnightlyInterval;
			entity.partWeekly = this.partWeekly;
			entity.status = this.status;
			entity.kycCompleted = this.kycCompleted;
			entity.validFrom = this.validFrom;
			entity.validUntil = this.validUntil;
			entity.heldTransactions = this.heldTransactions;
			entity.contactName = this.contactName;
			entity.contactDesignation = this.contactDesignation;
			entity.contactAuthorizedSignatory = this.contactAuthorizedSignatory;
			entity.contactEnableNotifications = this.contactEnableNotifications;
			entity.contactWorkPhone = this.contactWorkPhone;
			entity.contactMobilePhone = this.contactMobilePhone;
			entity.contactEmail = this.contactEmail;
			entity.contactStreet = this.contactStreet;
			entity.contactTown = this.contactTown;
			entity.contactZip_pin = this.contactZip_pin;
			entity.contactState = this.contactState;
			entity.contactCountry = this.contactCountry;
			entity.accountPaymentSystem = this.accountPaymentSystem;
			entity.accountBeneficiaryBankBIC = this.accountBeneficiaryBankBIC;
			entity.accountBeneficiaryName = this.accountBeneficiaryName;
			entity.accountBeneficiaryAccountNumber = this.accountBeneficiaryAccountNumber;
			entity.accountBeneficiaryCurrency = this.accountBeneficiaryCurrency;
			entity.accountBeneficiaryAddressLine1 = this.accountBeneficiaryAddressLine1;
			entity.accountBeneficiaryAddressLine2 = this.accountBeneficiaryAddressLine2;
			entity.accountBeneficiaryCountry = this.accountBeneficiaryCountry;
			entity.accountPaymentType = this.accountPaymentType;
			entity.accountSenderPurposeCode = this.accountSenderPurposeCode;
			entity.accountRecieverPurposeCode = this.accountRecieverPurposeCode;
			entity.accountDescription = this.accountDescription;
			entity.responseStatus = this.responseStatus;
			entity.rejectReason = this.rejectReason;
			entity.warning = this.warning;
			return entity;
		}
	}
}
