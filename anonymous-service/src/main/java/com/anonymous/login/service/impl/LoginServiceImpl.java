package com.anonymous.login.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.common.utils.CommonUtils;
import com.anonymous.common.utils.PasswordUtils;
import com.anonymous.login.service.LoginService;
import com.anonymous.redis.utils.RedisUtils;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${login}")
	private String login;
	
	@Value("${message_num}")
	private String message_num;
	
	@Autowired
	private AnonymousDao anonymousDao;

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
					String password = CommonUtils.MD5(PasswordUtils.getStringRandom(8)); //生成随机密码并加密
					
					//判断该手机号码是否已经注册
					List<Anonym> anonymList = anonymousDao.findAnonymByPhone(phone);
					if(anonymList != null && anonymList.size() != 0){
						//手机号已经注册，更改密码进行更新
						Anonym anonym = anonymList.get(0);
						anonym.setPassword(password);
						anonymousDao.updateAnonym(anonym);
						
						resultMap.put("anonymId", anonym.getAnonymId());
						resultMap.put("password", password);
					}else{
						//手机号未注册，写入到数据库中
						
						//nickName和headerImg考虑随机获取，现在暂时写死
						String anonymId = UUID.randomUUID().toString();
						Anonym anonym = new Anonym(anonymId, "张三", password, phone, "", phone, deviceId, new Date(), new Date());
						anonymousDao.saveAnonym(anonym);
						
						resultMap.put("anonymId", anonym.getAnonymId());
						resultMap.put("password", password);
					}
					
					//登录成功之后删除验证码
					redisUtils.deleteKey(login+"_"+phone);
					redisUtils.exec();
					
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
