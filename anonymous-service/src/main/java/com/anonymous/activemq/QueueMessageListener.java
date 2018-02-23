package com.anonymous.activemq;

import java.util.HashMap;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.anonymous.card.dao.CardCommentFabulousDao;
import com.anonymous.card.dao.CardFabulousDao;

public class QueueMessageListener implements MessageListener {
	
	private String queue = "queue://";
	
	@Autowired
	private CardCommentFabulousDao cardCommentFabulousDao;
	
	@Autowired
	private CardFabulousDao cardFabulousDao;

	/**
	 * 监听消息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		
        try {
        	String desName = message.getJMSDestination().toString().replaceAll(queue, "");
        	if("cardCommentFabulous".equals(desName)){
        		//卡片评论点赞
				HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
				Integer fabulousNum = cardCommentFabulousDao.isFabulous(map);
				if(fabulousNum == null || fabulousNum == 0){
					//如果没有点赞，则进行点赞
					cardCommentFabulousDao.fabulous(map);
				}
        	}else if("cardFabulous".equals(desName)){
        		//卡片点赞
        		HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
        		Integer fabulousNum = cardFabulousDao.isFabulous(map);
        		if(fabulousNum == null || fabulousNum == 0){
        			//如果没有点赞，则进行点赞
        			cardFabulousDao.fabulous(map);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mq处理消息异常");
        }
	}

}
