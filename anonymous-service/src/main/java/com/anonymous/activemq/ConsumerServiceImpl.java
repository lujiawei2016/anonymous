package com.anonymous.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public TextMessage receive(Destination destination){
        try{
        	TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
            System.out.println("从队列" + destination.toString() + "收到了消息：\t"
                    + textMessage.getText());
            return textMessage;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }
}
