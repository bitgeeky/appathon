package com.example.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Add extends Activity {
	public static final String DATA_PREFERENCES_USER = "AddUser";
	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        
        final EditText name = (EditText) findViewById(R.id.aname);
        final EditText dob = (EditText) findViewById(R.id.adob);
        final EditText gen = (EditText) findViewById(R.id.agen);
        Button ok = (Button) findViewById(R.id.adduser);
        //final SharedPreferences sharedPref = this.getSharedPreferences(null, Context.MODE_PRIVATE);
        ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   
				SharedPreferences example = getSharedPreferences(DATA_PREFERENCES_USER, v.getContext().MODE_PRIVATE);
				Editor editor = example.edit();
      			editor.putString("name",name.getText().toString());
      			editor.putString("fl3","0");
		        editor.putString("dob"+name.getText().toString(),dob.getText().toString());
		        editor.putString("gen"+name.getText().toString(),gen.getText().toString());
//		        SharedPreferences.Editor editor = sharedPref.edit();
//		        editor.putString("name",name.getText().toString());
//		        editor.putString("dob"+name.getText().toString(),dob.getText().toString());
//		        editor.putString("gen"+name.getText().toString(),gen.getText().toString());
		        editor.commit();
		        Intent intent = new Intent(Add.this,Family.class);
		     //   intent.putExtra("flag", "1");
		        startActivity(intent);
			}
		});
    
	}

}
