package com.anonymous.card.pojo;

import java.util.Date;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 卡片点赞表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午5:00:35
 */
public class CardFabulous {

	private String cardFabulousId;                   // 主键
	
	private Anonym anonym;                           // 用户
	
	private Card card;                               // 卡片
	
	private Date createTime;                         // 创建时间
	
	private Date updateTime;                         // 更新时间

	public String getCardFabulousId() {
		return cardFabulousId;
	}

	public void setCardFabulousId(String cardFabulousId) {
		this.cardFabulousId = cardFabulousId;
	}

	public Anonym getAnonym() {
		return anonym;
	}

	public void setAnonym(Anonym anonym) {
		this.anonym = anonym;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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
