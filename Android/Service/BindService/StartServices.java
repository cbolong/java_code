package com.example.showservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class StartServices extends Service {
	private static final String TAG = "ShowServices";
	private final MyIBinder myIBinder = new MyIBinder();  
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "#####VODKA: Bind - I am binding !");
		return myIBinder;
	}
	
    public class MyIBinder extends Binder {  
        public StartServices getService() {
            return StartServices.this; 
        }  
    }
	
    public void download() {
    	Log.d(TAG, "#####VODKA: Bind - I am downloading !");
    }
	public void onCreate() {
		Log.d(TAG, "#####VODKA: Bind - I Create servies !");
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "#####VODKA: Bind - I am in start servies !");
		stopSelf();
		return super.onStartCommand(intent, flags, startId);		
	}
	
	public void onDestroy() {  
		Log.d(TAG, "#####VODKA: Bind - I am in stop servies !");
		super.onDestroy();  
	}
}
