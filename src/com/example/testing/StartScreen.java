package com.example.testing;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StartScreen extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_start_screen);
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
//		startActivity(new Intent(StartScreen.this, .class));
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
	
}
