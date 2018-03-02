package com.anonymous.story.service;

/**
 * 故事收藏接口
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月2日上午9:53:06
 */
public interface StoryCollectionService {

	/**
	 * 故事收藏
	 * @param storyId
	 * @param anonymId
	 * @return
	 * @throws Exception
	 */
	public Object collection(String storyId,String anonymId) throws Exception;
}
