package com.example.secretary;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddProjectActivity extends Activity {
	EditText pname,pdate,ptime,pinfo;
	Button submit;
	Handler han=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_addpro);
    	findView();
    	init();
    }
	private void findView() {
		// TODO Auto-generated method stub
		pname=(EditText) findViewById(R.id.pro_name);
		pdate=(EditText) findViewById(R.id.date_show);
		ptime=(EditText) findViewById(R.id.time_show);
		pinfo=(EditText) findViewById(R.id.work_fragment_note);
		submit=(Button) findViewById(R.id.work_fragment_publish_btn);	
	}
	private void init() {
		// TODO Auto-generated method stub
		submit.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String pn=pname.getText().toString();
				String pd=pdate.getText().toString();
				String pt=ptime.getText().toString();
				String pi=pinfo.getText().toString();
				String url="http://6.worldcup2.sinaapp.com/secretary/AddProject.php";
				Map map=new HashMap();
				map.put("pname",pn);
				map.put("pinfo",pi);
				map.put("pc",MyApplication.email);
				JSONObject json=new JSONObject(map);
				JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Log.d("2",response.toString());
						han.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								finish();
							}
						});
					}
				},new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.d("",error.toString());
					}
				});
				MyApplication.mRequestQueue.add(jr);
			}
		});
	}
}
