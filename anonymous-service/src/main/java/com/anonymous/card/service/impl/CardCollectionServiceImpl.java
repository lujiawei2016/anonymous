package com.anonymous.card.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.card.dao.CardCollectionDao;
import com.anonymous.card.dao.CardDao;
import com.anonymous.card.pojo.Card;
import com.anonymous.card.service.CardCollectionService;
import com.anonymous.redis.utils.RedisUtils;

/**
 * 卡片收藏service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月23日上午11:57:30
 */
@Service
@Transactional
public class CardCollectionServiceImpl implements CardCollectionService {
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private CardDao cardDao;
	
	@Autowired
	private CardCollectionDao cardCollectionDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${cardCollection}")
	private String cardCollection;

	/**
	 * 卡片收藏
	 */
	@Override
	public Object collection(String anonymId, String cardId) throws Exception {
		
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(cardId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			Card card = cardDao.findCardById(cardId);
			if(anonym != null && card != null){
				String cardCollectionId = UUID.randomUUID().toString();
				Date createTime = new Date();
				Map<String, Object> map = new HashMap<>();
				map.put("cardCollectionId", cardCollectionId);
				map.put("anonymId", anonymId);
				map.put("cardId", cardId);
				map.put("createTime", createTime);
				map.put("updateTime", createTime);
				Integer collectionNum = cardCollectionDao.isCollection(map);
				
				//判断是否已经收藏，如果没有收藏则进行收藏
				if(collectionNum == null || collectionNum == 0){
					//在redis中进行数量计算
					int cardCollectionNum = 1;
					String collectionKey = cardCollection+"_"+cardId;
					String redisCardCollectionNum = redisUtils.get(collectionKey);  // redis中保存的数量
					if(!StringUtils.isBlank(redisCardCollectionNum)){
						//redis中不为空，数量+1
						cardCollectionNum = Integer.parseInt(redisCardCollectionNum)+1;
					}
					
					redisUtils.put(collectionKey, cardCollectionNum+"");
					redisUtils.exec();
					
					//保存到数据库中
					cardCollectionDao.saveCardCollection(map);
					
					result = "1";
					msg = "收藏成功";
				}
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
