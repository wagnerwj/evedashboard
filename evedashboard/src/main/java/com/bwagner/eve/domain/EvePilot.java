package com.bwagner.eve.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;


@Entity
public class EvePilot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080544559553494343L;

	private long id;
	

	private List<EveAPI> apiKeys;
	
	//Mapped for better speed- probably not needed, but I like maps.
	private Map<Long, EveCharacter> characterMap;
	
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
		return apiKeys;
	}

	public void setApiKeys(List<EveAPI> apiKeys) {
		this.apiKeys = apiKeys;
	}

	public Map<Long, EveCharacter> getCharacterMap() {
		return characterMap;
	}

	public void setCharacterMap(Map<Long, EveCharacter> characterMap) {
		this.characterMap = characterMap;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<MNDSServiceAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<MNDSServiceAccount> accounts) {
		this.accounts = accounts;
	}

}
