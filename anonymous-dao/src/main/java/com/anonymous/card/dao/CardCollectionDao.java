package com.anonymous.card.dao;

import java.util.Map;

/**
 * 卡片收藏dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月23日上午11:59:02
 */
public interface CardCollectionDao {
	
	/**
	 * 判断是否收藏
	 * @param map
	 * @return
	 */
	public Integer isCollection(Map<String, Object> map);

	/**
	 * 保存收藏
	 * @param map
	 */
	public void saveCardCollection(Map<String, Object> map);
}
