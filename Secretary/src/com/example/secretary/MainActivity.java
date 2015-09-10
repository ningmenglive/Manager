package com.example.secretary;

import com.example.secretary.view.LeftMenu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView titlebar;
    ImageView btn_menu;
    LeftMenu leftMenu;
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
		leftMenu=(LeftMenu) findViewById(R.id.menu);
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlebar.setText("ÏîÄ¿");
		btn_menu.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				leftMenu.toggle();
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
