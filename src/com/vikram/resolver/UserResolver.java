package com.vikram.resolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import com.vikram.custombeans.User;
import com.vikram.openidconnect.google.GoogleCredentials;
public class UserResolver implements HandlerMethodArgumentResolver{

	@Autowired
	private GoogleCredentials googleCredentials;
	
	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest request,
			WebDataBinderFactory arg3) throws Exception {
		
		Cookie authCode = WebUtils.getCookie(request.getNativeRequest(HttpServletRequest.class), "authorization_code");
		if(authCode!=null){
			return returnUser(authCode);
		}else{
			return User.INVALID_USER;
		}
	}

	private User returnUser(Cookie authCode) {
		
		String value = authCode.getValue();
		
		return googleCredentials.getCredentials(value).getConverter().getUser();		
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 return parameter.getParameterType().equals(User.class);
	}

}
