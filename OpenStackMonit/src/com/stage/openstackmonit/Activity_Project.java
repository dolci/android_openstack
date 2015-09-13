package com.stage.openstackmonit;



import java.util.List;

import org.json.JSONObject;


import com.stage.openstackmonit.adapter.ProjectAdapter;
import com.stage.openstackmonit.adapter.UserAdapter;
import com.stage.openstackmonit.data.AdressServer;
import com.stage.openstackmonit.data.Project;
import com.stage.openstackmonit.http.HttpGet_;
import com.stage.openstackmonit.jsonconvert.Listproject;
import com.stage.openstackmonit.jsonconvert.Listuser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Project extends DashboardActivity {

	List<Project>lis_project;
    ListView list;
    private ProjectAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listobject);
		 list = (ListView) findViewById(R.id.list);
		 setTitleFromActivityLabel (R.id.title_text);
		ProjectTask project_t = new ProjectTask();
		  project_t.execute();
	}
	protected void onResume() {
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}
private class ProjectTask extends AsyncTask<Void,Void,JSONObject> {
	    
		//String resul="";
		String adress=new AdressServer().getUrl()+":5000/v3/projects";
		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject json = null;
			HttpGet_ projectHttp=new HttpGet_(adress,"MIIQKgYJKoZIhvcNAQcCoIIQGzCCEBcCAQExCTAHBgUrDgMCGjCCDoAGCSqGSIb3DQEHAaCCDnEEgg5teyJhY2Nlc3MiOiB7InRva2VuIjogeyJpc3N1ZWRfYXQiOiAiMjAxNC0wOS0yOFQyMTowMjo1OS4xMTA0MjYiLCAiZXhwaXJlcyI6ICIyMDE0LTA5LTI5VDIxOjAyOjU5WiIsICJpZCI6ICJwbGFjZWhvbGRlciIsICJ0ZW5hbnQiOiB7ImRlc2NyaXB0aW9uIjogImFkbWluIHRlbmFudCIsICJlbmFibGVkIjogdHJ1ZSwgImlkIjogIjUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgIm5hbWUiOiAiYWRtaW4ifX0sICJzZXJ2aWNlQ2F0YWxvZyI6IFt7ImVuZHBvaW50cyI6IFt7ImFkbWluVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc0L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzQvdjIvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgiLCAiaWQiOiAiMTJkMzdmMWRkYTgzNDNjODliMGVmNTFiMzJjNTg5NGUiLCAicHVibGljVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc0L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4In1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImNvbXB1dGUiLCAibmFtZSI6ICJub3ZhIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojk2OTYvIiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojk2OTYvIiwgImlkIjogIjAyOTAwMjdiNjk3ZjQ4N2RhM2YxMGQyNDMzM2MzZWVkIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6OTY5Ni8ifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAibmV0d29yayIsICJuYW1lIjogIm5ldXRyb24ifSwgeyJlbmRwb2ludHMiOiBbeyJhZG1pblVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92Mi81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCIsICJyZWdpb24iOiAiUmVnaW9uT25lIiwgImludGVybmFsVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc2L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgImlkIjogIjE0OGY5M2NkODc0YjQ2ZDc4ZTg5MWQzZWI1ZDE1MWZkIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92Mi81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCJ9XSwgImVuZHBvaW50c19saW5rcyI6IFtdLCAidHlwZSI6ICJ2b2x1bWV2MiIsICJuYW1lIjogImNpbmRlcnYyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjgwODAiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MCIsICJpZCI6ICI5YmYwNjdhYTkzOGY0NzZjOTM5Yjc1MzVhYWE2NGU1NCIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjgwODAifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAiczMiLCAibmFtZSI6ICJzd2lmdF9zMyJ9LCB7ImVuZHBvaW50cyI6IFt7ImFkbWluVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo5MjkyIiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjkyOTIiLCAiaWQiOiAiM2NjZDM2ZmJkNDEzNDA3ZThlNDExMmFhOTJhZmRkNGYiLCAicHVibGljVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo5MjkyIn1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImltYWdlIiwgIm5hbWUiOiAiZ2xhbmNlIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzciLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3NyIsICJpZCI6ICIxZDU3MzcxNGU5ZmE0MzBhOWU4ZmRkNTA5ODk2YWI4ZCIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzcifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAibWV0ZXJpbmciLCAibmFtZSI6ICJjZWlsb21ldGVyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzYvdjEvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92MS81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCIsICJpZCI6ICI5MGY0ZWNjN2NkMzQ0ODk4YjJiZjc1OWIyZmVlYmY1MiIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzYvdjEvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAidm9sdW1lIiwgIm5hbWUiOiAiY2luZGVyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzMvc2VydmljZXMvQWRtaW4iLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3My9zZXJ2aWNlcy9DbG91ZCIsICJpZCI6ICI2YzFhMTkzN2RiYWQ0MzM0OGJiODUyNGI5Y2EzZTgzMyIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzMvc2VydmljZXMvQ2xvdWQifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAiZWMyIiwgIm5hbWUiOiAibm92YV9lYzIifSwgeyJlbmRwb2ludHMiOiBbeyJhZG1pblVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC8iLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC92MS9BVVRIXzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgImlkIjogIjBhNjQ2M2MyNzNmYzQ1Nzk5ZmZkNmUyYmFmYjFkNGJiIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC92MS9BVVRIXzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4In1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogIm9iamVjdC1zdG9yZSIsICJuYW1lIjogInN3aWZ0In0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjM1MzU3L3YyLjAiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6NTAwMC92Mi4wIiwgImlkIjogIjE0ZmZiNDAzN2Q3MTQ2NWU4YWJhNjAzYmVjNjVkNzdlIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6NTAwMC92Mi4wIn1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImlkZW50aXR5IiwgIm5hbWUiOiAia2V5c3RvbmUifV0sICJ1c2VyIjogeyJ1c2VybmFtZSI6ICJhZG1pbiIsICJyb2xlc19saW5rcyI6IFtdLCAiaWQiOiAiYWJhZjQ4YWE0ZGJmNGQzNjgwN2QyZjJlNDM5MjJiMjQiLCAicm9sZXMiOiBbeyJuYW1lIjogImFkbWluIn1dLCAibmFtZSI6ICJhZG1pbiJ9LCAibWV0YWRhdGEiOiB7ImlzX2FkbWluIjogMCwgInJvbGVzIjogWyJhYWM1N2FmZDAxMDY0ZWFlOGQ0NjU5NGE0Yjc0N2YyMiJdfX19MYIBgTCCAX0CAQEwXDBXMQswCQYDVQQGEwJVUzEOMAwGA1UECAwFVW5zZXQxDjAMBgNVBAcMBVVuc2V0MQ4wDAYDVQQKDAVVbnNldDEYMBYGA1UEAwwPd3d3LmV4YW1wbGUuY29tAgEBMAcGBSsOAwIaMA0GCSqGSIb3DQEBAQUABIIBABCwznBbxnpgH5YsADwnDa8yxKm6JpiYtzmzn0cc6KRJDiRd6fcfWhqkr37Ie52mwu5RZqDLXkTZsAZhRV7jz5HH+UNvS0dkL81l7XAvINo82jwMFDIxt55wjeR4efm11HPNi6fKEFmYPgqF1x8gr0eREX1ki1OcA6B7Gbd-OLIkLC1sSzLGr6slm3A+Yx0EdeROhWY5TcBPe4HIH31w6N3b6PxqPp13JMlhYFjqln-HD1oqUdyC-Jm0hvuDO9NZ5RC1ATsBq8O4l0YPDzLJEXgBtU1Y20o2lCFfOvcgedLPxqaJsJ06cCLpwL5TD6Icbu5U-y3mbX1xMNETABiI5-0=");//Access.getInstance().getToken());
			try {
				if(projectHttp.CodeConnection()==200)
				json= projectHttp.getMethod();
				else
				{
					json= new JSONObject().put("fail",projectHttp.display(projectHttp.CodeConnection()));
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
            		lis_project= new Listproject(json).getListProject();
            		adapter = new ProjectAdapter(getApplicationContext(),lis_project);
                    list.setAdapter(adapter);
            	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
           
		 }
		 
	}
	
}
