package com.example.secretary.fragement;


import com.example.secretary.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DynamicFragement extends Fragment {
	 private Context mContext;
	 private View mBaseView;
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
		
	}
	private void init() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
