package com.vikram.configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

import com.vikram.openconnect.login.core.providers.OAuthProvider;
import com.vikram.openconnect.login.web.IAccessToken;

public class AccessToken implements IAccessToken {

	private static String COOKIE_NAME = "access_token";

	@Override
	public void setAccessToken(String accessToken, HttpServletResponse response,OAuthProvider provider) {
		response.addCookie(new Cookie(COOKIE_NAME,accessToken));	
	}

	@Override
	public String getAccessToken(HttpServletRequest request,OAuthProvider provider) {
		Cookie accessToken = WebUtils.getCookie(request, COOKIE_NAME);
		return accessToken==null?"":accessToken.getValue();
	}
}
