/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : DifficultySelection.java
* description : The class for select the difficulty of the game                             
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class DifficultySelection {
	private Bitmap background;
	public DifficultySelection(Bitmap background){
		this.background = background;;
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(background, null, new Rect(0, 0, PhoneInfo.resolutionWidth,PhoneInfo.resolutionHeight), paint);
	}
	
	public void onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			GameInfo.vibrate.playVibrate(-1);
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			
		}
	}
}
