package com.vikram.openidconnect;

import com.vikram.custombeans.User;

public interface IOpenConnectCredentials {
	
	public ICredentials getCredentials(String authCode);
	
	public interface ICredentials{
		
		public String getAccessToken();
		
		public String getIdToken();
		
		public String expiresIn();
		
		public String tokenType();	
		
		public ICredentialToUserConverter getConverter();
	}
	
	
	public interface ICredentialToUserConverter{
		
		public User getUser();		
	}
	
}
