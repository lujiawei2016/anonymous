package com.anonymous.activemq;

import java.util.HashMap;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class QueueMessageListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
        try {
        	//System.out.println("监听："+message.getJMSDestination().toString());
    		/*TextMessage tm = (TextMessage) message;
            System.out.println("QueueMessageListener监听到了文本消息：\t"
                    + tm.getText());*/
            //do something ...
        	
        	ObjectMessage objectMessage = (ObjectMessage) message;
        	HashMap<String, Object> map = (HashMap<String, Object>) objectMessage.getObject();
        	System.out.println("监听监听.................................");
        	System.out.println(map);
        	System.out.println(map.get("msg"));
        	System.out.println(objectMessage.getJMSType());
        	System.out.println("监听监听.................................");
        	int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("异常异常");
        }
	}

}
