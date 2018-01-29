package com.anonymous.card.service;

/**
 * 卡片评论接口
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月26日上午11:14:55
 */
public interface CardCommentService {

	/**
	 * 获取卡片评论内容
	 * @param anonymId
	 * @param cardId
	 * @param offset    从第offset开始
	 * @param length    查找length条
	 * @return
	 * @throws Exception
	 */
	public Object getCardComment(String anonymId,String cardId,String offset,String length) throws Exception;
	
	/**
	 * 保存评论内容
	 * @param anonymId               评论用户
	 * @param cardId                 评论卡片
	 * @param cardCommentContent     评论内容
	 * @param carCommentReplyId      回复评论用户id
	 * @throws Exception
	 */
	public Object saveCardComment(String anonymId,String cardId,
			String cardCommentContent,String carCommentReplyId) throws Exception;
}
