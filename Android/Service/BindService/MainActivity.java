package com.example.showservices;

import com.example.showservices.StartServices.MyIBinder;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Intent it = new Intent();
	private connection conn = null;
	private StartServices myService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button start = (Button)findViewById(R.id.button1);
		Button stop = (Button)findViewById(R.id.button2);
		start.setOnClickListener(startService);
		stop.setOnClickListener(stopService);
	}
	
	private class connection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d("ShowServices", "#####VODKA: Bind services in activity !");
			MyIBinder myBinder = (MyIBinder) service;
			StartServices mBoundService = myBinder.getService();
			mBoundService.download();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d("ShowServices", "#####VODKA: Unbind services in activity !");
		}
		
	}
	private Button.OnClickListener startService = new Button.OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d("ShowServices", "#####VODKA: start servies !");			
			it.setClass(MainActivity.this, StartServices.class);
			conn = new connection();
			bindService(it, conn, Service.BIND_AUTO_CREATE);
		}
		
	};
	
	private Button.OnClickListener stopService = new Button.OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d("ShowServices", "#####VODKA: stop servies !");
			unbindService(conn); 
		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
