package com.bwagner.eve.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;



public class EveCharacter implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3506111020219862151L;


	private long id;
	
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCurrentCorp() {
		return currentCorp;
	}

	public void setCurrentCorp(String currentCorp) {
		this.currentCorp = currentCorp;
	}

	private String name;
	
	//I think this is a url string, don't remember;
	private String avatarUrl;
	
	private String currentCorp;

}
