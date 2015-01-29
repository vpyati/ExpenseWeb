package com.vikram.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/oauth2callback")
public class GoogleOauthCallback {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView callBack(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletResponse response){
		
		response.addCookie(new Cookie("authorization_code", code));
		
		RedirectView view = new RedirectView("/dashboard",true);
		
		return new ModelAndView(view);
		
	}

}
