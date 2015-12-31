package com.example.webcamapp.activities;

import com.example.webcamapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CamSelectActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cam_select);
	}
	
	public void onCamNumberClicked(View v){
		Intent intent = new Intent(this, ViewCamStreamActivity.class);
		startActivity(intent);
	}
}