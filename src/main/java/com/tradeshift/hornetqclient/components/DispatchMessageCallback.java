package com.tradeshift.hornetqclient.components;

import java.util.Enumeration;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.jms.core.BrowserCallback;

import com.tradeshift.dispatch.DispatchMessage;

public class DispatchMessageCallback implements BrowserCallback<List<String>> {
    private List<String> messsagesReceived;

    @Override
    public List<String> doInJms(Session session, QueueBrowser queueBrowser) throws JMSException {
        Enumeration<?> messages = queueBrowser.getEnumeration();
        while (messages.hasMoreElements()) {
            DispatchMessage dispatchMessage = (DispatchMessage) messages.nextElement();
            messsagesReceived.add("DocumentId: " + dispatchMessage.getObjectId().toString() + " - dispatchId: " + dispatchMessage.getDispatchId().toString());
        }

        return messsagesReceived;
    }
}
