package com.theater.model;

import java.util.List;

public interface TheaterDAO_interface {
	
	public void insert(TheaterVO theaterVO);
	public void update(TheaterVO theaterVO);
	public void delete(Integer theaterId);
	public TheaterVO findByPrimaryKey(Integer theaterId);
	public List<TheaterVO> getAll();
	public List<TheaterVO> getByArea(Integer areaNum);

}
