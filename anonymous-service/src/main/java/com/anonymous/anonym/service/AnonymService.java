package com.anonymous.anonym.service;

/**
 * 用户service
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日上午10:46:07
 */
public interface AnonymService {

	/**
	 * 判断该用户是否存在
	 * @param anonymId
	 * @param password
	 * @return
	 */
	public boolean judgeAnonym(String anonymId,String password);
	
	/**
	 * 获取用户信息
	 * @param anonymId
	 * @return
	 * @throws Exception
	 */
	public Object getAnonymInfoById(String anonymId) throws Exception;
	
}
