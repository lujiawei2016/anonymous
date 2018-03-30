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
	
	/**
	 * 保存头像
	 * @param headerImg
	 * @param anonymId
	 * @return
	 * @throws Exception
	 */
	public Object saveHeadImg(String headerImg,String anonymId) throws Exception;
	
	/**
	 * 保存背景图片
	 * @param headerImg
	 * @param anonymId
	 * @return
	 * @throws Exception
	 */
	public Object saveBackgroundImg(String backgroundImg,String anonymId) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param anonymId
	 * @param nickName
	 * @param personalSignature
	 * @param sex
	 * @return
	 * @throws Exception
	 */
	public Object updateMe(String anonymId,String nickName,String personalSignature,String sex) throws Exception;
	
}
