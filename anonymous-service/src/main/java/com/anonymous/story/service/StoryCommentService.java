package com.anonymous.story.service;

/**
 * 故事评论
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月2日下午3:47:10
 */
public interface StoryCommentService {

	/**
	 * 评论故事
	 * @param anonymId
	 * @param styroReplyCommentId  回复的用户
	 * @param storyId
	 * @param commentContent
	 * @return
	 * @throws Exception
	 */
	public Object comment(String anonymId,String storyReplyCommentId,String storyId,String commentContent) throws Exception;
	
	/**
	 * 
	 * @param storyId
	 * @param anonymId
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public Object searchComment(String storyId,String anonymId,int offset,int length) throws Exception;
	
	/**
	 * 故事评论点赞
	 * @param storyCommentId
	 * @param anonymId
	 * @return
	 */
	public Object storyCommentFabulous(String storyCommentId,String anonymId);
}
