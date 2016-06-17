package com.bwagner.eve.domain;

import java.util.Date;
import java.util.Set;

public class EveAPICallResult {
	
	private long accessMask;
	
	private String type;
	
	private Date expires;
	
	private Set<EveCharacter> characters;

	public long getAccessMask() {
		return accessMask;
	}

	public void setAccessMask(long accessMask) {
		this.accessMask = accessMask;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public Set<EveCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<EveCharacter> characters) {
		this.characters = characters;
	}

}
