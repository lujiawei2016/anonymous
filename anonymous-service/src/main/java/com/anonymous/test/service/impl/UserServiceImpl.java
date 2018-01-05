package com.anonymous.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonymous.redis.utils.RedisUtils;
import com.anonymous.test.dao.IUserDao;
import com.anonymous.test.pojo.Article;
import com.anonymous.test.pojo.User;
import com.anonymous.test.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public User getUserById(int id) {
		User user = userDao.getUserById(id);
		return user;
	}

	@Override
	public User getUserAndArticleByUserId(Integer userId) {
		User user = userDao.getUserAndArticleByUserId(userId);
		return user;
	}

	@Override
	public Article getArticleById(Integer articleId) {
		Article article = userDao.getArticleById(1);
		return article;
	}

	@Override
	public void setRedis(User user) {
		redisUtils.put("user", user);
	}

	@Override
	public Object getRedis(String key) {
		return null;
	}

}
