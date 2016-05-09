package com.bwagner.eve.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MNDSServiceAccount implements Serializable {
	
	
	@Id
	private long id;
	
	
	private String name;

}
