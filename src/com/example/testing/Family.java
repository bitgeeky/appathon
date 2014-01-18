package com.example.testing;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class Family extends Activity implements OnItemClickListener
{
	public static final String DATA_PREFERENCES_USER = "AddUser";
    GridView gridview;
    GridViewAdapter gridviewAdapter;
    ArrayList<Item> data = new ArrayList<Item>();
    int cflag=0;
    String newname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family);
        SharedPreferences example = getSharedPreferences(DATA_PREFERENCES_USER, 0);
        Editor editor = example.edit();
        editor.putString("fl1","0");
        editor.putString("fl2","0");
        String un = example.getString("uname", "Rajesh Khanna");
        String uadr = example.getString("uadd", "11, Talawali Chanda, Dewas Naka");
        TextView u1 = (TextView) findViewById(R.id.username);
        TextView u2 = (TextView) findViewById(R.id.useraddr);
        TextView u3 = (TextView) findViewById(R.id.finfo);
        u3.setTextColor(Color.BLACK);
        u1.setText(un);
        u1.setTextColor(Color.BLACK);
        u2.setText(uadr);
        u2.setTextColor(Color.BLACK);
     //   String str = getIntent().getStringExtra("flag");
        String str = example.getString("fl3","-1");
        if(str.equalsIgnoreCase("-1"))
        {
            editor.putString("fl3","-1");
        	cflag=0;
        }
        else
        {
        	cflag=1;
     //       editor.putString("fl3","0");
        	newname = example.getString("name", "Newc");

        }
        editor.commit();
        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        setDataAdapter(); // Set the Data Adapter
        Button adm = (Button) findViewById(R.id.add);
        adm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Family.this,Add.class);
				startActivity(intent);
				
			}
		});
    }

    // Initialize the GUI Components
    private void initView()
    {
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setOnItemClickListener(this);
    }

    // Insert The Data
    private void fillData()
    {
        data.add(new Item("Ramesh", getResources().getDrawable(R.drawable.ramesh)));
        data.add(new Item("Suresh", getResources().getDrawable(R.drawable.suresh)));
        if(cflag==1)
        	data.add(new Item(newname, getResources().getDrawable(R.drawable.newc)));
//        data.add(new Item("Google", getResources().getDrawable(R.drawable.google)));
//        data.add(new Item("Yahoo", getResources().getDrawable(R.drawable.yahoo)));
//        data.add(new Item("YouTube", getResources().getDrawable(R.drawable.youtube)));
//        data.add(new Item("Flickr", getResources().getDrawable(R.drawable.flickr)));
//        data.add(new Item("Whatsapp", getResources().getDrawable(R.drawable.whatsapp)));
//        data.add(new Item("Blogger", getResources().getDrawable(R.drawable.blogger)));
    }

    // Set the Data Adapter
    private void setDataAdapter()
    {
        gridviewAdapter = new GridViewAdapter(getApplicationContext(), R.layout.row_grid, data);
        gridview.setAdapter(gridviewAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String message = "Clicked : " + data.get(position).getTitle() + ". Data to be entered.";
        if(position==2 && !(data.get(position).getTitle().equalsIgnoreCase("newc")))
        {
        	 Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
        }
        else
        {
        //	Log.d("Check",position+" ");
        	Intent intent = new Intent(Family.this, Profile.class);
        	SharedPreferences example = getSharedPreferences(DATA_PREFERENCES_USER, 0);
//        SharedPreferences sharedPref = this.getSharedPreferences(
//        		"dob"+data.get(position).getTitle(), Context.MODE_PRIVATE);
        //  SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        	String dob = example.getString("dob"+data.get(position).getTitle(), "20-10-1994");
        	String gen = example.getString("gen"+data.get(position).getTitle(), "Male");
        	String[] value = { data.get(position).getTitle() , dob ,gen};
        	intent.putExtra("info", value);
        	startActivity(intent);
        }
//        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	Intent intent = new Intent(Family.this,StartScreen.class);
    	startActivity(intent);
    	super.onBackPressed();
    }

}