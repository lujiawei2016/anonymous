package com.anonymous.story.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 故事收藏dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月2日上午10:04:47
 */
public interface StoryCollectionDao {

	/**
	 * 判断是否收藏该故事
	 * @param storyId
	 * @param anonymId
	 * @return
	 */
	public Integer isCollection(@Param("storyId") String storyId,@Param("anonymId") String anonymId);
	
	/**
	 * 收藏故事
	 * @param map
	 */
	public void collection(Map<String, Object> map);
}
