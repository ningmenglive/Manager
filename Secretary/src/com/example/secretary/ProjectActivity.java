package com.example.secretary;


import com.example.secretary.fragement.MemberFragment;
import com.example.secretary.fragement.TalkFragment;
import com.example.secretary.fragement.TaskFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ProjectActivity extends Activity {
	protected static final String TAG = "ProjectActivity";
	RelativeLayout bt_task,bt_talk,bt_text,bt_member;
	RelativeLayout current_bt;
	ImageView back;
	TextView titlebar;
    public int pid=0;
    public String pname=null;
    Handler han=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		Intent intent = getIntent();
		pid= intent.getIntExtra("pid",0);
		pname=intent.getStringExtra("pname");
		findView();
		init();
	}
	private void findView() {
		// TODO Auto-generated method stub
		bt_task=(RelativeLayout)findViewById(R.id.tab_task);
		bt_talk=(RelativeLayout)findViewById(R.id.tab_talk);
		bt_text=(RelativeLayout)findViewById(R.id.tab_text);
		bt_member=(RelativeLayout)findViewById(R.id.tab_member);
		titlebar=(TextView) findViewById(R.id.pname);
		back=(ImageView) findViewById(R.id.back);
	}
	private void init() {
		// TODO Auto-generated method stub
		titlebar.setText(pname);
		current_bt=bt_task;
		bt_task.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                TaskFragment taskFragment=new TaskFragment();
                ft.replace(R.id.fl_content, taskFragment,ProjectActivity.TAG);
                ft.commit();
				setButton(bt_task);
				
			}
		});
		bt_talk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setButton(bt_talk);
			}
		});
        bt_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setButton(bt_text);
			}
		});
       bt_member.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                MemberFragment memberFragment=new MemberFragment();
                ft.replace(R.id.fl_content, memberFragment,ProjectActivity.TAG);
                ft.commit();
				setButton(bt_member);
			}
		});
       bt_talk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentManager fm=getFragmentManager();
               FragmentTransaction ft=fm.beginTransaction();
               TalkFragment talkFragment=new TalkFragment();
               ft.replace(R.id.fl_content, talkFragment,ProjectActivity.TAG);
               ft.commit();
				setButton(bt_talk);
			}
		});
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
       bt_task.performClick();
		
	}
	private void setButton(View view){
		if(current_bt!=null&&view!=current_bt){
			RelativeLayout crl=(RelativeLayout)view;
			TextView tv=(TextView) crl.findViewById(R.id.text);
			View vw=crl.findViewById(R.id.line);
			TextView ctv=(TextView) current_bt.findViewById(R.id.text);
			View cvw=(View)current_bt.findViewById(R.id.line);
			ctv.setTextColor(this.getResources().getColor(R.color.line_gray));
			cvw.setBackgroundColor(this.getResources().getColor(R.color.line_gray));
			tv.setTextColor(getResources().getColor(R.color.press));
			vw.setBackgroundColor(getResources().getColor(R.color.press));
			current_bt=crl;;
		}else{
			RelativeLayout crl=(RelativeLayout)view;
			TextView tv=(TextView) crl.findViewById(R.id.text);
			View vw=crl.findViewById(R.id.line);
			tv.setTextColor(getResources().getColor(R.color.press));
			vw.setBackgroundColor(getResources().getColor(R.color.press));
			current_bt=crl;
		}
	}
}
