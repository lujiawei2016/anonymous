package com.anonymous.message.service;

/**
 * 短信接口
 * @author  lujiawei
 * @version V1.0
 * @date    2018年1月5日下午1:58:01
 */
public interface MessageService {

	/**
	 * 发送登陆短信验证码
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public Object sendLoginMsg(String phone) throws Exception;
	
	/**
	 * 快捷登陆
	 * @param phone
	 * @param code
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	public Object quickLogin(String phone,String code,String deviceId) throws Exception;
}
