/**
 * 
 */
package com.stage.openstackmonit.data;

/**
 * @author Aspire
 *
 */
public class User {
	
	private String id;
	private String username;
	private String email;
	private boolean enabled;
	
	public String getId()
	{
		return id;
	}
	public String getUsername()
	{
		return username;
	}
	public String getEmail()
	{
		return email;
	}
	public boolean getEnabled()
	{
		return enabled;
	}
	public void setId(String id)
	{ this.id=id;}
	public void setusername(String id)
	{ username=id;}
	public void setEmail(String id)
	{ email=id;}
	public void setEnabled(boolean id)
	{ enabled=id;}
}
