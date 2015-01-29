package com.vikram.openidconnect;

import com.vikram.custombeans.User;

public interface IOpenConnectCredentials {
	
	public ICredentials getCredentials(String authCode);
	
	public interface ICredentials{
		
		public GetTokenResponse getTokenResponse();
				
		public ICredentialToUserConverter getConverter();
	}
	
	
	public interface ICredentialToUserConverter{
		
		public User getUser(GetTokenResponse tokenResponse);		
	}
	
}
