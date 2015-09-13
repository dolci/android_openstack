package com.stage.openstackmonit;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import com.stage.openstackmonit.adapter.ImageAdapter;
import com.stage.openstackmonit.adapter.UserAdapter;
import com.stage.openstackmonit.data.Access;
import com.stage.openstackmonit.data.AdressServer;
import com.stage.openstackmonit.data.Image;
import com.stage.openstackmonit.data.User;
import com.stage.openstackmonit.http.HttpGet_;
import com.stage.openstackmonit.jsonconvert.Listimage;
import com.stage.openstackmonit.jsonconvert.Listuser;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Images extends DashboardActivity  {
	
	 
	 List<Image>lis_image;
	 ListView list;
	 private ImageAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listobject);
		list = (ListView) findViewById(R.id.list);
	//	setHeader(getString(R.string.ImageActivityTitle), true, true);
		
		ImageTask img = new ImageTask();
		img.execute();
			
	}
	protected void onResume() {
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}
	
	private class ImageTask extends AsyncTask<Void,Void,JSONObject> {
	    
		//String resul="";
		String adress=new AdressServer().getUrl()+":8774/v3/"+Access.getInstance().getIdTenant()+"/images/detail";
		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject json = null;
			HttpGet_ imageHttp=new HttpGet_(adress,Access.getInstance().getToken());//Access.getInstance().getToken());
			try {
				if(imageHttp.CodeConnection()==200)
				json= imageHttp.getMethod();
				else
					{
					 json= new JSONObject().put("fail",imageHttp.display(imageHttp.CodeConnection()));
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
		}
	 @Override
        protected void onPostExecute(JSONObject json) {
		 try {
         	if (json.has("fail"))
         	{
         		Toast.makeText(getApplicationContext(),json.getString("fail") , 10).show();
         		
         	}
         	else
         	{
         		lis_image= new Listimage(json).getListImage();
        		 adapter = new ImageAdapter(getApplicationContext(),lis_image);
                list.setAdapter(adapter);
         	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
            
		 }
		 
	}
	
}
