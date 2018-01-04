package com.anonymous.story.pojo;

import java.util.Date;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 故事点赞表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:56:11
 */
public class StoryFabulous {

	private String storyFabulousId;          // 主键
	
	private Anonym anonym;                   // 点赞的用户
	
	private Story story;                     // 点赞的故事
	
	private Date createTime;                 // 创建时间
	
	private Date updateTime;                 // 更新时间

	public String getStoryFabulousId() {
		return storyFabulousId;
	}

	public void setStoryFabulousId(String storyFabulousId) {
		this.storyFabulousId = storyFabulousId;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
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
