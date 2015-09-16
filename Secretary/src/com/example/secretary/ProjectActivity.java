package com.example.secretary;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ProjectActivity extends Activity {
	
	RelativeLayout bt_task,bt_talk,bt_text,bt_member;
	RelativeLayout current_bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		findView();
		init();
	}
	private void findView() {
		// TODO Auto-generated method stub
		bt_task=(RelativeLayout)findViewById(R.id.tab_task);
		bt_talk=(RelativeLayout)findViewById(R.id.tab_talk);
		bt_text=(RelativeLayout)findViewById(R.id.tab_text);
		bt_member=(RelativeLayout)findViewById(R.id.tab_member);
	}
	private void init() {
		// TODO Auto-generated method stub
		current_bt=bt_task;
		bt_task.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
				setButton(bt_member);
			}
		});
		
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
			tv.setTextColor(Color.BLACK);
			vw.setBackgroundColor(Color.BLACK);
			current_bt=crl;;
		}else{
			RelativeLayout crl=(RelativeLayout)view;
			TextView tv=(TextView) crl.findViewById(R.id.text);
			View vw=crl.findViewById(R.id.line);
			tv.setTextColor(Color.BLACK);
			vw.setBackgroundColor(Color.BLACK);
			current_bt=crl;
		}
	}
}
