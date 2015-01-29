package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.custombeans.User;

@Controller
@RequestMapping("/login")
public class Login {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(User user) {
		
		if(user!=null && user.isValidUser()){
			RedirectView view = new RedirectView("/dashboard",true);			
			return new ModelAndView(view);
		}
		
		return new ModelAndView();
	}


}
