package com.anonymous.story.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 故事评论表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:58:52
 */
public class StoryComment implements Serializable {
	
	private static final long serialVersionUID = -8747962897465109737L;

	private String storyCommentId;                                    // 主键
	
	private String storyCommentContent;                               // 评论内容
	
	private Anonym anonym;                                            // 评论的用户
	
	private Story story;                                              // 评论的故事
	
	private String styroReplyCommentId;                               // 回复故事的id，不是回复的评论为null
	
	private Date createTime;                                          // 创建时间
	
	private Date updateTime;                                          // 更新时间
	
	private int delFlag;                                              // 删除标识，1-未删除，0-未删除
	
	private List<StoryCommentFabulous> storyCommentFabulousList;      // 点赞

	public String getStoryCommentId() {
		return storyCommentId;
	}

	public void setStoryCommentId(String storyCommentId) {
		this.storyCommentId = storyCommentId;
	}

	public String getStoryCommentContent() {
		return storyCommentContent;
	}

	public void setStoryCommentContent(String storyCommentContent) {
		this.storyCommentContent = storyCommentContent;
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

	public String getStyroReplyCommentId() {
		return styroReplyCommentId;
	}

	public void setStyroReplyCommentId(String styroReplyCommentId) {
		this.styroReplyCommentId = styroReplyCommentId;
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

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public List<StoryCommentFabulous> getStoryCommentFabulousList() {
		return storyCommentFabulousList;
	}

	public void setStoryCommentFabulousList(List<StoryCommentFabulous> storyCommentFabulousList) {
		this.storyCommentFabulousList = storyCommentFabulousList;
	}
	
}
