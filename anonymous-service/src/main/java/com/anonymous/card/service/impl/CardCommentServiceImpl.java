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
import com.anonymous.card.dao.CardCommentDao;
import com.anonymous.card.dao.CardDao;
import com.anonymous.card.pojo.Card;
import com.anonymous.card.service.CardCommentService;

import net.sf.json.JSONObject;

/**
 * 卡片评论实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月26日上午11:24:03
 */
@Service
@Transactional
public class CardCommentServiceImpl implements CardCommentService {
	
	private static final Logger logger = Logger.getLogger(CardCommentServiceImpl.class);
	
	@Autowired
	private CardCommentDao cardCommentDao;
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private CardDao cardDao;

	/**
	 * 获取卡片评论
	 */
	@Override
	public Object getCardComment(String anonymId, String cardId,String offset,String length) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(cardId) && StringUtils.isNumeric(offset) && StringUtils.isNumeric(length)){
			Card card = cardDao.findCardById(cardId);
			if(card != null){
				logger.info(anonymId+"获取"+cardId+"的评论");
				//当卡片和用户合法时查询评论
				Map<String, Object> commentMap = new HashMap<>();
				commentMap.put("anonymId", anonymId);
				commentMap.put("cardId", card.getCardId());
				commentMap.put("offset", Integer.parseInt(offset));
				commentMap.put("length", Integer.parseInt(length));
				List<Map<String, Object>> commentList = cardCommentDao.getCardComment(commentMap);
				
				result = "1";
				msg = "获取成功";
				resultMap.put("commentList", commentList);
			}else{
				result = "3";
				msg = "信息不存在";
			}
			
		}else{
			result = "2";
			msg = "系统繁忙，请稍后重试";
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	/**
	 * 保存平路
	 */
	@Override
	public Object saveCardComment(String anonymId, String cardId, 
			String cardCommentContent, String carCommentReplyId)
			throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(cardId) && !StringUtils.isBlank(cardCommentContent)){
			
			Anonym replyAnonym = null;
			//判断回复的用户是否为空
			if(!StringUtils.isBlank(carCommentReplyId)){
				replyAnonym = anonymousDao.findAnonymById(carCommentReplyId);
				if(replyAnonym == null){
					carCommentReplyId = null;
				}
			}
			
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				Card card = cardDao.findCardById(cardId);
				if(card != null){
					Map<String, Object> commentMap = new HashMap<>();
					String cardCommentId = UUID.randomUUID().toString();
					commentMap.put("cardCommentId", cardCommentId);
					commentMap.put("cardCommentContent", cardCommentContent);
					commentMap.put("carCommentReplyId", carCommentReplyId);
					commentMap.put("createTime", new Date());
					commentMap.put("updateTime", new Date());
					commentMap.put("anonymId", anonymId);
					commentMap.put("cardId", cardId);
					cardCommentDao.saveCardComment(commentMap);
					
					JSONObject json = new JSONObject();
					json.put("anonymNickName", anonym.getNickName());
					json.put("anonymHeaderImg", anonym.getHeaderImg());
					json.put("cardCommentId", cardCommentId);
					if(replyAnonym != null){
						json.put("replyAnonymNickName", replyAnonym.getNickName());
						json.put("replyAnonymHeaderImg", replyAnonym.getHeaderImg());
					}else{
						json.put("replyAnonymNickName", null);
						json.put("replyAnonymHeaderImg", null);
					}
					resultMap.put("json", json);
					
					result = "1";
					msg = "评论成功";
					
					logger.info(anonymId+"评论成功");
				}else{
					result = "3";
					msg = "该卡片信息不合法";
				}
			}else{
				result = "2";
				msg = "该用户不合法";
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
