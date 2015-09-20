package com.example.secretary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.secretary.fragement.MemberFragment.Member;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Int2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddTaskActivity extends Activity {
	EditText etname,etdate,ettime,etinfo;
	ListView memlistview;
	Handler han=new Handler();
	private Context mContext;
	Button bt;
	int pid=0;
	protected ArrayList<Member> mMemberList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addtask);
		mContext=this;
		Intent intent=getIntent();
		pid=intent.getIntExtra("pid",0);
		findView();
		init();
	}
	private void findView() {
		// TODO Auto-generated method stub
		etname=(EditText) findViewById(R.id.pro_name);
		memlistview=(ListView) findViewById(R.id.mem_list);
		bt=(Button)findViewById(R.id.work_fragment_publish_btn);
	}
	private void init() {
		// TODO Auto-generated method stub
		post();
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=etname.getText().toString();
				addtask(name);
			}
		});
	}
	
	protected void addtask(String name) {
		// TODO Auto-generated method stub
		String url="http://6.worldcup2.sinaapp.com/secretary/AddTask.php";
		Map map=new HashMap();
		map.put("pid",pid);
		map.put("content",name);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.d("2",response.toString());
				int sid=0;
				try {
					sid=response.getInt("sid");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				adds_u(sid);
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
	protected void adds_u(int sid) {
		// TODO Auto-generated method stub
		RelativeLayout current;
		CheckBox check;
		for(int i=0;i<mMemberList.size();i++){
			current=(RelativeLayout) memlistview.getChildAt(i);
			check=(CheckBox) current.findViewById(R.id.checkBox1);
			if(check.isChecked()){
				post_u(sid,mMemberList.get(i).email);
			}
		}
	}
	private void post_u(int sid, String email) {
		// TODO Auto-generated method stub
		String url="http://6.worldcup2.sinaapp.com/secretary/Adds_u.php";
		Map map=new HashMap();
		map.put("sid",sid);
		map.put("uemail",email);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.d("3",response.toString());
				
				
			}
		},new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("3",error.toString());
			}
		});
		MyApplication.mRequestQueue.add(jr);
	}
	private void post() {
		// TODO Auto-generated method stub
		String url="http://6.worldcup2.sinaapp.com/secretary/getPmember.php";
		Map map=new HashMap<String,Int2>();
		map.put("pid",pid);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				
				JSONArray ja = null;
				try {
					ja=response.getJSONArray("list");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mMemberList=new ArrayList<Member>();
				String email=null;
				String name=null;
				for(int i=0;i<ja.length();i++){
					JSONObject js=null;
					try {
						js=ja.getJSONObject(i);
						email=js.getString("email");
					    name=js.getString("name");
						mMemberList.add(new Member(email,name));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				han.post(new Runnable() {
					
					

					@Override
					public void run() {
						// TODO Auto-generated method stub
						memlistview.setAdapter(new selectAdapter(mContext, mMemberList));
					}
				});
			
				
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
	class selectAdapter extends BaseAdapter{
		private List<Member> mMemberList;
        private LayoutInflater mInflater;
		
		public selectAdapter(Context mContext, ArrayList<Member> mMemberList2) {
			// TODO Auto-generated constructor stub
			    super();
	            this.mMemberList = mMemberList2;
	            mInflater = LayoutInflater.from(mContext);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMemberList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return mMemberList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			Member task = mMemberList.get(arg0);
			TextView t1;
            String email=task.email;
            String name=task.name;
            arg1 = mInflater.inflate(R.layout.me_todos_li,null);   
            t1=(TextView) arg1.findViewById(R.id.textView2);
            t1.setText(name);
            return arg1;
			
		}
		
	}
	public class Member {
	    String email;
		String name;
		public Member(String email,String name){
         this.email=email;
         this.name=name;
	  }
	}
}
