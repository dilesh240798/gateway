package com.scb.scroe.gateway.ip88transaction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TransactionEntry extends TransactionRecord {

    public SettlementCycleValue settlementCycleValue;
    
    public LocalDateTime initiationDate;
    
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Message Id can only be Alphanumeric")
    @Size(max = 35, message = "Message Id format incorrect")
    public String messageId;
    
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Business Message Id can only be Alphanumeric")
    @Size(max = 35, message = "Business Message Id format incorrect")
    public String businessMessageId;
    
    public ServiceType serviceType;
    
    @Size(max = 3,min = 3, message = "schemeId length can only be 3")
    public String schemeId;
    
    @NotEmpty
	@Size(max = 11, message = "OFI Code max length can only be 11")
    public String ofiCode;
    
    @NotEmpty
	@Size(max = 20, message = "OFI Acc No max length can only be 20")
    public String ofiAccountNumber;
    
    public AccountType ofiAccountType;
    
    public String ofiAccountName;
    
    @Size(max = 11, message = "RFI Code max length can only be 11")
    public String rfiCode;
    
    @NotEmpty
	@Size(max = 20, message = "RFI Acc No imax length can only be 20")
    public String rfiAccountNumber;
    
    public AccountType rfiAccountType;
    
    @Size(max = 140, message = "RFI Acc Name max length can only be 140")
    public String rfiAccountName;
    
    public String transactionAmount;
    
    @Size(max = 3, min = 3, message = "currency format incorrect")
    public String currency;
    
    public String foreignExchangeNumber;
    
    public String foreignAmount;
    
    public String foreignExchangeRate;
    
    public TransactionStatus transactionStatus;
    
    @Size(max = 4,min = 4, message = "transactionSubCode length can only be 4")
    public String transactionSubCode;
    
    public LocalDate completedDate;
    
    public TransactionEntry() {
        this.recordType = TransactionRecordType.D;
    }
}
