package com.example.secretary;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class MyApplication extends Application {

	public static RequestQueue mRequestQueue;
	static int id=0;
	static String username=null;
	static String info=null;
	@Override
	public void onCreate() {
		
		mRequestQueue=Volley.newRequestQueue(this);
	}
	

	
	
	
}
