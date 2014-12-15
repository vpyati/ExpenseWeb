package com.vikram.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class HttpClientUtil {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	public String getJSONResponse(String url) throws HttpException{
		
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		try {
			return getResponse(client, request);
		} catch (Exception e) {
			throw new HttpException();
		}
 

		
	}

	private String getResponse(HttpClient client, HttpGet request)
			throws IOException, ClientProtocolException {
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}
	

}
