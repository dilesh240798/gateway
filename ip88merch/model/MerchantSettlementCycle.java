package com.scb.scroe.gateway.ip88merch.model;

public enum MerchantSettlementCycle {
	Monthly, Fortnightly, Weekly, Quarterly, HalfYearly, Yearly, T_PLUS_N;
	public static MerchantSettlementCycle value(String val) {
		switch(val) {
		case "T+N":
            return T_PLUS_N;
        case "Monthly":
            return Monthly;
        case "Fortnightly":
            return Fortnightly;
        case "Weekly":
            return Weekly;
        case "Quarterly":
            return Quarterly;
        case "HalfYearly":
            return HalfYearly;
        case "Yearly":
            return Yearly;
        default:
            return null;
		}
	}
}
