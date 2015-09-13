package com.stage.openstackmonit.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class Http_ {
	
	protected HttpClient httpclient;
	protected HttpResponse httpResponse;
	protected String url;
	
	
	public Http_(String url)
	{
		this.url=url;
		
		httpclient=new DefaultHttpClient();
	}
	
	public void setUrl(String url)
	{
		this.url=url;
	}
	
	public String display(int code)
	{ 
		 switch (code) {
         case 400:  return "bad request";   
         case 401:  return "Unauthorized";
         case 403:  return "userDisabled";        
         case 404:  return "ItemNotfound";
         case 405:  return "Bad Method";    
         case 413:  return "Overlimit";
         case 503:  return "serviceUnavailable ";
         default: return "No response";
         
	}
	
}	
}
