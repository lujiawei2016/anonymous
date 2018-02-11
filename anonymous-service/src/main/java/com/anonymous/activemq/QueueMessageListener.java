package com.anonymous.activemq;

import java.util.HashMap;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.anonymous.card.dao.CardCommentFabulousDao;

public class QueueMessageListener implements MessageListener {
	
	private String queue = "queue://";
	
	@Autowired
	private CardCommentFabulousDao cardCommentFabulousDao;

	/**
	 * 监听消息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		
        try {
        	String desName = message.getJMSDestination().toString();
        	if("cardCommentFabulous".equals(desName.replaceAll(queue, ""))){
        		//卡片评论点赞
				HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
				cardCommentFabulousDao.fabulous(map);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mq处理消息异常");
        }
	}

}
