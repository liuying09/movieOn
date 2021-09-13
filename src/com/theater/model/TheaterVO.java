package com.theater.model;

import java.io.Serializable;

public class TheaterVO implements Serializable {
	
	private Integer theaterId;
	private Integer areaNum;
	private String theaterName;
	
	public TheaterVO() {
		super();
	}
	
	public TheaterVO(Integer theaterId, Integer areaNum, String theaterName) {
		super();
		this.theaterId = theaterId;
		this.areaNum = areaNum;
		this.theaterName = theaterName;
	}
	public Integer getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}
	public Integer getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(Integer areaNum) {
		this.areaNum = areaNum;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	
	
	

}
