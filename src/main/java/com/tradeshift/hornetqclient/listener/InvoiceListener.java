package com.tradeshift.hornetqclient.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.tradeshift.dispatch.DispatchMessage;

@Component
public class InvoiceListener {
    private final Logger LOGGER = LoggerFactory.getLogger(InvoiceListener.class);

    @JmsListener(destination = "internalDispatchInvoice", containerFactory = "jmsListenerContainerFactory")
    public void consumeInvoice(DispatchMessage object) {
        LOGGER.info("Received a message for docId {} and dispatchId {}", object.getObjectId(), object.getDispatchId() );

        LOGGER.info(object.toString());
    }

    @JmsListener(destination = "internal", containerFactory = "jmsListenerContainerFactory")
    public void consumeInternalQueue(DispatchMessage message) {
        LOGGER.info("Received a message for docId {} and dispatchId {}", message.getObjectId(), message.getDispatchId() );

        LOGGER.info(message.toString());
    }

    @JmsListener(destination = "DocumentService_Dispatcher", containerFactory = "jmsListenerContainerFactory")
    public void consumeDispatcherQueue(DispatchMessage message) {
        LOGGER.info("Received a message for docId {} and dispatchId {}", message.getObjectId(), message.getDispatchId() );

        LOGGER.info(message.toString());
    }
}