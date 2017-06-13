package com.doshr.xmen.backend.client.grade.dto;

import java.io.Serializable;

public class ClassDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7039885529379707630L;
	
	private int id;
	private String name;//班级名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
