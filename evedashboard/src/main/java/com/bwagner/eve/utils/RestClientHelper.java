package com.bwagner.eve.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.bwagner.eve.domain.EveAPI;

public class RestClientHelper {

	
	
	private static HttpClient client = HttpClients.createDefault();
	
	public static HttpResponse getRestResponse( HttpUriRequest request) throws  IOException{
		return client.execute(request);
	}
	
	public static String returnAPIResult(EveAPI apiKey){
		return null;
	}

	public static String getHttpEntityResponseString(HttpEntity entity){
		String res = null;
		StringBuffer buff = new StringBuffer();
		try {
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			while( (line=reader.readLine())!= null){
				buff.append(line);
			}
			res=buff.toString();
			EntityUtils.consume(entity);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
