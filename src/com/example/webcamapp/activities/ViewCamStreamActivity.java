package com.example.webcamapp.activities;

import java.util.HashMap;
import java.util.Map;

import com.example.webcamapp.R;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class ViewCamStreamActivity extends Activity implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {
	final static String USERNAME = "";
	final static String PASSWORD = "";
	/*final static String RTSP_URL = "rtsp://50.182.79.184:8554/movie";*/
	final static String RTSP_URL = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";
	
	private MediaPlayer _mediaPlayer;
	private SurfaceHolder _surfaceHolder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set up a full-screen black window.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.setBackgroundDrawableResource(android.R.color.black);
		setContentView(R.layout.activity_view_cam_stream);

		// Configure the view that renders live video.
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.viewCamStream);
		_surfaceHolder = surfaceView.getHolder();
		_surfaceHolder.addCallback(this);
		_surfaceHolder.setFixedSize(320, 240);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		_mediaPlayer = new MediaPlayer();
		_mediaPlayer.setDisplay(_surfaceHolder);

		Context context = getApplicationContext();
		Map<String, String> headers = getRtspHeaders();
		Uri source = Uri.parse(RTSP_URL);

		try {
			// Specify the IP camera's URL and auth headers.
			_mediaPlayer.setDataSource(context, source, headers);

			// Begin the process of setting up a video stream.
			_mediaPlayer.setOnPreparedListener(this);
			_mediaPlayer.prepareAsync();
		} catch (Exception e) {
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		_mediaPlayer.release();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		_mediaPlayer.start();
		_mediaPlayer.setVolume(0, 1.0f);
		_mediaPlayer.getAudioSessionId();
	}

	private Map<String, String> getRtspHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		//String basicAuthValue = getBasicAuthValue(USERNAME, PASSWORD);
		//headers.put("Authorization", basicAuthValue);
		return headers;
	}

	private String getBasicAuthValue(String usr, String pwd) {
		String credentials = usr + ":" + pwd;
		int flags = Base64.URL_SAFE | Base64.NO_WRAP;
		byte[] bytes = credentials.getBytes();
		return "Basic " + Base64.encodeToString(bytes, flags);
	}
}
