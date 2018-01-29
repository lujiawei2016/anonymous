package com.anonymous.card.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 卡片评论表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午5:00:58
 */
public class CardComment implements Serializable {

	private static final long serialVersionUID = -9054702899829601916L;

	private String cardCommentId;                   // 主键
	
	private String cardCommentContent;              // 评论内容
	
	private Card card;                              // 卡片
	
	private Anonym anonym;                          // 用户
	
	private String carCommentReplyId;               // 回复评论id
	
	private int delFlag;                            // 删除标识，1-未删除，0-已删除
	
	private Date createTime;                        // 创建时间
	
	private Date updateTime;                        // 更新时间
	
	private List<CardCommentFabulous> cardCommentFabulousList;         // 点赞

	public String getCardCommentId() {
		return cardCommentId;
	}

	public void setCardCommentId(String cardCommentId) {
		this.cardCommentId = cardCommentId;
	}

	public String getCardCommentContent() {
		return cardCommentContent;
	}

	public void setCardCommentContent(String cardCommentContent) {
		this.cardCommentContent = cardCommentContent;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}

	public String getCarCommentReplyId() {
		return carCommentReplyId;
	}

	public void setCarCommentReplyId(String carCommentReplyId) {
		this.carCommentReplyId = carCommentReplyId;
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

	public List<CardCommentFabulous> getCardCommentFabulousList() {
		return cardCommentFabulousList;
	}

	public void setCardCommentFabulousList(List<CardCommentFabulous> cardCommentFabulousList) {
		this.cardCommentFabulousList = cardCommentFabulousList;
	}
	
}
