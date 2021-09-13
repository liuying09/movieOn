package com.area.model;

import java.util.List;

public class AreaTest {
	public static void main(String[] args) {
		
		AreaJDBCDAO dao = new AreaJDBCDAO();
		
		//�s�W
//		AreaVO areaVO1 = new AreaVO();
//		areaVO1.setCity("��饫");
//		dao.insert(areaVO1);
		
		//�ק�
//		AreaVO areaVO2 = new AreaVO();
//		areaVO2.setCity("�s�˥�");
//		areaVO2.setAreaNum(10);
		
//		dao.update(areaVO2);
		
		//�R��
//		dao.delete(10);
		
		//�d�߳��	
		AreaVO areaVO3 = dao.findByPrimaryKey(1);
		System.out.println(areaVO3.getAreaNum());
		System.out.println(areaVO3.getCity());
		
		System.out.println("=====================");
		
		//�d�ߥ���
		List<AreaVO> list = dao.getAll();
		for(AreaVO areaVO : list) {
			System.out.print(areaVO.getAreaNum()  + ",");
			System.out.print(areaVO.getCity());
			System.out.println();
		}
		
		
	}
}
