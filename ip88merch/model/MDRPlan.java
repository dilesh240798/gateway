package com.scb.scroe.gateway.ip88merch.model;

public enum MDRPlan {
	Merchant, Contract;
	
	public static MDRPlan value(String val) {
		switch(val) {
		case "Merchant":
			return Merchant;
		case "Contract":
			return Contract;
		default:
			return null;
		}
		
}
}
