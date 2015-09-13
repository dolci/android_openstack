package com.stage.openstackmonit.jsonconvert;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.stage.openstackmonit.data.Image;


public class Listimage {
JSONArray images;
	
	public Listimage(JSONObject json ) throws Exception
	{
		this.images=json.getJSONArray("images");
		Log.i("image",images.toString());
	}
  public ArrayList<Image>getListImage() throws Exception
  { 
	  ArrayList<Image> list=new ArrayList<Image>();
	  
	  for(int i = 0; i < images.length(); i++)
		 {
	            JSONObject c = images.getJSONObject(i);
	           Image img=new Image();
	            img.setName(c.getString("name"));
	            img.setCreated(c.getString("created"));
	            img.setId(c.getString("id"));
	            img.setMinDisk(c.getInt("minDisk"));
	            img.setMinRam(c.getInt("minRam"));
	            img.setSize( Math.round(c.getInt("size")/1048576));
	           list.add(img);
		 }
	 
	  return list;
  }

}
