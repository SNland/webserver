package com.dcm.webserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfig {
    /**
     *
     * @param connectionFactory:topicListenerFactory和queueListenerFactory
     *         topicListenerFactory: 将PubSubDomain设置为true,该Listener负责处理Topic
     *         queueListenerFactory: 将PubSubDomain设置为false,该Listener负责处理queue
     *         jms默认为queue模式
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory=new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory=new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
