package com.example.secretary;

import com.example.secretary.fragement.DynamicFragement;
import com.example.secretary.fragement.ProjectFragment;
import com.example.secretary.view.SlidingView;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected static final String TAG = "MainActivity";
    TextView titlebar;
    ImageView btn_menu;
    RelativeLayout menu,dynamic,project,notice,myself;
    SlidingView slidingView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		init();
	}

	private void findView() {
		// TODO Auto-generated method stub
		titlebar=(TextView) findViewById(R.id.common_title_title);
		btn_menu=(ImageView) findViewById(R.id.leftmenu);
		slidingView=(SlidingView) findViewById(R.id.sliding);
		menu=(RelativeLayout) slidingView.findViewById(R.id.menu);
		dynamic=(RelativeLayout) menu.findViewById(R.id.btn_dynamic);
		project=(RelativeLayout) menu.findViewById(R.id.btn_project);
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlebar.setText("项目");
		btn_menu.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				slidingView.toggle();
			}
		});
		//动态页面
		dynamic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlebar.setText("动态");
				FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                DynamicFragement df=new DynamicFragement();
                ft.replace(R.id.frame_content,df,MainActivity.TAG);
                ft.commit();
			}
		});
		project.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlebar.setText("项目");
				FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ProjectFragment pf=new ProjectFragment();
                ft.replace(R.id.frame_content,pf,MainActivity.TAG);
                ft.commit();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
