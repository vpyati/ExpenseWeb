package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.identity.Identity;


@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(Identity user) {
 
		if(user.isValid()){
			ModelAndView mv = new ModelAndView();
			mv.addObject("useremail", user.getEmailAddress());
			return mv;
		}
		
		RedirectView view = new RedirectView("/login",true);
		
		return new ModelAndView(view);		
	}
}
