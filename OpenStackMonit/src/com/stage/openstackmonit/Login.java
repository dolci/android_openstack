package com.stage.openstackmonit;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.stage.openstackmonit.data.*;
import com.stage.openstackmonit.http.HttpGet_;
import com.stage.openstackmonit.http.HttpPost_;
import com.google.gson.*;
public class Login extends Activity {

	protected ProgressBar progressBar;
	/** The txt username. */
	EditText txtUsername;
	/** The txt password. */
	EditText txtPassword;
	String tenant_name="";
	 Access acc;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
		progressBar.setIndeterminate(true);
		progressBar.bringToFront();
		progressBar.setVisibility(View.GONE);
		txtUsername = (EditText) findViewById(R.id.logintext);
		txtPassword = (EditText) findViewById(R.id.password);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		String[] tenants = getResources().getStringArray(R.array.tenant_name);
		Button btLogin = (Button) findViewById(R.id.login);
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,tenants);
	    spinner.setAdapter(adapter);
	    
	    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {         @Override
	         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	           
	    	tenant_name=adapter.getItem(position);
	         }

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	 
	        
	      });
		btLogin.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View v) {
				
				progressBar.setVisibility(View.VISIBLE);
				LoginTask loginTask = new LoginTask();
				loginTask.execute();
			}
		});
		
         
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	protected void onResume() {
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}
	
	
	
	
	private class LoginTask extends AsyncTask<Void, Void,String> {
		
        String adress_server= new AdressServer().getUrl();
        String resul="";
       
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject resp=null;
		
				JsonObject auth = new JsonObject();
				JsonObject json = new JsonObject();
		        auth.add("auth", json);
		        json.addProperty("tenant",tenant_name );
		        JsonObject pCredentials = new JsonObject();
	            json.add("passwordCredentials", pCredentials);
	            pCredentials.addProperty("username",txtUsername.getText().toString());
	            pCredentials.addProperty("password",txtPassword.getText().toString() );
	        	HttpPost_ httpost=new HttpPost_(adress_server+":5000/v2.0/tokens",auth.toString());
	        	try {
					resp= httpost.postMethod();
					
					 //Log.i("tokenre",resp.toString());
					if(resp!=null)
					{ 
						 resul="succ";
						 JSONObject resp_acess=resp.getJSONObject("access");
			             JSONObject resp_token=resp_acess.getJSONObject("token");
			             // Token
			            
			             Access.getInstance().setToken(resp_token.getString("id"));
			             Log.i("token0",resp_token.toString());
			             HttpGet_ httpget=new HttpGet_(adress_server+":5000/v2.0/tenants", Access.getInstance().getToken());
					    
			             if(httpget.getMethod()!=null)
					     {
					    	 JSONArray resp_tenant=httpget.getMethod().getJSONArray("tenants");
				               for(int i=0;i<resp_tenant.length();i++)
				            	   if(resp_tenant.getJSONObject(i).getString("name").equals(tenant_name))
				            	   { Access.getInstance().setId_Tenant(resp_tenant.getJSONObject(i).getString("id"));
				            	   Log.i("users",resp_tenant.getJSONObject(i).toString());
				            	   }
					     }
			             
					
					}
					else
					{
						resul=httpost.display(httpost.CodeConnection());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			return resul;
}

		
		protected void onPostExecute(String resul) {
			super.onPostExecute(null);
			progressBar.setVisibility(View.GONE);
			
			if (resul.equals("succ")) {
				
				Intent intent = new Intent(Login.this,
						HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(getBaseContext(), resul, Toast.LENGTH_LONG).show();
			}
	}
		
}
}