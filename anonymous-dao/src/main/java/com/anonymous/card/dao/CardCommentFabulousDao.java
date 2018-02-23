package com.anonymous.card.dao;

import java.util.HashMap;

/**
 * 卡片评论点赞dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月23日上午10:27:57
 */
public interface CardCommentFabulousDao {
	
	/**
	 * 判断是否点赞
	 * @param map
	 * @return
	 */
	public Integer isFabulous(HashMap<String, Object> map);

	/**
	 * 评论点赞
	 * @param map
	 */
	public void fabulous(HashMap<String, Object> map);
}
