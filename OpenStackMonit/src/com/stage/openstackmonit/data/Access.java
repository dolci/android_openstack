package com.stage.openstackmonit.data;

public class Access {

	private  static String token;
	private  static  String id_tenant;
	
	public static String getToken()
	{
		return token;
	}
	public static String getIdTenant()
	{
		return id_tenant;
	}
	public void setToken(String token)
	{
		this.token=token;
	}
	
	private static final Access acc = new Access();
	  public static Access getInstance() {return acc;}
	public void setId_Tenant(String id_tenant)
	{
		this.id_tenant=id_tenant;
	}
}
