package com.scb.scroe.gateway.ip88merch.model;

public enum Week {

	 	MON,TUE,WED,THU,FRI,SAT,SUN,
	 	MON_TUE,MON_WED,MON_THU,MON_FRI,MON_SAT,MON_SUN,
	    TUE_WED,TUE_THU,TUE_FRI,TUE_SAT,TUE_SUN,
	    WED_THU,WED_FRI,WED_SAT,WED_SUN,
	    THU_FRI,THU_SAT,THU_SUN,
	    FRI_SAT,FRI_SUN,
	    SAT_SUN;
		
	public static Week value(String val) {
		switch(val) {
		case "Mon":
			return MON;
		case "Tue":
			return TUE;
		case "Wed":
			return WED;
		case "Thu":
			return THU;
		case "Fri":
			return FRI;
		case "Sat":
			return SAT;
		case "Sun":
			return SUN;
		case "Mon-Tue":
			return MON_TUE;
		case "Mon-Wed":
			return MON_WED;
		case "Mon-Thu":
			return MON_THU;
		case "Mon-Fri":
			return MON_FRI;
		case "Mon-Sat":
			return MON_SAT;
		case "Mon-Sun":
			return MON_SUN;
		case "Tue-Wed":
			return TUE_WED;
		case "Tue-Thu":
			return TUE_THU;
		case "Tue-Fri":
			return TUE_FRI;
		case "Tue-Sat":
			return TUE_SAT;
		case "Tue-Sun":
			return TUE_SUN;
		case "Wed-Thu":
			return WED_THU;
		case "Wed-Fri":
			return WED_FRI;
		case "Wed-Sat":
			return WED_SAT;
		case "Wed-Sun":
			return WED_SUN;
		case "Thu-Fri":
			return THU_FRI;
		case "Thu-Sat":
			return THU_SAT;
		case "Thu-Sun":
			return THU_SUN;
		case "Fri-Sat":
			return FRI_SAT;
		case "Fri-Sun":
			return FRI_SUN;
		case "Sat-Sun":
			return SAT_SUN;
		default:
			return null;
		}
		}
}
