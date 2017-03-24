private Button.OnClickListener startClickListener = new Button.OnClickListener() {
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {				
			@Override
			public void run() {
				//Looper.prepare();
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(MainActivity.this, "This is thread!", Toast.LENGTH_LONG);
				toast.show();
				//Looper.loop(); 
			}				
		}).start();
		
		runOnUiThread(new Runnable(){ 
			public void run(){ 
				Toast.makeText(MainActivity.this, "This is thread!", Toast.LENGTH_LONG).show();
			}
		});
	}
};