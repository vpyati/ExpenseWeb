package test.com.vikram.db.awsdynamodb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import test.com.vikram.configuration.AppConfigTest;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.vikram.db.awsdynamodb.AwsDynamoDBKeyValueStore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfigTest.class, loader=AnnotationConfigContextLoader.class)
public class AwsDynamoDBKeyValueStoreTest {

	@Autowired
	private AmazonDynamoDB amazonDynamoDb;
	
	private AwsDynamoDBKeyValueStore kvStore = new AwsDynamoDBKeyValueStore();
	
	@Before
	public void setUp(){
		kvStore.setAmazonDynamoDB(amazonDynamoDb);
	}
	
	@Test
	public void testSetValue(){
		kvStore.setValue("test_val","testing");
		Assert.assertEquals(kvStore.getValue("test_val"), "testing");
		
		kvStore.deleteKey("test_val");
		Assert.assertEquals(kvStore.getValue("test_val"), null);
	}
	
	

}
