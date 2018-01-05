package com.anonymous.message.auote;

import org.apache.log4j.Logger;

/**
 * 短信接口
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月5日下午3:56:34
 */
public class MessageQuote implements Runnable {
	
	private static final Logger logger = Logger.getLogger(MessageQuote.class);
	
	private String phone;
	
	private String content;

	public void run() {
		//此处调用发短信接口
		logger.info("给"+phone+"发送了一条短信，内容为："+content);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageQuote() {
		super();
	}

	public MessageQuote(String phone, String content) {
		super();
		this.phone = phone;
		this.content = content;
	}
	
}
