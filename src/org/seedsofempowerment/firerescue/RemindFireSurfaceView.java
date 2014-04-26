/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : RemindFireSurfaceView.java
* description : This game will remind the user which floor the people is                          
* 
* created by Wang Shiliang at 6/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class RemindFireSurfaceView extends SurfaceView implements Callback {
	public static RemindFireSurfaceView myView;
	public static int screenW, screenH;
	private Canvas canvas;
	private SurfaceHolder sfh;
	private Paint paint;
	private Bitmap fireremindbackground;
	private Bitmap crosslevelbackground;
	private Resources res = this.getResources();
	public int gameState;
	public final static int REMINDFIRE = 0;
	public final static int LEVELCROSS = 1;
	public final static int UPPERROUND = 2;
	public final static int SUCCESS = 3;
	private int flag;
	public RemindFireSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//super(context, attrs);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		flag = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		screenW = this.getWidth();
		screenH = this.getHeight();
		myView = this;
		initGame();      //use to initialize this game
		myDraw();
	}

	private void myDraw() {
		// TODO Auto-generated method stub
		try{
			canvas = sfh.lockCanvas();
			if(canvas != null){
				if(gameState == REMINDFIRE){
					if(flag == 0){
						canvas.drawBitmap(fireremindbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						float textSize = (float) (36 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE); 
						String text1 = "Round 1:";
						String text2 = "You need to rescue 5";
						String text3 = "people in this round";
						canvas.drawText(text1, PhoneInfo.getRealWidth(190),PhoneInfo.getRealHeight(70),paint);
						canvas.drawText(text2, PhoneInfo.getRealWidth(120),PhoneInfo.getRealHeight(120),paint);
						canvas.drawText(text3, PhoneInfo.getRealWidth(120),PhoneInfo.getRealHeight(170),paint);
					}
					else if(flag == 1){
						canvas.drawBitmap(fireremindbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						int number = GameInfo.doorLevel;
						float textSize = (float) (36 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE); 
						String remind1 = "Help! We are on the ";
						String remind2 = "floor " + Integer.toString(number)+"!";
						canvas.drawText(remind1, PhoneInfo.getRealWidth(100), PhoneInfo.getRealHeight(150), paint);
						canvas.drawText(remind2, PhoneInfo.getRealWidth(200), PhoneInfo.getRealHeight(200), paint);
					}
				}
				else if(gameState == LEVELCROSS){
					if(flag == 0){
						canvas.drawBitmap(crosslevelbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						float textSize = (float) (40 * PhoneInfo.heightRatio);
						float textSize2 = (float) (28 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE); 
						String remind1 = "";
						String addtext = "";
						int width = (int) paint.measureText(GameInfo.calculation);
						int startX = (PhoneInfo.getRealWidth(500) - width) / 2; 
						int leftnum = GameInfo.remainPeople;
						addtext = "You have "+leftnum+" remaining";
						canvas.drawText(remind1, PhoneInfo.getRealWidth(70), PhoneInfo.getRealHeight(100), paint);
						paint.setTextSize(textSize2);
						canvas.drawText(GameInfo.calculation, startX, PhoneInfo.getRealHeight(160), paint);
						paint.setTextSize(textSize);
						canvas.drawText(addtext, PhoneInfo.getRealWidth(30), PhoneInfo.getRealHeight(210), paint);
					}
					else if(flag == 1){
						canvas.drawColor(Color.BLACK);	
						canvas.drawBitmap(fireremindbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						int number = GameInfo.doorLevel;
						float textSize = (float) (40 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE); 
						String remind1 = "Help! We are on the ";
						String remind2 = Integer.toString(number)+" floor!";
						canvas.drawText(remind1, PhoneInfo.getRealWidth(100), PhoneInfo.getRealHeight(150), paint);
						canvas.drawText(remind2, PhoneInfo.getRealWidth(200), PhoneInfo.getRealHeight(200), paint);
					}
				}
				else if(gameState == UPPERROUND){
					if(flag == 0){
						canvas.drawBitmap(crosslevelbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						float textSize = (float) (40 * PhoneInfo.heightRatio);
						float textSize2 = (float) (28 * PhoneInfo.heightRatio);
						float textSize3 = (float) (32 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE); 
						String remind1 = "";
						String addtext = "You have succesfully passed";
						String addtext2 = "this round";
						remind1 = "Congratulations! ";
						int width = (int) paint.measureText(GameInfo.calculation);
						int startX = (PhoneInfo.getRealWidth(500) - width) / 2; 
						canvas.drawText(remind1, PhoneInfo.getRealWidth(70), PhoneInfo.getRealHeight(100), paint);
						paint.setTextSize(textSize2);
						canvas.drawText(GameInfo.calculation, startX, PhoneInfo.getRealHeight(160), paint);
						paint.setTextSize(textSize3);
						canvas.drawText(addtext, PhoneInfo.getRealWidth(30), PhoneInfo.getRealHeight(210), paint);
						canvas.drawText(addtext2, PhoneInfo.getRealWidth(80), PhoneInfo.getRealHeight(260), paint);
					}
					else if(flag == 1){
						canvas.drawBitmap(fireremindbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						float textSize = (float) (36 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE);
						String text1 = "Round "+GameInfo.round+":";
						String text2 = "You need to rescue 5";
						String text3 = "people in this round";
						canvas.drawText(text1, PhoneInfo.getRealWidth(190),PhoneInfo.getRealHeight(70),paint);
						canvas.drawText(text2, PhoneInfo.getRealWidth(120),PhoneInfo.getRealHeight(120),paint);
						canvas.drawText(text3, PhoneInfo.getRealWidth(120),PhoneInfo.getRealHeight(170),paint);
					}
					else if(flag == 2){
						canvas.drawBitmap(fireremindbackground, null, new Rect(0,0,screenW,screenH) ,paint);
						int number = GameInfo.doorLevel;
						float textSize = (float) (36 * PhoneInfo.heightRatio);
						paint.setTextSize(textSize);
						paint.setColor(Color.WHITE); 
						String remind1 = "Help! We are on the ";
						String remind2 = Integer.toString(number)+" floor!";
						canvas.drawText(remind1, PhoneInfo.getRealWidth(100), PhoneInfo.getRealHeight(150), paint);
						canvas.drawText(remind2, PhoneInfo.getRealWidth(200), PhoneInfo.getRealHeight(200), paint);
					}
				}
				else if(gameState == SUCCESS){
					canvas.drawColor(Color.BLACK);	
					canvas.drawBitmap(crosslevelbackground, null, new Rect(0,0,screenW,screenH) ,paint);
					float textSize = (float) (36 * PhoneInfo.heightRatio);
					paint.setTextSize(textSize);
					paint.setColor(Color.WHITE); 
					String remind1 = "Congratulations! ";
					String remind2 = "You've cleared all customes!";
					canvas.drawText(remind1, PhoneInfo.getRealWidth(100), PhoneInfo.getRealHeight(150), paint);
					canvas.drawText(remind2, PhoneInfo.getRealWidth(30), PhoneInfo.getRealHeight(210), paint);
				}
			}
		}
		catch(Exception e){
			Log.v("yuan","Draw error");
		}
		finally{
			if(canvas != null){
				sfh.unlockCanvasAndPost(canvas);	
			}
		}		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
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
					imgRecycle(fireremindbackground);
					imgRecycle(crosslevelbackground);
					Intent intent = new Intent();
					intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
					RemindFireActivity.instance.finish();
				}
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
					imgRecycle(fireremindbackground);
					imgRecycle(crosslevelbackground);
					Intent intent = new Intent();
					intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
					RemindFireActivity.instance.finish();
				}
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
					imgRecycle(fireremindbackground);
					imgRecycle(crosslevelbackground);
					Intent intent = new Intent();
					intent.setClass(RemindFireActivity.instance, GameEngineActivity.class);
					RemindFireActivity.instance.finish();
				}
			}
		}
		else if(gameState == SUCCESS){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				GameInfo.vibrate.playVibrate(-1);	
			}
			else if(event.getAction() == MotionEvent.ACTION_UP){
				imgRecycle(fireremindbackground);
				imgRecycle(crosslevelbackground);
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
		myDraw();
		return true;
	}
	
	private void imgRecycle(Bitmap picture){
		if(picture != null){
			picture.recycle();
		}
	}
	
	private void initGame(){
		fireremindbackground = BitmapFactory.decodeResource(res, R.drawable.remind);
		crosslevelbackground = BitmapFactory.decodeResource(res, R.drawable.crosslevel);
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
		flag = 0;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
