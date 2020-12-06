package com.scb.scroe.gateway.ip88merch.model;

public enum MerchRecordActionType {
	Onboard, Amend, Offboard;
	
	public static MerchRecordActionType getAction(String value) {
		if(value.equals("Onboard")) {
			return Onboard;
		}
		else if(value.equals("Amend")) {
			return Amend;
		}
		else if(value.equals("Offboard")) {
			return Offboard;
		}
		else{
			System.out.println("Add exception of action Type not valid");
			return Onboard;			
		}
	}
}
