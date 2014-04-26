/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : GameEngineActivity.java
* description : This class is the main activity of the game                            
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


public class GameEngineActivity extends Activity {
    /** Called when the activity is first created. */
	public static Activity instance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
      //hidden the battery flag and any other parts
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hidden the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.gamelayout);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//设置Activity背景透明
      //get the resolution of the phone(获取手机的分别率)
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = 0;
        int height = 0;
        if(metric.widthPixels >= metric.heightPixels){
        	width = metric.widthPixels;  
        	height = metric.heightPixels;
        }
        else{
        	width = metric.heightPixels;
        	height = metric.widthPixels;
        }
        PhoneInfo.resolutionWidth = width;
        if(height == 800){
        	PhoneInfo.resolutionHeight = 750;
        }
        else{
        	PhoneInfo.resolutionHeight = height;
        }
        
        PhoneInfo.widthRatio = (double)PhoneInfo.resolutionWidth / 800;
        PhoneInfo.heightRatio = (double)PhoneInfo.resolutionHeight / 480;
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240 / 320）
        PhoneInfo.densityDpi = densityDpi;
        
        if(densityDpi == 240){
        	PhoneInfo.figureWidthRatio = (double)PhoneInfo.resolutionWidth / 800;
        	PhoneInfo.figureHeightRatio = (double)PhoneInfo.resolutionHeight / 480;
        }
        else if(densityDpi == 160){
        	PhoneInfo.figureWidthRatio = (double)PhoneInfo.resolutionWidth / 480;
        	PhoneInfo.figureHeightRatio = (double)PhoneInfo.resolutionHeight / 320;
        }
        else if(densityDpi == 120){
        	PhoneInfo.figureWidthRatio = (double)PhoneInfo.resolutionWidth / 320;
        	PhoneInfo.figureHeightRatio = (double)PhoneInfo.resolutionHeight / 240;
        }
        else if(densityDpi == 320){
        	PhoneInfo.figureWidthRatio = (double)PhoneInfo.resolutionWidth / 800;
        	PhoneInfo.figureHeightRatio = (double)PhoneInfo.resolutionHeight / 480;
        }
    }
    
    @Override
	public void onBackPressed() {
		if(GameSurfaceView.gameState == GameSurfaceView.GAME_MENU){
			instance.finish();
			System.exit(0);
		}
		else if(GameSurfaceView.gameState == GameSurfaceView.GAMMING){
			Intent intent = new Intent(GameEngineActivity.instance, EnsureExitActivity.class);
			GameEngineActivity.instance.startActivity(intent);
		}
	}
    
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("CardGameActivity","onDestroy");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("CardGameActivity","onPause");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.v("CardGameActivity","onRestart");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("CardGameActivity","onResume");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("CardGameActivity","onStart");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("CardGameActivity","onStop");
	}
}