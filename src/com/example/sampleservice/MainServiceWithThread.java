package com.example.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainServiceWithThread extends Service {

	public static final String TAG = "MainServiceWithThread";
	private final Handler handler = new Handler();

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		// main process in onCreate
		Toast.makeText(this, "service start (different thread).", Toast.LENGTH_SHORT).show();
		
		Log.d(TAG, "onCreate end.");
	}

	@Override
	public int onStartCommand(Intent intent, int flag, int startId) {
		Log.d(TAG, "onStartCommand start.");
		
		// main process in onStartCommand
		new Thread(cmd).start();
		
		Toast.makeText(MainServiceWithThread.this, "onStartCommand end.", Toast.LENGTH_SHORT).show();
		Log.d(TAG, "onStartCommand end.");
		
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// main process in onDestroy
		Toast.makeText(this, "service end (different thread).", Toast.LENGTH_SHORT).show();
		
		Log.d(TAG, "onDestroy end.");
	}
	
	/**
	 * main process executed in onStartCommand
	 */
	private Runnable cmd = new Runnable() {
		@Override
		public void run() {
			try {
				Log.d(TAG, "process start.");
				
				// main
				Thread.sleep(4000);
				
				// finish message is noted by toast
				handler.post(showFinishMsg);
				
				Log.d(TAG, "process end.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	// finish message to be noted by toast
	private Runnable showFinishMsg = new Runnable() {
		@Override
		public void run() {
			Toast.makeText(MainServiceWithThread.this, "process end.", Toast.LENGTH_SHORT).show();
		}
	};

}
