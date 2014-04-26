/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : SelectLadderSurfaceView.java
* description : When the user press the laderr button                            
* 
* created by Wang Shiliang at 5/22/2012 21:19:50
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

public class SelectLadderSurfaceView extends SurfaceView implements Callback{
	public static SelectLadderSurfaceView myView;
	public static int screenW, screenH;
	private Canvas canvas;
	private SurfaceHolder sfh;
	private Paint paint;
	
	private Resources res = this.getResources();
	private Bitmap selectladderBackGround;
	private Bitmap[] ladderOrientation;
	private Bitmap[] ladderSelection;
	private Bitmap[] jumpSpringSelection;
	public static int jumpTime = 0;
	private int ladderSelectionTextX;
	private int ladderSelectionTextY;
	private int ladderOrientationX;
	private int ladderOrientationY;
	private int ladderSelectionX;
	private int ladderSelectionY;
	private int currentLevel;
	private int ladderChoose;
	private int selectionRound;
	private int orientationChoose;
	private boolean selectLadder;
	private boolean selectJumpSpring;
	public static final int SELECT_LADDER_SIZE = 0;
	public static final int SELECT_LADDER_DIRECTION = 1;
	public static final int ERRORSELECTION = 2;

	public SelectLadderSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		Intent intent = SelectLadderActivity.instance.getIntent();
		currentLevel = intent.getIntExtra("currentLevel", 0);
		// TODO Auto-generated constructor stub
		ladderChoose = 0;
		orientationChoose = 0;
		selectionRound = 0;
		jumpTime = 0;
		selectLadder = false;
		selectJumpSpring = false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		screenW = this.getWidth();
		screenH = this.getHeight();
		myView = this;
		initGame();      //use to initialize this game
		myDraw();
		
	}

	private void myDraw() {
		try{
			canvas = sfh.lockCanvas();
			if(canvas != null){
				canvas.drawColor(Color.BLACK);	
				canvas.drawBitmap(selectladderBackGround, null, new Rect(0,0,screenW,screenH) ,paint);
				//draw the ladder to be selected
				if(GameInfo.difficulty == GameInfo.TRAINEE){
					for(int i = 0;i!=4;++i){
						if(FireRescueGamming.ladderLevel[i] > 0){
							if(ladderChoose == i+1){
								canvas.drawBitmap(ladderSelection[1],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
							}
							else{
								canvas.drawBitmap(ladderSelection[0],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
							}
						}
					}
					if(ladderChoose == 5){
						canvas.drawBitmap(ladderSelection[3],null,new Rect(ladderSelectionX+4*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+4*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
					}
					else{
						canvas.drawBitmap(ladderSelection[2],null,new Rect(ladderSelectionX+4*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+4*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
					}
				}
				else if(GameInfo.difficulty == GameInfo.FIREFIGHTER){
					if(selectionRound == 0){
						for(int i = 0;i!=5;++i){
							if(FireRescueGamming.ladderLevel[i] > 0){
								if(ladderChoose == i+1){
									canvas.drawBitmap(ladderSelection[1],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
								}
								else{
									canvas.drawBitmap(ladderSelection[0],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
								}
							}
						}
					}
					else if(selectionRound == 1){
						if(FireRescueGamming.ladderLevel[5] > 0){
							if(ladderChoose == 1){
								canvas.drawBitmap(ladderSelection[1],null,new Rect(ladderSelectionX,ladderSelectionY,ladderSelectionX+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
							}
							else{
								canvas.drawBitmap(ladderSelection[0],null,new Rect(ladderSelectionX,ladderSelectionY,ladderSelectionX+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
							}
						}
						if(ladderChoose == 2){
							canvas.drawBitmap(ladderSelection[3],null,new Rect(ladderSelectionX+PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
						}
						else{
							canvas.drawBitmap(ladderSelection[2],null,new Rect(ladderSelectionX+PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
						}
					}
				}
				else if(GameInfo.difficulty == GameInfo.CAPTAIN){
					for(int i = 0;i!=4;++i){
						if(FireRescueGamming.jumpSpringLevel[i] > 0){
							if(ladderChoose == i+1){
								canvas.drawBitmap(jumpSpringSelection[1],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
							}
							else{
								canvas.drawBitmap(jumpSpringSelection[0],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
							}
						}
						if(ladderChoose == 5){
							canvas.drawBitmap(ladderSelection[3],null,new Rect(ladderSelectionX+4*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+4*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
						}
						else{
							canvas.drawBitmap(ladderSelection[2],null,new Rect(ladderSelectionX+4*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+4*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
						}
					}
				}
				else if(GameInfo.difficulty == GameInfo.CHIEF){
					if(selectionRound == 0){
						for(int i = 0;i!=4;++i){
							if(FireRescueGamming.ladderLevel[i] > 0){
								if(ladderChoose == i+1){
									canvas.drawBitmap(ladderSelection[1],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
								}
								else{
									canvas.drawBitmap(ladderSelection[0],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
								}
							}
						}
						if(ladderChoose == 5){
							canvas.drawBitmap(ladderSelection[3],null,new Rect(ladderSelectionX+4*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+4*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
						}
						else{
							canvas.drawBitmap(ladderSelection[2],null,new Rect(ladderSelectionX+4*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+4*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
						}
					}
					else if(selectionRound == 1){
						for(int i = 0;i!=4;++i){
							if(FireRescueGamming.jumpSpringLevel[i] > 0){
								if(ladderChoose == i+1){
									canvas.drawBitmap(jumpSpringSelection[1],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
								}
								else{
									canvas.drawBitmap(jumpSpringSelection[0],null,new Rect(ladderSelectionX+i*PhoneInfo.getRealWidth(115),ladderSelectionY,ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[1].getWidth()),ladderSelectionY+PhoneInfo.getFigureHeight(ladderSelection[0].getHeight())), paint);
								}
							}
						}
					}
				}
				//draw the level number of each ladder
				if(GameInfo.difficulty == GameInfo.TRAINEE){
					paint.setColor(Color.rgb(247, 126, 8));
					float textSize = (float) (24 * PhoneInfo.heightRatio);
					paint.setTextSize(textSize);
					for(int i = 0;i!=4;++i){
						if(FireRescueGamming.ladderLevel[i] > 0){
							int width = (int) paint.measureText(FireRescueGamming.ladderLevel[i]+"");
							int width2 = PhoneInfo.getRealWidth(50);
							ladderSelectionTextX = (width2 - width) / 2 + PhoneInfo.getRealWidth(182);
							ladderSelectionTextY = (int) (PhoneInfo.getRealHeight(185) + textSize / 2);
							canvas.drawText(FireRescueGamming.ladderLevel[i]+"", ladderSelectionTextX+i*PhoneInfo.getRealWidth(115), ladderSelectionTextY, paint);
						}
					}
					textSize = (float) (18 * PhoneInfo.heightRatio);	
					paint.setTextSize(textSize);
					ladderSelectionTextX = PhoneInfo.getRealWidth(648);
					ladderSelectionTextY = PhoneInfo.getRealHeight(190);
					canvas.drawText("more",ladderSelectionTextX,ladderSelectionTextY,paint);
				}	
				else if(GameInfo.difficulty == GameInfo.FIREFIGHTER){
					paint.setColor(Color.rgb(247, 126, 8));
					float textSize = (float) (24 * PhoneInfo.heightRatio);
					paint.setTextSize(textSize);
					if(selectionRound == 0){
						for(int i = 0;i!=5;++i){
							if(FireRescueGamming.ladderLevel[i] > 0){
								int width = (int) paint.measureText(FireRescueGamming.ladderLevel[i]+"");
								int width2 = PhoneInfo.getRealWidth(50);
								ladderSelectionTextX = (width2 - width) / 2 + PhoneInfo.getRealWidth(182);
								ladderSelectionTextY = (int) (PhoneInfo.getRealHeight(185) + textSize / 2);
								canvas.drawText(FireRescueGamming.ladderLevel[i]+"", ladderSelectionTextX+i*PhoneInfo.getRealWidth(115), ladderSelectionTextY, paint);
							}
						}
					}
					else if(selectionRound == 1){				
						textSize = (float) (18 * PhoneInfo.heightRatio);	
						paint.setTextSize(textSize);
						int width = (int) paint.measureText(FireRescueGamming.ladderLevel[5]+"");
						int width2 = PhoneInfo.getRealWidth(50);
						ladderSelectionTextX = (width2 - width) / 2 + PhoneInfo.getRealWidth(182);
						ladderSelectionTextY = (int) (PhoneInfo.getRealHeight(185) + textSize / 2);
						if(FireRescueGamming.ladderLevel[5] > 0){
							canvas.drawText(FireRescueGamming.ladderLevel[5]+"", ladderSelectionTextX, ladderSelectionTextY, paint);
						}
						canvas.drawText("more",PhoneInfo.getRealWidth(300),ladderSelectionTextY,paint);
					}		
				}
				else if(GameInfo.difficulty == GameInfo.CAPTAIN){
					paint.setColor(Color.rgb(247, 126, 8));
					float textSize = (float) (24 * PhoneInfo.heightRatio);
					paint.setTextSize(textSize);
					for(int i = 0;i!=4;++i){
						if(FireRescueGamming.jumpSpringLevel[i] > 0){
							int width = (int) paint.measureText(FireRescueGamming.jumpSpringLevel[i]+"");
							int width2 = PhoneInfo.getRealWidth(50);
							ladderSelectionTextX = (width2 - width) / 2 + PhoneInfo.getRealWidth(182);
							ladderSelectionTextY = (int) (PhoneInfo.getRealHeight(185) + textSize / 2);
							canvas.drawText(FireRescueGamming.jumpSpringLevel[i]+"", ladderSelectionTextX+i*PhoneInfo.getRealWidth(115), ladderSelectionTextY, paint);
						}		
					}
					textSize = (float) (18 * PhoneInfo.heightRatio);	
					paint.setTextSize(textSize);
					ladderSelectionTextX = PhoneInfo.getRealWidth(648);
					ladderSelectionTextY = PhoneInfo.getRealHeight(190);
					canvas.drawText("more",ladderSelectionTextX,ladderSelectionTextY,paint);
				}
				else if(GameInfo.difficulty == GameInfo.CHIEF){
					paint.setColor(Color.rgb(247, 126, 8));
					float textSize = (float) (24 * PhoneInfo.heightRatio);
					paint.setTextSize(textSize);
					if(selectionRound == 0){
						for(int i = 0;i!=4;++i){
							if(FireRescueGamming.ladderLevel[i] > 0){
								int width = (int) paint.measureText(FireRescueGamming.ladderLevel[i]+"");
								int width2 = PhoneInfo.getRealWidth(50);
								ladderSelectionTextX = (width2 - width) / 2 + PhoneInfo.getRealWidth(182);
								ladderSelectionTextY = (int) (PhoneInfo.getRealHeight(185) + textSize / 2);
								canvas.drawText(FireRescueGamming.ladderLevel[i]+"", ladderSelectionTextX+i*PhoneInfo.getRealWidth(115), ladderSelectionTextY, paint);
							}	
						}
						textSize = (float) (18 * PhoneInfo.heightRatio);	
						paint.setTextSize(textSize);
						ladderSelectionTextX = PhoneInfo.getRealWidth(648);
						ladderSelectionTextY = PhoneInfo.getRealHeight(190);
						canvas.drawText("more",ladderSelectionTextX,ladderSelectionTextY,paint);
					}
					else if(selectionRound == 1){
						for(int i = 0;i!=4;++i){
							if(FireRescueGamming.jumpSpringLevel[i]>0){
								int width = (int) paint.measureText(FireRescueGamming.jumpSpringLevel[i]+"");
								int width2 = PhoneInfo.getRealWidth(50);
								ladderSelectionTextX = (width2 - width) / 2 + PhoneInfo.getRealWidth(182);
								ladderSelectionTextY = (int) (PhoneInfo.getRealHeight(185) + textSize / 2);
								canvas.drawText(FireRescueGamming.jumpSpringLevel[i]+"", ladderSelectionTextX+i*PhoneInfo.getRealWidth(115), ladderSelectionTextY, paint);
							}
						}
					}
				}
				//draw the orientation of the ladder
				for(int i = 0;i!=2;++i){
					if(orientationChoose == i+1){
						canvas.drawBitmap(ladderOrientation[2*i+1],null,new Rect(ladderOrientationX+i*PhoneInfo.getRealWidth(230),ladderOrientationY,ladderOrientationX+i*PhoneInfo.getRealWidth(230)+PhoneInfo.getFigureWidth(ladderOrientation[0].getWidth()),ladderOrientationY+PhoneInfo.getFigureHeight(ladderOrientation[0].getHeight())),paint);		
					}
					canvas.drawBitmap(ladderOrientation[2*i],null,new Rect(ladderOrientationX+i*PhoneInfo.getRealWidth(230),ladderOrientationY,ladderOrientationX+i*PhoneInfo.getRealWidth(230)+PhoneInfo.getFigureWidth(ladderOrientation[0].getWidth()),ladderOrientationY+PhoneInfo.getFigureHeight(ladderOrientation[0].getHeight())),paint);
				}		
			}
		}
		catch(Exception e){
			Log.v("wang","Draw error");
		}
		finally{
			if(canvas != null){
				sfh.unlockCanvasAndPost(canvas);	
			}
		}		
	}

	private void initGame() {
		// TODO Auto-generated method stub
		
		selectladderBackGround = BitmapFactory.decodeResource(res, R.drawable.selectladderbackground);//background of selectlader dialog
		
		ladderOrientation = new Bitmap[4];
		ladderOrientation[0] = BitmapFactory.decodeResource(res, R.drawable.ladderup);
		ladderOrientation[1] = BitmapFactory.decodeResource(res, R.drawable.ladderupgreen);
		ladderOrientation[2] = BitmapFactory.decodeResource(res, R.drawable.ladderdown);
		ladderOrientation[3] = BitmapFactory.decodeResource(res, R.drawable.ladderdowngreen);
		
		ladderSelection = new Bitmap[4];
		ladderSelection[0] = BitmapFactory.decodeResource(res, R.drawable.laddersize);
		ladderSelection[1] = BitmapFactory.decodeResource(res, R.drawable.laddersizegreen);
		ladderSelection[2] = BitmapFactory.decodeResource(res, R.drawable.ladderforall);
		ladderSelection[3] = BitmapFactory.decodeResource(res, R.drawable.ladderforallgreen);
		
		jumpSpringSelection = new Bitmap[2];
		jumpSpringSelection[0] = BitmapFactory.decodeResource(res, R.drawable.jumpspring);
		jumpSpringSelection[1] = BitmapFactory.decodeResource(res, R.drawable.jumpspringgreen);
		ladderSelectionX = PhoneInfo.getRealWidth(125);
		ladderSelectionY = PhoneInfo.getRealHeight(130);
		ladderOrientationX = PhoneInfo.getRealWidth(230);
		ladderOrientationY = PhoneInfo.getRealHeight(280);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 /* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int pointX = (int)event.getX();
		int pointY = (int)event.getY();
		//if press the left button
		if(pointX >= PhoneInfo.getRealWidth(75) && pointX <= PhoneInfo.getRealWidth(115)
			&&pointY >= PhoneInfo.getRealHeight(170) && pointY <= PhoneInfo.getRealHeight(215)){
			if(selectionRound >0){
				--selectionRound;
			}
		}
		//if press the right button
		if(pointX >= PhoneInfo.getRealWidth(700) && pointX <= PhoneInfo.getRealWidth(740)
			&&pointY >= PhoneInfo.getRealHeight(170) && pointY <= PhoneInfo.getRealHeight(215)){
			if((GameInfo.ladderNumber + GameInfo.jumpSpringNumber) / 5 > selectionRound){
				++selectionRound;
			}
		}
		//if select the ladder
		if(pointX >= ladderSelectionX && pointX <= PhoneInfo.getRealWidth(690)
			&&pointY >= PhoneInfo.getRealHeight(130) && pointY <= PhoneInfo.getRealHeight(235)){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				GameInfo.vibrate.playVibrate(-1);
			}
			else if(event.getAction() == MotionEvent.ACTION_UP){
				if(GameInfo.difficulty == GameInfo.TRAINEE){
					for(int i = 0;i!=5;++i){
						if(pointX >= ladderSelectionX+i*PhoneInfo.getRealWidth(115) && pointX <= ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth())){
							ladderChoose = i+1;
						}
					}
					if(ladderChoose == 5){
						orientationChoose = 0;
						//we need to input the value
						if(GameInfo.randomLadderNumber > 0){
							Intent intent = new Intent();
							intent.putExtra("index", 4);
							intent.setClass(SelectLadderActivity.instance, InputLadderSizeActivity.class);
							SelectLadderActivity.instance.startActivity(intent);
						}
						else{
							ladderChoose = 0;
						}
					}
				}
				else if(GameInfo.difficulty == GameInfo.FIREFIGHTER){
					if(selectionRound == 0){
						for(int i = 0;i!=5;++i){
							if(pointX >= ladderSelectionX+i*PhoneInfo.getRealWidth(115) && pointX <= ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth())){
								ladderChoose = i+1;
							}
						}
					}
					else if(selectionRound == 1){
						for(int i = 0;i!=2;++i){
							if(pointX >= ladderSelectionX+i*PhoneInfo.getRealWidth(115) && pointX <= ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth())){
								ladderChoose = i+1;
							}
						}
						if(ladderChoose == 2){
							orientationChoose = 0;
							//we need to input the value
							if(GameInfo.randomLadderNumber > 0){
								Intent intent = new Intent();
								intent.putExtra("index", 6);
								intent.setClass(SelectLadderActivity.instance, InputLadderSizeActivity.class);
								SelectLadderActivity.instance.startActivity(intent);
							}
							else{
								ladderChoose = 0;
							}
						}
					}
				}
				else if(GameInfo.difficulty == GameInfo.CAPTAIN){
					for(int i = 0;i!=5;++i){
						if(pointX >= ladderSelectionX+i*PhoneInfo.getRealWidth(115) && pointX <= ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth())){
							ladderChoose = i+1;					
						}
					}
					if(ladderChoose >= 1 && ladderChoose < 5){
						orientationChoose = 0;
						Intent intent = new Intent();
						intent.setClass(SelectLadderActivity.instance, InsertJumpTimeActivity.class);
						SelectLadderActivity.instance.startActivity(intent);
					}
					if(ladderChoose == 5){
						orientationChoose = 0;
						//we need to input the value
						if(GameInfo.randomLadderNumber > 0){
							Intent intent = new Intent();
							intent.putExtra("index", 4);
							intent.setClass(SelectLadderActivity.instance, InputLadderSizeActivity.class);
							SelectLadderActivity.instance.startActivity(intent);
						}
						else{
							ladderChoose = 0;
						}
					}
				}
				else if(GameInfo.difficulty == GameInfo.CHIEF){
					if(selectionRound == 0){
						for(int i = 0;i!=5;++i){
							if(pointX >= ladderSelectionX+i*PhoneInfo.getRealWidth(115) && pointX <= ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth())){
								ladderChoose = i+1;
								selectLadder = true;
							}
						}
						if(ladderChoose == 5){
							orientationChoose = 0;
							//we need to input the value
							if(GameInfo.randomLadderNumber > 0){
								Intent intent = new Intent();
								intent.putExtra("index", 4);
								intent.setClass(SelectLadderActivity.instance, InputLadderSizeActivity.class);
								SelectLadderActivity.instance.startActivity(intent);
							}
							else{
								ladderChoose = 0;
							}
						}
					}
					else if(selectionRound == 1){
						for(int i = 0;i!=4;++i){
							if(pointX >= ladderSelectionX+i*PhoneInfo.getRealWidth(115) && pointX <= ladderSelectionX+i*PhoneInfo.getRealWidth(115)+PhoneInfo.getFigureWidth(ladderSelection[0].getWidth())){
								ladderChoose = i+1;
								selectJumpSpring = true;
							}
						}
						if(ladderChoose > 0 && ladderChoose < 5){
							orientationChoose = 0;
							Intent intent = new Intent();
							intent.setClass(SelectLadderActivity.instance, InsertJumpTimeActivity.class);
							SelectLadderActivity.instance.startActivity(intent);
						}
					}
				}
				
				//if the user has selected both the ladder and the orientation
				if(ladderChoose!=0&&orientationChoose!=0){
					if(GameInfo.difficulty == GameInfo.TRAINEE || GameInfo.difficulty == GameInfo.FIREFIGHTER){
						setLadderInfo();
					}
					else if(GameInfo.difficulty == GameInfo.CAPTAIN){
						setJumpSpringInfo();
					}
					else if(GameInfo.difficulty == GameInfo.CHIEF){
						if(selectLadder == true){
							setLadderInfo();
							selectLadder = false;
						}
						else if(selectJumpSpring == true){
							setJumpSpringInfo();
							selectJumpSpring = false;
						}
					}
					GameInfo.isLadderSurfaceView = false;
					//Resources release
					for(int i = 0;i!=4;++i){
						imgRecycle(ladderOrientation[i]);
					}
					for(int i = 0;i!=2;++i){
						imgRecycle(ladderSelection[i]);
					}
					imgRecycle(selectladderBackGround);
					GameInfo.soundEffect[0].play((float) 1.0);
					SelectLadderActivity.instance.finish();
				}
			}
		}
		
		//if select the orientation
		if(pointX >= ladderOrientationX && pointX <= PhoneInfo.getRealWidth(605)
			&&pointY >= PhoneInfo.getRealHeight(290) && pointY <= PhoneInfo.getRealHeight(460)){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				GameInfo.vibrate.playVibrate(-1);
			}
			else if(event.getAction() == MotionEvent.ACTION_UP){
				for(int i = 0;i!=2;++i){
					if(pointX >= ladderOrientationX+i*PhoneInfo.getRealWidth(230) && pointX <= ladderOrientationX+i*PhoneInfo.getRealWidth(230)+PhoneInfo.getFigureWidth(ladderOrientation[0].getWidth())){
						orientationChoose = i+1;
					}
				}
				//if the user has selected both the ladder and the orientation
				if(ladderChoose!=0&&orientationChoose!=0){
					if(GameInfo.difficulty == GameInfo.TRAINEE || GameInfo.difficulty == GameInfo.FIREFIGHTER){
						setLadderInfo();
					}
					else if(GameInfo.difficulty == GameInfo.CAPTAIN){
						if(ladderChoose == 5){
							setLadderInfo();
						}
						else{
							setJumpSpringInfo();
						}
					}
					else if(GameInfo.difficulty == GameInfo.CHIEF){
						if(selectLadder == true){
							setLadderInfo();
							selectLadder = false;
						}
						else if(selectJumpSpring == true){
							setJumpSpringInfo();
							selectJumpSpring = false;
						}
					}
					GameInfo.isLadderSurfaceView = false;
					//Resources release
					for(int i = 0;i!=4;++i){
						imgRecycle(ladderOrientation[i]);
					}
					for(int i = 0;i!=2;++i){
						imgRecycle(ladderSelection[i]);
					}
					imgRecycle(selectladderBackGround);
					GameInfo.soundEffect[0].play((float) 1.0);
					SelectLadderActivity.instance.finish();
				}
			}	
		}
		//if select the exit button
		if(pointX >= PhoneInfo.getRealWidth(725) && pointX <= PhoneInfo.getRealWidth(800)
			&&pointY >= PhoneInfo.getRealHeight(0) && pointY <= PhoneInfo.getRealHeight(75)){
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				GameInfo.vibrate.playVibrate(-1);
			}
			else if(event.getAction() == MotionEvent.ACTION_UP){
				GameInfo.isLadderSurfaceView = false;
				SelectLadderActivity.instance.finish();
			}
		}
		myDraw();
		return true;
	}
	public void imgRecycle(Bitmap picture)
	{
		if(picture != null)
			picture.recycle();
	}
	
	private void setJumpSpringInfo(){
		FireRescueGamming.jumpSpring[FireRescueGamming.jumpSpringCurrentNumber].setStartLevel(currentLevel);
		int endLevel = 0;
		if(orientationChoose == 1){
			endLevel = currentLevel + (FireRescueGamming.jumpSpringLevel[ladderChoose-1] * jumpTime);
		}
		else if(orientationChoose == 2){
			endLevel = currentLevel - (FireRescueGamming.jumpSpringLevel[ladderChoose-1] * jumpTime);
		}
		FireRescueGamming.jumpSpring[FireRescueGamming.jumpSpringCurrentNumber].setEndLevel(endLevel);
		FireRescueGamming.jumpSpring[FireRescueGamming.jumpSpringCurrentNumber].setJumpSize(FireRescueGamming.jumpSpringLevel[ladderChoose-1]);
		FireRescueGamming.jumpSpringLevel[ladderChoose-1] = -FireRescueGamming.jumpSpringLevel[ladderChoose-1];
		FireRescueGamming.jumpSpring[FireRescueGamming.jumpSpringCurrentNumber].setJumpTimes(jumpTime);
		FireRescueGamming.jumpSpring[FireRescueGamming.jumpSpringCurrentNumber].setPositionY(FireRescueGamming.jumpSpringY[FireRescueGamming.fireman.getCurrentLevel()]);
		++(FireRescueGamming.jumpSpringCurrentNumber);
	}
	
	private void setLadderInfo(){
		if(GameInfo.difficulty == GameInfo.TRAINEE){
			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setLaddersize(FireRescueGamming.ladderLevel[ladderChoose-1]);
			FireRescueGamming.ladderLevel[ladderChoose-1] = -FireRescueGamming.ladderLevel[ladderChoose-1];
		}
		else if(GameInfo.difficulty == GameInfo.FIREFIGHTER){
			 if(selectionRound == 0){
				FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setLaddersize(FireRescueGamming.ladderLevel[ladderChoose-1]);
				FireRescueGamming.ladderLevel[ladderChoose-1] = -FireRescueGamming.ladderLevel[ladderChoose-1];
			 }
			 else if(selectionRound == 1){
				FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setLaddersize(FireRescueGamming.ladderLevel[ladderChoose+4]);
				FireRescueGamming.ladderLevel[ladderChoose+4] = -FireRescueGamming.ladderLevel[ladderChoose+4];
			 }
		}
		else if(GameInfo.difficulty == GameInfo.CAPTAIN){
			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setLaddersize(FireRescueGamming.ladderLevel[ladderChoose-1]);
			FireRescueGamming.ladderLevel[ladderChoose-1] = -FireRescueGamming.ladderLevel[ladderChoose-1];
		}
		else if(GameInfo.difficulty == GameInfo.CHIEF){
			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setLaddersize(FireRescueGamming.ladderLevel[ladderChoose-1]);
			FireRescueGamming.ladderLevel[ladderChoose-1] = -FireRescueGamming.ladderLevel[ladderChoose-1];
		}
		FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setStartLevel(currentLevel);
		if(orientationChoose == 1){
			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setEndLevel(currentLevel+FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].getLaddersize());
		}
		else if(orientationChoose == 2){
			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setEndLevel(currentLevel-FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].getLaddersize());
		}
		
		int ladderlevelsize = FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].getEndLevel() - FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].getStartLevel();
		if(ladderlevelsize > 0){
			if(FireRescueGamming.fireman.getCurrentLevel() == 0){
	    		if(ladderlevelsize < 3){
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(ladderlevelsize-1);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[ladderlevelsize]);
	    		}
	    		else{
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(2);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[3]);
	    		}
			}
			else if(FireRescueGamming.fireman.getCurrentLevel() == 1){
				if(ladderlevelsize < 2){
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(ladderlevelsize-1);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[ladderlevelsize+1]);
				}
				else{
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(1);
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[3]);							
				}
			}
			else if(FireRescueGamming.fireman.getCurrentLevel() == 2){
				FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(0);
    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[3]);
			}
		}
		else{
			if(FireRescueGamming.fireman.getCurrentLevel() == 0){
				FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(0);
    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[0]);
			}
			else if(FireRescueGamming.fireman.getCurrentLevel() == 1){
				if(Math.abs(ladderlevelsize) < 2){
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(Math.abs(ladderlevelsize)-1);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[1]);
				}
				else{
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(1);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[1]);
				}
			}
			else if(FireRescueGamming.fireman.getCurrentLevel() == 2){
				if(Math.abs(ladderlevelsize) < 3){
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(Math.abs(ladderlevelsize)-1);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[2]);
				}
				else{
					FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setIndex(2);
	    			FireRescueGamming.ladder[FireRescueGamming.ladderCurrentNumber].setPositionY(FireRescueGamming.ladderY[2]);
				}
			}
		}
		++(FireRescueGamming.ladderCurrentNumber);
	}
}
