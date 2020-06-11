package com.tradeshift.hornetqclient.configuration;


import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.tradeshift.commons.jaxb.JaxbSupport;
import com.tradeshift.commons.messaging.jms.converters.JaxbMessageConverter;
import com.tradeshift.commons.messaging.jms.hornetq.HornetQConnectionFactory;

@Configuration
public class HornetQConfiguration {
    @Value("hosts.hornetq")
    private  String hosts;

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws Exception {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(hornetQConnectionFactory());
        return factory;
    }

    @Bean
    public HornetQJMSConnectionFactory hornetQConnectionFactory() throws Exception {
        final HornetQConnectionFactory cf = new HornetQConnectionFactory();
        cf.setHosts(hosts);
        return cf.getObject();
    }

    @Bean
    public JaxbMessageConverter jaxbMessageConverter() {
        return new JaxbMessageConverter(new JaxbSupport());
    }
}
