package com.anonymous.story.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 故事表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:57:58
 */
public class Story implements Serializable {
	
	private static final long serialVersionUID = -4017627680895783953L;

	private String storyId;                                            // 主键
	
	private String storyTitle;                                         // 标题
	
	private String storyContent;                                       // 内容
	
	private Anonym anonym;                                             // 作者（即用户）
	
	private int isLock;                                                // 是否锁定，1-未锁定，0-已锁定
	
	private String lockReason;                                         // 锁定理由
	
	private Date createTime;                                           // 创建时间
	
	private Date updateTime;                                           // 更新时间
	
	private List<StoryFabulous> storyFabulousList;                     // 故事点赞
	
	private List<StoryCollection> storyCollectionList;                 // 故事收藏
	
	private List<StoryComment> storyCommentList;                       // 故事的评论
	
	private int delFlag;                                               // 删除标识，1-未删除，0-已删除

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}

	public String getStoryContent() {
		return storyContent;
	}

	public void setStoryContent(String storyContent) {
		this.storyContent = storyContent;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}
	
	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public String getLockReason() {
		return lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
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

	public List<StoryFabulous> getStoryFabulousList() {
		return storyFabulousList;
	}

	public void setStoryFabulousList(List<StoryFabulous> storyFabulousList) {
		this.storyFabulousList = storyFabulousList;
	}

	public List<StoryCollection> getStoryCollectionList() {
		return storyCollectionList;
	}

	public void setStoryCollectionList(List<StoryCollection> storyCollectionList) {
		this.storyCollectionList = storyCollectionList;
	}

	public List<StoryComment> getStoryCommentList() {
		return storyCommentList;
	}

	public void setStoryCommentList(List<StoryComment> storyCommentList) {
		this.storyCommentList = storyCommentList;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
}
