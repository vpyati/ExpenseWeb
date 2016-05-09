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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.model.Expense;
import com.vikram.openidconnect.login.core.identity.Identity;
import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.util.Environment;
import com.vikram.util.TestIdentity;

@Controller
@RequestMapping("/newexpense")
public class NewExpense {
	
	private static final String SERVICE_ENDPOINT = "http://www.trackthespending.in/services/expense";
	private static final String SERVICE_ENDPOINT_LOCAL = "http://localhost:8080/services/expense";
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	
	private static Logger logger = LoggerFactory.getLogger(NewExpense.class);


	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addExpense(Identity user, Expense expense, HttpServletRequest request) {
 		
		if(Environment.isDevelopment(request)){
			String UID = request.getParameter("UID");			
			user = UID==null?TestIdentity.get():TestIdentity.get(UID);
		}
		
		if(!user.isValid()){			
			RedirectView view = new RedirectView("/login",true);			
			return new ModelAndView(view);		
		}
		
		invokeAddExpenseService(expense,user,request);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("useremail", user.getEmailAddress());
		mv.addObject("tab","NEW_EXPENSE");
		mv.addObject("expenseAdded",true);
		mv.addObject("current_date", dateFormat.format(new Date()));
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showBlankExpense(Identity user,HttpServletRequest request){
		if(Environment.isDevelopment(request)){
			user = TestIdentity.get();
		}
		
		if(!user.isValid()){			
			RedirectView view = new RedirectView("/login",true);			
			return new ModelAndView(view);		
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("useremail", user.getEmailAddress());
		mv.addObject("tab","NEW_EXPENSE");
		mv.addObject("current_date", dateFormat.format(new Date()));
		return mv;
		
	}
	
	private void invokeAddExpenseService(Expense expense,Identity user, HttpServletRequest request)  {
		HttpClient client = HttpClientBuilder.create().build();

		String serviceEndpoint = Environment.isDevelopment(request)?SERVICE_ENDPOINT_LOCAL:SERVICE_ENDPOINT;
		
		HttpPost post = new HttpPost(serviceEndpoint);
		post.addHeader("AUTHORIZATION", user.getAccessToken());
		post.addHeader("OAUTH_PROVIDER", OAuthProvider.GOOGLE.name());
		
		expense.setTags(getTags(expense));
		

		try {
			post.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(expense),ContentType.APPLICATION_JSON));
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode()!=200){
				logger.error("Response from Expense service is "+response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getTags(Expense expense) {
		
		StringBuilder str = new StringBuilder();
		
		String[] tokens = expense.getName().split(" ");
		for(String token:tokens){
			str.append(token+",");
		}
		
		str.append(expense.getCategory());
		
		return str.toString();
		
	}
	

}
