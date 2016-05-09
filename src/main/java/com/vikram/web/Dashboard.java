package com.vikram.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.model.Expense;
import com.vikram.openidconnect.login.core.identity.Identity;
import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.util.Environment;
import com.vikram.util.TestIdentity;


@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	
	private static final String SERVICE_ENDPOINT = "http://www.trackthespending.in/services/expense";
	private static final String SERVICE_ENDPOINT_LOCAL = "http://localhost:8080/services/expense";
	private static final long TWO_DAYS_MILLISEC = 2*24*60*60*1000L;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static Logger logger = LoggerFactory.getLogger(Dashboard.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(Identity user, HttpServletRequest request) {		
		
		if(Environment.isDevelopment(request)){
			String UID = request.getParameter("UID");			
			user = UID==null?TestIdentity.get():TestIdentity.get(UID);
		}
		
		if(!user.isValid()){			
			RedirectView view = new RedirectView("/login",true);			
			return new ModelAndView(view);		
		}
		
		List<Expense> expenses = invokeAddExpenseService(user, request);
		for(Expense expense:expenses){
			expense.setCreationDate(convertMillisecToDate(expense.getCreationDate()));
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("useremail", user.getEmailAddress());
		mv.addObject("tab","DASHBOARD");
		mv.addObject("expenses", expenses);
		return mv;
	}
	
	private String convertMillisecToDate(String creationDateMs) {
		
		long l = Long.parseLong(creationDateMs);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(l);
		return dateFormat.format(cal.getTime());		
	}

	private List<Expense> invokeAddExpenseService(Identity user, HttpServletRequest request)  {
		HttpClient client = HttpClientBuilder.create().build();

		String serviceEndpoint = Environment.isDevelopment(request)?SERVICE_ENDPOINT_LOCAL:SERVICE_ENDPOINT;
		
		serviceEndpoint = serviceEndpoint + "?" + "e="+getCurrentMillesec()+"&"+"s="+getTwoDaysAgoMillisec()+getLocalUID(request,user);
		
		HttpGet get = new HttpGet(serviceEndpoint);
		get.addHeader("AUTHORIZATION", user.getAccessToken());
		get.addHeader("OAUTH_PROVIDER", OAuthProvider.GOOGLE.name());
		

		try {
			HttpResponse response = client.execute(get);
			if(response.getStatusLine().getStatusCode()!=200){
				logger.error("Response from Expense service is "+response.getStatusLine().getStatusCode());
				return new ArrayList<Expense>();
			}
			List<Expense> expenses = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Expense>>(){});
			return expenses;
			
		} catch (IOException e) {
			logger.error("Unable to get response from Expense service", e);
			throw new RuntimeException(e);
		}
	}

	private String getLocalUID( HttpServletRequest request, Identity user) {
		StringBuilder str = new StringBuilder("&");
		if(Environment.isDevelopment(request)){
			str.append("UID="+user.getEmailAddress());
		}

		return str.toString();		
	}

	private long getTwoDaysAgoMillisec() {
		return getCurrentMillesec()-TWO_DAYS_MILLISEC;
	}

	private long getCurrentMillesec() {
		return new Date().getTime();
	}

}
