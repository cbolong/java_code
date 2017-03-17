package com.example.vodka;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		setListener();
		/*final Button button = (Button) findViewById(R.id.button1);
		
				final TextView textView = (TextView)findViewById(R.id.textView5); 
		
				button.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						EditText weight_field = (EditText)findViewById(R.id.editText1);
						float weight = Integer.valueOf(w.getText().toString());
						EditText height_field = (EditText)findViewById(R.id.editText2);
						float height = Integer.valueOf(h.getText().toString());
						float bmi=weight/((height*height)/10000);
						textView.setText(Float.toString(bmi));
					}

		});*/
	}
	private void setListener() {
		// TODO Auto-generated method stub
		buttom_cal.setOnClickListener(calcBMI);
	}
	private Button.OnClickListener calcBMI = new Button.OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			float weight = Integer.valueOf(weight_field.getText().toString());
			float height = Float.valueOf(height_field.getText().toString())/100;
			float bmi=weight/((height*height));
			show.setText(Float.toString(bmi));
		}
	
	};
	
	private EditText weight_field;
	private EditText height_field;
	private TextView show;
	private Button buttom_cal;
	
	private void findView() {
		// TODO Auto-generated method stub
		weight_field = (EditText)findViewById(R.id.editText1);
		height_field = (EditText)findViewById(R.id.editText2);
		buttom_cal = (Button)findViewById(R.id.button1);
		show = (TextView)findViewById(R.id.textView5);
	}

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
