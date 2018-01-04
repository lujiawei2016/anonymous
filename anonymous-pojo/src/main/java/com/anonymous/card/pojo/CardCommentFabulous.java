package com.anonymous.card.pojo;

import java.util.Date;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 卡片评论点赞表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午5:01:21
 */
public class CardCommentFabulous {

	private String cardCommentFabulousId;                 // 主键
	
	private Anonym anonym;                                // 用户
	
	private CardComment cardComment;                      // 评论
	
	private Date createTime;                              // 创建时间
	
	private Date updateTime;                              // 更新时间

	public String getCardCommentFabulousId() {
		return cardCommentFabulousId;
	}

	public void setCardCommentFabulousId(String cardCommentFabulousId) {
		this.cardCommentFabulousId = cardCommentFabulousId;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}

	public CardComment getCardComment() {
		return cardComment;
	}

	public void setCardComment(CardComment cardComment) {
		this.cardComment = cardComment;
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
