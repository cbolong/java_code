package com.example.showservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartServices extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate() {
		Log.d("ShowServices", "#####VODKA: I Create servies !");
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("ShowServices", "#####VODKA: I am in start servies !");
		stopSelf();
		return super.onStartCommand(intent, flags, startId);		
	}
	
	public void onDestroy() {  
		Log.d("ShowServices", "#####VODKA: I am in stop servies !");
		super.onDestroy();  
	}
}
