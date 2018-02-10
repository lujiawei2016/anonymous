package com.anonymous.activemq;

import java.util.HashMap;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class QueueMessageListener implements MessageListener {
	
	private String queue = "queue://";

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
        try {
        	String desName = message.getJMSDestination().toString();
        	if("cardCommentFabulous".equals(desName.replaceAll(queue, ""))){
        		//卡片评论点赞
        		ObjectMessage objectMessage = (ObjectMessage) message;
				HashMap<String, Object> map = (HashMap<String, Object>) ((ObjectMessage) message).getObject();
				
        	}
        		
        	System.out.println(desName);
        	ObjectMessage objectMessage = (ObjectMessage) message;
        	HashMap<String, Object> map = (HashMap<String, Object>) objectMessage.getObject();
        	System.out.println("监听监听.................................");
        	System.out.println(map);
        	System.out.println(map.get("msg"));
        	System.out.println(objectMessage.getJMSType());
        	System.out.println("监听监听.................................");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mq处理消息异常");
        }
	}

}
