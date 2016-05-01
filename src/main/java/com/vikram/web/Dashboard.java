package com.vikram.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openidconnect.login.core.identity.Identity;
import com.vikram.util.Environment;
import com.vikram.util.TestIdentity;


@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(Identity user, HttpServletRequest request) {		
		
		if(Environment.isDevelopment(request)){
			user = TestIdentity.get();
		}
		
		if(!user.isValid()){			
			RedirectView view = new RedirectView("/login",true);			
			return new ModelAndView(view);		
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("useremail", user.getEmailAddress());
		mv.addObject("current_date", dateFormat.format(new Date()));
		return mv;
	}
}
