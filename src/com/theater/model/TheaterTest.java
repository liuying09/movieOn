package com.theater.model;

import java.util.List;

import com.area.model.AreaJDBCDAO;
import com.area.model.AreaVO;

public class TheaterTest {
public static void main(String[] args) {
		
		TheaterJDBCDAO dao = new TheaterJDBCDAO();
		
		//�s�W
//		TheaterVO theaterVO1 = new TheaterVO();
//		theaterVO1.setAreaNum(6);
//		theaterVO1.setTheaterName("�ߺ��ȮɥN�v��");
//		dao.insert(theaterVO1);
		
		//�ק�
//		TheaterVO theaterVO2 = new TheaterVO();
//		theaterVO2.setAreaNum(6);
//		theaterVO2.setTheaterName("��ŹD�껫�v��");
//		theaterVO2.setTheaterId(17);
//		
//		dao.update(theaterVO2);
//		
//		//�R��
//		dao.delete(17);
		
		//�d�߳��	
		TheaterVO theaterVO3 = dao.findByPrimaryKey(1);
		System.out.println(theaterVO3.getTheaterId());
		System.out.println(theaterVO3.getAreaNum());
		System.out.println(theaterVO3.getTheaterName());
		
		System.out.println("=====================");
		
		//�d�ߥ���
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
