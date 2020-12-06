package com.scb.scroe.gateway.ip88transaction.mapper;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.scb.scroe.gateway.ip88merch.model.AuthorizedSignatory;
import com.scb.scroe.gateway.ip88merch.model.EnableNotifications;
import com.scb.scroe.gateway.ip88merch.model.FortnightyInterval;
import com.scb.scroe.gateway.ip88merch.model.HeldTransactions;
import com.scb.scroe.gateway.ip88merch.model.KYCCompleted;
import com.scb.scroe.gateway.ip88merch.model.MDRPlan;
import com.scb.scroe.gateway.ip88merch.model.MDRType;
import com.scb.scroe.gateway.ip88merch.model.MerchRecord;
import com.scb.scroe.gateway.ip88merch.model.MerchRecordActionType;
import com.scb.scroe.gateway.ip88merch.model.SettlementPlan;
import com.scb.scroe.gateway.ip88merch.model.Month;
import com.scb.scroe.gateway.ip88merch.model.MerchantSettlementCycle;
import com.scb.scroe.gateway.ip88merch.model.Status;
import com.scb.scroe.gateway.ip88merch.model.Week;

public class MerchantRecordFieldSetMapper implements FieldSetMapper<MerchRecord>{

	@Override
	public MerchRecord mapFieldSet(FieldSet fieldSet)throws BindException{
		MerchRecord record= new MerchRecord();
		MerchRecordActionType actionType= MerchRecordActionType.getAction(fieldSet.readString(0));
		SettlementPlan settlementPlan = SettlementPlan.value(fieldSet.readString(9));
		MDRPlan mdrPlan = MDRPlan.value(fieldSet.readString(10));
		MDRType mdrType = MDRType.value(fieldSet.readString(11));
		MerchantSettlementCycle settlementCycle = MerchantSettlementCycle.value(fieldSet.readString(15));
		Month month = Month.value(fieldSet.readString(18));
		FortnightyInterval interval = FortnightyInterval.value(fieldSet.readString(19));
		Week week = Week.value(fieldSet.readString(20));
		Status status = Status.value(fieldSet.readString(21));
		KYCCompleted kycCompleted = KYCCompleted.value(fieldSet.readString(22));
		HeldTransactions heldTransactions = HeldTransactions.value(fieldSet.readString(25));
		AuthorizedSignatory authorizedSignatory = AuthorizedSignatory.value(fieldSet.readString(28));
		EnableNotifications enableNotifications = EnableNotifications.value(fieldSet.readString(29));
		BigInteger workPhone = new BigInteger(fieldSet.readString(30));
		BigInteger mobilePhone = new BigInteger(fieldSet.readString(31));
		BigInteger beneficiaryAccountNumber = new BigInteger(fieldSet.readString(41));
		record.setAction(actionType);
		record.setContractID(fieldSet.readString(1));
		record.setParticipantName(fieldSet.readString(2));
		record.setParticipantID(fieldSet.readString(3));
		record.setResponsibility(fieldSet.readString(4));
		record.setEffectiveFrom(LocalDate.parse(fieldSet.readString(5)));
		record.participantAttributes.setCountryOfIncorporation(fieldSet.readString(6));
		record.participantAttributes.setRegisteredAddress(fieldSet.readString(7));
		record.participantAttributes.setZip_pin(fieldSet.readString(8));
		record.participantAttributes.setMerchantSettlementPlan(settlementPlan);
		record.participantAttributes.setPartMDRplan(mdrPlan);
		record.participantAttributes.setPartMDRtype(mdrType);
		record.participantAttributes.setPartMDRpercentage(fieldSet.readString(12));
		record.participantAttributes.setPartMDRfixedAmount(fieldSet.readString(13));
		record.participantAttributes.setPartMDRcappedAmount(fieldSet.readString(14));
		record.participantAttributes.setSettlementCycle(settlementCycle);
		record.participantAttributes.setT_Plus_N(fieldSet.readLong(16));
		record.participantAttributes.setDayOfMonth(fieldSet.readInt(17));
		record.participantAttributes.setMonth(month);
		record.participantAttributes.setFortnightlyInterval(interval);
		record.participantAttributes.setWeekly(week);
		record.setStatus(status);
		record.setKycCompleted(kycCompleted);
		record.setValidFrom(LocalDate.parse(fieldSet.readString(23)));
		record.setValidUntil(LocalDate.parse(fieldSet.readString(24)));
		record.setHeldTransactions(heldTransactions);
		record.contact.setContactName(fieldSet.readString(26));
		record.contact.setDesignation(fieldSet.readString(27));
		record.contact.setAuthorizedSignatory(authorizedSignatory);
		record.contact.setEnableNotifications(enableNotifications);
		record.contact.setWorkPhone(workPhone);
		record.contact.setMobilePhone(mobilePhone);
		record.contact.setEmail(fieldSet.readString(32));
		record.contact.setStreet(fieldSet.readString(33));
		record.contact.setTown(fieldSet.readString(34));
		record.contact.setZip_pin(fieldSet.readString(35));
		record.contact.setState(fieldSet.readString(36));
		record.contact.setCountry(fieldSet.readString(37));
		record.account.setPaymentSystem(fieldSet.readString(38));
		record.account.setBeneficiaryBankBIC(fieldSet.readString(39));
		record.account.setBeneficiaryName(fieldSet.readString(40));
		record.account.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
		record.account.setBeneficiaryCurrency(fieldSet.readString(42));
		record.account.setBeneficiaryAddressLine1(fieldSet.readString(43));
		record.account.setBeneficiaryAddressLine2(fieldSet.readString(44));
		record.account.setBeneficiaryCountry(fieldSet.readString(45));
		record.account.setPaymentType(fieldSet.readString(46));
		record.account.setSenderPurposeCode(fieldSet.readString(47));
		record.account.setRecieverPurposeCode(fieldSet.readString(48));
		record.account.setDescription(fieldSet.readString(49));
		return record;
	}
}
