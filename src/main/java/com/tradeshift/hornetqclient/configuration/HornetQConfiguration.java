package com.tradeshift.hornetqclient.configuration;

import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessageConverter;

import com.tradeshift.commons.jaxb.JaxbSupport;
import com.tradeshift.commons.messaging.jms.converters.JaxbMessageConverter;
import com.tradeshift.commons.messaging.jms.hornetq.HornetQConnectionFactory;

@Configuration
public class HornetQConfiguration {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws Exception {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(hornetQConnectionFactory());
        factory.setMessageConverter(jaxbMessageConverter());
        return factory;
    }

    @Bean
    public HornetQJMSConnectionFactory hornetQConnectionFactory() throws Exception {
        final HornetQConnectionFactory cf = new HornetQConnectionFactory();
        cf.setHosts("localhost:5445");
        return cf.getObject();
    }

    @Bean
    public MessageConverter jaxbMessageConverter() {
        return new JaxbMessageConverter(new JaxbSupport());
    }
}
