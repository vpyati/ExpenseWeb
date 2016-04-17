package com.vikram.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.model.Expense;
import com.vikram.openidconnect.login.core.identity.Identity;
import com.vikram.util.Environment;
import com.vikram.util.TestIdentity;

@Controller
@RequestMapping("/newexpense")
public class NewExpense {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addExpense(Identity user, Expense expense, HttpServletRequest request) {
 		
		if(Environment.isDevelopment(request)){
			user = TestIdentity.get();
		}
		
		if(!user.isValid()){			
			RedirectView view = new RedirectView("/login",true);			
			return new ModelAndView(view);		
		}
			
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	

}
