package com.anonymous.anonymous.dao;

import java.util.List;

import com.anonymous.anonym.pojo.Anonym;

public interface AnonymousDao {

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
	public void saveAnonym(Anonym anonym);
}
