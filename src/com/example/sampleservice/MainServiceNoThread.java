package com.example.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainServiceNoThread extends Service {

	public static final String TAG = "MainServiceNoThread";
			
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		// main process in onCreate
		Toast.makeText(this, "service start (same thread).", Toast.LENGTH_LONG).show();
		
		Log.d(TAG, "onCreate end.");
	}

	@Override
	public int onStartCommand(Intent intent, int flag, int startId) {
		Log.d(TAG, "onStartCommand start.");
		
		try {
			Log.d(TAG, "process start.");
		
			// main process in onStartCommand
			Thread.sleep(4000);
			
			// finish message
			Toast.makeText(MainServiceNoThread.this, "process end.", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "process end.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Toast.makeText(MainServiceNoThread.this, "onStartCommand end.", Toast.LENGTH_SHORT).show();
		Log.d(TAG, "onStartCommand end.");
		
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		// main process in onDestroy
		Toast.makeText(this, "service end (same thread).", Toast.LENGTH_SHORT).show();
		
		Log.d(TAG, "onDestroy end.");
	}
	
}
