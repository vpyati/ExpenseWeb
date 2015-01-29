package com.vikram.openidconnect.google;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.vikram.custombeans.User;
import com.vikram.http.HttpClientUtil;
import com.vikram.openidconnect.GetTokenResponse;
import com.vikram.openidconnect.IOpenConnectCredentials.ICredentialToUserConverter;

public class GoogleCredentialToUserConverter implements
		ICredentialToUserConverter {

	@Autowired
	private GoogleOpenConnectDiscovery discovery;
	
	@Autowired
	private HttpClientUtil httpUtil;

	
	@Override
	public User getUser(GetTokenResponse response) {
		
		
		String userInfoEndpoint = discovery.getUserinfo_endpoint();
		
		try {
			String jsonResponse = httpUtil.getJSONResponse(userInfoEndpoint+"?access_token="+response.getAccess_token());
			JSONObject json = new JSONObject(jsonResponse);
			final String name = json.getString("name");
			final String email = json.getString("email");
			return new User(){

				@Override
				public boolean isValidUser() {
					return true;
				}

				@Override
				public String getName() {
					return name;
				}

				@Override
				public String getEmailAddress() {
					return email;
				}
				
			};
			
		} catch (HttpException | JSONException e) {
			return null;
		}
		
		
	}

}
