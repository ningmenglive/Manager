package com.example.secretary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingView extends HorizontalScrollView {
	private LinearLayout wapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int mMenuWidth;
	private int mMenuRightPadding=50;
	private boolean once=false;
	private boolean isopen=false;

	public SlidingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//获得屏幕的宽
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth=outMetrics.widthPixels;
		
		mMenuRightPadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,
				context.getResources().getDisplayMetrics());
		
	}
	//设置子View的宽和高，设置自己的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	// TODO Auto-generated method stub
    	if(!once){
	    	wapper=(LinearLayout) getChildAt(0);
	    	mMenu=(ViewGroup) wapper.getChildAt(0);
	    	mContent=(ViewGroup) wapper.getChildAt(1);
	    	mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
	    	mContent.getLayoutParams().width=mScreenWidth;
	    	once=true;
    	}
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    	// TODO Auto-generated method stub
    	super.onLayout(changed, l, t, r, b);
    	if(changed){
    		this.scrollTo(mScreenWidth, 0);
    	}
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
    	// TODO Auto-generated method stub
    	int action=ev.getAction();
    	switch(action){
    	 case MotionEvent.ACTION_UP:
    	     int ScrollX=getScrollX();
    	     if(ScrollX>=mMenuWidth/2){
    	    	 this.smoothScrollTo(mMenuWidth,0);
    	    	 isopen=false;
    	     }else{
    	    	 this.smoothScrollTo(0,0);
                 isopen=true;
    	     }
    		 return true;
    	}
    	return super.onTouchEvent(ev);
    }
    public void openMenu(){
    	if(isopen) return;
    	this.smoothScrollTo(0, 0);
    	isopen=true;
    }
    public void closeMenu(){
    	if(!isopen) return;
    	this.smoothScrollTo(mMenuWidth, 0);
    	isopen=false ;
    }
    public void toggle(){
    	if(isopen){
    		closeMenu();
    	}
    	else{
    		openMenu();
    	}
    }
}
