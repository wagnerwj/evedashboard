package com.bwagner.eve.domain.forms;

import java.io.Serializable;

public class EvePilotEmailForm implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5943795340440356061L;
	private String emailAddress;
	
	private Long characterID;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getCharacterID() {
		return characterID;
	}

	public void setCharacterID(Long characterID) {
		this.characterID = characterID;
	}
	
}
