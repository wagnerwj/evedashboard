package com.bwagner.eve.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bwagner.eve.domain.EveAccountStatus;
import com.bwagner.eve.domain.EveCharacter;
import com.bwagner.eve.domain.EvePilot;
import com.bwagner.eve.domain.forms.EvePilotEmailForm;
import com.bwagner.eve.service.EveDashboardService;
import com.bwagner.eve.utils.RestClientHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage; 

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
	
	@Value("${eve.characterurl}")
	private String characterUrl;
	
	@Value("${eve.corpname}")
	private String corpName;
	
	@Resource
	private EveDashboardService dashboardService;

	
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
		String jspPage = "ssoEntry";
		HttpPost postUrl = new HttpPost(tokenUrl);
		ArrayList<BasicNameValuePair> postParameters= new ArrayList<BasicNameValuePair>();
		String encodedAuthString = Base64.getEncoder().encodeToString((eveClientId+":"+eveSecretId).getBytes());
		postUrl.setHeader("Authorization", "Basic "+encodedAuthString);
		postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParameters.add(new BasicNameValuePair("code", code.trim()));
		
		try {
			postUrl.setEntity(new UrlEncodedFormEntity(postParameters,"UTF-8"));	
			HttpEntity entity = RestClientHelper.getRestResponse(postUrl).getEntity();
			String accessToken = null;
			String tokenType=null;
			String restResponse = RestClientHelper.getHttpEntityResponseString(entity);
			Map<String, String> properties = new HashMap<String, String>();
			try {
			 
			    ObjectMapper mapper = new ObjectMapper();
			    properties = mapper.readValue(restResponse, new TypeReference<Map<String, String>>() {});
			    accessToken = properties.get("access_token");
			    tokenType = properties.get("token_type");
			    
			    if(accessToken!=null){
			    	//get characters here
			    	HttpGet getUrl = new HttpGet(characterUrl);
			    	getUrl.setHeader("Authorization", "Bearer "+accessToken);
			    	entity = RestClientHelper.getRestResponse(getUrl).getEntity();
			    	restResponse = RestClientHelper.getHttpEntityResponseString(entity);
			    	properties = mapper.readValue(restResponse, new TypeReference<Map<String, String>>() {});
			    	
			    	model.addAttribute("characterName", properties.get("CharacterName"));
			    	model.addAttribute("characterID", properties.get("CharacterID"));
			    	jspPage = characterValidation(properties, model);
			    }
			} catch (IOException e) {   
			   
			}
			
			System.out.println(restResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jspPage;
	}
	
	private  String characterValidation(Map<String, String> properties,Model model){
		EveAccountStatus status = dashboardService.validateCharacter(properties, corpName);
		String retval = "ssoEntry";
    	switch (status) {
		case INVALID_ACCOUNT:
			retval = "getAccountInfo";
			model.addAttribute("evePilotEmailForm", new EvePilotEmailForm());
			break;
		case INVALID_API:
			retval = "getApiInfo";
			break;
		case INVALID_PASSWORD:
			retval = "getPassword";
			break;
		case INVALID_CORP:
			//kill forum access here
			break;
		default:
			break;
		}
    	return retval;
	}
	
	
	@RequestMapping(value = "submitEmail", method = RequestMethod.POST)
	public String submitEmail( @ModelAttribute("evePilotEmailForm") EvePilotEmailForm emailForm, Model model,
								BindingResult result){
		String email = emailForm.getEmailAddress();
		
		//here validate the email.
		//Create a Pilot- with one character
		EveCharacter character = new EveCharacter();
		character.setId(emailForm.getCharacterID());
		
		EvePilot pilot = new EvePilot();
		
		pilot.setEmailAddress(email);
		pilot.getCharacters().add(character);
		dashboardService.savePilot(pilot);
		
		
		return "ssoEntry";
	}
	//public void sendConfirmEmail(String email){
//	
//
//	      // Sender's email ID needs to be mentioned
//	      String from = "web@gmail.com";
//
//	      // Assuming you are sending email from localhost
//	      String host = "localhost";
//
//	      // Get system properties
//	      Properties properties = System.getProperties();
//
//	      // Setup mail server
//	      properties.setProperty("mail.smtp.host", host);
//
//	      // Get the default Session object.
//	      Session session = Session.getDefaultInstance(properties);
//
//	      try{
//	         // Create a default MimeMessage object.
//	         MimeMessage message = new MimeMessage(session);
//
//	         // Set From: header field of the header.
//	         message.setFrom(new InternetAddress(from));
//
//	         // Set To: header field of the header.
//	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//
//	         // Set Subject: header field
//	         message.setSubject("This is the Subject Line!");
//
//	         // Now set the actual message
//	         message.setText("This is actual message");
//
//	         // Send message
//	         Transport.send(message);
//	         System.out.println("Sent message successfully....");
//	      }catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
//	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
