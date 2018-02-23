package com.anonymous.card.dao;

import java.util.HashMap;

/**
 * 卡片点赞dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日下午4:49:59
 */
public interface CardFabulousDao {
	
	/**
	 * 判断是否点赞
	 * @param map
	 * @return
	 */
	public Integer isFabulous(HashMap<String, Object> map);

	/**
	 * 卡片点赞
	 * @param map
	 */
	public void fabulous(HashMap<String, Object> map);
}
