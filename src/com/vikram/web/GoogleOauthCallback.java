package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.openidconnect.login.web.controller.OpenconnectCallbackController;

@Controller
@RequestMapping("/oauth2callback")
public class GoogleOauthCallback extends OpenconnectCallbackController {

	@Override
	public String getRedirectView() {
		return "/dashboard" ;
	}
}
