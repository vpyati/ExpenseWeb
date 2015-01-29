package test.com.vikram.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
public class AppConfigTest {
	
	
	@Bean
	public AWSCredentialsProvider getAwsCredentialsProvider(){
		return new DefaultAWSCredentialsProviderChain();
	}
	
	@Bean
	public AmazonDynamoDB getAmazonDynamoDb(){
		AmazonDynamoDB amazonDynamoDb = new AmazonDynamoDBClient(getAwsCredentialsProvider());
		amazonDynamoDb.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
		return amazonDynamoDb;
	}	
	

}
