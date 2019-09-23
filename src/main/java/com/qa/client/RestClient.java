package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// Create One GET Method
	public CloseableHttpResponse Get(String URL) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();// will create one client connection

		// HttpGet
		HttpGet httpget = new HttpGet(URL);

		// get the response from the url

		CloseableHttpResponse httpresponse = httpClient.execute(httpget);

		// from the response get the status code

//		int statusCode = httpresponse.getStatusLine().getStatusCode();
//
//		System.out.println(statusCode);
//
//		// to get the entire response as string
//
//		String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
//
//		JSONObject responsejsondata = new JSONObject(responseString);
//
//		System.out.println(responsejsondata);
//
//		// to get the header
//		Header[] allHeaders = httpresponse.getAllHeaders();
//
//		HashMap<String, String> Headerdata = new HashMap<String, String>();
//
//		for (Header header : allHeaders) {
//
//			Headerdata.put(header.getName(), header.getValue());
//
//		}
//
//		System.out.println(Headerdata);

		return httpresponse;
	}

	public CloseableHttpResponse Get(String URL, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(URL);

		for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
			httpget.addHeader(headerEntry.getKey(), headerEntry.getValue());
		}

		CloseableHttpResponse httpresponse = httpClient.execute(httpget);

		return httpresponse;

	}

	// creation of POST method
	public CloseableHttpResponse Post(String URL, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpCLient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(URL);

		httppost.setEntity(new StringEntity(entityString));

		//headers
		for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
			httppost.addHeader(headerEntry.getKey(), headerEntry.getValue());
		}

		CloseableHttpResponse httpPostResponse = httpCLient.execute(httppost);
		return httpPostResponse;

	}
}
