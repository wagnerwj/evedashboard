package com.bwagner.eve.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EveAPI implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3669527828596154615L;


	@Id
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getvCode() {
		return vCode;
	}


	public void setvCode(String vCode) {
		this.vCode = vCode;
	}


	private String key;
	
	
	private String vCode;

}
