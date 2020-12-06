package com.scb.scroe.gateway.ip88transaction.constant;

public final class TransactionMessage {

    public static final String TRAILER_NOT_FOUND = "Trailer record not found";
    public static final String HEADER_NOT_FOUND = "Header record not found";
    public static final String BALANCE_NOT_FOUND = "Balance record not found";
    public static final String MULTIPLE_HT_FOUND = "Multiple header/trailer in file";
    public static final String TRAILER_ALREADY_REACHED = "Records found after trailer";
    public static final String TRAILER_VALIDATION_FAILED = "Trailer validation failed";
    public static final String HTD_ACCEPTED_ONLY = "Record type H/T/D is supported";
    public static final String DATE_TIME_MISMATCH = "Data Time mismatch found in header";
}
