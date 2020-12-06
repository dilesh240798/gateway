package com.scb.scroe.gateway.ip88merch.step1;

import com.scb.scroe.gateway.ip88merch.constant.MerchConstant;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import com.scb.scroe.gateway.constant.BatchConstant;
import com.scb.scroe.gateway.entity.MerchantLandingEntity;
import com.scb.scroe.gateway.ip88merch.model.MerchRecord;

public class MerchantCompositeProcessor implements ItemProcessor<MerchRecord,MerchantLandingEntity>{

	private JobExecution jobExecution;
	
	@Override
	public MerchantLandingEntity process(MerchRecord record)throws Exception {
		
		jobExecution.getExecutionContext().get(MerchConstant.VALUE_DATE);
		return MerchantLandingEntity.builder()
				.jobId(this.jobExecution.getJobParameters().getString(BatchConstant.JOB_ID))
				.action(record.action)
				.contractId(record.contractID)
				.participantName(record.participantName)
				.participantID(record.participantID)
				.responsibility(record.responsibility)
				.effectiveFrom(record.effectiveFrom)
				.partCountryOfIncorporation(record.participantAttributes.countryOfIncorporation)
				.partRegisteredAddress(record.participantAttributes.registeredAddress)
				.partZip_pin(record.participantAttributes.zip_pin)
				.partSettlementPlan(record.participantAttributes.merchantSettlementPlan)
				.partMDRplan(record.participantAttributes.partMDRplan)
				.partMDRtype(record.participantAttributes.partMDRtype)
				.partMDRpercentage(record.participantAttributes.partMDRpercentage)
				.partMDRfixedAmount(record.participantAttributes.partMDRfixedAmount)
				.partMDRcappedAmount(record.participantAttributes.partMDRcappedAmount)
				.partSettlementCycle(record.participantAttributes.settlementCycle)
				.partT_Plus_N(record.participantAttributes.T_Plus_N)
				.partDayOfMonth(record.participantAttributes.dayOfMonth)
				.partMonth(record.participantAttributes.month)
				.partFortnightlyInterval(record.participantAttributes.fortnightlyInterval)
				.partWeekly(record.participantAttributes.weekly)
				.status(record.status)
				.kycCompleted(record.kycCompleted)
				.validFrom(record.validFrom)
				.validUntil(record.validUntil)
				.heldTransactions(record.heldTransactions)
				.contactName(record.contact.contactName)
				.contactDesignation(record.contact.designation)
				.contactAuthorizedSignatory(record.contact.authorizedSignatory)
				.contactEnableNotifications(record.contact.enableNotifications)
				.contactWorkPhone(record.contact.workPhone)
				.contactMobilePhone(record.contact.mobilePhone)
				.contactEmail(record.contact.email)
				.contactStreet(record.contact.street)
				.contactTown(record.contact.town)
				.contactZip_pin(record.contact.zip_pin)
				.contactState(record.contact.state)
				.contactCountry(record.contact.country)
				.accountPaymentSystem(record.account.paymentSystem)
				.accountBeneficiaryBankBIC(record.account.beneficiaryBankBIC)
				.accountBeneficiaryName(record.account.beneficiaryName)
				.accountBeneficiaryAccountNumber(record.account.beneficiaryAccountNumber)
				.accountBeneficiaryCurrency(record.account.beneficiaryCurrency)
				.accountBeneficiaryAddressLine1(record.account.beneficiaryAddressLine1)
				.accountBeneficiaryAddressLine2(record.account.beneficiaryAddressLine2)
				.accountBeneficiaryCountry(record.account.beneficiaryCountry)
				.accountPaymentType(record.account.paymentType)
				.accountSenderPurposeCode(record.account.senderPurposeCode)
				.accountRecieverPurposeCode(record.account.recieverPurposeCode)
				.accountDescription(record.account.description)
				.build();
	}
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }
}
