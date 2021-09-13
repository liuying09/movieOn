package com.history.model;

import java.util.List;

public interface HistoryDAO_interface {
	
	public void insert(HistoryVO historyVO);
	public void update(HistoryVO historyVO);
	public void delete(Integer userId,Integer showingId);
	public HistoryVO findByPrimaryKey(Integer historyId);
	public List<HistoryVO> getAll();
	public List<HistoryVO> getByUderId(Integer userId);

}
