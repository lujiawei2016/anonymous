package com.anonymous.anonymous.dao;

import java.util.List;

import com.anonymous.anonym.pojo.Anonym;

public interface AnonymousDao {
	
	/**
	 * 根据id查找用户
	 * @param anonymId
	 * @return
	 */
	public Anonym findAnonymById(String anonymId);

	/**
	 * 根据手机号码查找用户
	 * @param phone
	 * @return
	 */
	public List<Anonym> findAnonymByPhone(String phone);
	
	/**
	 * 保存用户
	 * @param anonym
	 */
	public void saveQuickAnonym(Anonym anonym);
	
	/**
	 * 更新用户
	 * @param anonym
	 */
	public void updateQuickAnonym(Anonym anonym);
	
	/**
	 * 根据主键查询用户信息
	 * @param anonymId
	 * @return
	 */
	public Anonym findAnonymInfoById(String anonymId);
}
