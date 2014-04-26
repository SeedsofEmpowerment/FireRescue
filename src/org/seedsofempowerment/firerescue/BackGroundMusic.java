/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : AccountAlreadyExistActivity.java
* description : The class for BackGroundMusic                               
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.content.Context;
import android.media.MediaPlayer;

public class BackGroundMusic {
	private MediaPlayer mediaPlayer;
	
	public BackGroundMusic(Context context, int resid){
		mediaPlayer = MediaPlayer.create(context, resid);
	}
	
	public void play(){
		mediaPlayer.start();
	}
	
	public void stop(){
		mediaPlayer.stop();
	}
	
	public void setLooping(){
		mediaPlayer.setLooping(true);
	}
	
	public void release(){
		mediaPlayer.release();
	}
}
