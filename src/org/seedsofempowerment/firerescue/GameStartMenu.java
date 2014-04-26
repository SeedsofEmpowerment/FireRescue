/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : GameStartMenu.java
* description : This class for the start screen of the game                          
* 
* created by Wang Shiliang at 6/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;

public class GameStartMenu {
	private Bitmap backGround;
	public static boolean isNameButtonPress;
	public static boolean isAgeButtonPress;
	public static boolean isGenderButtonPress; 
	
	public static final int START = 0;
	public static final int INPUT_NAME = 1;
	public static final int SELECT_GENDER = 2;
	public static final int INPUT_AGE = 3;
	public static final int READY = 4;
	public static int gameState = START;
		
	private int newaccountX,newaccountY; //the positionX and PositionY of image new account
	private int settingX,settingY;//the positionX and PositionY of image setting
	private int loadaccountX,loadaccountY;//the positionX and PositionY of image load account
	private int startX,startY;//the positionX and PositionY of image start
	private int exitX,exitY;
	
	public GameStartMenu(Bitmap backGround){
		this.backGround = backGround;
		newaccountX  = PhoneInfo.getRealWidth(480);//new account
		newaccountY  = PhoneInfo.getRealHeight(270);//new account
		loadaccountX = newaccountX;
		loadaccountY = PhoneInfo.getRealHeight(315);//load account
		settingX = loadaccountX;
		settingY = PhoneInfo.getRealHeight(360);//setting
		startX = loadaccountX;
		startY = PhoneInfo.getRealHeight(400);//start
		exitX = PhoneInfo.getRealWidth(750);
		exitY = PhoneInfo.getRealHeight(0);
	}
	
	//draw the start menu
	public void draw(Canvas canvas, Paint paint){
		//background
		canvas.drawBitmap(backGround, null, new Rect(0,0,PhoneInfo.resolutionWidth,PhoneInfo.resolutionHeight), paint);
	}
	
	public void onTouchEvent(MotionEvent event) {
		//get the point where user touch
		int pointX = (int)event.getX();
		int pointY = (int)event.getY();
		if(pointX > newaccountX && pointX < PhoneInfo.getRealWidth(700) && pointY > newaccountY && pointY < PhoneInfo.getRealHeight(310)){
    		if(event.getAction() == MotionEvent.ACTION_DOWN){
				//process the vibrate and sound effect
				GameInfo.soundEffect[0].play((float) 0.3);
				GameInfo.vibrate.playVibrate(-1);
			}
    		
    		else if(event.getAction() == MotionEvent.ACTION_UP){
				//go the the next action
    			if(GameInfo.isCreateSurfaceView == false){
					Intent intent = new Intent(GameEngineActivity.instance, CreateAccountActivity.class);
					GameEngineActivity.instance.startActivity(intent);
    				//UserinfoInputDialog dialog = new UserinfoInputDialog(GameEngineActivity.instance);
					GameInfo.isCreateSurfaceView = true;
					//dialog.show();
    			}
			}
		}
		
		//if press the load account button means load a new game
		else if(pointX > loadaccountX && pointX < PhoneInfo.getRealWidth(700) && pointY > loadaccountY && pointY < PhoneInfo.getRealHeight(350)){
    		if(event.getAction() == MotionEvent.ACTION_DOWN){
				//process the viberate and sound effect
				GameInfo.soundEffect[0].play((float) 0.3);
				GameInfo.vibrate.playVibrate(-1);
			}
    		else if(event.getAction() == MotionEvent.ACTION_UP){
    			//go the the next action
    			if(GameInfo.isCreateSurfaceView == false){
    				GameInfo.isCreateSurfaceView = true;
    				//judge whether the account exist
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
    						int i = 0;
    						for(String temp: nameSequence){
    							++i;
    						}
    						if(i > 0){
								Intent intent = new Intent(GameEngineActivity.instance, InputNameActivity.class);
								GameEngineActivity.instance.startActivity(intent);
    						}
    						else{
    							Intent intent = new Intent(GameEngineActivity.instance, AccountNotExistActivity.class);
								GameEngineActivity.instance.startActivity(intent);
    						}
    					}
    					else{
    						Intent intent = new Intent(GameEngineActivity.instance, AccountNotExistActivity.class);
							GameEngineActivity.instance.startActivity(intent);
    					}
    				}
    				else{
    					File path = new File("/");
    					if(path.exists()){
    						final String nameSequence[];
    						nameSequence = path.list();
    						int i = 0;
    						for(String temp: nameSequence){
    							++i;
    						}
    						if(i > 0){
								Intent intent = new Intent(GameEngineActivity.instance, InputNameActivity.class);
								GameEngineActivity.instance.startActivity(intent);
    						}
    						else{
    							Intent intent = new Intent(GameEngineActivity.instance, AccountNotExistActivity.class);
								GameEngineActivity.instance.startActivity(intent);
    						}
    					}
    					else{
    						Intent intent = new Intent(GameEngineActivity.instance, AccountNotExistActivity.class);
							GameEngineActivity.instance.startActivity(intent);
    					}
    				}
    			}
    		}
		}
		
		//if press the setting button 
		else if(pointX > settingX && pointX < PhoneInfo.getRealWidth(700) && pointY > settingY && pointY < PhoneInfo.getRealHeight(390)){
    		if(event.getAction() == MotionEvent.ACTION_DOWN){
    			//process the viberate and sound effect
				GameInfo.soundEffect[0].play((float) 0.3);
				GameInfo.vibrate.playVibrate(-1);
			}

    		else if(event.getAction() == MotionEvent.ACTION_UP){
    			//go the the next action
    			if(GameInfo.isCreateSurfaceView == false){
					Intent intent = new Intent(GameEngineActivity.instance, SettingActivity.class);
					GameEngineActivity.instance.startActivity(intent);
					GameInfo.isCreateSurfaceView = true;
    			}
			}	
		}
			
		//if press the start button means start the game
		else if(pointX > startX && pointX < PhoneInfo.getRealWidth(700) && pointY > startY && pointY < PhoneInfo.getRealHeight(430)){
    		if(event.getAction() == MotionEvent.ACTION_DOWN){
				//process the viberate and sound effect
				GameInfo.vibrate.playVibrate(-1);
			}
    		else if(event.getAction() == MotionEvent.ACTION_UP){
    			if(GameInfo.isStartAllowed == true){
					GameSurfaceView.gameState = GameSurfaceView.START_GAME;
					GameSurfaceView.myView.isStart = true;
    			}
    			else{
    				//go the the next action
    				Intent intent = new Intent(GameEngineActivity.instance, CreateAccountWarningActivity.class);
    				GameEngineActivity.instance.startActivity(intent);
    			}
			}
		}
		
		//if press the exist button 
		else if(pointX > exitX && pointX < PhoneInfo.getRealWidth(800) && pointY > exitY && pointY < PhoneInfo.getRealHeight(50)){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				//process the viberate and sound effect
				GameInfo.soundEffect[0].play((float) 0.3);
				GameInfo.vibrate.playVibrate(-1);
			}
    		else if(event.getAction() == MotionEvent.ACTION_UP){
    			System.exit(0);
			}
		}
	}
}
