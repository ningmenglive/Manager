package com.example.secretary;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class LoginActivity extends Activity {
    ImageButton login;
    EditText et_email,et_pwd;
    String email;
    String password;
    Context m;
    Handler han=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	    m=this;
		findView();
		init();
	}
	private void findView() {
		// TODO Auto-generated method stub
		et_email=(EditText) findViewById(R.id.login_email);
		et_pwd=(EditText) findViewById(R.id.login_passward);
		login=(ImageButton) findViewById(R.id.btn_login);
	}
	private void init() {
		// TODO Auto-generated method stub
	
		et_email.setText("755327458@qq.com");
		et_pwd.setText("755327458");
		login.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				email=et_email.getText().toString();
				password=et_pwd.getText().toString();
				isexist();
				
			}
		});
	}
	protected void isexist() {
		// TODO Auto-generated method stub
		
		String url = "http://6.worldcup2.sinaapp.com/secretary/login.php";
		Map<String,String> map=new HashMap();
		map.put("email", email);
		map.put("pwd",password);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST,url,json,new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				String status=null;
				try {
					status=response.getString("status");
					if(status.equals("Success")){
						MyApplication.email=email;
						MyApplication.username=response.getString("name");
						han.post(new Runnable() {		
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Intent intent=new Intent(LoginActivity.this,MainActivity.class);
								startActivity(intent);
								finish();
							}
						});
					
						
						
						
						
					}else{
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("1",error.toString());
			}
		});
		
		MyApplication.mRequestQueue.add(jr);
	}
}
