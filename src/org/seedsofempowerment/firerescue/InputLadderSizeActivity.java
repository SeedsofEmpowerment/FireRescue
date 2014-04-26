/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : InputLadderSizeActivity.java
* description : When the user want to input the ladder, it will pop up a new window for the user
* 				to select the ladder size.                          
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class InputLadderSizeActivity extends Activity{
	private Button okButton;
	private Button cancelButton;
	private EditText ladderSize;
	private int index;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//hidden the battery flag and any other parts
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hidden the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.inputladdersizelayout);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//…Ë÷√Activity±≥æ∞Õ∏√˜
        Intent intent = InputLadderSizeActivity.this.getIntent();
		index = intent.getIntExtra("index", 0);
        ladderSize = (EditText)findViewById(R.id.laddersize);
        okButton = (Button)findViewById(R.id.okbutton);      
        okButton.setOnTouchListener(new OnTouchListener(){
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				if(event.getAction() == MotionEvent.ACTION_DOWN){
 					GameInfo.vibrate.playVibrate(-1);
 					GameInfo.soundEffect[0].play((float) 0.3);
 				}
 				else if(event.getAction() == MotionEvent.ACTION_UP){
 					if(!(ladderSize.getText().toString().trim().equals(""))){
 						FireRescueGamming.ladderLevel[index] = Integer.parseInt(ladderSize.getText().toString());
 						--(GameInfo.randomLadderNumber);
 						InputLadderSizeActivity.this.finish();
 					}
 					else{
 						ladderSize.setText("");
 					}
 				}
				return false;
 			}
         });
		
		cancelButton = (Button) findViewById(R.id.cancelbutton);
		cancelButton.setOnTouchListener(new OnTouchListener(){
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				if(event.getAction() == MotionEvent.ACTION_DOWN){
 					GameInfo.vibrate.playVibrate(-1);
 					GameInfo.soundEffect[0].play((float) 0.3);
 				}
 				else if(event.getAction() == MotionEvent.ACTION_UP){
 					InputLadderSizeActivity.this.finish();
 				}
				return false;
 			}
         });
	}
}
