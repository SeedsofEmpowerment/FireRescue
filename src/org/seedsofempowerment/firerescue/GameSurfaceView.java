/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : GameSurfaceView.java
* description : This class is the main surfaceview of the game                            
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class GameSurfaceView extends SurfaceView implements Callback, Runnable{
	public static GameSurfaceView myView;
	public static int screenW, screenH;
	private Thread th;
	private Canvas canvas;
	private SurfaceHolder sfh;
	private Paint paint;
	private boolean flag;
	
	//define the game status
	public static final int GAME_MENU = 0;  //game menu
	public static final int START_GAME = 1; //start the game now
	public static final int LOAD_GAME = 2;  //load the game now
	public static final int SAVE_GAME = 3;
	public static final int GAME_INTRO = 4; //introduce the game
	public static final int GAMMING = 5;    //start playing
	public static final int GAMMINGBACK = 6;    //start playing
	
	//the current status in the game
	public static int gameState = GAME_MENU;
	
	//declare a resource to load the pictures
	private Resources res = this.getResources();
	
	private Bitmap backGround;
	private Bitmap difficultyBackGround;
	private Bitmap[] difficultyButton;
	private Bitmap startScreen;
	public boolean isStart;
	public boolean isLoad;
	private GameStartMenu gameStartMenu;
	private Bitmap[] loadScreen;
	private Bitmap[] startGameScreen;
	private Bitmap gameBackGround;
	private Bitmap door;
	private Bitmap[] savePicture;
	private Bitmap[] objects;
	//private Bitmap[] tools;
	//private Bitmap[] gameIntroBackGround;
	//private Bitmap[] soils;
	private Bitmap[] tools;//firetools
	private Bitmap[] fireman;//fireman motion picture
	private Bitmap[] arrows;
	private Bitmap[] fire;//fire motion picture
	private Bitmap[] ladder;
	private Bitmap[] water;
	private Bitmap life;
	private Bitmap waterNumber;
	private Bitmap jumpSpring;
	private int loadingIndex = 0;
	
	private FireRescueGamming fireRescueGamming;
	private Typeface font;
	//private Bitmap[][] crops;
	//private Bitmap[][] animations;
	//private Bitmap[] month;
	
	public GameSurfaceView(Context context, AttributeSet attrs){
		super(context, attrs);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		font = Typeface.createFromAsset(context.getAssets(), "mailr.ttf");
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		screenW = this.getWidth();
		screenH = this.getHeight();
		initGame();      //use to initialize this game
		flag = true;
		myView = this;
		th = new Thread(this);
		th.start();
		myDraw();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
//		GameInfo.backGroundMusic.stop();
//		GameInfo.backGroundMusic.release();
//		GameInfo.soundEffect.release();
		flag = false;	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (flag) {
			long start = System.currentTimeMillis();
			logic();
			myDraw();
			
			long end = System.currentTimeMillis();
			try {
				if (end - start < 100) {
					Thread.sleep(100 - (end - start));
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			} 
	    }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (gameState){
		case GAME_MENU:
			break;
		case GAME_INTRO:
			break;
		case GAMMING:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (gameState){
		case GAME_MENU:
			break;
		case GAME_INTRO:
			break;
		case GAMMING:
			break;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (gameState){
		case GAME_MENU:
			gameStartMenu.onTouchEvent(event);
			if(event.getAction() == MotionEvent.ACTION_UP){
				myDraw();
 				if(isStart == true){
					releaseGameMenuResource();
					initiateNewGame();
					gameState = GAMMING;
					isStart = false;
				}
				else if(isLoad == true){
					;
				}
			}
			break;
		case GAME_INTRO:
			myDraw();
			break;
		case GAMMING:
			fireRescueGamming.onTouchEvent(event);
			myDraw();
			break;
		case GAMMINGBACK:
			break;
		}
		return true;
	}

	private void releaseGameMenuResource() {
		// TODO Auto-generated method stub
		backGround.recycle();
		if(isStart == true){
			for(int i = 0;i!=4;++i){
				loadScreen[i].recycle();
			}
		}
		if(isLoad == true){
			for(int i = 0;i!=4;++i){
				startGameScreen[i].recycle();
			}
		}
	}
	
	public void logic(){
		switch(gameState){
		case GAME_MENU:
			break;
		case START_GAME:
			++loadingIndex;
			if(loadingIndex >=4){
				loadingIndex = 0;
			}
			break;
		case LOAD_GAME:
			++loadingIndex;
			if(loadingIndex >=4){
				loadingIndex = 0;
			}
			break;
		case GAME_INTRO:
			++loadingIndex;
			if(loadingIndex >=4){
				loadingIndex = 0;
			}
			break;
		case SAVE_GAME:
			++loadingIndex;
			if(loadingIndex >=4){
				loadingIndex = 0;
			}
			break;
		case GAMMING:
			fireRescueGamming.logic();
			break;
		case GAMMINGBACK:
			releaseGameResources();
			gameState = GAME_MENU;
			initGame();
			break;
		}
	}
	
	public void myDraw(){
		try{
			canvas = sfh.lockCanvas();
			if(canvas != null){
				canvas.drawColor(Color.BLACK);
				switch (gameState){
				case GAME_MENU:
					gameStartMenu.draw(canvas, paint);
					break;
				case START_GAME:
					canvas.drawBitmap(startGameScreen[loadingIndex], null, new Rect(0,0,PhoneInfo.resolutionWidth,PhoneInfo.resolutionHeight), paint);
					break;
				case LOAD_GAME:
					canvas.drawBitmap(loadScreen[loadingIndex], null, new Rect(0,0,PhoneInfo.resolutionWidth,PhoneInfo.resolutionHeight), paint);
					break;
				case SAVE_GAME:
					//canvas.drawBitmap(saveScreen[loadingIndex], null, new Rect(0,0,PhoneInfo.resolutionWidth,PhoneInfo.resolutionHeight), paint);
				case GAME_INTRO:
					//farmGameIntro.draw(canvas, paint);
					break;
				case GAMMING:				
					fireRescueGamming.draw(canvas, paint);
					break;
				case GAMMINGBACK:
					break;
				}
			}
		}
		catch(Exception e){
			Log.v("wang","Draw error");
		}
		finally{
			if(canvas != null)
			{
				sfh.unlockCanvasAndPost(canvas);
			}
		}	
	}
			
	
	public void initGame(){
		startScreen = BitmapFactory.decodeResource(res, R.drawable.start_screen);
		int backGroundX = (screenW - startScreen.getWidth()) / 2;
		int backGroundY = (screenH - startScreen.getHeight()) / 2;
		try{
			canvas = sfh.lockCanvas();
			if(canvas != null){
				canvas.drawColor(Color.BLACK);
				canvas.drawBitmap(startScreen, backGroundX, backGroundY, paint);
			}
		}
		catch(Exception e){
			Log.v("wang","Draw error");
		}
		finally{
			if(canvas != null)
			{
				sfh.unlockCanvasAndPost(canvas);
			}
		}
		
		if(gameState == GAME_MENU){
			//load the resources of the game
			GameInfo.backGroundMusic = new BackGroundMusic(GameEngineActivity.instance, R.raw.background_music);
			GameInfo.backGroundMusic.setLooping();
			GameInfo.soundEffect = new SoundEffect[5];
			GameInfo.soundEffect[0] = new SoundEffect(GameEngineActivity.instance, R.raw.ladder);
			GameInfo.soundEffect[1] = new SoundEffect(GameEngineActivity.instance, R.raw.watering);
			GameInfo.soundEffect[2] = new SoundEffect(GameEngineActivity.instance, R.raw.winmusic);
			GameInfo.soundEffect[3] = new SoundEffect(GameEngineActivity.instance, R.raw.scream);
			GameInfo.soundEffect[4] = new SoundEffect(GameEngineActivity.instance, R.raw.money);
			GameInfo.vibrate = new Vibrate(GameEngineActivity.instance);
			
			backGround = BitmapFactory.decodeResource(res, R.drawable.startbackground);
			gameStartMenu = new GameStartMenu(backGround);	
			
			startGameScreen = new Bitmap[4];
			startGameScreen[0] = BitmapFactory.decodeResource(res, R.drawable.startprogress);
			startGameScreen[1] = BitmapFactory.decodeResource(res, R.drawable.startprogress1);
			startGameScreen[2] = BitmapFactory.decodeResource(res, R.drawable.startprogress2);
			startGameScreen[3] = BitmapFactory.decodeResource(res, R.drawable.startprogress3);
			loadScreen = new Bitmap[4];
			loadScreen[0] = BitmapFactory.decodeResource(res, R.drawable.loadingprogress);
			loadScreen[1] = BitmapFactory.decodeResource(res, R.drawable.loadingprogress1);
			loadScreen[2] = BitmapFactory.decodeResource(res, R.drawable.loadingprogress2);
			loadScreen[3] = BitmapFactory.decodeResource(res, R.drawable.loadingprogress3);
			
			GameInfo.backGroundMusic.play();
		}
	}
	
	public void imgRecycle(Bitmap picture)
	{
		if(picture != null)
		{
			picture.recycle();
		}
	}
	
	public void releaseGameResources()
	{
		imgRecycle(difficultyBackGround);
		imgRecycle(gameBackGround);
		for(int i = 0;i != 5;++i)
		{
			imgRecycle(tools[i]);
		}
		
		for(int i = 0;i != 4 ;++i)
		{
			imgRecycle(fireman[i]);
		}
		
		for(int i = 0;i != 9 ;++i)
		{
			imgRecycle(arrows[i]);
		}
		for(int i = 0;i != 5 ;++i)
		{
			imgRecycle(fire[i]);
		}
		for(int i = 0;i != 4 ;++i)
		{
			imgRecycle(water[i]);
		}
		for(int i = 0;i != 3 ;++i)
		{
			imgRecycle(ladder[i]);
		}
		imgRecycle(door);
	}
	
	public void initiateNewGame(){	
		difficultyBackGround = BitmapFactory.decodeResource(res, R.drawable.difficutybackground);
		difficultyButton = new Bitmap[8];
		difficultyButton[0] = BitmapFactory.decodeResource(res, R.drawable.trainee);
		difficultyButton[1] = BitmapFactory.decodeResource(res, R.drawable.firefighter);
		difficultyButton[2] = BitmapFactory.decodeResource(res, R.drawable.captain);
		difficultyButton[3] = BitmapFactory.decodeResource(res, R.drawable.chief);
		difficultyButton[4] = BitmapFactory.decodeResource(res, R.drawable.traineegrey);
		difficultyButton[5] = BitmapFactory.decodeResource(res, R.drawable.firefightergrey);
		difficultyButton[6] = BitmapFactory.decodeResource(res, R.drawable.captaingrey);
		difficultyButton[7] = BitmapFactory.decodeResource(res, R.drawable.chiefgrey);
		
		gameBackGround = BitmapFactory.decodeResource(res, R.drawable.gamebackground);
		tools = new Bitmap[9];
		tools[0] = BitmapFactory.decodeResource(res, R.drawable.toolbar);
		tools[1] = BitmapFactory.decodeResource(res, R.drawable.watertool);
		tools[2] = BitmapFactory.decodeResource(res, R.drawable.laddertool);
		tools[3] = BitmapFactory.decodeResource(res, R.drawable.returntool);
		tools[4] = BitmapFactory.decodeResource(res, R.drawable.save);
		tools[5] = BitmapFactory.decodeResource(res, R.drawable.watertoolpress);
		tools[6] = BitmapFactory.decodeResource(res, R.drawable.laddertoolpress);
		tools[7] = BitmapFactory.decodeResource(res, R.drawable.returntoolpress);		
		tools[8] = BitmapFactory.decodeResource(res, R.drawable.savepress);
		fireman = new Bitmap[4];
		fireman[0] = BitmapFactory.decodeResource(res, R.drawable.fireman1);
		fireman[1] = BitmapFactory.decodeResource(res, R.drawable.fireman2);
		fireman[2] = BitmapFactory.decodeResource(res, R.drawable.fireman3);
		fireman[3] = BitmapFactory.decodeResource(res, R.drawable.fireman4);
		arrows = new Bitmap[9];
		arrows[0] = BitmapFactory.decodeResource(res, R.drawable.arrowholder);
		arrows[1] = BitmapFactory.decodeResource(res, R.drawable.left);
		arrows[2] = BitmapFactory.decodeResource(res, R.drawable.up);
		arrows[3] = BitmapFactory.decodeResource(res, R.drawable.right);
		arrows[4] = BitmapFactory.decodeResource(res, R.drawable.down);
		arrows[5] = BitmapFactory.decodeResource(res, R.drawable.leftpress);
		arrows[6] = BitmapFactory.decodeResource(res, R.drawable.uppress);
		arrows[7] = BitmapFactory.decodeResource(res, R.drawable.rightpress);
		arrows[8] = BitmapFactory.decodeResource(res, R.drawable.downpress);
		fire = new Bitmap[5];
		fire[0] = BitmapFactory.decodeResource(res, R.drawable.fire1);
		fire[1] = BitmapFactory.decodeResource(res, R.drawable.fire2);
		fire[2] = BitmapFactory.decodeResource(res, R.drawable.fire3);
		fire[3] = BitmapFactory.decodeResource(res, R.drawable.fire4);
		fire[4] = BitmapFactory.decodeResource(res, R.drawable.fire5);
		water = new Bitmap[4];
		water[0] = BitmapFactory.decodeResource(res, R.drawable.water1);
		water[1] = BitmapFactory.decodeResource(res, R.drawable.water2);
		water[2] = BitmapFactory.decodeResource(res, R.drawable.water3);
		water[3] = BitmapFactory.decodeResource(res, R.drawable.water4);
		ladder = new Bitmap[3];
		ladder[0] = BitmapFactory.decodeResource(res, R.drawable.ladder1);
		ladder[1] = BitmapFactory.decodeResource(res, R.drawable.ladder2);
		ladder[2] = BitmapFactory.decodeResource(res, R.drawable.ladder3);
		door = BitmapFactory.decodeResource(res, R.drawable.door);	
		life = BitmapFactory.decodeResource(res, R.drawable.blood);
		waterNumber = BitmapFactory.decodeResource(res, R.drawable.water);
		savePicture = new Bitmap[2];
		savePicture[0] = BitmapFactory.decodeResource(res, R.drawable.savesuccess);
		savePicture[1] = BitmapFactory.decodeResource(res, R.drawable.savefail);
		objects = new Bitmap[6];
		objects[0] = BitmapFactory.decodeResource(res, R.drawable.bluecross);
		objects[1] = BitmapFactory.decodeResource(res, R.drawable.greencross);
		objects[2] = BitmapFactory.decodeResource(res, R.drawable.purplecross);
		objects[3] = BitmapFactory.decodeResource(res, R.drawable.redcross);
		objects[4] = BitmapFactory.decodeResource(res, R.drawable.whitecross);
		objects[5] = BitmapFactory.decodeResource(res, R.drawable.yellowcross);
		jumpSpring = BitmapFactory.decodeResource(res, R.drawable.jumpspring3);
		fireRescueGamming = new FireRescueGamming(difficultyBackGround,difficultyButton,gameBackGround,fireman,arrows,tools,fire,water,ladder,font,door,savePicture,life,waterNumber,objects,jumpSpring);
	}
	
	public void initiateLoadGame(){
		difficultyBackGround = BitmapFactory.decodeResource(res, R.drawable.difficutybackground);
		difficultyButton = new Bitmap[8];
		difficultyButton[0] = BitmapFactory.decodeResource(res, R.drawable.trainee);
		difficultyButton[1] = BitmapFactory.decodeResource(res, R.drawable.firefighter);
		difficultyButton[2] = BitmapFactory.decodeResource(res, R.drawable.captain);
		difficultyButton[3] = BitmapFactory.decodeResource(res, R.drawable.chief);
		difficultyButton[4] = BitmapFactory.decodeResource(res, R.drawable.traineegrey);
		difficultyButton[5] = BitmapFactory.decodeResource(res, R.drawable.firefightergrey);
		difficultyButton[6] = BitmapFactory.decodeResource(res, R.drawable.captaingrey);
		difficultyButton[7] = BitmapFactory.decodeResource(res, R.drawable.chiefgrey);
		
		gameBackGround = BitmapFactory.decodeResource(res, R.drawable.gamebackground);
		tools = new Bitmap[9];
		tools[0] = BitmapFactory.decodeResource(res, R.drawable.toolbar);
		tools[1] = BitmapFactory.decodeResource(res, R.drawable.watertool);
		tools[2] = BitmapFactory.decodeResource(res, R.drawable.laddertool);
		tools[3] = BitmapFactory.decodeResource(res, R.drawable.returntool);
		tools[4] = BitmapFactory.decodeResource(res, R.drawable.save);
		tools[5] = BitmapFactory.decodeResource(res, R.drawable.watertoolpress);
		tools[6] = BitmapFactory.decodeResource(res, R.drawable.laddertoolpress);
		tools[7] = BitmapFactory.decodeResource(res, R.drawable.returntoolpress);		
		tools[8] = BitmapFactory.decodeResource(res, R.drawable.savepress);
		fireman = new Bitmap[4];
		fireman[0] = BitmapFactory.decodeResource(res, R.drawable.fireman1);
		fireman[1] = BitmapFactory.decodeResource(res, R.drawable.fireman2);
		fireman[2] = BitmapFactory.decodeResource(res, R.drawable.fireman3);
		fireman[3] = BitmapFactory.decodeResource(res, R.drawable.fireman4);
		arrows = new Bitmap[9];
		arrows[0] = BitmapFactory.decodeResource(res, R.drawable.arrowholder);
		arrows[1] = BitmapFactory.decodeResource(res, R.drawable.left);
		arrows[2] = BitmapFactory.decodeResource(res, R.drawable.up);
		arrows[3] = BitmapFactory.decodeResource(res, R.drawable.right);
		arrows[4] = BitmapFactory.decodeResource(res, R.drawable.down);
		arrows[5] = BitmapFactory.decodeResource(res, R.drawable.leftpress);
		arrows[6] = BitmapFactory.decodeResource(res, R.drawable.uppress);
		arrows[7] = BitmapFactory.decodeResource(res, R.drawable.rightpress);
		arrows[8] = BitmapFactory.decodeResource(res, R.drawable.downpress);
		fire = new Bitmap[5];
		fire[0] = BitmapFactory.decodeResource(res, R.drawable.fire1);
		fire[1] = BitmapFactory.decodeResource(res, R.drawable.fire2);
		fire[2] = BitmapFactory.decodeResource(res, R.drawable.fire3);
		fire[3] = BitmapFactory.decodeResource(res, R.drawable.fire4);
		fire[4] = BitmapFactory.decodeResource(res, R.drawable.fire5);
		water = new Bitmap[4];
		water[0] = BitmapFactory.decodeResource(res, R.drawable.water1);
		water[1] = BitmapFactory.decodeResource(res, R.drawable.water2);
		water[2] = BitmapFactory.decodeResource(res, R.drawable.water3);
		water[3] = BitmapFactory.decodeResource(res, R.drawable.water4);
		ladder = new Bitmap[3];
		ladder[0] = BitmapFactory.decodeResource(res, R.drawable.ladder1);
		ladder[1] = BitmapFactory.decodeResource(res, R.drawable.ladder2);
		ladder[2] = BitmapFactory.decodeResource(res, R.drawable.ladder3);
		door = BitmapFactory.decodeResource(res, R.drawable.door);	
		life = BitmapFactory.decodeResource(res, R.drawable.blood);
		waterNumber = BitmapFactory.decodeResource(res, R.drawable.water);
		savePicture = new Bitmap[2];
		savePicture[0] = BitmapFactory.decodeResource(res, R.drawable.savesuccess);
		savePicture[1] = BitmapFactory.decodeResource(res, R.drawable.savefail);
		objects = new Bitmap[6];
		objects[0] = BitmapFactory.decodeResource(res, R.drawable.bluecross);
		objects[1] = BitmapFactory.decodeResource(res, R.drawable.greencross);
		objects[2] = BitmapFactory.decodeResource(res, R.drawable.purplecross);
		objects[3] = BitmapFactory.decodeResource(res, R.drawable.redcross);
		objects[4] = BitmapFactory.decodeResource(res, R.drawable.whitecross);
		objects[5] = BitmapFactory.decodeResource(res, R.drawable.yellowcross);
		jumpSpring = BitmapFactory.decodeResource(res, R.drawable.jumpspring3);
		GameInfo.isLoad = true;
		fireRescueGamming = new FireRescueGamming(difficultyBackGround,difficultyButton,gameBackGround,fireman,arrows,tools,fire,water,ladder,font,door,savePicture,life,waterNumber,objects,jumpSpring);
	}
}
