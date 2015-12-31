package com.example.webcamapp.activities;

import com.example.webcamapp.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class PlayAudioFileActivity extends Activity {
	
	MediaPlayer mPlayer;
	Uri uri;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_audio_file);
	}
	
	public void onPlayButtonClicked(View v) {
		/*
		 * mPlayer = MediaPlayer.create(PlayAudioFileActivity.this, R.raw.song);
		 */
		if (uri != null) {
			if (mPlayer != null) {
				mPlayer.stop();
			}
			mPlayer = MediaPlayer.create(PlayAudioFileActivity.this, uri);
			mPlayer.start();
		}
	}
	
	public void onSelectButtonClicked(View v){
		if(isExternalStorageReadable()){
			/*ExternalFileUtility fileUtil = new ExternalFileUtility();
			fileUtil.showFileChooser();*/
			showFileChooser();
		}else{
			// tell user app is not authorized to access external storage.
		}
	}
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	@Override
	public void onDestroy() {
		if(mPlayer!=null){
			mPlayer.stop();
		}
	    super.onDestroy();
	}
	
	
	
	private static final String TAG = "PlayAudioFileActivity";
	private static final int FILE_SELECT_CODE = 0;

	public void showFileChooser() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		try {
			startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
		} catch (android.content.ActivityNotFoundException ex) {
			// Potentially direct the user to the Market with a Dialog
			Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case FILE_SELECT_CODE:
			if (resultCode == RESULT_OK) {
				// Get the Uri of the selected file
				uri = data.getData();
				Log.d(TAG, "File Uri: " + uri.toString());
				// Get the path
				String path = "";
				// path = ExternalFileUtility.getPath(this, uri);
				/*mPlayer = MediaPlayer.create(PlayAudioFileActivity.this, uri);*/
				Log.d(TAG, "File Path: " + path);
				// Get the file instance
				// File file = new File(path);
				// Initiate the upload
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
