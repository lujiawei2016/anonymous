package com.anonymous.card.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anonymous.card.pojo.Card;

/**
 * 卡片dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月9日上午11:30:57
 */
public interface CardDao {
	
	/**
	 * 根据id查询卡片
	 * @param cardId
	 * @return
	 */
	public Card findCardById(String cardId);

	/**
	 * 保存卡片
	 * @param cardMap
	 */
	public void saveCard(Map<String, Object> cardMap);

	/**
	 * 查出主页最新卡片信息
	 * @return
	 */
	public List<Map<String, Object>> searchNewCard();
	
	/**
	 * 获取用户发布的卡片
	 * @param anonymId
	 * @return
	 */
	public List<Map<String, Object>> findCardByAnonymId(@Param("anonymId") String anonymId);
}
