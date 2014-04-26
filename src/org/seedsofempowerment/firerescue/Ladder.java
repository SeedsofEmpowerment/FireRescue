/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : Ladder.java
* description : This class for Ladder                            
* 
* created by Wang Shiliang at 6/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Ladder {
	private Bitmap[] picture;
	private int positionX;
	private int positionY;
	private int width;
	private int height;
	private int startLevel;
	private int endLevel;
	private int laddersize;
	private int index;//ladder id
	

	public Ladder(Bitmap[] picture){
		this.picture = picture;
		index = 0;
		laddersize = 0;
	}	
	
	public int getLaddersize() {
		return laddersize;
	}

	public void setLaddersize(int laddersize) {
		this.laddersize = laddersize;
	}

	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture[index],null,new Rect(positionX,positionY,positionX+PhoneInfo.getFigureWidth(picture[index].getWidth()),positionY+PhoneInfo.getFigureHeight(picture[index].getHeight())),paint);
	}
	
	public void setPositionX(int positionX){
		this.positionX = positionX;
	}
	
	public void setPositionY(int positionY){
		this.positionY = positionY;
	}
	public int getPositionX(){
		return this.positionX;
	}
	
	public int getPositionY(){
		return this.positionY;
	}
	
	public int getWidth(){
		width =  PhoneInfo.getFigureWidth(picture[index].getWidth());
		return width;
	}
	
	public int getHeight(){
		height = PhoneInfo.getFigureHeight(picture[index].getHeight());
		return height;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public void setStartLevel(int level){
		this.startLevel = level;
	}
	
	public void setEndLevel(int level){
		this.endLevel = level;
	}
	
	public int getStartLevel(){
		return this.startLevel;
	}
	
	public int getEndLevel(){
		return this.endLevel;
	}
}
