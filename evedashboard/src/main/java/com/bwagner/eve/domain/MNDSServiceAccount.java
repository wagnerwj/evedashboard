package com.bwagner.eve.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


public class MNDSServiceAccount implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5104868353636459320L;


	private long id;
	
	
	private String name;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
