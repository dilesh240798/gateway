package com.scb.scroe.gateway.ip88merch.model;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantAttributes {
	
	@Size(max = 2, min = 2, message = "Participant Attribute - Country of Incorporation format incorrect")
	public String countryOfIncorporation;
	
	@Size(max = 140, min = 1, message = "Participant Attribute - Registered Adderess format incorrect")
	public String registeredAddress;
	
	@Size(max = 10, min = 2, message = "Participant Attribute - Country of Incorporation format incorrect")
	public String zip_pin;
	
	public SettlementPlan merchantSettlementPlan;
	
	public MDRPlan partMDRplan;
	
	public MDRType partMDRtype;
	
	public String partMDRpercentage;
	
	public String partMDRfixedAmount;
	
	public String partMDRcappedAmount;
	
	public MerchantSettlementCycle settlementCycle;
	
	public long T_Plus_N;
	
	public int dayOfMonth;
	
	public Month month;
	
	public FortnightyInterval fortnightlyInterval;
	
	public Week weekly;
}
