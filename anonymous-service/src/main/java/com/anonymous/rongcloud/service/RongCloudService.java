package com.anonymous.rongcloud.service;

/**
 * 融云service
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月8日上午10:02:58
 */
public interface RongCloudService {

	/**
	 * 获取token
	 * @param anonymId
	 * @return
	 */
	public Object getToken(String anonymId) throws Exception;
}
