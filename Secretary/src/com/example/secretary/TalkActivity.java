package com.example.secretary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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
import android.renderscript.Int2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TalkActivity extends Activity {
	int tid=0,pid=0;
	String tname=null;
	Context mContext;
	TextView titlebar,talktitle,protitle;
	ListView mTalkListView;
	List<Message> mMessages;
	ImageView back;
	EditText et;
	Button bt;
	Handler han=new Handler();
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk);
		mContext=this;
		Intent intent=getIntent();
		tid=intent.getIntExtra("tid",0);
		pid=intent.getIntExtra("pid",0);
		tname=intent.getStringExtra("tname");
		findView();
		init();
		
	}
	private void findView() {
		// TODO Auto-generated method stub
		titlebar=(TextView)findViewById(R.id.title_talk);
		talktitle=(TextView)findViewById(R.id.talk_title);
		protitle=(TextView)findViewById(R.id.project_title);
		mTalkListView=(ListView) findViewById(R.id.listview_talk);
		et=(EditText) findViewById(R.id.work_fragment_note);
		bt=(Button) findViewById(R.id.publish_btn);
		back=(ImageView) findViewById(R.id.common_title_back);
	
	}
	private void init(){
		// TODO Auto-generated method stub
		titlebar.setText(tname);
		talktitle.setText(tname);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				han.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
					 finish();	
					}
				});
			}
		});
		post();
		bt.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			  	String url="http://6.worldcup2.sinaapp.com/secretary/AddMessage.php";
			  	Map map=new HashMap();
			  	map.put("tid",tid);
			  	map.put("uemail",MyApplication.email);
			  	map.put("content",et.getText().toString());
			  	JSONObject json=new JSONObject(map);
			  	JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Log.d("1",response.toString());
						post();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.d("1",error.toString());
					}
				});
				MyApplication.mRequestQueue.add(jr);
				et.setText("");
			}
		});
	}
	private void post() {
		// TODO Auto-generated method stub
		String url="http://6.worldcup2.sinaapp.com/secretary/getTmessage.php";
		Map map=new HashMap<String,Int2>();
		map.put("tid",tid);
		map.put("pid",pid);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
	  			// TODO Auto-generated method stub
				Log.d("1",response.toString());
				String pname="";
				try {
					pname=response.getString("pname");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				protitle.setText(pname);
				JSONArray ja = null;
				try {
					ja=response.getJSONArray("list");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								mMessages=new ArrayList<Message>();
				String name=null;
				String date=null;
				String content=null;
				for(int i=0;i<ja.length();i++){
					JSONObject js=null;
					try {
						js=ja.getJSONObject(i);
						name=js.getString("name");
						date=js.getString("time");
						content=js.getString("content");
						mMessages.add(new Message(name,date,content));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				han.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						

						mTalkListView.setAdapter(new MessageAdapter(mContext, mMessages));
					    mTalkListView.setSelection(mMessages.size());
					}
				});
				
			}
		},new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("1",error.toString());
			}
		});
		MyApplication.mRequestQueue.add(jr);
	}
	class MessageAdapter extends BaseAdapter{

		  private List<Message> mProList;
	        private LayoutInflater mInflater;
	        public MessageAdapter(Context context, List<Message> vector) {
	            super();
	            this.mProList = vector;
	            mInflater = LayoutInflater.from(context);
	        }
	        public int getCount() {
	            return mProList.size();
	        }

	        public Object getItem(int position) {
	            return mProList.get(position);
	        }

	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	           
	            TextView cnameView;
	            TextView tnameView;
	            TextView tnextView;
	            Message pro = mProList.get(position);
	          
	            String cname = pro.cname;
	            String tname = pro.content; 
	            String date=pro.date;
	            convertView = mInflater.inflate(R.layout.item_message,
	                    null);
	           
	            cnameView = (TextView) convertView
	                    .findViewById(R.id.cname);
	            tnameView=(TextView) convertView.findViewById(R.id.tnext);
	            tnextView=(TextView) convertView.findViewById(R.id.tname);
	            cnameView.setText(cname);
                tnameView.setText(tname);
                tnextView.setText(date);
	            return convertView;
	      }
     }
	class Message{
		String cname;
		String date;
		String content;
		public Message(String cname,String date,String content){
			this.cname=cname;
			this.date=date;
			this.content=content;
		}
	}
}
