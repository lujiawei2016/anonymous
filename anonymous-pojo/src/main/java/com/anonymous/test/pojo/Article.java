package com.anonymous.test.pojo;

import java.io.Serializable;

public class Article implements Serializable {

	private static final long serialVersionUID = -4129527781726519162L;

	private Integer articleId;
	
	private String articleContent;
	
	private User user;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleContent=" + articleContent + ", user=" + user + "]";
	}

}
