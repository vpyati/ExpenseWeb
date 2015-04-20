package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.openconnect.login.controller.OpenconnectLoginController;
import com.vikram.openconnect.login.providers.OAuthProvider;

@Controller
@RequestMapping("/googlelogin")
public class GoogleLogin extends OpenconnectLoginController {

	@Override
	protected OAuthProvider getProvider() {
		return OAuthProvider.GOOGLE;
	}
}
