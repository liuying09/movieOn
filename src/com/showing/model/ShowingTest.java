package com.showing.model;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ShowingTest {
public static void main(String[] args) {
		
		ShowingJDBCDAO dao = new ShowingJDBCDAO();
		
		Calendar cal = new GregorianCalendar(2021, Calendar.AUGUST, 26, 14, 10, 0);
		java.util.Date date = (java.util.Date)cal.getTime();
		long long1 = date.getTime();
		java.sql.Date date2 = new java.sql.Date(long1);
		Time time = new Time(long1);
		
		
		//新增
//		ShowingVO showingVO1 = new ShowingVO();
//		showingVO1.setTheaterId(2);
//		showingVO1.setMovieId(1);
//		showingVO1.setShowingDate(date2);
//		showingVO1.setShowingTime(time);
//		dao.insert(showingVO1);
		
		//修改
//		ShowingVO showingVO2 = new ShowingVO();
//		showingVO2.setTheaterId(3);
//		showingVO2.setMovieId(3);
//		showingVO2.setShowingDate(date2);
//		showingVO2.setShowingTime(time);
//		showingVO2.setShowingId(28);
//		
//		dao.update(showingVO2);
//		
//		//刪除
//		dao.delete(28);
		
		//查詢單個	
//		ShowingVO showingVO3 = dao.findByPrimaryKey(1);
//		System.out.println(showingVO3.getShowingId());
//		System.out.println(showingVO3.getTheaterId());
//		System.out.println(showingVO3.getMovieId());
//		System.out.println(showingVO3.getShowingDate());
//		System.out.println(showingVO3.getShowingTime());
		
		System.out.println("=====================");
		
		//查詢全部
//		List<ShowingVO> list = dao.getAll();
//		for(ShowingVO showingVO : list) {
//			System.out.print(showingVO.getShowingId() + ",");
//			System.out.print(showingVO.getTheaterId()  + ",");
//			System.out.print(showingVO.getMovieId() + ",");
//			System.out.println(showingVO.getShowingDate() + ",");
//			System.out.println(showingVO.getShowingTime());
//			System.out.println();
//		}
		
		
//		List<ShowingVO> list = dao.getByTheaterAndMovie(1,1001);
//		for(ShowingVO showingVO : list) {
//			System.out.println(showingVO.getShowingDate() + ",");
//			System.out.println(showingVO.getShowingTime());
//			System.out.println();
//		}
		
		
//		List<ShowingVO> list = dao.getByTheater(1);
//		for(ShowingVO showingVO : list) {
//			System.out.print(showingVO.getShowingId() + ",");
//			System.out.print(showingVO.getTheaterId()  + ",");
//			System.out.print(showingVO.getMovieId() + ",");
//			System.out.println(showingVO.getShowingDate() + ",");
//			System.out.println(showingVO.getShowingTime());
//			System.out.println();
//		}
		
		

		
		
		List<ShowingVO> list = dao.getShowingTime(1, 1001, date2);
		for(ShowingVO showingVO : list) {
			System.out.print(showingVO.getShowingId() + ",");
			System.out.print(showingVO.getTheaterId()  + ",");
			System.out.print(showingVO.getMovieId() + ",");
			System.out.println(showingVO.getShowingDate() + ",");
			System.out.println(showingVO.getShowingTime());
			System.out.println();
		}

	}
}
