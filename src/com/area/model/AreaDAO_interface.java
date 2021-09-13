package com.area.model;

import java.util.List;

public interface AreaDAO_interface {
	
	public void insert(AreaVO area);
	public void update(AreaVO area);
	public void delete(Integer areaNum);
	public AreaVO findByPrimaryKey(Integer areaNum);
	public List<AreaVO> getAll();

}
