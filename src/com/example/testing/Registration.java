package com.example.testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_registration);
		Button done = (Button)findViewById(R.id.registration_done);
		final EditText name = (EditText)findViewById(R.id.name_input);
		final EditText address = (EditText)findViewById(R.id.address_input);
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(name.getText().toString()==""||address.getText().toString()=="")
				{
					//do some shit to verify that the input is valid
				}
				else
				{
					//this is to start the new intent with required data being sent to it.
					
					//Intent intent = new Intent(getApplicationContext(),someClass.class);
					//intent.putExtra("registration_name", name.getText().toString());
					//intent.putExtra("registration_address", address.getText().toString());
				}
			}
		});
	}
}
