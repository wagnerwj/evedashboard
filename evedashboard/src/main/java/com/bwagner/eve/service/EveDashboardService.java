package com.bwagner.eve.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bwagner.eve.dao.EveDashboardDao;
import com.bwagner.eve.domain.EvePilot;

@Service
public class EveDashboardService {
	
	@Resource
	private EveDashboardDao dao;
	
	
	public EvePilot processLogin(Map<String,String> properties){
		EvePilot pilot = dao.findByEveCharacterId(Long.decode(properties.get("CharacterID")));
		return pilot;
	}

	public EvePilot verifyEmail(String email){
		EvePilot pilot = dao.findByEmail(email);
		return pilot;
	}
}
