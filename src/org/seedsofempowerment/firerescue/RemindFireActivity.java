/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : RemindFireActivity.java
* description : The game will remind user which floor the people is                             
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class RemindFireActivity extends Activity{
	public static Activity instance;
	private TextView remindfire;
	public int gameState;
	public final static int REMINDFIRE = 0;
	public final static int LEVELCROSS = 1;
	public final static int UPPERROUND = 2;
	public final static int SUCCESS = 3;
	public final static int RESCUING = 4;
	private int flag;
	private int setflag;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        flag = 0;
        setflag = 0;
        //hidden the battery flag and any other parts
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hidden the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.remindfirelayout);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//设置Activity背景透明
        remindfire = (TextView)findViewById(R.id.remindinfo);
        if(GameInfo.firstStart == false){
			if(GameInfo.remainPeople == 0){
				if(GameInfo.round < 4){
					GameInfo.remainPeople = 5;
					++(GameInfo.round);
					gameState = UPPERROUND;
				}
				else{
					gameState = SUCCESS;
				}
			}
			else{
				gameState = LEVELCROSS;
			}
		}
		else{
			gameState = REMINDFIRE;
		}
        if(GameInfo.isrescuing == 3){
        	gameState = RESCUING;
        }
		flag = 0;
        setRemindText();
        remindfire.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(gameState == REMINDFIRE){
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						GameInfo.vibrate.playVibrate(-1);
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						if(flag == 0){
							flag = 1;
						}
						else if(flag == 1){
							GameInfo.calculation = Integer.toString(GameInfo.startlevel);
							Intent intent = new Intent();
							intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
							RemindFireActivity.instance.finish();
						}
						setRemindText();
					}
				}
				else if(gameState == LEVELCROSS){
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						GameInfo.vibrate.playVibrate(-1);	
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						if(flag == 0){
							flag = 1;
						}
						else if(flag == 1){
							flag = 2;
						}
						else if(flag == 2){
							GameInfo.calculation = Integer.toString(GameInfo.startlevel);
							GameInfo.isrescuing = 0;
							Intent intent = new Intent();
							intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
							RemindFireActivity.instance.finish();
						}
						setRemindText();
					}
				}
				else if(gameState == UPPERROUND){
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						GameInfo.vibrate.playVibrate(-1);	
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						if(flag == 0){
							flag = 1;
						}
						else if(flag == 1){
							flag = 2;
						}
						else if(flag == 2){
							GameInfo.calculation = Integer.toString(GameInfo.startlevel);
							Intent intent = new Intent();
							intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
							RemindFireActivity.instance.finish();
						}
						setRemindText();
					}
				}
				else if(gameState == SUCCESS){
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						GameInfo.vibrate.playVibrate(-1);	
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						RemindFireActivity.instance.finish();
						GameInfo.isLadderSurfaceView = false;
						GameInfo.isStartAllowed = false;
						GameSurfaceView.myView.releaseGameResources();
						GameInfo.initGameResources();
						GameSurfaceView.gameState = GameSurfaceView.GAME_MENU;
						GameInfo.firstStart = true;
						GameSurfaceView.myView.initGame();
					}
				}
				else if(gameState == RESCUING){
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						GameInfo.vibrate.playVibrate(-1);	
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
							GameInfo.startlevel = GameInfo.endlevel;
							Intent intent = new Intent();
							intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
							RemindFireActivity.instance.finish();
							GameInfo.isrescuing = 0;
						
					}
				}
				//RemindFireActivity.this.finish();
				return true;
			}
         });
    }
	
	public void setRemindText(){
        if(gameState == REMINDFIRE){
			if(flag == 0){
				String text1 = "Round 1: ";
				String text2 = "You need to rescue 5";
				String text3 = " people in this round.";
				remindfire.setText(text1+text2+text3);
			}
			else if(flag == 1){
				int number = GameInfo.doorLevel;
				String remind1 = "Help! We are on the ";
				String remind2 = "floor " + Integer.toString(number)+"!";
				remindfire.setText(remind1+remind2);
			}
		}
		else if(gameState == LEVELCROSS){
				if(flag == 0){
					String remindtext = "Your Correct Process: \n" + GameInfo.calculation;
					remindfire.setTextColor(Color.BLUE);
					remindfire.setText(remindtext);
					GameInfo.RescueProcess = GameInfo.startlevel + ""; //初始化营救过程为空
				}
				else if(flag == 1){
					String addtext = "";
					int leftnum = GameInfo.remainPeople;
					addtext = "You have "+leftnum+" people left";
					remindfire.setTextColor(Color.parseColor("#f64109"));
					remindfire.setText(addtext);
				}
				else if(flag == 2){
					int number = GameInfo.doorLevel;
					String remind1 = "Help! We are on the ";
					String remind2 = Integer.toString(number)+" floor!";
					remindfire.setTextColor(Color.parseColor("#f64109"));
					remindfire.setText(remind1+remind2);
				}
				
		}
		else if(gameState == UPPERROUND){
			if(flag == 0){
				String remind1 = "";
				String addtext = "You have succesfully passed ";
				String addtext2 = "this round. ";
				remind1 = "Congratulations! ";
				remindfire.setText(addtext+addtext2+remind1);
			}
			else if(flag == 1){
				String text1 = "Round "+GameInfo.round+":";
				String text2 = "You need to rescue 5 ";
				String text3 = "people in this round.";
				remindfire.setText(text1+text2+text3);
			}
			else if(flag == 2){
				String remind1 = "Help! We are on the ";
				int number = GameInfo.doorLevel;
				String remind2 = Integer.toString(number)+" floor!";
				remindfire.setText(remind1+remind2);
			}
		}
		else if(gameState == SUCCESS){
			String remind1 = "Congratulations! ";
			String remind2 = "You've cleared all customes!";
			remindfire.setText(remind1+remind2);
		}
		else if(gameState == RESCUING){
			if(setflag == 0){
				remindfire.setTextColor(Color.BLUE);//设置显示字体
				if(GameInfo.endlevel != GameInfo.doorLevel){
					if(GameInfo.startlevel <= GameInfo.endlevel){
						String remind = GameInfo.startlevel + "+" + (GameInfo.endlevel - GameInfo.startlevel);
						String allremind = remind + " != " +GameInfo.doorLevel;
						remindfire.setText(allremind);
						GameInfo.RescueProcess = GameInfo.RescueProcess + "+" + (GameInfo.endlevel - GameInfo.startlevel);
					}
					else{
						String remind = GameInfo.startlevel + "-" + (GameInfo.startlevel - GameInfo.endlevel);
						String allremind = remind + " != " +GameInfo.doorLevel;
						remindfire.setText(allremind);
						GameInfo.RescueProcess = GameInfo.RescueProcess + "-" + (GameInfo.startlevel - GameInfo.endlevel);
					}
				}
				else {
					if(GameInfo.startlevel <= GameInfo.endlevel){
						String remind = GameInfo.startlevel + "+" + (GameInfo.endlevel - GameInfo.startlevel);
						String allremind = remind + " = " +GameInfo.doorLevel;
						remindfire.setText(allremind);
						GameInfo.RescueProcess = GameInfo.RescueProcess + "+" + (GameInfo.endlevel - GameInfo.startlevel);
					}
					else{
						String remind = GameInfo.startlevel + "-" + (GameInfo.startlevel - GameInfo.endlevel);
						String allremind = remind + " = " +GameInfo.doorLevel;
						remindfire.setText(allremind);
						GameInfo.RescueProcess = GameInfo.RescueProcess + "-" + (GameInfo.startlevel - GameInfo.endlevel);
					}
					GameInfo.RescueProcess = GameInfo.RescueProcess + " = " + GameInfo.doorLevel;
				}
				setflag = 1;
			}
		}
	}
}
