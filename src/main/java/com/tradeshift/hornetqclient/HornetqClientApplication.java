package com.tradeshift.hornetqclient;

import java.util.Enumeration;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.management.ManagementHelper;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;

import com.tradeshift.commons.messaging.jms.hornetq.HornetQConnectionFactory;

@SpringBootApplication
public class HornetqClientApplication {

	public static void main(String[] args) throws JMSException {
		HornetQConnectionFactory factory = new HornetQConnectionFactory();
		factory.setHosts("localhost:5446");
		Connection connection = null;
		Session session = null;
		QueueBrowser browser = null;

		try {
			HornetQJMSConnectionFactory hornetQJMSConnectionFactory = factory.getObject();
			connection = hornetQJMSConnectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("internalDispatchInvoice");

			browser = session.createBrowser(queue);
			while (true) {
				Enumeration e = browser.getEnumeration();
				while (e.hasMoreElements()) {
					TextMessage message = (TextMessage) e.nextElement();
					System.out.println("Browse [" + message.getText() + "]");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			browser.close();
			session.close();
			connection.close();
		}
	}

}
