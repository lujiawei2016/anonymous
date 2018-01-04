package com.anonymous.test.dao;

import com.anonymous.test.pojo.Article;
import com.anonymous.test.pojo.User;

public interface IUserDao {

	public User getUserById(int userId);
	
	public User getUserAndArticleByUserId(Integer userId);
	
	public Article getArticleById(Integer articleId);
	
}
