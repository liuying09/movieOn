package com.showing.model;

import java.sql.Date;
import java.util.List;

public interface ShowingDAO_interface {
	
	public void insert(ShowingVO showingVO);
	public void update(ShowingVO showingVO);
	public void delete(Integer showingId);
	public ShowingVO findByPrimaryKey(Integer showingId);
	public List<ShowingVO> getByTheaterAndMovie(Integer theaterId, Integer movieId);
	public List<ShowingVO> getAll();
	public List<ShowingVO> getByTheater(Integer theaterId);
	public List<ShowingVO> getShowingTime(Integer theaterId,Integer movieId,Date showingDate);
	public List<ShowingVO> getByShowingId(Integer showingId);


}
