package com.anonymous.card.dao;

import java.util.List;
import java.util.Map;

/**
 * 卡片dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月9日上午11:30:57
 */
public interface CardDao {

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
}
