package com.scb.scroe.gateway.ip88merch.model;

public enum FortnightyInterval {
	
	_1_15,_2_16,_3_17,_4_18,_5_19,_6_20, _7_21,_8_22,_9_23,
	_10_24,_11_25,_12_26,_13_27,_14_28,_15_29,_16_30,_17_31;
	
	public static FortnightyInterval value(String val) {
		switch(val) {
		case "1-15":
			return _1_15;
		case "2-16":
			return _2_16;
		case "3-17":
			return _3_17;
		case "4-18":
			return _4_18;
		case "5-19":
			return _5_19;
		case "6-20":
			return _6_20;
		case "7-21":
			return _7_21;
		case "8-22":
			return _8_22;
		case "9-23":
			return _9_23;
		case "10-24":
			return _10_24;
		case "11-25":
			return _11_25;
		case "12-26":
			return _12_26;
		case "13-27":
			return _13_27;
		case "14-28":
			return _14_28;
		case "15-29":
			return _15_29;
		case "16-30":
			return _16_30;
		case "17-31":
			return _17_31;
		default:
			return null;
		}
		}
}
