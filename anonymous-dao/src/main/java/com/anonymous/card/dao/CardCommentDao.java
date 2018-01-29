package com.anonymous.card.dao;

import java.util.List;
import java.util.Map;

/**
 * 卡片评论Dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月29日下午2:52:48
 */
public interface CardCommentDao {

	/**
	 * 获取卡片评论
	 * @param commentMap
	 * @return
	 */
	public List<Map<String, Object>> getCardComment(Map<String, Object> commentMap);
	
	/**
	 * 保存卡片评论
	 * @param commentMap
	 */
	public void saveCardComment(Map<String, Object> commentMap);
	
}
