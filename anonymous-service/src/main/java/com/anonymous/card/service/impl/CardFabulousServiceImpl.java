package com.anonymous.card.service.impl;

import java.util.Date;
import java.util.HashMap;
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
import com.anonymous.card.dao.CardDao;
import com.anonymous.card.pojo.Card;
import com.anonymous.card.service.CardFabulousService;
import com.anonymous.mq.utils.ActiveMQUtils;
import com.anonymous.redis.utils.RedisUtils;

/**
 * 卡片点赞实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日下午4:12:10
 */
@Service
@Transactional
public class CardFabulousServiceImpl implements CardFabulousService {
	
	private static final Logger logger = Logger.getLogger(CardFabulousServiceImpl.class);
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private CardDao cardDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ActiveMQUtils activeMQUtils;
	
	@Value("${card_fabulous_num}")
	private String card_fabulous_num;

	/**
	 * 卡片点赞
	 */
	@Override
	public Object fabulous(String anonymId, String cardId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(cardId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			Card card = cardDao.findCardById(cardId);
			if(anonym != null && card != null){
				//在redis中查找判断是否已经点赞
				String redis_anonymId_key = anonymId+"_"+cardId;
				String isFabulous = redisUtils.get(redis_anonymId_key);
				
				if(StringUtils.isBlank(isFabulous)){
					//没有点赞
					
					//在redis中进行运算，再放到activemq中进行数据库的增减
					String redis_fabulous_key = card_fabulous_num+"_"+cardId;
					String commentFabulousNum = redisUtils.get(redis_fabulous_key);
					int fabulousNum = 1;
					if(!StringUtils.isBlank(commentFabulousNum)){
						fabulousNum = Integer.parseInt(commentFabulousNum);
						fabulousNum = fabulousNum + 1;
					}
					
					//将点赞+1后再写入到redis中
					redisUtils.put(redis_fabulous_key, fabulousNum+"");
					
					//将最新的数据放到activemq中进行数据库的插入
					HashMap<String, Object> mqMap = new HashMap<>();
					mqMap.put("cardFabulousId", UUID.randomUUID().toString());
					mqMap.put("cardId", cardId);
					mqMap.put("anonymId", anonymId);
					mqMap.put("createTime", new Date());
					mqMap.put("updateTime", new Date());
					activeMQUtils.sendMessage("cardFabulous", mqMap);
					
					//点赞成功进行redis标记
					redisUtils.put(redis_anonymId_key, "1");
					redisUtils.exec();
					
					resultMap.put("fabulousNum", fabulousNum);
					result = "1";
					msg = "点赞成功";
					
					logger.info(anonymId+"点赞了卡片"+cardId+"成功");
				}
				
			}else{
				result = "2";
				msg = "用户或卡片内容不合法";
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
}
