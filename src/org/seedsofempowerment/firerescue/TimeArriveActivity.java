/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : GameEngineActivity.java
* description : When the times arrives                            
* 
* created by Wang Shiliang at 5/22/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class TimeArriveActivity extends Activity{
	private Button okButton;
	private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//hidden the battery flag and any other parts
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//hidden the title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.timearrivelayout);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//…Ë÷√Activity±≥æ∞Õ∏√˜
		tv = (TextView)findViewById(R.id.tv);
		tv.setTextColor(Color.BLACK);
		if(GameInfo.remainTimes == 2){
			tv.setText("Time's up. You have two more chances left.");
		}
		else if(GameInfo.remainTimes == 1){
			tv.setText("Time's up. You have one more chances left.");
		}
		else if(GameInfo.remainTimes == 0){
			tv.setText("You lost the game!");
		}
		okButton = (Button) findViewById(R.id.okbutton);
		okButton.setOnTouchListener(new OnTouchListener(){
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				if(event.getAction() == MotionEvent.ACTION_DOWN){
 					GameInfo.vibrate.playVibrate(-1);
 					//GameInfo.soundEffect[0].play((float) 0.3);
 				}
 				else if(event.getAction() == MotionEvent.ACTION_UP){
 					if(GameInfo.remainTimes == 2 || GameInfo.remainTimes == 1){
 						--(GameInfo.remainTimes);
 						FireRescueGamming.fireman.setPositionX(PhoneInfo.getRealWidth(150));
 						FireRescueGamming.fireman.setPositionY(PhoneInfo.getRealHeight(333));
 						if (GameInfo.difficulty == GameInfo.TRAINEE || GameInfo.difficulty == GameInfo.FIREFIGHTER) {
	 						int i = 0;
 							for(int temp :FireRescueGamming.ladderLevel){
	 							FireRescueGamming.ladderLevel[i] = Math.abs(temp);
	 							++i;
	 						}
 						}
 						else {
 							int i = 0;
 							for(int temp :FireRescueGamming.ladderLevel){
 								FireRescueGamming.ladderLevel[i] = Math.abs(temp);
 								++i;
	 						}
 							i = 0;
 							for(int temp :FireRescueGamming.jumpSpringLevel){
	 							FireRescueGamming.jumpSpringLevel[i] = Math.abs(temp);
	 							++i;
	 						}
 						}
 						FireRescueGamming.ladderCurrentNumber = 0;
 						FireRescueGamming.jumpSpringCurrentNumber = 0;
 						GameInfo.roundStartTime = System.currentTimeMillis();
 						FireRescueGamming.isTimeArrived = false;
 						TimeArriveActivity.this.finish();
 					}
 					else if(GameInfo.remainTimes == 0){
 						TimeArriveActivity.this.finish();
 	 					GameInfo.isLadderSurfaceView = false;
 	 					GameInfo.isStartAllowed = false;
 	 					GameSurfaceView.myView.releaseGameResources();
 	 					GameSurfaceView.gameState = GameSurfaceView.GAME_MENU;
 	 					GameInfo.firstStart = true;
 	 					GameSurfaceView.myView.initGame();
 					}	
 				}
				return false;
 			}
         });
	}
}
