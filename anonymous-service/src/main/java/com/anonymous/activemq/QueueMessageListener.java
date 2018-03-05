package com.anonymous.activemq;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.anonymous.card.dao.CardCommentFabulousDao;
import com.anonymous.card.dao.CardFabulousDao;
import com.anonymous.story.dao.StoryCommentDao;
import com.anonymous.story.dao.StoryFabulousDao;
import com.anonymous.story.pojo.StoryFabulous;

public class QueueMessageListener implements MessageListener {
	
	private String queue = "queue://";
	
	@Autowired
	private CardCommentFabulousDao cardCommentFabulousDao;
	
	@Autowired
	private CardFabulousDao cardFabulousDao;
	
	@Autowired
	private StoryFabulousDao storyFabulousDao;
	
	@Autowired
	private StoryCommentDao storyCommentDao;

	/**
	 * 监听消息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		
        try {
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
        	String desName = message.getJMSDestination().toString().replaceAll(queue, "");
        	if("cardCommentFabulous".equals(desName)){
        		//卡片评论点赞
				HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
				Integer fabulousNum = cardCommentFabulousDao.isFabulous(map);  //判断是否已经点赞
				if(fabulousNum == null || fabulousNum == 0){
					//如果没有点赞，则进行点赞
					cardCommentFabulousDao.fabulous(map);
				}
        	}else if("cardFabulous".equals(desName)){
        		//卡片点赞
        		HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
        		Integer fabulousNum = cardFabulousDao.isFabulous(map);  //判断是否已经点赞
        		if(fabulousNum == null || fabulousNum == 0){
        			//如果没有点赞，则进行点赞
        			cardFabulousDao.fabulous(map);
        		}
        	}else if("storyFabulous".equals(desName)){
        		//故事点赞
        		HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
        		Integer fabulousNum = storyFabulousDao.isFabulous(map.get("storyId").toString(), map.get("anonymId").toString());  //判断是否已经点赞
        		if(fabulousNum == null || fabulousNum == 0){
        			//如果没有点赞，则进行点赞
        			storyFabulousDao.fabulous(map);
        		}
        	}else if("storyCommentFabulous".equals(desName)){
        		//故事评论点赞
        		HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
        		Integer fabulousNum = storyCommentDao.isFabulous(map.get("storyCommentId").toString(), map.get("anonymId").toString());
        		if(fabulousNum == null || fabulousNum == 0){
        			//如果没有点赞，则进行点赞
        			storyCommentDao.fabulous(map);
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mq处理消息异常");
        }
	}

}
