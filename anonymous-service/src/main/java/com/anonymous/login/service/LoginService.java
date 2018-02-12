package com.anonymous.login.service;

/**
 * 登陆接口
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月12日上午10:01:26
 */
public interface LoginService {

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
