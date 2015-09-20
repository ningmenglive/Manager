package com.example.secretary.fragement;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.secretary.AddTalkActivity;
import com.example.secretary.AddTaskActivity;
import com.example.secretary.MyApplication;
import com.example.secretary.ProjectActivity;
import com.example.secretary.R;
import com.example.secretary.TalkActivity;
import com.example.secretary.fragement.ProjectFragment.ProjectEntity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Int2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TalkFragment extends Fragment {
	 private Context mContext;
	 private View mBaseView;
	 private ListView mTalkListView;
	 private List<Talk> mTalkList;
	 private int pid=0;
	 Handler han=new Handler();
	 Button bt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContext=getActivity();
		mBaseView=inflater.inflate(R.layout.fragment_talk,null);
		pid=(int)((ProjectActivity)getActivity()).pid;
		findView();
		init();
		return mBaseView;
	}
	private void findView() {
		// TODO Auto-generated method stub
		bt=(Button) mBaseView.findViewById(R.id.btn_addtalk);
		mTalkListView=(ListView) mBaseView.findViewById(R.id.list_talk);
	}
	private void init() {
		
		// TODO Auto-generated method stub
		bt.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,AddTalkActivity.class);
				intent.putExtra("pid",pid);
				startActivity(intent);
			}
		});
		String url="http://6.worldcup2.sinaapp.com/secretary/getPtalk.php";
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
				mTalkList=new ArrayList<Talk>();
				int tid=0;
				String next="";
				String name="";
				String content="";
				for(int i=0;i<ja.length();i++){
					JSONObject js=null;
					try {
						js=ja.getJSONObject(i);
						tid=js.getInt("tid");
						next=js.getString("next");
						name=js.getString("name");
						content=js.getString("ttheme");
						if(next==null)
							next="";
						mTalkList.add(new Talk(tid,name,next,content));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				han.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mTalkListView.setAdapter(new TalkListAdapter(mContext, mTalkList));
					}
				});
			
				
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		MyApplication.mRequestQueue.add(jr);
		mTalkListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Talk talk = mTalkList.get(arg2);
				Intent intent = new Intent(mContext,TalkActivity.class);
				intent.putExtra("tid", talk.tid);
				intent.putExtra("pid",pid);
				intent.putExtra("tname",talk.content);
				startActivity(intent);
			}
		});
	}
	class Talk {
		int  tid;
		int pid;
		String name;
		String next;
		String content;
		public Talk(int tid,String name,String next,String content){
         this.tid=tid;
         this.name=name;
         this.next=next;
         this.content=content;
	  }
	}
	class TalkListAdapter extends BaseAdapter{
        private List<Talk> mTalkList;
        private LayoutInflater mInflater;
        public TalkListAdapter(Context context, List<Talk> vector) {
            super();
            this.mTalkList = vector;
            mInflater = LayoutInflater.from(context);
        }
        public int getCount() {
            return mTalkList.size();
        }

        public Object getItem(int position) {
            return mTalkList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t1,t2,t3;
            Talk talk = mTalkList.get(position);
            int tid=talk.tid;
            String name=talk.name;
            String next=talk.next;
            String content=talk.content;
            convertView = mInflater.inflate(R.layout.item_talk,null);
            t1=(TextView) convertView.findViewById(R.id.tname);
            t2=(TextView) convertView.findViewById(R.id.tnext);
            t3=(TextView) convertView.findViewById(R.id.cname);
            t1.setText(content);
            t2.setText(next);
            t3.setText(name);
            return convertView;
        }
    }
}
