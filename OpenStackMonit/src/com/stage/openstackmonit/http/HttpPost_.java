package com.stage.openstackmonit.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HttpPost_ extends Http_{
	
	private String param;
	private String token;
	public HttpPost_(String url,String param)
	{
		super(url);
		//this.setToekn(token);
		this.param=param;
		
	}
	public HttpPost_(String url,String param,String token)
	{
		super(url);
		//this.setToekn(token);
		this.param=param;
		this.token=token;
		
	}
	public int CodeConnection() throws IOException 
	{
		
		HttpPost httpost = new HttpPost(url);
		httpost.setHeader("Accept", "application/json");
		httpost.setHeader("Content-type", "application/json");
		httpost.setEntity(new StringEntity(param, "UTF-8"));
	    httpResponse = httpclient.execute(httpost);
	    Log.i("length",httpResponse.getFirstHeader("Content-Length").toString());
	    Log.i("Connection",httpResponse.getFirstHeader("Connection").toString());
	    return httpResponse.getStatusLine().getStatusCode();
	}
	public int CodeConnection_() throws IOException 
	{
		
		HttpPost httpost = new HttpPost(url);
		httpost.setHeader("Accept", "application/json");
		httpost.setHeader("Content-type", "application/json");
		httpost.addHeader("X-Auth-Token",token);
		httpost.setEntity(new StringEntity(param, "utf-8"));
	    httpResponse = httpclient.execute(httpost);
	    return httpResponse.getStatusLine().getStatusCode();
	}
	public JSONObject postMethod() throws ClientProtocolException, IOException, ParseException, JSONException
    {
		JSONObject json=null;
   	 if(CodeConnection()==200)
   	 {
   		 HttpEntity resEntity = httpResponse.getEntity(); 
   		
			 if (resEntity != null) 
			 {
				 json=new JSONObject(EntityUtils.toString(resEntity));
				 /* InputStream is = resEntity.getContent();
				    BufferedReader reader = new BufferedReader(
				      new InputStreamReader(is,"iso-8859-1"),8);
				    StringBuilder sb = new StringBuilder();
				    String line = null;
				   
				    StringBuffer stringBuffer = new StringBuffer();
		            while ((line = reader.readLine()) != null) {
		                stringBuffer.append(line);
		            }
				    is.close();
				    String vracString = stringBuffer.toString();
				 */
			 }
   	 }
   	 return json;
    }
}
