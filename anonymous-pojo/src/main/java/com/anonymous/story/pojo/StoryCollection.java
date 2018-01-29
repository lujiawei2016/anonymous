package com.anonymous.story.pojo;

import java.io.Serializable;
import java.util.Date;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 故事收藏表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:57:31
 */
public class StoryCollection implements Serializable {
	
	private static final long serialVersionUID = -2785694547117251928L;

	private String storyCollectiionId;          // 主键
	
	private Story story;                        // 收藏的故事
	
	private Anonym anonym;                      // 收藏的用户
	
	private Date createTime;                    // 创建时间
	
	private Date updateTime;                    // 更新时间

	public String getStoryCollectiionId() {
		return storyCollectiionId;
	}

	public void setStoryCollectiionId(String storyCollectiionId) {
		this.storyCollectiionId = storyCollectiionId;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
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
