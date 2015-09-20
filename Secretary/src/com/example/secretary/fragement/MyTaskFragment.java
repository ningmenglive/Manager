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

import com.example.secretary.MyApplication;
import com.example.secretary.R;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Int2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyTaskFragment extends Fragment {
	 private Context mContext;
	 private View mBaseView;
	 private ListView mTaskListView;
	 private List<Task> mTaskList;
	 private int pid=1;
	 private String email;
	 Handler han=new Handler();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContext=getActivity();
		mBaseView=inflater.inflate(R.layout.fragment_mytask,null);
		findView();
		init();
		return mBaseView;
	}
	private void findView() {
		// TODO Auto-generated method stub
		mTaskListView=(ListView) mBaseView.findViewById(R.id.list_task);
	}
	private void init() {	
		// TODO Auto-generated method stub
		email=MyApplication.email;
		String url="http://6.worldcup2.sinaapp.com/secretary/getMytask.php";
		Map map=new HashMap<String,Int2>();
		map.put("email",email);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST, url, json,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.d("2",response.toString());
				JSONArray ja = null;
				try {
					ja=response.getJSONArray("list");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mTaskList=new ArrayList<Task>();
				int pid=0;
				String content=null;
				for(int i=0;i<ja.length();i++){
					JSONObject js=null;
					try {
						js=ja.getJSONObject(i);
						pid=js.getInt("pid");
						content=js.getString("content");
						mTaskList.add(new Task(pid,content));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				han.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mTaskListView.setAdapter(new TaskListAdapter(mContext, mTaskList));
					}
				});
			
				
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("q",error.toString());
			}
		});
		MyApplication.mRequestQueue.add(jr);
	}
	class Task {
		int  sid;
		int pid;
		String content;
		public Task(int sid,String content){
         this.sid=sid;
         this.content=content;
	  }
	}
	class TaskListAdapter extends BaseAdapter{
        private List<Task> mTaskList;
        private LayoutInflater mInflater;
        public TaskListAdapter(Context context, List<Task> vector) {
            super();
            this.mTaskList = vector;
            mInflater = LayoutInflater.from(context);
        }
        public int getCount() {
            return mTaskList.size();
        }

        public Object getItem(int position) {
            return mTaskList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t1,t2;
            Task task = mTaskList.get(position);
            int sid=task.sid;
            String content=task.content;
            convertView = mInflater.inflate(R.layout.my_todos_li,null);
            t1=(TextView) convertView.findViewById(R.id.textView1);
            t2=(TextView) convertView.findViewById(R.id.textView2);
            t2.setText(content);
            return convertView;
        }
    }
}
