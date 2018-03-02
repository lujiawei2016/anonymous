package com.anonymous.story.dao;

import java.util.Map;

/**
 * 故事评论dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月2日下午4:01:17
 */
public interface StoryCommentDao {

	/**
	 * 故事评论
	 * @param map
	 */
	public void comment(Map<String, Object> map);
}
