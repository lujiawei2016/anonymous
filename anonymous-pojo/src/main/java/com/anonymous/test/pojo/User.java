package com.anonymous.test.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 2149785594278623256L;

	private Integer userId;
	
	private String userName;
	
	private Integer userAge;
	
	private List<Article> articleList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public User(Integer userId, String userName, Integer userAge, List<Article> articleList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.articleList = articleList;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userAge=" + userAge + ", articleList="
				+ articleList + "]";
	}
	
}
