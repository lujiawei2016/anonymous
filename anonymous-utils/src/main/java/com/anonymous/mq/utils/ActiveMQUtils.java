package com.anonymous.mq.utils;

import java.util.HashMap;

/**
 * activemq工具类接口
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月5日上午11:16:51
 */
public interface ActiveMQUtils {

	/**
	 * 发送文本消息
	 * @param desName destination名称
	 * @param msg     发送的消息
	 */
	public void sendMessage(final String desName,final String msg);

	/**
	 * 发送对象消息
	 * @param desName destination名称
	 * @param msg     发送的消息
	 */
	public void sendMessage(final String desName,final HashMap<String, Object> msg);
	
	/**
	 * 获取消息
	 * @param desName
	 * @return
	 */
	public Object receiveMessage(final String desName);
}
