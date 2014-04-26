/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : SettingActivity.java
* description : The settings of the game                            
* 
* created by Wang Shiliang at 5/12/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingActivity extends Activity{
	private SeekBar seekBar1, seekBar2;
	private TextView tv1, tv2;
	private Button okButton, cancelButton;
	private int speed;
	private int currentVolume;
	AudioManager mAudioManager;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//hidden the battery flag and any other parts
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//hidden the title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.settinglayout);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//…Ë÷√Activity±≥æ∞Õ∏√˜
		seekBar1 = (SeekBar)findViewById(R.id.volume);
		seekBar2 = (SeekBar)findViewById(R.id.gamespeedseekbar);
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		seekBar1.setMax(maxVolume);
		//speed = GameInfo.speed;
		seekBar1.setProgress(currentVolume);
		//seekBar2.setProgress(GameInfo.speed);
		tv1 = (TextView)findViewById(R.id.tv1);
		tv2 = (TextView)findViewById(R.id.tv2);
		tv1.setTextColor(Color.BLACK);
		tv2.setTextColor(Color.BLACK);
		tv1.setText(currentVolume+"");
		//tv2.setText(GameInfo.speed+"");
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
				// TODO Auto-generated method stub
				tv1.setText(Integer.toString(progress));
				currentVolume = progress;
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
				// TODO Auto-generated method stub
				tv2.setText(Integer.toString(progress));
				speed = progress;
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});	
		
		okButton = (Button) findViewById(R.id.okbutton);
		okButton.setOnTouchListener(new OnTouchListener(){
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				if(event.getAction() == MotionEvent.ACTION_DOWN){
 					GameInfo.vibrate.playVibrate(-1);
 					GameInfo.soundEffect[0].play((float) 0.3);
 				}
 				else if(event.getAction() == MotionEvent.ACTION_UP){
 					int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
 					int volume = currentVolume;
 					mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
 					/*
 					GameInfo.speed = speed;
 					if(GameInfo.speed == 0){
 			    		GameInfo.speedChange = 0.5;
 			    	}
 			    	else if(GameInfo.speed == 1){
 			    		GameInfo.speedChange = 0.6;
 			    	}
 			    	else if(GameInfo.speed == 2){
 			    		GameInfo.speedChange = 0.7;
 			    	}
 			    	else if(GameInfo.speed == 3){
 			    		GameInfo.speedChange = 0.8;
 			    	}
 			    	else if(GameInfo.speed == 4){
 			    		GameInfo.speedChange = 0.9;
 			    	}
 			    	else if(GameInfo.speed == 5){
 			    		GameInfo.speedChange = 1;
 			    	}
 			    	else if(GameInfo.speed == 6){
 			    		GameInfo.speedChange = 1.2;
 			    	}
 			    	else if(GameInfo.speed == 7){
 			    		GameInfo.speedChange = 1.4;
 			    	}
 			    	else if(GameInfo.speed == 8){
 			    		GameInfo.speedChange = 1.6;
 			    	}
 			    	else if(GameInfo.speed == 9){
 			    		GameInfo.speedChange = 1.8;
 			    	}
 			    	else if(GameInfo.speed == 10){
 			    		GameInfo.speedChange = 5;
 			    	}		
 			    	*/		
 					GameInfo.isCreateSurfaceView = false;
					SettingActivity.this.finish();
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
					SettingActivity.this.finish();
 				}
				return false;
 			}
         });
	}
	
	@Override
	public void onBackPressed() {
		GameInfo.isCreateSurfaceView = false;
	}
}
