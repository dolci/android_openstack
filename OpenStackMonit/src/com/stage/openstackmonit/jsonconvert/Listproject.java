package com.stage.openstackmonit.jsonconvert;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stage.openstackmonit.data.Project;

public class Listproject {

	JSONArray projects;
	
	public Listproject(JSONObject json ) throws Exception
	{
		this.projects=json.getJSONArray("projects");
	}
  public ArrayList<Project>getListProject() throws Exception
  { 
	  ArrayList<Project> list=new ArrayList<Project>();
	  
	  for(int i = 0; i < projects.length(); i++)
		 {
	            JSONObject c = projects.getJSONObject(i);
	           Project project=new Project();
	            //project.setDescri(c.getString(""));
	            project.setEnabled(c.getBoolean("enabled"));
	            project.setId(c.getString("id"));
	            project.setName(c.getString("name")); 
	           
	           list.add(project);
		 }
	 
	  return list;
  }
  }
