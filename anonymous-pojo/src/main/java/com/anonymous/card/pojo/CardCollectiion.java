package com.anonymous.card.pojo;

import java.io.Serializable;
import java.util.Date;

import com.anonymous.anonym.pojo.Anonym;

/**
 * 卡片收藏表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:59:45
 */
public class CardCollectiion implements Serializable {

	private static final long serialVersionUID = -3076040469404017166L;

	private String cardCollectiionId;                  // 主键
	
	private Card card;                                 // 收藏的卡片
	
	private Anonym anonym;                             // 收藏的用户
	
	private Date createTime;                           // 创建时间
	
	private Date updateTime;                           // 更新时间

	public String getCardCollectiionId() {
		return cardCollectiionId;
	}

	public void setCardCollectiionId(String cardCollectiionId) {
		this.cardCollectiionId = cardCollectiionId;
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
