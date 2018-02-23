package com.anonymous.card.service;

/**
 * 卡片收藏service
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月23日上午11:55:53
 */
public interface CardCollectionService {

	/**
	 * 卡片收藏
	 * @param anonymId
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	public Object collection(String anonymId,String cardId) throws Exception;
}
