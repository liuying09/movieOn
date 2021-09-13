package com.area.model;

import java.util.List;

public class AreaService {

	private AreaDAO_interface dao;
	
	public AreaService(){
		dao = new AreaJDBCDAO();
	}
	
	public AreaVO addArea(String city) {
		
		AreaVO areaVO = new AreaVO();
	
		areaVO.setCity(city);
		dao.insert(areaVO);
		
		return areaVO;
	}
	
	public AreaVO updateArea(String city) {
		
		AreaVO areaVO = new AreaVO();
		
		areaVO.setCity(city);
		dao.update(areaVO);
		
		return areaVO;
	}
	
	public void deleteAreaVO(Integer areaNum) {
		
		AreaVO areaVO = new AreaVO();
		areaVO.setAreaNum(areaNum);
		dao.delete(areaNum);
		
	}
	public AreaVO getOneArea(Integer areaNum) {
		
		return dao.findByPrimaryKey(areaNum);
	}
	
	public List<AreaVO> getAll() {
		
		return dao.getAll();
	}
}
