package com.vikram.configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.openidconnect.login.web.IAccessToken;

public class AccessToken implements IAccessToken {

	private static String COOKIE_NAME = "access_token";
	
	private Logger logger = LoggerFactory.getLogger(AccessToken.class);

	@Override
	public void setAccessToken(String accessToken, HttpServletResponse response,OAuthProvider provider) {
		logger.info("Setting accesstoken");
		response.addCookie(new Cookie(COOKIE_NAME,accessToken));	
	}

	@Override
	public String getAccessToken(HttpServletRequest request,OAuthProvider provider) {
		logger.info("Fetching access token from cookie");
		Cookie accessToken = WebUtils.getCookie(request, COOKIE_NAME);
		return accessToken==null?"":accessToken.getValue();
	}
}
