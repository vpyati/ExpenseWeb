package com.vikram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.custombeans.User;
import com.vikram.openidconnect.google.GoogleOpenConnectDiscovery;


@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	@Autowired
	private GoogleOpenConnectDiscovery googleOpenConnectDiscovery;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(User user) {
 

		if(user.isValidUser()){
			// redirect to dashboard
		}
		
		RedirectView view = new RedirectView("/login",true);
		
		 return new ModelAndView(view);
		
	}

	


}
