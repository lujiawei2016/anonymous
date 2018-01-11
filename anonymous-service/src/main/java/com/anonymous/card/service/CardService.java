package com.anonymous.card.service;

/**
 * 卡片
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月9日上午10:18:19
 */
public interface CardService {

	/**
	 * 发布卡片
	 * @param anonymId
	 * @param cardContent
	 * @param imgPath
	 * @return
	 * @throws Exception
	 */
	public Object release(String anonymId,String cardContent,String imgPath) throws Exception;
	
	/**
	 * 查出主页最新卡片信息
	 * @param anonymId
	 * @return
	 * @throws Exception
	 */
	public Object searchNewCard(String anonymId) throws Exception;
}
