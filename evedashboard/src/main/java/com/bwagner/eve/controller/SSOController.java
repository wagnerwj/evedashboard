package com.bwagner.eve.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bwagner.eve.utils.RestClientHelper;

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
		String eveUrl = MessageFormat.format(authUrl,callbackUrl, eveClientId,"login");
		model.addAttribute("eveUrl", eveUrl);
		return "ssoEntry";
	}
	
	@RequestMapping(value="/signIn", method = RequestMethod.GET)
	public String redirectToEve(Model model){

		return "ssoEntry";
	}
	
	@RequestMapping(value="authStep1", method = RequestMethod.GET)
	public String authStep1(Model model, @RequestParam String code, @RequestParam String state){

		HttpPost postUrl = new HttpPost(tokenUrl);
		ArrayList<BasicNameValuePair> postParameters= new ArrayList<BasicNameValuePair>();
		String encodedAuthString = Base64.getEncoder().encodeToString((eveClientId+":"+eveSecretId).getBytes());
		postParameters.add(new BasicNameValuePair("Authorization","Basic "+encodedAuthString));
		postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParameters.add(new BasicNameValuePair("code", code.trim()));
		
		try {
			postUrl.setEntity(new UrlEncodedFormEntity(postParameters,"UTF-8"));	
			HttpEntity entity = RestClientHelper.getRestResponse(postUrl).getEntity();
			
			String restResponse = RestClientHelper.getHttpEntityResponseString(entity);
			int rhe=2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ssoEntry";
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
