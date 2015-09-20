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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddMemActivity extends Activity {
	EditText etname;
	TextView tv;
	Button bt;
	int pid=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addmem);
		Intent intent=getIntent();
		pid=intent.getIntExtra("pid",0);
		findView();
		init();
	}
	private void findView() {
		// TODO Auto-generated method stub
		etname=(EditText) findViewById(R.id.pro_name);
		tv=(TextView) findViewById(R.id.item_text_image_title);
		bt=(Button) findViewById(R.id.work_fragment_publish_btn);
	}
	private void init() {
		// TODO Auto-generated method stub
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="http://6.worldcup2.sinaapp.com/secretary/AddMem.php";
				Map map=new HashMap();
				map.put("pid",pid);
				map.put("name",etname.getText().toString());
				JSONObject json=new JSONObject(map);
				JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Log.d("4",response.toString());
						String a=null;
						try {
							a=response.getString("status");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(a.equals("Success")){
							tv.setText("添加成功");
						}else{
							tv.setText("找不到该用户");
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.d("4",error.toString());
					}
				});
				MyApplication.mRequestQueue.add(jr);
			}
		});
	}
}
