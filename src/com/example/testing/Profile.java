package com.example.testing;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Bundle input = getIntent().getExtras();
        String[] val = input.getStringArray("info");
        String name = val[0];
        String dob = val[1];
        String gen = val[2];

        ImageView img = (ImageView) findViewById(R.id.img);
        int picId = getResources().getIdentifier(name.toLowerCase(), "drawable", getApplicationContext().getPackageName());
        Log.i("Check", name+ " " +picId);
        Drawable d = getResources().getDrawable(picId);
        img.setImageDrawable(d);
        TextView na = (TextView) findViewById(R.id.name);
        na.setText(name);
        na.setTextColor(Color.BLACK);
        TextView db = (TextView) findViewById(R.id.dob);
        db.setText(dob);
        db.setTextColor(Color.BLACK);
        TextView gn = (TextView) findViewById(R.id.gen);
        gn.setText(gen);
        gn.setTextColor(Color.BLACK);
        TextView det = (TextView) findViewById(R.id.detail);
        det.setTextColor(Color.BLACK);
	}
}
