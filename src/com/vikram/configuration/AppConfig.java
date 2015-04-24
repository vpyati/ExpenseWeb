package com.vikram.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
import com.vikram.openconnect.login.IAccessToken;
import com.vikram.openconnect.login.input.ICredentialInput;
import com.vikram.openconnect.login.input.IOAuthCredentials;
import com.vikram.openconnect.login.input.OAuthCredentials;
import com.vikram.openconnect.login.providers.OAuthProvider;

@Configuration
@ComponentScan("com.vikram.web")
@ImportResource("classpath:META-INF/oal.xml")
@EnableWebMvc
public class AppConfig  extends WebMvcConfigurerAdapter {
	
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
	public InternalResourceViewResolver getViewResolver(){
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;	
	}
	
	@Bean
	public AWSCredentialsProvider getAwsCredentialsProvider(){				      
		return getStaticCredentialProvider();
	}

	private StaticCredentialsProvider getStaticCredentialProvider() {
				
		String accessKey = System.getProperty("AWS_ACCESS_KEY_ID");
		String secretKey = System.getProperty("AWS_SECRET_KEY");
		
		if(accessKey == null || secretKey == null){
			accessKey = System.getenv("AWS_ACCESS_KEY_ID");
			secretKey = System.getenv("AWS_SECRET_KEY"); 
		}
				
		return new StaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey));
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
	public IOAuthCredentials getOauthCredentials(){
		return new OAuthCredentials(getCredentials());
	}

	@Bean
	public IAccessToken getAccessToken(){
		return new AccessToken();
	}
	
	private List<ICredentialInput> getCredentials() {
		ICredentialInput input = new ICredentialInput() {
			
			@Override
			public String getRedirectUri() {
				return getKeyValueStore().getValue("google_redirect_uri");
			}
			
			@Override
			public OAuthProvider getProvider() {
				return OAuthProvider.GOOGLE;
			}
			
			@Override
			public String getClientSecret() {
				return getKeyValueStore().getValue("google_client_secret");
			}
			
			@Override
			public String getClientId() {
				return getKeyValueStore().getValue("google_client_id");
			}
		};
		List<ICredentialInput> credentials = new ArrayList<ICredentialInput>();
		credentials.add(input);
		return credentials;
	}
}
