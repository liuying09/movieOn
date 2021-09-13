package com.showing.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.sql.Date;

public class ShowingService {
	
	private ShowingDAO_interface dao;

	public ShowingService() {
		dao = new ShowingJDBCDAO();
	}
	
	public ShowingVO addShowing(Integer theaterId, Integer movieId,Date showingDate,Time showingTime) {
		
		ShowingVO showingVO = new ShowingVO();
		showingVO.setTheaterId(theaterId);
		showingVO.setMovieId(movieId);
		showingVO.setShowingDate(showingDate);
		showingVO.setShowingTime(showingTime);
		dao.insert(showingVO);
		
		return showingVO;
	}
	
	public ShowingVO updaShowing(Integer theaterId, Integer movieId,Date showingDate,Time showingTime,Integer showingId) {
		
		ShowingVO showingVO = new ShowingVO();
		showingVO.setTheaterId(theaterId);
		showingVO.setMovieId(movieId);
		showingVO.setShowingDate(showingDate);
		showingVO.setShowingTime(showingTime);
		showingVO.setShowingId(showingId);
		dao.update(showingVO);
		
		return showingVO;
	}
	
	public void deleteShowing(Integer showingId) {
		ShowingVO showingVO = new ShowingVO();
		showingVO.setShowingId(showingId);
		dao.delete(showingId);
	}
	
	public ShowingVO getOneShowing(Integer showingId) {
		return dao.findByPrimaryKey(showingId);
	}
	
	public List<ShowingVO> getAll(){
		return dao.getAll();
	}
	
	
	public List<ShowingVO> getByTheaterAndMovie(Integer theaterId, Integer movieId){
		return dao.getByTheaterAndMovie(theaterId, movieId);
	}
	
	
	public List<ShowingVO> getByTheater(Integer theaterId){
		return dao.getByTheater(theaterId);
	}
	
	public List<ShowingVO> getShowingTime(Integer theaterId, Integer movieId,Date showingDate ){
		return dao.getShowingTime(theaterId, movieId, showingDate);
	}
	
	public List<ShowingVO> getByShowingId(Integer showingId){
		return dao.getByShowingId(showingId);
	}
	
	

}
