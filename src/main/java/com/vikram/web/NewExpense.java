package com.vikram.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.model.Expense;
import com.vikram.openidconnect.login.core.identity.Identity;
import com.vikram.util.Environment;
import com.vikram.util.TestIdentity;

@Controller
@RequestMapping("/newexpense")
public class NewExpense {
	
	private static final String SERVICE_ENDPOINT = "http://localhost:8080/services/expense";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");


	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addExpense(Identity user, Expense expense, HttpServletRequest request) {
 		
		if(Environment.isDevelopment(request)){
			user = TestIdentity.get();
		}
		
		if(!user.isValid()){			
			RedirectView view = new RedirectView("/login",true);			
			return new ModelAndView(view);		
		}
		
		invokeAddExpenseService(expense);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("useremail", user.getEmailAddress());
		mv.addObject("expenseAdded",true);
		mv.addObject("current_date", dateFormat.format(new Date()));
		mv.setViewName("dashboard");
		return mv;
	}

	private void invokeAddExpenseService(Expense expense)  {
		HttpClient client = HttpClientBuilder.create().build();

		HttpPost post = new HttpPost(SERVICE_ENDPOINT);
		expense.setTags("sample tags");

		try {
			post.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(expense),ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(post);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	

}
