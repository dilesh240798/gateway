package com.scb.scroe.gateway.ip88merch.model;

import java.math.BigInteger;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
	
	@Value("LI")
	public String paymentSystem;
	
	public String beneficiaryBankBIC;
	
	@Size(max = 35, message = "Account - beneficiary Name format incorrect")
	public String beneficiaryName;
	
	public BigInteger beneficiaryAccountNumber;
	
	@Size(max = 3, message = "Account - beneficiary Currency code format incorrect")
	public String beneficiaryCurrency;	
	
	@Size(max = 35, message = "Account - beneficiary Address Line 1 format incorrect")
	public String beneficiaryAddressLine1;
	
	@Size(max = 35, message = "Account - beneficiary Address Line 2 format incorrect")
	public String beneficiaryAddressLine2;
	
	@Size(max = 3, message = "Account - beneficiary Country code format incorrect")
	public String beneficiaryCountry;
	
	public String paymentType;
	
	public String senderPurposeCode;
	
	public String recieverPurposeCode;
	
	@Size(max = 35, message = "Account - Description format incorrect")
	public String description;
}
