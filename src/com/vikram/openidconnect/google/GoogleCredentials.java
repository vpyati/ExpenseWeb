package com.vikram.openidconnect.google;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.db.KeyValueStore;
import com.vikram.http.HttpClientUtil;
import com.vikram.openidconnect.GetTokenResponse;
import com.vikram.openidconnect.IOpenConnectCredentials;

public class GoogleCredentials implements IOpenConnectCredentials {

	@Autowired
	private GoogleOpenConnectDiscovery discovery;
	
	@Autowired
	private KeyValueStore keyValueStore;
	
	
	@Autowired
	private HttpClientUtil httpUtil;
	
	@Autowired
	private GoogleCredentialToUserConverter userConverter;
	
	@Override
	public ICredentials getCredentials(String authCode) {
		
		
		String tokenEndpoint = discovery.getToken_endpoint();
		
		Map<String, String> postParams = new HashMap<String, String>();
		postParams.put("code", authCode);
		postParams.put("client_id", keyValueStore.getValue("google_client_id"));
		postParams.put("client_secret", keyValueStore.getValue("google_client_secret"));
		postParams.put("redirect_uri", keyValueStore.getValue("google_redirect_uri"));
		postParams.put("grant_type", "authorization_code");
		postParams.put("access_type", "offline");
		
		String response = getResponse(tokenEndpoint, postParams);
		if(response == null){
			return null;
		}
		
		final GetTokenResponse tokenResponse = getTokenResponse(response);
		
		return new ICredentials() {
			
			@Override
			public GetTokenResponse getTokenResponse() {
				return tokenResponse;
			}
			
			@Override
			public ICredentialToUserConverter getConverter() {
				return userConverter;
			}
		};
		
	}

	private GetTokenResponse getTokenResponse(String response)  {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(response,GetTokenResponse.class);
		} catch (IOException e) {
			return null;
		}
	}

	private String getResponse(String tokenEndpoint,Map<String, String> postParams) {
		try {
			return httpUtil.getJSONResponse(tokenEndpoint, postParams);
		} catch (HttpException e) {
			return null;
		}
	}

}
