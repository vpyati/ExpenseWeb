package com.vikram.configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

import com.vikram.openconnect.login.IAccessToken;

public class AccessToken implements IAccessToken {

	private static String COOKIE_NAME = "access_token";

	@Override
	public void setAccessToken(String accessToken, HttpServletResponse response) {
		response.addCookie(new Cookie(COOKIE_NAME,accessToken));	
	}

	@Override
	public String getAccessToken(HttpServletRequest request) {
		Cookie accessToken = WebUtils.getCookie(request, COOKIE_NAME);
		if(accessToken!=null){
			return accessToken.getValue();
		}
		return "";
	}

	
	

}
