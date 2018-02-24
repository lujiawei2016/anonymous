package com.anonymous.story.dao;

import java.util.Map;

/**
 * 故事dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午5:01:26
 */
public interface StoryDao {

	/**
	 * 保存故事
	 * @param map
	 */
	public void saveStory(Map<String, Object> map);
}
