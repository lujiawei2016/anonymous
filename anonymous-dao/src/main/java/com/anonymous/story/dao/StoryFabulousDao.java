package com.anonymous.story.dao;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 故事点赞dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午5:01:26
 *
 */
public interface StoryFabulousDao {

	/**
	 * 判断该故事是否已经点赞
	 * @param storyId
	 * @param anonymId
	 * @return
	 */
	public Integer isFabulous(@Param("storyId") String storyId,@Param("anonymId") String anonymId);
	
	/**
	 * 进行点赞
	 * @param storyFabulousId
	 * @param createTime
	 * @param updateTime
	 * @param storyId
	 * @param anonymId
	 */
	public void fabulous(Map<String, Object> map);
}
