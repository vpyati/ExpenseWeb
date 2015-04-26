package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.openidconnect.login.web.controller.OpenconnectLoginController;

@Controller
@RequestMapping("/googlelogin")
public class GoogleLogin extends OpenconnectLoginController {

	@Override
	protected OAuthProvider getProvider() {
		return OAuthProvider.GOOGLE;
	}
}
