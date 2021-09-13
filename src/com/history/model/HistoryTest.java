package com.history.model;

import java.util.List;



public class HistoryTest {

	public static void main(String[] args) {
		
		HistoryJDBCDAO dao = new HistoryJDBCDAO();
		
		//新增
//		HistoryVO historyVO1 = new HistoryVO();
//		historyVO1.setUserId(2);
//		historyVO1.setShowingId(4);
//		dao.insert(historyVO1);
		
		//修改
//		HistoryVO historyVO2 = new HistoryVO();
//		historyVO2.setHistoryId(13);
//		historyVO2.setUserId(1);
//		historyVO2.setShowingId(2);	
//		dao.update(historyVO2);
//		
//		//刪除
		dao.delete(5,3);
		
		//查詢單個	
		HistoryVO historyVO3 = dao.findByPrimaryKey(1);
		System.out.println(historyVO3.getHistoryId());
		System.out.println(historyVO3.getUserId());
		System.out.println(historyVO3.getShowingId());
		
		System.out.println("=====================");
		
		//查詢全部
		List<HistoryVO> list = dao.getAll();
		for(HistoryVO historyVO : list) {
			System.out.print(historyVO.getHistoryId() + ",");
			System.out.print(historyVO.getUserId() + ",");
			System.out.print(historyVO.getShowingId());
			System.out.println();
		}
	}
}
