package com.example.webcamapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/*
	 * Clicked Handler for "selectCamButton".
	 */
	public void onSlctCamClicked(View v){
		Log.d("Main Activity", "Select cam button clicked.");
		Log.i("Main Activity", "Select cam button clicked.");
		Intent intent = new Intent(this, CamSelectActivity.class);
		startActivity(intent);
	}
}