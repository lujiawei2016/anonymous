package com.anonymous.card.service;

/**
 * 卡片评论
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日下午4:11:01
 */
public interface CardFabulousService {

	/**
	 * 卡片点赞
	 * @param anonymId
	 * @param cardCommentId
	 * @return
	 * @throws Exception
	 */
	public Object fabulous(String anonymId, String cardId) throws Exception;
}
