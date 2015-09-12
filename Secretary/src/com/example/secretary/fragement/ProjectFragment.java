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
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.secretary.MyApplication;
import com.example.secretary.R;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProjectFragment extends Fragment {
	 private Context mContext;
	 private View mBaseView;
	 private ListView prolistview;
	 private List<ProjectEntity> pros;
	 List<ProjectEntity> prolist=new ArrayList();
	 Handler han=new Handler();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
        mBaseView = inflater.inflate(R.layout.fragment_project, null);

        findView();
        init();
        return mBaseView;
	}
	private void findView() {
		// TODO Auto-generated method stub
		prolistview=(ListView) mBaseView.findViewById(R.id.list_pro);
		
	}
	private void init() {
		// TODO Auto-generated method stub
		post();
	}
	
	private void post() {
		// TODO Auto-generated method stub
		String email=MyApplication.email;
		String url="http://6.worldcup2.sinaapp.com/secretary/getProject.php";
		Map<String,String> map=new HashMap();
		map.put("email",email);
		JSONObject json=new JSONObject(map);
		JsonObjectRequest jr=new JsonObjectRequest(Method.POST,url, json,new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				JSONArray jarr=null;
				 int pid=0;
				 String pname=null;
				
				try {
					jarr=response.getJSONArray("list");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject json=null;
				for(int i=0;i<jarr.length();i++){
					try {
						json=(JSONObject) jarr.get(i);
						pid=json.getInt("pid");
						pname=json.getString("pname");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					prolist.add(new ProjectEntity(pid, pname));
				}
				han.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						  prolistview.setAdapter(new ProjectAdapter(mContext,prolist));
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
	}

	class ProjectAdapter extends BaseAdapter{

		  private List<ProjectEntity> mProList;
	        private LayoutInflater mInflater;
	        public ProjectAdapter(Context context, List<ProjectEntity> vector) {
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
	            ImageView avatarView;
	            TextView nameView;
	           
	            ProjectEntity pro = mProList.get(position);
	          
	            String name = pro.pname;
	           
	            convertView = mInflater.inflate(R.layout.item_project,
	                    null);
	            avatarView = (ImageView) convertView
	                    .findViewById(R.id.phead);
	            nameView = (TextView) convertView
	                    .findViewById(R.id.pname);
	            nameView.setText(name);

	            return convertView;
	}
  }
	class ProjectEntity{
		int  pid;
		String pname;
		String pinfo;
		public ProjectEntity(int  pid,String pname){
			this.pid=pid;
			this.pname=pname;
		}
		
	}
}
