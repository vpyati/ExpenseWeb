package com.vikram.web;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class Login {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login() {
 

//		if(isUserLoggedIn()){
//			// redirect to dashboard
//		}else{
//			// redirect to login
//		}
		
		return new ModelAndView();
	}

	private boolean isUserLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}


}
