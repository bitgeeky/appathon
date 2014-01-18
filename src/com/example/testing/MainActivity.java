package com.example.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);
        sleep splash = new sleep();
        splash.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public class sleep extends AsyncTask<Void, Void, Void>{

    	SharedPreferences myPrefs;
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Log.i("MyTag", "do in background");
			try {
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				Log.i("MyTag","exception"+e.getMessage());
			}
			Log.i("MyTag","done sleeping");
			done();
			return null;
		}
		
		protected void done(){
			Intent intent = new Intent();
			myPrefs = getSharedPreferences("stattup_preferences", Context.MODE_PRIVATE);
			Boolean first_start = myPrefs.getBoolean("first_start", true);
			Log.i("MyTag","first start - "+first_start);
			if(first_start){
				//need to call commit()
				myPrefs.edit().putBoolean("first_start", false).commit();
				Log.i("MyTag","first start after - "+myPrefs.getBoolean("first_start", true));
				intent.setClass(getApplicationContext(), Registration.class);
			}
			else {
				intent.setClass(getApplicationContext(), StartScreen.class);
			}
			startActivity(intent);
			finish();
		}
    	
    }
    
}
