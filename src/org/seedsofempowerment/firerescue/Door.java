/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : Door.java
* description : The class for the door                               
* 
* created by Wang Shiliang at 6/1/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Door {
	private Bitmap picture;
	private int positionX;
	private int positionY;
	private int level;
	private int width;
	public Door(Bitmap picture){
		this.picture = picture;
		level = GameInfo.doorLevel;
		positionX = PhoneInfo.getRealWidth(500);
		width = PhoneInfo.getFigureWidth(picture.getWidth());
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture,null,new Rect(positionX,positionY,positionX+PhoneInfo.getFigureWidth(picture.getWidth()),positionY+PhoneInfo.getFigureHeight(picture.getHeight())),paint);
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setPositionY(int positionY){
		this.positionY = positionY;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
