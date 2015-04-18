package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.identity.Identity;

@Controller
@RequestMapping("/login")
public class Login {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(Identity user) {
		
		if(user!=null && user.isValid()){
			RedirectView view = new RedirectView("/dashboard",true);			
			return new ModelAndView(view);
		}
		
		return new ModelAndView();
	}


}
