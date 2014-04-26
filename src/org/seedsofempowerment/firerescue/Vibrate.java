/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : Vibrate.java
* description : This class of Vibrate                          
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.content.Context;
import android.os.Vibrator;

public class Vibrate {
	final static String TAG = "GameEngine";
	long[] pattern = {0, 50};
	private Vibrator vibrator;
	public Vibrate(Context context){
		vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void playVibrate(int times){
		vibrator.vibrate(pattern, times);
	}
	
	public void Stop(){
		vibrator.cancel();
	}
}
