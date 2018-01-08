package com.anonymous.message.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
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

	/**
	 * 快捷登陆
	 */
	@Override
	public Object quickLogin(String phone, String code, String deviceId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(!StringUtils.isBlank(phone) && !StringUtils.isBlank(code) && !StringUtils.isBlank(deviceId) && CommonUtils.isPhone(phone)){
			//对比用户输入的验证码和发送的验证码是否一致
			String verificationCode = redisUtils.get(login+"_"+phone);
			if(verificationCode == null){
				//没有发送验证码或验证码已过期
				result = "2";
				msg = "请重新发送验证码";
			}else{
				if(verificationCode.equals(code)){
					//发送验证码和用户验证码一致
					
					//判断该手机号码是否已经注册
					List<Anonym> anonymList = anonymousDao.findAnonymByPhone(phone);
					if(anonymList != null && anonymList.size() != 0){
						//手机号已经注册
					}else{
						//手机号未注册，写入到数据库中
						
						//nickName和headerImg考虑随机获取，现在暂时写死
						String anonymId = UUID.randomUUID().toString();
						Anonym anonym = new Anonym(anonymId, "张三", phone, "", phone, deviceId, new Date(), new Date());
						anonymousDao.saveAnonym(anonym);
						
						resultMap.put("anonymId", anonym.getAnonymId());
					}
					
					//登录成功之后删除验证码
					redisUtils.deleteKey(login+"_"+phone);
					
					logger.info(phone+"快捷登陆成功");
					
					result = "1";
					msg = "登陆成功";
					
				}else{
					//发送验证码和用户验证码不一致
					result = "3";
					msg = "验证码错误";
				}
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
