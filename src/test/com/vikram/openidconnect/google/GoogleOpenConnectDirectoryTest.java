package test.com.vikram.openidconnect.google;

import java.io.IOException;

import org.apache.http.HttpException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vikram.http.HttpClientUtil;
import com.vikram.openidconnect.google.GoogleOpenConnectDiscovery;


public class GoogleOpenConnectDirectoryTest {
	
	private GoogleOpenConnectDiscovery googleDiscovery;
	
	@Before
	public void setUp(){
		googleDiscovery = new GoogleOpenConnectDiscovery();
	}
	
	@Test
	public void testInit(){
		
		HttpClientUtil util = new HttpClientUtil();
		googleDiscovery.setHttpUtil(util);
		
		
		try {
			googleDiscovery.init();
		} catch (IOException | HttpException e) {
			Assert.assertFalse(false);
		}		
	}

}
