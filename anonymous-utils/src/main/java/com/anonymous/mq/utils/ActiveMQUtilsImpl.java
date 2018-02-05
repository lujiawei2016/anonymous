package com.anonymous.mq.utils;

import java.util.HashMap;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * activemq工具类实现
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月5日上午11:17:36
 */
@Service
@Transactional
public class ActiveMQUtilsImpl implements ActiveMQUtils {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/**
	 * 发送文本消息
	 */
	@Override
	public void sendMessage(String desName, String msg) {
		try {
			Destination destination = jmsTemplate.getConnectionFactory().createConnection().createSession().createQueue(desName);
			jmsTemplate.send(destination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(msg);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送对象
	 */
	@Override
	public void sendMessage(String desName, HashMap<String, Object> msg) {
		try {
			Destination destination = jmsTemplate.getConnectionFactory().createConnection().createSession().createQueue(desName);
			jmsTemplate.send(destination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage(msg);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object receiveMessage(String desName) {
		// TODO Auto-generated method stub
		return null;
	}

}
