package com.vikram.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.vikram.http.HttpClientUtil;
import com.vikram.openidconnect.google.GoogleCredentials;
import com.vikram.openidconnect.google.GoogleOpenConnectDiscovery;
import com.vikram.resolver.UserResolver;

@Configuration
@ComponentScan("com.vikram.web")
@EnableWebMvc
public class AppConfig  extends WebMvcConfigurerAdapter {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		UserResolver resolver = new UserResolver();
		argumentResolvers.add(resolver);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	    return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public GoogleOpenConnectDiscovery getGoogleDiscovery(){
		return new GoogleOpenConnectDiscovery();
	}
	
	@Bean
	public HttpClientUtil getHttpUtil(){
		return new HttpClientUtil();
	}
	
	@Bean
	public GoogleCredentials getGoogleCredentials(){
		return new GoogleCredentials();
	}
	
	@Bean
	public InternalResourceViewResolver getViewResolver(){
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
		
	}
}
