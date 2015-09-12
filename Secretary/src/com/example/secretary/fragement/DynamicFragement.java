package com.example.secretary.fragement;


import com.example.secretary.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class DynamicFragement extends Fragment {
	 private Context mContext;
	 private View mBaseView;
	 ListView dylist;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
        mBaseView = inflater.inflate(R.layout.fragment_dynamic, null);

        findView();
        init();
        return mBaseView;
	}
	private void findView() {
		// TODO Auto-generated method stub
		dylist=(ListView) mBaseView.findViewById(R.id.dynamic_list);
	}
	private void init() {
		// TODO Auto-generated method stub
		
	}
	
	
	class DynamicAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
