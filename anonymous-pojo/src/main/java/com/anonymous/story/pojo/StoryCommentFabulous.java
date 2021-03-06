package com.anonymous.story.pojo;

import java.io.Serializable;
import java.util.Date;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 故事评论点赞表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:56:44
 */
public class StoryCommentFabulous implements Serializable {

	private static final long serialVersionUID = 6939317200495581758L;

	private String StoryCommentFabulousId;
	
	private Anonym anonym;                         // 点赞的用户
	
	private StoryComment storyComment;             // 点赞的故事
	
	private Date createTime;                       // 创建时间
	
	private Date updateTime;                       // 更新时间

	public String getStoryCommentFabulousId() {
		return StoryCommentFabulousId;
	}

	public void setStoryCommentFabulousId(String storyCommentFabulousId) {
		StoryCommentFabulousId = storyCommentFabulousId;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}

	public StoryComment getStoryComment() {
		return storyComment;
	}

	public void setStoryComment(StoryComment storyComment) {
		this.storyComment = storyComment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
