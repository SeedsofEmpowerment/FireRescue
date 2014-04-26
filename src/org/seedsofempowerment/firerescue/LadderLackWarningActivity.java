/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : LadderLackWarningActivity.java
* description : When the user doesn't have enough ladder                            
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class LadderLackWarningActivity extends Activity{
	private Button okButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//hidden the battery flag and any other parts
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hidden the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.inputladdersizelayout);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//…Ë÷√Activity±≥æ∞Õ∏√˜
        okButton.setOnTouchListener(new OnTouchListener(){
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				if(event.getAction() == MotionEvent.ACTION_DOWN){
 					GameInfo.vibrate.playVibrate(-1);
 					GameInfo.soundEffect[0].play((float) 0.3);
 				}
 				else if(event.getAction() == MotionEvent.ACTION_UP){
 					LadderLackWarningActivity.this.finish();
 				}
				return false;
 			}
         });
	}
}
