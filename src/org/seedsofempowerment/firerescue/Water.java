/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : Water.java
* description : This class of water                            
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Water {
	private Bitmap[] picture;
	private int positionX;
	private int positionY;
	private int direction;
	private int index;
	private int height;
	private int width;
	
	public final static int LEFT_DIRECTION = 0;
	public final static int RIGHT_DIRECTION = 1;
	
	public Water(Bitmap[] picture){
		this.picture = picture;
		index = 0;
	}
	
	public void logic(){
		if(direction == RIGHT_DIRECTION){
			++index;
			if(index >= 2){
				index = 0;			
			}
		}
		else if(direction == LEFT_DIRECTION){
			++index;
			if(index >= 4 ||index < 2){
				index = 2;
			}
		}
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture[index],null,new Rect(positionX,positionY,positionX+PhoneInfo.getFigureWidth(picture[index].getWidth()),positionY+PhoneInfo.getFigureHeight(picture[index].getHeight())),paint);
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
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public int getHeight(){
		height = PhoneInfo.getFigureHeight(picture[0].getHeight());
		return height;
	}
	
	public int getWidth(){
		width = PhoneInfo.getFigureWidth(picture[0].getWidth());
		return width;
	}	
	
	public void setIndex(int index){
		this.index = index;
	}
}
