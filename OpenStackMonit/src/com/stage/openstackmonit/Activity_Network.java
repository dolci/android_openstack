package com.stage.openstackmonit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Activity_Network extends DashboardActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__network);
		 setTitleFromActivityLabel (R.id.title_text);
	}

	
}
