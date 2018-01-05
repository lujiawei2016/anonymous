package com.anonymous.message.service.impl;

import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.common.utils.CommonUtils;
import com.anonymous.message.auote.MessageQuote;
import com.anonymous.message.service.MessageService;
import com.anonymous.redis.utils.RedisUtils;

/**
 * 短信实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月5日下午2:04:26
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${login}")
	private String login;
	
	/**
	 * 发送登陆验证码
	 */
	@Override
	public Object sendLoginMsg(String phone) throws Exception {
		
		String result = "0";
		String redisVal = redisUtils.get(login+"_"+phone);
		if(StringUtils.isBlank(redisVal)){
			//redis中没有验证码，进行发送
			if(CommonUtils.isPhone(phone)){
				//生成四位数数字验证码
				String verificationCode = "";
				for(int i=0;i<4;i++){
					int random = (int) (Math.random() * 10);
					verificationCode = verificationCode+random;
				}
				
				//将生成的验证码放入到redis中，并设置时间为120秒
				redisUtils.put(login+"_"+phone, verificationCode, 120, TimeUnit.SECONDS);
				redisUtils.exec();
				
				//开启一个线程进行短信的发送
				MessageQuote messageQuote = new MessageQuote(phone, "【匿秘科技】您好，你的验证码为"+verificationCode+"，打死不告诉任何人");
				Thread messageThread = new Thread(messageQuote);
				messageThread.start();
				
				logger.info("向"+phone+"用户发送了一条登陆验证码");
				
				result = "1";
				
			}else{
				//手机号码不正确
				result = "3";
			}
		}else{
			//距离上次发送没有超过120秒
			result = "2";
		}
		
		return result;
	}

}
