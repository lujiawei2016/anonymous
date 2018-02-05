package com.anonymous.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.anonymous.mq.utils.ActiveMQUtils;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private ActiveMQUtils activeMQUtils;
	
	@Override
	public void sendMessage(Destination destination,final String msg){
		try {
			System.out.println(Thread.currentThread().getName()+" 向队列"+destination.toString()+"发送消息---------------------->"+msg);
	        jmsTemplate.send(destination, new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                return session.createTextMessage(msg);
	            }
	        });
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
	@Override
    public void sendMessage(final String msg){
		try {
			Destination destination2 = jmsTemplate.getConnectionFactory().createConnection().createSession().createQueue("Jaycekon");
			System.out.println(destination2);
			Destination destination3 = jmsTemplate.getConnectionFactory().createConnection().createSession().createQueue("Jaycekon3333");
			System.out.println(destination3);
	        System.out.println(Thread.currentThread().getName()+" 向队列"+destination2+"发送消息---------------------->"+msg);
	        jmsTemplate.send(destination2, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(msg);
				}
			});
	        jmsTemplate.send(destination3, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(msg);
				}
			});
	        /*jmsTemplate.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                return session.createTextMessage(msg);
	            }
	        });*/
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public void sendMessage(String desName, String msg) {
		activeMQUtils.sendMessage(desName, msg);
	}

	@Override
	public void sendMessage(String desName, HashMap<String, Object> msg) {
		activeMQUtils.sendMessage(desName, msg);
	}
}
