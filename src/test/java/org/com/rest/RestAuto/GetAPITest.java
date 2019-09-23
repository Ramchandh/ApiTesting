package org.com.rest.RestAuto;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.restbase.RestAuto.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.util.rest.TestUtill;

public class GetAPITest extends TestBase {

	TestBase testbase;
	String url;
	String serviceurl;
	String URI;
	RestClient rc;
	CloseableHttpResponse httpresponse;

	@Test(priority = 1)
	public void GetTest() throws ClientProtocolException, IOException {

		rc = new RestClient();
		httpresponse = rc.Get(URI);

		// status code
		int statusCode = httpresponse.getStatusLine().getStatusCode();

		System.out.println(statusCode);

		Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Status code is " + statusCode);

		// to get the entire response as string

		String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");

		JSONObject responsejsondata = new JSONObject(responseString);

		System.out.println(responsejsondata);

		// per_page
		String valuebyJpath = TestUtill.getValuebyJpath(responsejsondata, "per_page");

		System.out.println(valuebyJpath);

		// total
		String totalvaluebyJpath = TestUtill.getValuebyJpath(responsejsondata, "total");

		System.out.println(totalvaluebyJpath);

		// how to get the value from data array in json
		System.out.println(TestUtill.getValuebyJpath(responsejsondata, "data[0]/last_name"));

		// to get the header
		Header[] allHeaders = httpresponse.getAllHeaders();

		HashMap<String, String> Headerdata = new HashMap<String, String>();

		for (Header header : allHeaders) {

			Headerdata.put(header.getName(), header.getValue());

		}

		System.out.println(Headerdata);
	}

	@Test(priority = 2)
	public void getWithHeaders() throws ClientProtocolException, IOException {
		System.out.println("get method with headers");

		rc = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		CloseableHttpResponse httpresponse = rc.Get(URI, headerMap);

		int statusCode = httpresponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);

		String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");

		JSONObject jsonResponse = new JSONObject(responseString);

	}

}
