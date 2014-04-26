/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : CreateAccountActivity.java
* description : When the user create a new accout, it will pop up a new window                               
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class CreateAccountActivity extends Activity{
	private Button okButton;
	private Button cancelButton;
	private EditText name;
	private EditText age;
	private RadioButton boy;
	private RadioButton girl;
	private boolean isComplete = true;
	private boolean isExist = false;
	public static Activity instance;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
   	 	//hidden the battery flag and any other parts
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hidden the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.little);
        setContentView(R.layout.createaccountlayout);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//…Ë÷√±≥æ∞Õ∏√˜
        Log.v("debug","34");
        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        boy = (RadioButton)findViewById(R.id.boybutton);
        girl = (RadioButton)findViewById(R.id.girlbutton);
        okButton = (Button)findViewById(R.id.okbutton);
        cancelButton = (Button)findViewById(R.id.cancelbutton);
        okButton.setOnTouchListener(new OnTouchListener(){
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				if(event.getAction() == MotionEvent.ACTION_DOWN){
 					GameInfo.vibrate.playVibrate(-1);
 					GameInfo.soundEffect[0].play((float) 0.3);
 				}
 				else if(event.getAction() == MotionEvent.ACTION_UP){
 					if(!(name.getText().toString().trim().equals(""))){
 						GameInfo.name = name.getText().toString();
 					}
 					else{
 						isComplete = false;
 						name.setText("");
 					}
 					GameInfo.age = 0;
 					if(!(age.getText().toString().trim().equals(""))){
 						GameInfo.age = Integer.parseInt(age.getText().toString());
 					}		
 					if(GameInfo.age <= 0 || GameInfo.age > 100){
 						isComplete = false;
 						age.setText("");
 					}
 					if(boy.isChecked()==true){
 						GameInfo.gender = 0;
 					}
 					else if(girl.isChecked()==true){
 						GameInfo.gender = 1;
 					}
 					else{
 						GameInfo.gender = 2;
 					}
 					if(isComplete == true){
 						//judge whether this account has already existed
 						boolean isHaveSDCard = false;
	    				if(Environment.getExternalStorageState() != null && !Environment.getExternalStorageState().equals("removed")){
	    					Log.v("wang","read from files");
	    					isHaveSDCard = true;
	    				}
	    				if(isHaveSDCard){
	    					File path = new File("/sdcard/FireRescue/save");
	    					if(path.exists()){
	    						final String nameSequence[];
	    						nameSequence = path.list();
	    						for(String temp: nameSequence){
	    							int j = temp.indexOf(".");
	    				        	if(j != -1){
	    				        		temp = temp.substring(0, j);
	    				        	} 
	    							if(temp.equals(GameInfo.name)){
	    								isExist = true;
	    							}
	    						}
	    					}
	    				}
	    				else{
	    					File path = new File("/");
	    					if(path.exists()){
	    						final String nameSequence[];
	    						nameSequence = path.list();
	    						for(String temp: nameSequence){
	    							int j = temp.indexOf(".");
	    				        	if(j != -1){
	    				        		temp = temp.substring(0, j);
	    				        	} 
	    							if(temp.equals(GameInfo.name)){
	    								isExist = true;
	    							}
	    						}
	    					}
	    				}
	    				if(isExist == false){
	 						GameInfo.isStartAllowed = true;
							GameInfo.isCreateSurfaceView = false;
							CreateAccountActivity.this.finish();
	    				}
	    				else{
	    					Intent intent = new Intent(CreateAccountActivity.this, AccountAlreadyExistActivity.class);
	 						CreateAccountActivity.this.startActivity(intent);
	    				}
 					}
 					else{
 						Intent intent = new Intent(CreateAccountActivity.this, LackInfoActivity.class);
 						CreateAccountActivity.this.startActivity(intent);
 					}
 					isComplete = true;
 					isExist = false;
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
 					GameInfo.isCreateSurfaceView = false;
					CreateAccountActivity.this.finish();
 				}
				return false;
 			}
         });
	}
	
	@Override
	public void onBackPressed() {
		GameInfo.isCreateSurfaceView = false;
	}
	
	public class WarningDialog extends  AlertDialog{
		private Button okButton;
		private TextView tv;
		protected WarningDialog(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.createaccountwarninglayout);
			setCancelable(false);
			tv = (TextView)findViewById(R.id.tv);
			tv.setTextColor(Color.BLACK);
			okButton = (Button) findViewById(R.id.okbutton);
			okButton.setOnTouchListener(new OnTouchListener(){
	 			@Override
	 			public boolean onTouch(View v, MotionEvent event) {
	 				if(event.getAction() == MotionEvent.ACTION_DOWN){
	 					GameInfo.vibrate.playVibrate(-1);
	 					//GameInfo.soundEffect[0].play((float) 0.3);
	 				}
	 				else if(event.getAction() == MotionEvent.ACTION_UP){
						WarningDialog.this.dismiss();
	 				}
					return false;
	 			}
	         });
		}
		
	}
}
