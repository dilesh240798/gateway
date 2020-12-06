package com.scb.scroe.gateway.ip88transaction.callback;

//import lombok.extern.log4j.Log4j2;


import org.springframework.batch.item.file.LineCallbackHandler;
//@Log4j2
public class DefaultSkipLineCallback implements LineCallbackHandler {
    @Override
    public void handleLine(String s) {
        //log.info("THIS IS THE SKIPPED LINE {}", s);
    }
}
