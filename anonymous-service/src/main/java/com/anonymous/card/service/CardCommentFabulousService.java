package com.anonymous.card.service;

/**
 * 评论点赞
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月9日上午9:59:37
 */
public interface CardCommentFabulousService {

	/**
	 * 用户点赞评论
	 * @param anonymId       用户id
	 * @param cardCommentId  评论id
	 * @return
	 * @throws Exception
	 */
	public Object fabulous(String anonymId,String cardCommentId) throws Exception;
}
