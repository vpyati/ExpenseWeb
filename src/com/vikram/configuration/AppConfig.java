package com.vikram.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.vikram.db.KeyValueStore;
import com.vikram.db.awsdynamodb.AwsDynamoDBKeyValueStore;
import com.vikram.http.HttpClientUtil;
import com.vikram.openidconnect.google.GoogleCredentialToUserConverter;
import com.vikram.openidconnect.google.GoogleCredentials;
import com.vikram.openidconnect.google.GoogleOpenConnectDiscovery;
import com.vikram.resolver.UserResolver;

@Configuration
@ComponentScan("com.vikram.web")
@EnableWebMvc
public class AppConfig  extends WebMvcConfigurerAdapter {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		UserResolver resolver = new UserResolver(getGoogleCredentials());
		argumentResolvers.add(resolver);
	}
	
	@Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
            registry.addResourceHandler("/css/**").addResourceLocations("/css/");
            registry.addResourceHandler("/js/**").addResourceLocations("/js/");
            registry.addResourceHandler("/img/**").addResourceLocations("/img/");
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
	
	@Bean
	public AWSCredentialsProvider getAwsCredentialsProvider(){
				      
		//return new AWSCredentialsProviderChain(new DefaultAWSCredentialsProviderChain());
		return getStaticCredentialProvider();

	}

	private StaticCredentialsProvider getStaticCredentialProvider() {
		
		String accessKey = System.getProperty("AWS_ACCESS_KEY_ID");
		String secretKey = System.getProperty("AWS_SECRET_KEY");
		
		if(accessKey == null || secretKey == null){
			accessKey = System.getenv("AWS_ACCESS_KEY_ID");
			secretKey = System.getenv("AWS_SECRET_KEY"); 
		}
		
		
		BasicAWSCredentials credentials = null;
		try {
			credentials = new BasicAWSCredentials(accessKey,secretKey);

		} catch (Exception e) {
			credentials = new BasicAWSCredentials("","");
		}
		
		return new StaticCredentialsProvider(credentials);
	}
	
	@Bean
	public AmazonDynamoDB getAmazonDynamoDb(){
		AmazonDynamoDB amazonDynamoDb = new AmazonDynamoDBClient(getAwsCredentialsProvider());
		amazonDynamoDb.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
		return amazonDynamoDb;
	}
	
	@Bean
	public KeyValueStore getKeyValueStore(){
		return new AwsDynamoDBKeyValueStore();
	}
	
	@Bean
	public GoogleCredentialToUserConverter getGoogleUserConverter(){
		return new GoogleCredentialToUserConverter();
	}
}
