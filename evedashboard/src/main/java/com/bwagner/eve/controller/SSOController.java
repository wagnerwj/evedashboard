package com.bwagner.eve.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PropertySource("classpath:instance.properties")
public class SSOController {

	
	@Value("${eve.clientid}")
	private String eveClientId;
	
	@Value("${eve.secretid}")
	private String eveSecretId;
	
	@Value("${eve.callbackurl}")
	private String callbackUrl;
	
	@Value("${eve.authurl}")
	private String authUrl;
	
	@Value("${eve.tokenurl}")
	private String tokenUrl;
	

	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String entryPoint(Model model){
		
		return "ssoEntry";
	}
	
	@RequestMapping(value="/signIn", method = RequestMethod.GET)
	public String redirectToEve(Model model){
		
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
