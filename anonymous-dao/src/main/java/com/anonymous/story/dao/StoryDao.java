package com.anonymous.story.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anonymous.story.pojo.Story;

/**
 * 故事dao
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午5:01:26
 */
public interface StoryDao {

	/**
	 * 根据id获取故事
	 * @param storyId
	 * @return
	 */
	public Story findStoryById(String storyId);
	
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

	/**
	 * 获取故事详情
	 * @param storyId
	 * @param anonymId
	 * @return
	 */
	public Story getStoryDetailInfo(@Param("storyId") String storyId,@Param("anonymId") String anonymId);

	/**
	 * 判断该用户是否已经收藏此故事
	 * @param storyId
	 * @param anonymId
	 * @return
	 */
	public Integer getCollectionByAnonymId(@Param("storyId") String storyId,@Param("anonymId") String anonymId);
	
	/**
	 * 判断该用户是否点赞此故事
	 * @param storyId
	 * @param anonymId
	 * @return
	 */
	public Integer getFabulousByAnonymId(@Param("storyId") String storyId,@Param("anonymId") String anonymId);

	/**
	 * 获取故事评论数量
	 * @param storyId
	 * @return
	 */
	public Integer getStoryCommentNum(@Param("storyId") String storyId);
	
	/**
	 * 获取用户发布的故事
	 * @param anonymId
	 * @return
	 */
	public List<Map<String, Object>> findStoryByAnonymId(@Param("anonymId") String anonymId);
	
}
