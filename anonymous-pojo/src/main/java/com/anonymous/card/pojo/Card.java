package com.anonymous.card.pojo;

import java.util.Date;
import java.util.List;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 卡片表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午5:00:05
 */
public class Card {
	
	private String cardId;                                      // 主键
	
	private String cardContent;                                 // 卡片内容
	
	private String cardImg;                                     // 卡片图片
	
	private int isLock;                                         // 是否锁定，1-未锁定，0-已锁定
	
	private String lockReason;                                  // 锁定理由
	
	private Date createTime;                                    // 创建时间
	
	private Date updateTime;                                    // 更新时间
	
	private int delFlag;                                        // 删除标识，1-未删除，0-已删除
	
	private Anonym anonym;                                      // 用户

	private List<CardCollectiion> cardCollectiionList;          // 收藏的卡片
	
	private List<CardFabulous> cardFabulousList;                // 卡片点赞
	
	private List<CardComment> cardCommentList;                  // 卡片评论

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}

	public String getCardContent() {
		return cardContent;
	}

	public void setCardContent(String cardContent) {
		this.cardContent = cardContent;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
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

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public List<CardCollectiion> getCardCollectiionList() {
		return cardCollectiionList;
	}

	public void setCardCollectiionList(List<CardCollectiion> cardCollectiionList) {
		this.cardCollectiionList = cardCollectiionList;
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

	public Card() {
		super();
	}

	public Card(String cardId, String cardContent, String cardImg, Date createTime, Date updateTime, Anonym anonym) {
		super();
		this.cardId = cardId;
		this.cardContent = cardContent;
		this.cardImg = cardImg;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.anonym = anonym;
	}
	
}
