package com.theater.model;

import java.util.List;

import com.area.model.AreaJDBCDAO;
import com.area.model.AreaVO;

public class TheaterTest {
public static void main(String[] args) {
		
		TheaterJDBCDAO dao = new TheaterJDBCDAO();
		
		//新增
//		TheaterVO theaterVO1 = new TheaterVO();
//		theaterVO1.setAreaNum(6);
//		theaterVO1.setTheaterName("喜滿客時代影城");
//		dao.insert(theaterVO1);
		
		//修改
//		TheaterVO theaterVO2 = new TheaterVO();
//		theaterVO2.setAreaNum(6);
//		theaterVO2.setTheaterName("草衙道國賓影城");
//		theaterVO2.setTheaterId(17);
//		
//		dao.update(theaterVO2);
//		
//		//刪除
//		dao.delete(17);
		
		//查詢單個	
		TheaterVO theaterVO3 = dao.findByPrimaryKey(1);
		System.out.println(theaterVO3.getTheaterId());
		System.out.println(theaterVO3.getAreaNum());
		System.out.println(theaterVO3.getTheaterName());
		
		System.out.println("=====================");
		
		//查詢全部
//		List<TheaterVO> list = dao.getAll();
//		for(TheaterVO theaterVO : list) {
//			System.out.print(theaterVO.getTheaterId() + ",");
//			System.out.print(theaterVO.getAreaNum()  + ",");
//			System.out.print(theaterVO.getTheaterName());
//			System.out.println();
//		}
		
		
		
		List<TheaterVO> list = dao.getByArea(2);
		for(TheaterVO theaterVO : list) {
			System.out.print(theaterVO.getTheaterId() + ",");
			System.out.print(theaterVO.getAreaNum()  + ",");
			System.out.print(theaterVO.getTheaterName());
			System.out.println();
		}
		
		
		
	}

}
