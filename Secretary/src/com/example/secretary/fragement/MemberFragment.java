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

import com.example.secretary.AddMemActivity;
import com.example.secretary.MyApplication;
import com.example.secretary.ProjectActivity;
import com.example.secretary.R;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MemberFragment extends Fragment {
	 private Context mContext;
	 private View mBaseView;
	 private ListView mMemberListView;
	 private List<Member> mMemberList;
	 private int pid=0;
	 Button bt;
	 Handler han=new Handler();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContext=getActivity();
		mBaseView=inflater.inflate(R.layout.fragment_member,null);
		pid=((ProjectActivity)getActivity()).pid;
		Log.d("6",""+pid);
		findView();
		init();
		return mBaseView;
	}
	private void findView() {
		// TODO Auto-generated method stub
		mMemberListView=(ListView) mBaseView.findViewById(R.id.list_mem);
		bt=(Button) mBaseView.findViewById(R.id.btn_addmember);
	}
	private void init() {
		Log.d("11","1");
		bt.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,AddMemActivity.class);
				intent.putExtra("pid",pid);
				startActivity(intent);
				
			}
		});
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
						mMemberListView.setAdapter(new MemberListAdapter(mContext, mMemberList));
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
	public class Member {
	    String email;
		String name;
		public Member(String email,String name){
         this.email=email;
         this.name=name;
	  }
	}
	class MemberListAdapter extends BaseAdapter{
        private List<Member> mMemberList;
        private LayoutInflater mInflater;
        public MemberListAdapter(Context context, List<Member> vector) {
            super();
            this.mMemberList = vector;
            mInflater = LayoutInflater.from(context);
        }
        public int getCount() {
            return mMemberList.size();
        }

        public Object getItem(int position) {
            return mMemberList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t1;
            Member task = mMemberList.get(position);
            String email=task.email;
            String name=task.name;
            convertView = mInflater.inflate(R.layout.item_member,null);
           
            t1=(TextView) convertView.findViewById(R.id.mname);
            t1.setText(name);
            return convertView;
        }
    }
}
