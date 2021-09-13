package com.theater.model;

import java.util.List;

public class TheaterService {
	
	private TheaterDAO_interface dao;
	
	public TheaterService() {
		dao = new TheaterJDBCDAO();
	}
	
	public TheaterVO addTheater(Integer areaNum, String theaterName) {
		
		TheaterVO theaterVO = new TheaterVO();
		theaterVO.setAreaNum(areaNum);
		theaterVO.setTheaterName(theaterName);
		dao.insert(theaterVO);
		
		return theaterVO;
	}
	
	public TheaterVO updateTheater(Integer areaNum, String theaterName) {
		
		TheaterVO theaterVO = new TheaterVO();
		theaterVO.setAreaNum(areaNum);
		theaterVO.setTheaterName(theaterName);
		dao.update(theaterVO);
		
		return theaterVO;
		
	}
	
	public void deleteTheater(Integer theaterId) {
		TheaterVO theaterVO = new TheaterVO();
		theaterVO.setTheaterId(theaterId);
		dao.delete(theaterId);
		
	}
	
	public TheaterVO getOneTheater(Integer theaterId) {
		return dao.findByPrimaryKey(theaterId);
	}
	
	public List<TheaterVO> getAll(){
		return dao.getAll();
	}
	
	public List<TheaterVO> getByArea(Integer areaNum){
		return dao.getByArea(areaNum);
	}
	

}
