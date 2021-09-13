package com.history.model;

import java.util.List;

public class HistoryService {
	
	private HistoryDAO_interface dao;
	
	public HistoryService() {
		dao = new HistoryJDBCDAO();
	}
	
	
	public HistoryVO addHistory(Integer userId,Integer showingId) {
		
		HistoryVO historyVO = new HistoryVO();
		historyVO.setUserId(userId);
		historyVO.setShowingId(showingId);
		dao.insert(historyVO);
		return historyVO;
	}
	
	public HistoryVO updateHistory(Integer historyId) {
		HistoryVO historyVO = new HistoryVO();
		historyVO.setHistoryId(historyId);
		dao.update(historyVO);
		return historyVO;
	}
	
	public void deleteHistory(Integer userId,Integer showingId) {
		HistoryVO historyVO = new HistoryVO();
		historyVO.setUserId(userId);
		historyVO.setShowingId(showingId);
		dao.delete(userId,showingId);
	}
	
	public List<HistoryVO> getAll(){
		return dao.getAll();
	}
	
	public List<HistoryVO> getByUser(Integer userId){
		return dao.getByUderId(userId);
	}

}
