package com.bwagner.eve.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bwagner.eve.dao.EveDashboardDao;
import com.bwagner.eve.domain.EveAPI;
import com.bwagner.eve.domain.EveAccountStatus;
import com.bwagner.eve.domain.EveCharacter;
import com.bwagner.eve.domain.EvePilot;
import com.bwagner.eve.utils.RestClientHelper;

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
	
	
	public EvePilot savePilot(EvePilot pilot){
		dao.save(pilot);
		
		return dao.findByEmail(pilot.getEmailAddress());
	}
	public EveAccountStatus validateCharacter(Map<String,String> properties,String corpName){
		EvePilot pilot = dao.findByEveCharacterId(Long.decode(properties.get("CharacterID")));
		if(pilot == null){
			return EveAccountStatus.INVALID_ACCOUNT;
		}
		if(pilot.getApiKeys()== null || pilot.getApiKeys().size() ==0){
			return EveAccountStatus.INVALID_API;
		}
		for(EveAPI apiKey:pilot.getApiKeys()){
			String result = RestClientHelper.returnAPIResult(apiKey);
			if(result == null){
				return EveAccountStatus.INVALID_API;
			}
		}
		boolean inCorpChar = false;
		for(EveCharacter character: pilot.getCharacters()){
			if(corpName.equalsIgnoreCase(character.getCurrentCorp())){
				inCorpChar = true;
				break;
			}
			
		}
		 if(inCorpChar == false){
			 return EveAccountStatus.INVALID_CORP;
		 }
		return EveAccountStatus.NO_ERROR;
	}
}
