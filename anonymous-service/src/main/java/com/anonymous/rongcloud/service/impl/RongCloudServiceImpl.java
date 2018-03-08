package com.anonymous.rongcloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.httpclient.utils.HttpUtils;
import com.anonymous.redis.utils.RedisUtils;
import com.anonymous.rongcloud.service.RongCloudService;

import net.sf.json.JSONObject;

/**
 * 融云service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月8日上午10:06:38
 */
@Service
@Transactional
public class RongCloudServiceImpl implements RongCloudService {
	
	private static final Logger logger = Logger.getLogger(RongCloudServiceImpl.class);
	
	@Value("${rongcloud}")
	private String rongcloud;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${rong_app_key}")
	private String rong_app_key;
	
	@Value("${rong_app_secret}")
	private String rong_app_secret;
	
	@Value("${rong_token_url}")
	private String rong_token_url;
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	

	/**
	 * 获取token
	 */
	@Override
	public Object getToken(String anonymId) throws Exception {
		
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		//判断redis中是否有该token
		String rongKey = rongcloud+"_"+rongcloud;
		String token = redisUtils.get(rongKey);
		if(StringUtils.isBlank(token)){
			//redis中没有token，生成token
			
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				String nonce = UUID.randomUUID().toString();
				String timestamp = String.valueOf(System.currentTimeMillis());
				String signature = HttpUtils.getSha1(rong_app_secret+nonce+timestamp);
				
				//组装请求体数据
				List<NameValuePair> basicNameValuePairs = new ArrayList<>();
				NameValuePair userIdValuePair = new NameValuePair("userId", anonym.getAnonymId());
				NameValuePair nameValuePair = new NameValuePair("name", anonym.getNickName());
				NameValuePair portraitUriValuePair = new NameValuePair("portraitUri", anonym.getHeaderImg());
				basicNameValuePairs.add(userIdValuePair);
				basicNameValuePairs.add(nameValuePair);
				basicNameValuePairs.add(portraitUriValuePair);
				
				//组装请求头数据
				Map<String, String> headerMap = new HashMap<>();
				headerMap.put("App-Key", rong_app_key);
				headerMap.put("Nonce", nonce);
				headerMap.put("Timestamp", timestamp);
				headerMap.put("Signature", signature);
				
				String tokenResult = HttpUtils.PostByHttpClient(rong_token_url, basicNameValuePairs, headerMap);
				if(!StringUtils.isBlank(tokenResult)){
					JSONObject json = JSONObject.fromObject(tokenResult);
					String ront_token = json.getString("token");  //获取融云的token
					resultMap.put("token", ront_token);
					result = "1";
					msg = "获取成功";
					
					//将token放入到redis中
					redisUtils.put(rongKey, ront_token);
					redisUtils.exec();
					
					logger.info(anonymId+"获取融云token成功"+json.getString("token"));
				}
			}
			
		}else{
			//redis中有token，直接返回
			result = "1";
			msg = "获取成功";
			resultMap.put("token", token);
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
	
}
