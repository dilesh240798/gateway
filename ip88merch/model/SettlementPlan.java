package com.scb.scroe.gateway.ip88merch.model;

public enum SettlementPlan {
	Merchant, Contract;
	public static SettlementPlan value(String val) {
		switch(val) {
		case "Merchant":
			return Merchant;
		case "Contract":
			return Contract;
		default:{
			return null;
			}
		}
	}
}
