package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

@Configuration
public class SomeConfiguration {

    @Bean
    public void gateway () {

    }

    @Bean
    @ServiceActivator(inputChannel = "inputChannel1")
    public MessageHandler someHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                Message<?> toSend = new GenericMessage<>("adasdfasdf", message.getHeaders());
            }
        };
    }

}