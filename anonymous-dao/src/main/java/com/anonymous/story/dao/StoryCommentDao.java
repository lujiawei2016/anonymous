package com.anonymous.story.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anonymous.story.pojo.StoryComment;

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
	
	/**
	 * 查找评论
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> searchComment(Map<String, Object> map);
	
	/**
	 * 根据id查找评论
	 * @param storyCommentId
	 * @return
	 */
	public StoryComment findStoryCommentById(String storyCommentId);
	
	/**
	 * 判断是否已经点赞
	 * @param storyCommentId
	 * @param anonymId
	 * @return
	 */
	public Integer isFabulous(@Param("storyCommentId") String storyCommentId,@Param("anonymId") String anonymId);

	/**
	 * 为评论点赞
	 * @param map
	 */
	public void fabulous(HashMap<String, Object> map);
}
