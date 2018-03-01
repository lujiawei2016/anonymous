package com.anonymous.story.service;

/**
 * 故事点赞service
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月1日下午4:47:22
 */
public interface StoryFabulousService {

	/**
	 * 故事点赞
	 * @param anonymId
	 * @param storyId
	 * @return
	 * @throws Exception
	 */
	public Object fabulous(String anonymId,String storyId) throws Exception;
}
