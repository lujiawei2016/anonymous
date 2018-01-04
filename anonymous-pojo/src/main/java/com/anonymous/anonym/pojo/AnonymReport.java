package com.anonymous.anonym.pojo;

import java.util.Date;

/**
 * 用户举报表
 * @author  lujiawei
 * @version V1.0
 * @date    2017年12月12日下午4:55:12
 */
public class AnonymReport {

	private String reportId;
	
	private String passiveAnonymId;      // 被举报用户id
	
	private String activeAnonymId;       // 举报用户id
	
	private String reportReason;         // 举报理由
	
	private int isHandle;                // 是否处理，1-已处理，0-未处理
	
	private String reportResult;         // 处理结果
	
	private Date repotHandleTime;        // 处理时间
	
	private Date createTime;             // 创建时间
	
	private Date updateTime;             // 更新时间

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getPassiveAnonymId() {
		return passiveAnonymId;
	}

	public void setPassiveAnonymId(String passiveAnonymId) {
		this.passiveAnonymId = passiveAnonymId;
	}

	public String getActiveAnonymId() {
		return activeAnonymId;
	}

	public void setActiveAnonymId(String activeAnonymId) {
		this.activeAnonymId = activeAnonymId;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public int getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(int isHandle) {
		this.isHandle = isHandle;
	}

	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	public Date getRepotHandleTime() {
		return repotHandleTime;
	}

	public void setRepotHandleTime(Date repotHandleTime) {
		this.repotHandleTime = repotHandleTime;
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
