package com.bwagner.eve.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;



public class EvePilot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080544559553494343L;


	private long id;
	

	private List<EveAPI> apiKeys;
	

	private List<EveCharacter> characters;
	
	private String emailAddress;
	

	private List<MNDSServiceAccount> accounts;
	
	
	private String salt;
	
	private String hashedPassword;
	

	
	
	
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<EveAPI> getApiKeys() {
		if(this.apiKeys == null){
			this.apiKeys = new ArrayList<EveAPI>();
		}
		return apiKeys;
	}

	public void setApiKeys(List<EveAPI> apiKeys) {
		this.apiKeys = apiKeys;
	}

	public List<EveCharacter> getCharacters() {
		if(this.characters == null){
			this.characters = new ArrayList<EveCharacter>();
		}
		return characters;
	}

	public void setCharacters(List<EveCharacter> characters) {
		this.characters = characters;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<MNDSServiceAccount> getAccounts() {
		if(this.accounts == null){
			this.accounts = new ArrayList<MNDSServiceAccount>();
		}
		return accounts;
	}

	public void setAccounts(List<MNDSServiceAccount> accounts) {
		this.accounts = accounts;
	}


}
