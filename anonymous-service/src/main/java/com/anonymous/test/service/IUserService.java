package com.anonymous.test.service;

import com.anonymous.test.pojo.Article;
import com.anonymous.test.pojo.User;

public interface IUserService {

	public User getUserById(int id);
	
	public User getUserAndArticleByUserId(Integer userId);
	
	public Article getArticleById(Integer articleId);
	
	public void setRedis(User user);
	
	public Object getRedis(String key);
}
