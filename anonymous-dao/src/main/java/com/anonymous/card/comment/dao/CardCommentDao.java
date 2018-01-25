package com.anonymous.card.comment.dao;

import java.util.List;
import java.util.Map;

public interface CardCommentDao {

	/**
	 * 获取卡片评论
	 * @param anonymId
	 * @param cardId
	 * @return
	 */
	public List<Map<String, Object>> getCardComment(Map<String, Object> commentMap);
}
