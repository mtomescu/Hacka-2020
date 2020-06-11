package com.tradeshift.hornetqclient.listener;

import org.springframework.jms.annotation.JmsListener;

public class InvoiceListener {
    @JmsListener(destination = "internalDispatchInvoice")
    public void consumeInvoice(Object object) {
        System.out.println(object);
    }
}