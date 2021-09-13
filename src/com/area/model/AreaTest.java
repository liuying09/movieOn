package com.area.model;

import java.util.List;

public class AreaTest {
	public static void main(String[] args) {
		
		AreaJDBCDAO dao = new AreaJDBCDAO();
		
		//新增
//		AreaVO areaVO1 = new AreaVO();
//		areaVO1.setCity("桃園市");
//		dao.insert(areaVO1);
		
		//修改
//		AreaVO areaVO2 = new AreaVO();
//		areaVO2.setCity("新竹市");
//		areaVO2.setAreaNum(10);
		
//		dao.update(areaVO2);
		
		//刪除
//		dao.delete(10);
		
		//查詢單個	
		AreaVO areaVO3 = dao.findByPrimaryKey(1);
		System.out.println(areaVO3.getAreaNum());
		System.out.println(areaVO3.getCity());
		
		System.out.println("=====================");
		
		//查詢全部
		List<AreaVO> list = dao.getAll();
		for(AreaVO areaVO : list) {
			System.out.print(areaVO.getAreaNum()  + ",");
			System.out.print(areaVO.getCity());
			System.out.println();
		}
		
		
	}
}
