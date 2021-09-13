package com.history.model;

public class HistoryVO {
	
	private Integer historyId;
	private Integer userId;
	private Integer showingId;
	
	public HistoryVO() {
		super();

	}
	public HistoryVO(Integer historyId, Integer userId, Integer showingId) {
		super();
		this.historyId = historyId;
		this.userId = userId;
		this.showingId = showingId;
	}
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShowingId() {
		return showingId;
	}
	public void setShowingId(Integer showingId) {
		this.showingId = showingId;
	}
	
	

}
