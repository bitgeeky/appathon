package com.example.testing;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class StartScreen extends Activity{
	public static final String DATA_PREFERENCES_USER = "AddUser";
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_start_screen);
		SharedPreferences example = getSharedPreferences(DATA_PREFERENCES_USER, 0);
		Button vb = (Button)findViewById(R.id.vacbutton);
		Button fb = (Button)findViewById(R.id.family_button);
		Button nb = (Button)findViewById(R.id.notification_button);
		Button ib = (Button)findViewById(R.id.instructions_button);
		vb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartScreen.this, VaccinationScreen.class));
			}
		});
vb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartScreen.this, VaccinationScreen.class));
			}
		});
fb.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(StartScreen.this, Family.class));
	}
});
nb.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(StartScreen.this, NotificationScreen.class));
	}
});
ib.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(StartScreen.this, Instructions.class));
	}
});

	
	
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(StartScreen.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("Exit me", true);
		startActivity(intent);
		finish();

		
	}
	
}
