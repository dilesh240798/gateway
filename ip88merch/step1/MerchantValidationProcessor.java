package com.scb.scroe.gateway.ip88merch.step1;

import com.scb.scroe.gateway.ip88merch.exception.MandatoryFieldMissingException;
import org.springframework.batch.item.ItemProcessor;

import com.scb.scroe.gateway.entity.MerchantLandingEntity;
import com.scb.scroe.gateway.ip88merch.model.KYCCompleted;
import com.scb.scroe.gateway.ip88merch.model.MDRPlan;
import com.scb.scroe.gateway.ip88merch.model.MerchRecordActionType;
import com.scb.scroe.gateway.ip88merch.model.SettlementPlan;
import com.scb.scroe.gateway.ip88merch.model.RejectReason;
import com.scb.scroe.gateway.ip88merch.model.Response;

public class MerchantValidationProcessor implements ItemProcessor<MerchantLandingEntity,MerchantLandingEntity>{
	@Override
	public MerchantLandingEntity process(MerchantLandingEntity item) throws Exception {		
		try {
			return performValidations(item);
		}
		catch(MandatoryFieldMissingException e) {
			return item.builder()
					.responseStatus(Response.REJECTED)
					.rejectReason(RejectReason.INVALID_RECORD)
					.build();	
		}
	}
	private MerchantLandingEntity performValidations(MerchantLandingEntity entity) throws MandatoryFieldMissingException {		
		if (!entity.getAction().equals(MerchRecordActionType.Offboard)) {
			stringCheck(entity.getParticipantName(), "ParticipantName is mandatory field");
            nullCheck(entity.getResponsibility(), "Responsibility is mandatory field");
            nullCheck(entity.getPartCountryOfIncorporation(), "PartCountry is mandatory field");
            nullCheck(entity.getPartRegisteredAddress(), "PartAddress is mandatory field");
            nullCheck(entity.getPartZip_pin(),"Zip Pin is Mandatory Field");
            nullCheck(entity.getPartSettlementPlan(), "PartSettlementPlan is mandatory field");
            nullCheck(entity.getPartMDRplan(), "PartMDRPlan is mandatory field");
            nullCheck(entity.getPartMDRtype(), "PartMDRType is mandatory field");            
            if (entity.getPartMDRplan().equals(MDRPlan.Merchant)) {
            	switch(entity.getPartMDRtype()) {
            		case PERCENTAGE:
            			nullCheck(entity.getPartMDRpercentage(),"PartMDRPercentage is mandatory field");
            			break;
            		case FIXED :
            			nullCheck(entity.getPartMDRfixedAmount(),"PartMDRFixed is Mandatory field");
            			break;
					case GREATER_OF_PERCENT_OR_FIXED:
					case LOWER_OF_PERCENT_OR_FIXED:
					case PERCENTAGE_PLUS_FIXED:
	        			nullCheck(entity.getPartMDRfixedAmount(),"PartMDRFixed is Mandatory field");
	        			nullCheck(entity.getPartMDRpercentage(),"PartMDRPercentage is mandatory field");
						break;
            	}
            }           
            nullCheck(entity.getPartSettlementCycle(),"PartSettlementCycle is Mandatory field");       
            if(entity.getPartSettlementPlan().equals(SettlementPlan.Merchant)) {
	            switch(entity.getPartSettlementCycle()) {
		            case T_PLUS_N :
		            	nullCheck(entity.getPartT_Plus_N(),"PartT_Plus_N is Mandatory field");
		            	break;
		            case Quarterly:
		            case HalfYearly :
		            case Yearly :
		            	nullCheck(entity.getPartMonth(),"PartMonth is Mandatory field");
		            	break;
		            case Fortnightly :
		            	nullCheck(entity.getPartFortnightlyInterval(),"PartFortnightlyInterval is Mandatory field");
		            	break;
		            case Monthly :
		            	nullCheck(entity.getPartDayOfMonth(),"PartDayOfMonth is Mandatory field");
		            	break;
		            case Weekly :
		            	nullCheck(entity.getPartWeekly(), "PartWeekly is Mandatory field");
		            	break;
	            	}         
            }            
            nullCheck(entity.getStatus(),"Status is Mandatory field");
            nullCheck(entity.getKycCompleted(),"KYCCompleted is Mandatory field");
            if(entity.getKycCompleted().equals(KYCCompleted.Yes)) {
            	nullCheck(entity.getValidFrom(),"ValidFrom is Mandatory Field");
            	nullCheck(entity.getValidUntil(),"ValidUntil is Mandatory Field");
            }
            accountDetailChecker(entity,
            		entity.getAccountPaymentSystem(),
            		entity.getAccountBeneficiaryBankBIC(),
            		entity.getAccountBeneficiaryName(),
            		entity.getAccountBeneficiaryAccountNumber(),
            		entity.getAccountBeneficiaryCurrency(),
            		entity.getAccountBeneficiaryAddressLine1(),
            		entity.getAccountBeneficiaryCountry());  		
		}
		return null;
	}
	private void accountDetailChecker(MerchantLandingEntity entity, final Object...objects) {
		if(objects[0]==null) {
			for(Object object:objects) {
				if(object==null){
					throw new MandatoryFieldMissingException("Account Details Missing");
				}
			}
			entity.builder().accountPaymentSystem("LT");
		}
		else {
			for(Object object:objects) {
				if(object==null){
					throw new MandatoryFieldMissingException("Account Details Missing");
				}
			}
		}
	}
	private void stringCheck(String string, String message) {
		
		if (string == null || string.length() == 0)
            throw new MandatoryFieldMissingException(message);
	}
	private void nullCheck(Object object, String message) {
		if (object == null)
            throw new MandatoryFieldMissingException(message);		
	}
}
