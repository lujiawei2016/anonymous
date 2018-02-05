package com.anonymous.activemq;

import java.util.HashMap;

import javax.jms.Destination;

public interface ProducerService {

	public void sendMessage(Destination destination,final String msg);
	
	public void sendMessage(final String msg);
	
	public void sendMessage(final String desName,final String msg);
	
	public void sendMessage(final String desName,final HashMap<String, Object> msg);
}
