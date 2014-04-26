/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : AccountAlreadyExistActivity.java
* description : The class for Button                               
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button {
	private Bitmap[] picture;
	private int positionX;
	private int positionY;
	private int state;
	public static final int UNPRESS = 0;
	public static final int PRESS = 1;
	public static final int LEFT = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	
	public Button(Bitmap[] picture){
		this.picture = picture;
		state = UNPRESS;
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture[state],null,new Rect(positionX,positionY,positionX+PhoneInfo.getFigureWidth(picture[state].getWidth()),positionY+PhoneInfo.getFigureHeight(picture[state].getHeight())),paint);
	}

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	public boolean isPositionBelong(int x, int y){
		if(x > positionX && x < positionX+PhoneInfo.getFigureWidth(picture[state].getWidth())
		&&y>positionY && y < positionY+PhoneInfo.getFigureHeight(picture[state].getHeight())){
			return true;
		}
		return false;
	}
}
