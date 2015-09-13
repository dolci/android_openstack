package com.stage.openstackmonit.jsonconvert;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stage.openstackmonit.data.Instance;

public class Listinstance {
JSONArray instances;
	
	public Listinstance(JSONObject json ) throws Exception
	{
		this.instances=json.getJSONArray("servers");
	}
  public ArrayList<Instance>getListInstance() throws Exception
  { 
	  ArrayList<Instance> list=new ArrayList<Instance>();
	  
	  for(int i = 0; i < instances.length(); i++)
		 {
	            JSONObject c = instances.getJSONObject(i);
	           Instance ins=new Instance();
	           ins.setId(c.getString("id"));
	           ins.setStatus(c.getString("status"));
	           ins.setName(c.getString("name"));
	           ins.setZone(c.getString("OS-EXT-AZ:availability_zone"));
	       //    ins.getImage(c.get("image"));   
	           list.add(ins);
		 }
	 
	  return list;
  }
}
