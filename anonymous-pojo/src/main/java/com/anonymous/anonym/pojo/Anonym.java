package com.anonymous.anonym.pojo;

import java.util.Date;
import java.util.List;

import com.anonymous.card.pojo.Card;
import com.anonymous.card.pojo.CardCollectiion;
import com.anonymous.card.pojo.CardComment;
import com.anonymous.card.pojo.CardCommentFabulous;
import com.anonymous.card.pojo.CardFabulous;
import com.anonymous.story.pojo.Story;
import com.anonymous.story.pojo.StoryCollection;
import com.anonymous.story.pojo.StoryComment;
import com.anonymous.story.pojo.StoryCommentFabulous;
import com.anonymous.story.pojo.StoryFabulous;

/**
 * 用户表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:52:12
 */
public class Anonym {
	
	private String anonymId;                                              // 主键
	
	private String nickName;                                              // 昵称
	
	private String userName;                                              // 账号
	
	private String password;                                              // 密码
	
	private String headerImg;                                             // 头像
	
	private String phone;                                                 // 手机号码
	
	private int isLock;                                                   // 是否锁定，1-未锁定，0-已锁定
	
	private String deviceId;                                              // 设备唯一标识
	
	private String lockReason;                                            // 锁定理由
	
	private int delFlag;                                                  // 删除标识，1-未删除，0-已删除
	
	private Date createTime;                                              // 创建时间
	
	private Date updateTime;                                              // 更新时间
	
	private List<StoryFabulous> storyFabulousList;                        // 故事点赞
	
	private List<StoryCommentFabulous> storyCommentFabulousList;          // 故事评论点赞
	
	private List<StoryCollection> storyCollectionList;                    // 收藏的故事
	
	private List<Story> storyList;                                        // 发表的故事
	
	private List<StoryComment> storyCommentList;                          // 评论的故事
	
	private List<CardCollectiion> cardCollectiionList;                    // 收藏的卡片
	
	private List<Card> cardList;                                          // 卡片
	
	private List<CardFabulous> cardFabulousList;                          // 卡片点赞表
	
	private List<CardComment> cardCommentList;                            // 卡片评论
	
	private List<CardCommentFabulous> cardCommentFabulousList;            // 卡片评论点赞

	public String getAnonymId() {
		return anonymId;
	}

	public void setAnonymId(String anonymId) {
		this.anonymId = anonymId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
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

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
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

	public List<StoryCommentFabulous> getStoryCommentFabulousList() {
		return storyCommentFabulousList;
	}

	public void setStoryCommentFabulousList(List<StoryCommentFabulous> storyCommentFabulousList) {
		this.storyCommentFabulousList = storyCommentFabulousList;
	}

	public List<StoryCollection> getStoryCollectionList() {
		return storyCollectionList;
	}

	public void setStoryCollectionList(List<StoryCollection> storyCollectionList) {
		this.storyCollectionList = storyCollectionList;
	}

	public List<Story> getStoryList() {
		return storyList;
	}

	public void setStoryList(List<Story> storyList) {
		this.storyList = storyList;
	}

	public List<StoryComment> getStoryCommentList() {
		return storyCommentList;
	}

	public void setStoryCommentList(List<StoryComment> storyCommentList) {
		this.storyCommentList = storyCommentList;
	}

	public List<CardCollectiion> getCardCollectiionList() {
		return cardCollectiionList;
	}

	public void setCardCollectiionList(List<CardCollectiion> cardCollectiionList) {
		this.cardCollectiionList = cardCollectiionList;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public List<CardFabulous> getCardFabulousList() {
		return cardFabulousList;
	}

	public void setCardFabulousList(List<CardFabulous> cardFabulousList) {
		this.cardFabulousList = cardFabulousList;
	}

	public List<CardComment> getCardCommentList() {
		return cardCommentList;
	}

	public void setCardCommentList(List<CardComment> cardCommentList) {
		this.cardCommentList = cardCommentList;
	}

	public List<CardCommentFabulous> getCardCommentFabulousList() {
		return cardCommentFabulousList;
	}

	public void setCardCommentFabulousList(List<CardCommentFabulous> cardCommentFabulousList) {
		this.cardCommentFabulousList = cardCommentFabulousList;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Anonym() {
		super();
	}

	public Anonym(String anonymId, String nickName, String userName, String headerImg, String phone, String deviceId,
			Date createTime, Date updateTime) {
		super();
		this.anonymId = anonymId;
		this.nickName = nickName;
		this.userName = userName;
		this.headerImg = headerImg;
		this.phone = phone;
		this.deviceId = deviceId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
}
