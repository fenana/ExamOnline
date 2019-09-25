package com.exam.model;
/*
 * 做测试
 * 实体类
 * 
 */
public class THobbyModel {
	private Integer HobbyID;
	private String HobbyName;
	
	
	public Integer getHobbyID() {
		return HobbyID;
	}
	public void setHobbyID(Integer HobbyID) {
		//局部变量用this获取
		this.HobbyID = HobbyID;
	}
	public String getHobbyName() {
		return HobbyName;
	}
	public void setHobbyName(String HobbyName) {
		this.HobbyName = HobbyName;
	}
	
}
