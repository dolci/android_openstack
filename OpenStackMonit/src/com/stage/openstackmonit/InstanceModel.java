package com.stage.openstackmonit;



import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class InstanceModel extends DashboardActivity {
	
	 public EditText name,status,adresse,image,ram,disque;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instancemodel);
		name=(EditText)findViewById(R.id.name);
		status=(EditText)findViewById(R.id.stauts);
		adresse=(EditText)findViewById(R.id.adresse);
		image=(EditText)findViewById(R.id.nomimage);
		ram=(EditText)findViewById(R.id.ram);
		disque=(EditText)findViewById(R.id.disque);
		name.setText("fed");
		status.setText("Shutoff");
		adresse.setText("192.168.10.106,  172.24.4.237");
		image.setText("Fedora");
		ram.setText("512Mo");
		disque.setText("20Go");
		 
	}
}
