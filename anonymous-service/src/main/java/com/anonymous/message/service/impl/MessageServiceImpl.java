package com.anonymous.message.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonymous.dao.AnonymousDao;
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
	
	@Value("${message_num}")
	private String message_num;
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	/**
	 * 发送登陆验证码
	 */
	@Override
	public Object sendLoginMsg(String phone) throws Exception {
		
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(CommonUtils.isPhone(phone)){
			
			//判断该手机号码是否已经超过了发短信次数
			int message_num = 0;
			String message_num_redis = redisUtils.get("message_num_"+phone);
			if(message_num_redis != null){
				message_num = Integer.parseInt(message_num_redis);
			}
			
			if(message_num <= 10){
				String redisVal = redisUtils.get(login+"_"+phone);
				if(StringUtils.isBlank(redisVal)){
					//redis中没有验证码，进行发送
					//生成四位数数字验证码
					String verificationCode = "";
					for(int i=0;i<4;i++){
						int random = (int) (Math.random() * 10);
						verificationCode = verificationCode+random;
					}
					
					//将生成的验证码放入到redis中，并设置时间为120秒
					redisUtils.put(login+"_"+phone, verificationCode, 120, TimeUnit.SECONDS);
					redisUtils.exec();
					
					//该短信发送次数加1
					message_num = message_num + 1;
					redisUtils.put("message_num_"+phone, message_num+"", 5, TimeUnit.HOURS);
					redisUtils.exec();
					
					//开启一个线程进行短信的发送
					MessageQuote messageQuote = new MessageQuote(phone, "【匿秘科技】您好，你的验证码为"+verificationCode+"，打死不告诉任何人");
					Thread messageThread = new Thread(messageQuote);
					messageThread.start();
					
					logger.info("向"+phone+"用户发送了一条登陆验证码");
					
					result = "1";
					msg = "发送成功";
				}else{
					//距离上次发送没有超过120秒
					result = "2";
					msg = "已发送短信，请稍后重试";
				}
			}else{
				//手机号码不正确
				result = "4";
				msg = "该手机号发送短信太频繁，请稍后重试";
			}
		
		}else{
			//手机号码不正确
			result = "3";
			msg = "手机号不正确";
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
}
