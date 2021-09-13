package com.area.model;

import java.io.Serializable;

public class AreaVO implements Serializable{
	
	private Integer areaNum;
	private String city;
	
	public AreaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaVO(Integer areaNum, String city) {
		super();
		this.areaNum = areaNum;
		this.city = city;
	}

	public Integer getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(Integer areaNum) {
		this.areaNum = areaNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}