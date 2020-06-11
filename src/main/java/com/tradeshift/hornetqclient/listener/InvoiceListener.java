package com.tradeshift.hornetqclient.listener;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceListener {
    private final Logger LOGGER = LoggerFactory.getLogger(InvoiceListener.class);

    @JmsListener(destination = "internalDispatchInvoice")
    public void consumeInvoice(Message object) {
        LOGGER.info(object.toString());
    }
}