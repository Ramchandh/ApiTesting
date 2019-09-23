package com.util.rest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtill {
	
	public static String getValuebyJpath(JSONObject responseJson,String jPath)
	{
		Object obj = responseJson;
		for(String s : jPath.split("/")) 
			if(!s.isEmpty())
				if(!(s.contains("[")||s.contains("]")))
					obj= ((JSONObject) obj).get(s);
				else if(s.contains("[")||s.contains("]"))
				obj=  ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));
		return obj.toString();
		
		
		
		
		
		
		
	}

}
