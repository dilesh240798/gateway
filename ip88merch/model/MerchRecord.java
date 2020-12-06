package com.scb.scroe.gateway.ip88merch.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchRecord {

	@NotNull(message = "Action cannot be empty")
	public MerchRecordActionType action;
	
	@NotNull(message = "Contract ID cannot be empty")
	public String contractID;
	
	@Size(max = 140, message = "Participant Name format incorrect")
	public String participantName;
	
	@NotNull(message = "Participant ID cannot be empty")
	@Size(max = 35, message = "Participant Id format incorrect")
	public String participantID;
	
	@Value("Merchant")
	public String responsibility;
	
//	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$)", message = "Effective from date format incorrect")
	public LocalDate effectiveFrom;
	
	public ParticipantAttributes participantAttributes;
	
	public	Status	status;
	
	public KYCCompleted	kycCompleted;
	
	public LocalDate validFrom;
	
	public LocalDate validUntil;
	
	public HeldTransactions	heldTransactions;
	
	public Contact contact;
	
	public Account account;
	
	public String inputMerchantRow;

}
