package com.tradeshift.hornetqclient.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class HornetQBrowserQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(HornetQBrowserQueue.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    public void iterateOverQueueMessages() {
        List<String> messages = jmsTemplate.browse("BackendService.DocumentService_Dispatcher", new DispatchMessageCallback());
        messages.forEach(el -> LOGGER.info("{}" , el));
    }
}
