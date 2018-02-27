package com.anonymous.story.dao;

import java.util.List;
import java.util.Map;

/**
 * 故事dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午5:01:26
 */
public interface StoryDao {
	
	/**
	 * 查找最新故事
	 * @return
	 */
	public List<Map<String, Object>> searchNewStory();

	/**
	 * 保存故事
	 * @param map
	 */
	public void saveStory(Map<String, Object> map);
}
