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
import com.anonymous.card.dao.CardCommentDao;
import com.anonymous.card.pojo.CardComment;
import com.anonymous.card.service.CardCommentFabulousService;
import com.anonymous.mq.utils.ActiveMQUtils;
import com.anonymous.redis.utils.RedisUtils;

/**
 * 评论点赞实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月9日上午10:07:28
 */
@Service
@Transactional
public class CardCommentFabulousServiceImpl implements CardCommentFabulousService {
	
	private static final Logger logger = Logger.getLogger(CardCommentFabulousServiceImpl.class);
	
	@Value("${card_comment_fabulous_num}")
	private String card_comment_fabulous_num;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ActiveMQUtils activeMQUtils;
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private CardCommentDao cardCommentDao;
	
	/**
	 * 用户点赞评论
	 */
	@Override
	public Object fabulous(String anonymId, String cardCommentId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(cardCommentId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				CardComment cardComment = cardCommentDao.findCardCommentById(cardCommentId);
				if(cardComment != null){
					//在redis中进行运算，再放到activemq中进行数据库的增减
					String redis_fabulous_key = card_comment_fabulous_num+"_"+cardCommentId;
					String commentFabulousNum = redisUtils.get(redis_fabulous_key);
					int fabulousNum = 1;
					if(!StringUtils.isBlank(commentFabulousNum)){
						fabulousNum = Integer.parseInt(commentFabulousNum);
					}
					fabulousNum = fabulousNum + 1;
					
					//将点赞+1后再写入到redis中
					redisUtils.put("redis_fabulous_key", fabulousNum);
					redisUtils.exec();
					
					//将最新的数据放到activemq中进行数据库的插入
					HashMap<String, Object> mqMap = new HashMap<>();
					mqMap.put("cardCommentFabulousId", UUID.randomUUID().toString());
					mqMap.put("anonymId", anonymId);
					mqMap.put("cardCommentId", cardCommentId);
					mqMap.put("createTime", new Date());
					mqMap.put("updateTime", new Date());
					activeMQUtils.sendMessage("cardCommentFabulous", mqMap);
					
					resultMap.put("fabulousNum", fabulousNum);
					result = "1";
					msg = "点赞成功";
					
					logger.info(anonymId+"点赞了卡片"+cardCommentId+"成功");
				}else{
					result = "3";
					msg = "卡片不合法";
				}
			}else{
				result = "2";
				msg = "用户不合法";
			}
		}
		
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
