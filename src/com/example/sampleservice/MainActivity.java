package com.example.sampleservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // action to start service in the same thread as this activity
        Button btnStartNoThread = (Button) findViewById(R.id.startServiceNoThread);
        btnStartNoThread.setOnClickListener(lstnStartNoThread);

        // action to start service in a different thread from this activity
        Button btnStartWithThread = (Button) findViewById(R.id.startServiceWithThread);
        btnStartWithThread.setOnClickListener(lstnStartWithThread);

        // action to stop services
        Button btnStopService = (Button) findViewById(R.id.stopService);
        btnStopService.setOnClickListener(lstnStopService);

    }

    /**
     * action to start service in the same thread as this activity
     */
	private OnClickListener lstnStartNoThread = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, MainServiceNoThread.class);
			startService(intent);
		}
	};
	
	/**
	 * action to start service in a different thread from this activity
	 */
	private OnClickListener lstnStartWithThread = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, MainServiceWithThread.class);
			startService(intent);
		}
	};
	
	/**
	 * action to stop services
	 */
	private OnClickListener lstnStopService = new OnClickListener() {
		@Override
		public void onClick(View v) {
			stopService(new Intent(MainActivity.this, MainServiceNoThread.class));
			stopService(new Intent(MainActivity.this, MainServiceWithThread.class));
		}
	};

}
