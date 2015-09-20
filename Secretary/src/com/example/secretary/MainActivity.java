package com.example.secretary;

import com.example.secretary.fragement.DynamicFragement;
import com.example.secretary.fragement.MyTaskFragment;
import com.example.secretary.fragement.ProjectFragment;
import com.example.secretary.view.SlidingView;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected static final String TAG = "MainActivity";
	Context context;
    TextView titlebar,btn_add;
    ImageView btn_menu;
    RelativeLayout menu,dynamic,project,notice,myself,current;
    SlidingView slidingView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=this;
		findView();
		init();
	}
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	current.performClick();
    	
    }
	private void findView() {
		// TODO Auto-generated method stub
		titlebar=(TextView) findViewById(R.id.common_title_title);
		btn_menu=(ImageView) findViewById(R.id.leftmenu);
		btn_add=(TextView)findViewById(R.id.common_add_btn);
		slidingView=(SlidingView) findViewById(R.id.sliding);
		menu=(RelativeLayout) slidingView.findViewById(R.id.menu);
		dynamic=(RelativeLayout) menu.findViewById(R.id.btn_dynamic);
		project=(RelativeLayout) menu.findViewById(R.id.btn_project);
		myself=(RelativeLayout) menu.findViewById(R.id.btn_myself);
		notice=(RelativeLayout) menu.findViewById(R.id.btn_notice);
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		
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
				btn_add.setText("");
				current=dynamic;
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
				btn_add.setText("添加");
				current=project;
				btn_add.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					  	Intent intent=
					  			new Intent(MainActivity.this,AddProjectActivity.class);
					  	context.startActivity(intent);
					}
				});
				FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ProjectFragment pf=new ProjectFragment();
                ft.replace(R.id.frame_content,pf,MainActivity.TAG);
                ft.commit();
			}
		});
		myself.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				titlebar.setText("我的任务");
				btn_add.setText("");
				current=myself;
				FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                MyTaskFragment pf=new MyTaskFragment();
                ft.replace(R.id.frame_content,pf,MainActivity.TAG);
                ft.commit();
			}
		});
		project.performClick();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
