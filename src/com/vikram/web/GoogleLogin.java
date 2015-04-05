package com.vikram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.db.KeyValueStore;
import com.vikram.openidconnect.google.GoogleOpenConnectDiscovery;

@Controller
@RequestMapping("/googlelogin")
public class GoogleLogin {

	@Autowired
	private GoogleOpenConnectDiscovery googleOpenConnectDiscovery;
	
	@Autowired
	private KeyValueStore keyValueStore;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(){
				
		RedirectView rv =  new RedirectView(getURL());
		return new ModelAndView(rv);				
	}


	private String getURL() {
		String authEndpointURL = googleOpenConnectDiscovery.getAuthorization_endpoint();
		
		StringBuilder url = new StringBuilder(authEndpointURL);
		url.append("?")
		.append("client_id=").append(keyValueStore.getValue("google_client_id")+"&")
		.append("response_type=").append("code&")
		.append("scope=").append("openid%20email&")
		.append("redirect_uri=").append(keyValueStore.getValue("google_redirect_uri")+"&")
		.append("state=dummy");
		
		return url.toString();		
	}

}
