package com.tradeshift.hornetqclient.configuration;

import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.tradeshift.commons.messaging.jms.converters.JaxbMessageConverter;
import com.tradeshift.commons.messaging.jms.hornetq.HornetQConnectionFactory;
import com.tradeshift.commons.messaging.jms.queue.JmsQueueSemantics;
import com.tradeshift.commons.messaging.jms.topic.JmsTopicSemantics;
import com.tradeshift.jaxb.SupplierInfoMarshallingOverrideUtility;
import com.tradeshift.jaxb.TradeshiftDocumentJaxbSupport;

@Configuration
public class HornetQConfiguration {

    @Value("${hosts.hornetq}")
    private  String hosts;

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
        cf.setHosts(hosts);
        return cf.getObject();
    }

    @Bean
    public TradeshiftDocumentJaxbSupport tradeshiftDocumentJaxbSupport() {
        TradeshiftDocumentJaxbSupport jaxbSupport = new TradeshiftDocumentJaxbSupport();
        jaxbSupport.setBasePackages(new String[] {"com.tradeshift", "oasis.names"});
        return jaxbSupport;
    }

    @Bean
    public SupplierInfoMarshallingOverrideUtility supplierInfoMarshallingOverrideUtility() {
        return new SupplierInfoMarshallingOverrideUtility();
    }

    @Bean
    public JaxbMessageConverter jaxbMessageConverter() {
        return new JaxbMessageConverter(tradeshiftDocumentJaxbSupport());
    }

    @Bean
    public JmsTopicSemantics jmsTopicSemantics() {
        return new JmsTopicSemantics();
    }

    @Bean
    public JmsQueueSemantics jmsQueueSemantics() throws Exception {
        return new JmsQueueSemantics(
                jaxbMessageConverter(),
                hornetQConnectionFactory(),
                null,
                null,
                null);
    }
}
