package com.anonymous.card.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.card.dao.CardDao;
import com.anonymous.card.service.CardService;

/**
 * 卡片实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月9日上午10:25:50
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {
	
	private static final Logger logger = Logger.getLogger(CardServiceImpl.class);
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private CardDao cardDao;

	/**
	 * 保存卡片信息
	 */
	@Override
	public Object release(String anonymId, String cardContent, String imgPath) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(cardContent) && !StringUtils.isBlank(imgPath)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				String cardId = UUID.randomUUID().toString();
				
				//将数据封装到map
				Map<String, Object> cardMap = new HashMap<>();
				cardMap.put("cardId", cardId);
				cardMap.put("cardContent", cardContent);
				cardMap.put("cardImg", imgPath);
				cardMap.put("createTime", new Date());
				cardMap.put("updateTime", new Date());
				cardMap.put("anonymId", anonym.getAnonymId());
				
				cardDao.saveCard(cardMap);
				
				logger.info(anonymId+"成功添加了一条卡片");
				
				result = "1";
				msg = "添加成功";
				
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	/**
	 * 查出主页最新卡片信息
	 */
	@Override
	public Object searchNewCard(String anonymId) throws Exception {
		
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId)){
			List<Map<String, Object>> cardList = cardDao.searchNewCard();
			result = "1";
			msg = "";
			resultMap.put("cardList", cardList);
			
			logger.info(anonymId+"获取最新卡片信息");
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	/**
	 * 分页查询卡片
	 */
	@Override
	public Object pagingSearchCard(String anonymId, String offset, String length) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(StringUtils.isNumeric(offset) && StringUtils.isNumeric(length)){
			List<Map<String, Object>> cardList = cardDao.pagingSearchCard(anonymId, Integer.parseInt(offset), Integer.parseInt(length));
			result = "1";
			msg = "";
			resultMap.put("cardList", cardList);
			
			logger.info(anonymId+"获取最新卡片信息");
		}else{
			result = "2";
			msg = "参数不合法";
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
