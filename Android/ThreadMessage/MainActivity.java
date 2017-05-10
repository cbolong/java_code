package com.example.threadex;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv;
	Button start;
	Button cancel;
	Handler mHandler;
	Thread t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView)this.findViewById(R.id.textView1);
		tv.setText("Test Start\n");
		
		start=(Button) this.findViewById(R.id.button1);
		cancel=(Button) this.findViewById(R.id.button2);
		t = new Thread(run);
		
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					t.start();
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				t.interrupt();
			}
		});
		
		mHandler = new Handler(){
			int i = 0;
			public void handleMessage(Message msg) {
				switch(msg.what){
					case 1:
						i++;
						tv.setText("running"+Integer.toString(i));
						break;
					case 2:
						i++;
						tv.setText("end"+Integer.toString(i));
						break;	
				}
			}
		};
	}
	private Runnable run = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!Thread.currentThread().isInterrupted()) {
				try {
					Message msg = new Message();
					msg.what = 1;
					mHandler.sendMessage(msg);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Thread.currentThread().interrupt();
					//break;
				}
			}
			Message msg = new Message();
			msg.what = 2;
			mHandler.sendMessage(msg);
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
