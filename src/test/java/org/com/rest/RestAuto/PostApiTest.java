package org.com.rest.RestAuto;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.restbase.RestAuto.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase {

	TestBase testbase;
	String url;
	String serviceurl;
	String URI;
	RestClient restclient;
	CloseableHttpResponse closeableHttpPostResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		testbase = new TestBase();

		url = prop.getProperty("appurl");
		serviceurl = prop.getProperty("Service_Url");

		URI = url.concat(serviceurl);

	}

	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restclient = new RestClient();

		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");

		// jackson api to convert pojo(obj) to json and vice versa
		ObjectMapper objmap = new ObjectMapper();
		Users users = new Users("morphus", "legend");

		// obj to json file conversion
		objmap.writeValue(new File(
				"C:\\Users\\Guestuser\\eclipse-workspace\\RestAuto\\src\\main\\java\\org\\com\\qa\\config\\users.json"),
				users);

		// obj to json in string
		String userJsonstring = objmap.writeValueAsString(users);
		System.out.println(userJsonstring);

		closeableHttpPostResponse = restclient.Post(URI, userJsonstring, headermap);

		int statusCode = closeableHttpPostResponse.getStatusLine().getStatusCode();

		Assert.assertEquals(statusCode, RESPONSE_CODE_201);

		// jsonString
		String postResponseString = EntityUtils.toString(closeableHttpPostResponse.getEntity(), "UTF-8");

		// obj to JSON
		JSONObject postresponsejson = new JSONObject(postResponseString);

		System.out.println("post response Json -->" + postresponsejson);

		
		//json to obj
		Users userResponse = objmap.readValue(postResponseString, Users.class);

		System.out.println(userResponse);
		
		System.out.println(users.getName().equals(userResponse.getName()));

		System.out.println(users.getJob().equals(userResponse.getJob()));
	}

}
