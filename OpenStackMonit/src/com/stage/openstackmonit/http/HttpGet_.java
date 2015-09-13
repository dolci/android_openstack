package com.stage.openstackmonit.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.stage.openstackmonit.data.Access;

public class HttpGet_ extends Http_{
	
	
	String token;
	public HttpGet_(String url,String token)
	{
		super(url);
		this.token=token;
	}
	
	
	public int CodeConnection() throws ClientProtocolException, IOException
	{
		 HttpGet get = new HttpGet(url);
		 
		 get.setHeader("Accept-Encoding", "gzip,deflate,sdch");
        
         get.addHeader("X-Auth-Token",token);
          httpResponse = httpclient.execute(get);
        //  Log.i("imaget",httpResponse.getStatusLine().toString());
          return httpResponse.getStatusLine().getStatusCode();
	}
	
	
	public JSONObject getMethod() throws ClientProtocolException, IOException, ParseException, JSONException
     {
		JSONObject json=null;
    	 if(CodeConnection()==200)
    	 {
    		 HttpEntity resEntity = httpResponse.getEntity(); 
			 if (resEntity != null) 
			 {
				 json=new JSONObject(EntityUtils.toString(resEntity));
			 }
    	 }
    	 return json;
     }
	
	
}
