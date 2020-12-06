package com.scb.scroe.gateway.ip88transaction.model;

public enum SettlementCycleValue {
	Cycle1, Cycle2, Errorcycle;
	
	public static SettlementCycleValue value(String val) {
		switch (val) {
        case "001":
            return Cycle1;
        case "002":
            return Cycle2;
        default:{
        	return Errorcycle;
        		//throw new TransactionException("Wrong Settlement Cycle Vlaue. It should be either 001 or 002");
        	}
            //throw new Exception(TransactionMessage.001_002_only_accepted);
    }
	}
}
