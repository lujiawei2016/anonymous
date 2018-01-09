package com.anonymous.card.dao;

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
}
