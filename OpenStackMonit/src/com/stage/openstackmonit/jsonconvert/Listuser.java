package com.stage.openstackmonit.jsonconvert;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import com.stage.openstackmonit.data.User;

public class Listuser {
	JSONArray users;
	
	public Listuser(JSONObject json ) throws Exception
	{
		this.users=json.getJSONArray("users");
	}
  public ArrayList<User>getListUser() throws Exception
  { 
	  ArrayList<User> list=new ArrayList<User>();
	  
	  for(int i = 0; i < users.length(); i++)
		 {
	            JSONObject c = users.getJSONObject(i);
	           User user=new User();
	            user.setEmail(c.getString("email"));
	            user.setEnabled(c.getBoolean("enabled"));
	            user.setId(c.getString("id"));
	            user.setusername(c.getString("name"));
	            
	           
	           list.add(user);
		 }
	 
	  return list;
  }
}
