/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : UserinfoInputDialog.java
* description :                            
* 
* created by Wang Shiliang at 5/12/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

class UserinfoInputDialog extends AlertDialog{
	private Button okButton;
	private Button cancelButton;
	private EditText name;
	private EditText age;
	private RadioButton boy;
	private RadioButton girl;
	private boolean isComplete = true;
	
	public UserinfoInputDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccountlayout);
		setCancelable(false);
		name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        boy = (RadioButton)findViewById(R.id.boybutton);
        girl = (RadioButton)findViewById(R.id.girlbutton);
        okButton = (Button)findViewById(R.id.okbutton);
        cancelButton = (Button)findViewById(R.id.cancelbutton);
        name.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				if(imm.isActive()){
					imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0 );
				}
				return false;
			}
        
        });
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
 						GameInfo.isStartAllowed = true;
						GameInfo.isCreateSurfaceView = false;
						UserinfoInputDialog.this.dismiss();
 					}
 					else{
 						//Intent intent = new Intent(CreateAccountActivity.this, LackInfoActivity.class);
 						//CreateAccountActivity.this.startActivity(intent);
 						Intent intent = new Intent(GameEngineActivity.instance, LackInfoActivity.class);
 						GameEngineActivity.instance.startActivity(intent);
 					}
 					isComplete = true;
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
 					UserinfoInputDialog.this.dismiss();
 				}
				return false;
 			}
         });
	}
}
