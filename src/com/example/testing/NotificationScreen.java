package com.example.testing;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationScreen extends Activity {

	public static final String DATA_PREFERENCES_USER = "AddUser";
	SharedPreferences example ;
	int child;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_notify_screen);
		int x = 2;
		example = getSharedPreferences(DATA_PREFERENCES_USER, MODE_PRIVATE);
		
		final LinearLayout ll = (LinearLayout)findViewById(R.id.notification_linear_layout);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		if (example.getString("fl3","-1")=="0")
			x++;
		
		for (int i=0; i<x ; i++){
			Button button = new Button(this);
			if(i==0)
			{
				button.setText("Ramesh");
				button.setTag("Ramesh");
				button.setEnabled(!example.getBoolean("clicked1", false));
			}
			else if (i==1)
			{
				button.setText("Suresh");
				button.setTag("Suresh");
				button.setEnabled(!example.getBoolean("clicked2", false));
			}
			else if(i==2)
			{
				button.setText(example.getString("name", "Mahesh"));
				button.setTag(example.getString("name", "Mahesh"));
				button.setEnabled(!example.getBoolean("clicked1", false));
			}
			
			button.setOnClickListener(new OnClickListener() {	
							
				@Override
				public void onClick(View v) {
					AlertDialog.Builder adb = new AlertDialog.Builder(v.getContext());
					final upload thisone = new upload();
					
					if(v.getTag()=="Ramesh")
					{	
						thisone.uname = "Ramesh";
						thisone.udob = "19/01/2014";
						thisone.uaddr = "Gachibowli, Hyderabad";
						child = 1;
					}
					
					else if(v.getTag()=="Suresh")
					{	
						thisone.uname = "Suresh";
						thisone.udob = "19/01/2014";
						thisone.uaddr = "Gachibowli, Hyderabad";
						child = 2;
					}
					else 
					{
						thisone.uname = example.getString("name", "Mahesh");
						thisone.udob = example.getString("dob"+thisone.uname, "19/01/2014");
						thisone.uaddr = example.getString("geb"+thisone.uname, "Gachibowli, Hyderabad");
						child = 3;
					}
					adb
				    .setTitle("Confirm")
				    .setMessage("Are you sure "+v.getTag()+" has attended this camp")
				    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog1, int which) { 
				            // submit it to the server
				        	thisone.execute();
				        }
				     })
				    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
				        @SuppressLint("ShowToast")
						public void onClick(DialogInterface dialog, int which) { 
				        	Toast.makeText(getApplicationContext(), "Confirmation cancled", 0).show();
				        }
				     })
				     .show();
				}
			});
			ll.addView(button);
		}	
	}
	public class upload extends AsyncTask<Void, Void, Void>{

		String uname = "";
		String udob = "";
		String uaddr = "";
		Button send ;
		TextView tv;
		HttpPost httppost;
		StringBuffer buffer;
		HttpResponse response;
		HttpClient httpclient;
		String returnResponse;
		List<NameValuePair> nameValuePairs;
		
		private final ProgressDialog dialog = new ProgressDialog(NotificationScreen.this);
		
		protected void onPreExecute(){
		this.dialog.setMessage("Please wait, uploading...");
		this.dialog.setTitle("Uploading");
		this.dialog.show();
		}
		
		protected Void doInBackground(Void... params) {
			login();
			return null;
		}
		void login(){
            try{                   
                     
                    httpclient=new DefaultHttpClient();
                    httppost= new HttpPost("http://felicity.iiit.ac.in/~breakin_beta/appathon/enter.php"); // make sure the url is correct.
                    //add your data
                    nameValuePairs = new ArrayList<NameValuePair>(2);
                    // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
                    nameValuePairs.add(new BasicNameValuePair("name",uname));  // $Edittext_value = $_POST['Edittext_value'];
                    nameValuePairs.add(new BasicNameValuePair("dob",udob));
                    nameValuePairs.add(new BasicNameValuePair("addr",uaddr));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    returnResponse = httpclient.execute(httppost, responseHandler);
                    System.out.println("Response : " + response);
            }catch(Exception e){
            		returnResponse = e.getMessage();
                    System.out.println("Exception : " + e.getMessage());
            }
		}
		
		@SuppressLint("ShowToast")
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			this.dialog.dismiss();
			if(returnResponse.contains("Entered"))
			{
				Toast.makeText(getApplicationContext(), "Confirmed", 0).show();
				switch(child)
				{
				case 1:
					example.edit().putBoolean("clicked1" , true).commit();
					break;
				case 2:
					example.edit().putBoolean("clicked2", true).commit();
					break;
				case 3:
					example.edit().putBoolean("clicked3", true).commit();
					break;
				default:
					System.out.println("DAFUQ BRO?!");
					break;
				}
				finish();
				Intent intent = new Intent (getApplicationContext(),NotificationScreen.class);
				startActivity(intent);
			}
			else 
			{
				Toast.makeText(getApplicationContext(), returnResponse , 0).show();
			}
		}
	}
		}
		

