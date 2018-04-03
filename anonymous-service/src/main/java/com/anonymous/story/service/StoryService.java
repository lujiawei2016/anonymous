package com.anonymous.story.service;

/**
 * 故事service
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午4:41:39
 */
public interface StoryService {

	/**
	 * 查找最新故事
	 * @return
	 * @throws Exception
	 */
	public Object searchNewStory() throws Exception;

	/**
	 * 发布故事
	 * @param anonymId
	 * @param story_title
	 * @param story_article
	 * @param story_article_summary
	 * @return
	 * @throws Exception
	 */
	public Object release(String anonymId,String story_title,String story_article,String story_article_summary) throws Exception;

	/**
	 * 根据id查找故事
	 * @param storyId
	 * @param anonymId
	 * @return
	 * @throws Exception
	 */
	public Object findStoryById(String storyId,String anonymId) throws Exception;
	
	/**
	 * 分页查询故事
	 * @param anonymId
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public Object pagingSearchStory(String anonymId,String offset,String length) throws Exception;
}
