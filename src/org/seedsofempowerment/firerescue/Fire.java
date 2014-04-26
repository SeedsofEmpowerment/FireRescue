/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : Fire.java
* description : The class for Fire                               
* 
* created by Wang Shiliang at 6/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Fire {
	private Bitmap[] picture;
	private int positionX;
	private int positionY;
	private int direction;
	private int startX;
	private int endX;
	private int level;
	private int index;
	private Random rand;
	private int firestate;
	
	public final static int LEFT_DIRECTION = 0;
	public final static int RIGHT_DIRECTION = 1;
	
	public Fire(Bitmap[] picture){
		rand = new Random();
		this.picture = picture;
		direction = RIGHT_DIRECTION;
		index = 0;
		startX = getStartRand();
		endX = getEndRand();
		positionX = startX; 
		positionY = PhoneInfo.getRealHeight(350);
		firestate = 0;
	}
	public int getFirestate() {
		return firestate;
	}

	public void setFirestate(int firesate) {
		this.firestate = firesate;
	}

	public void logic(){
		if(direction == RIGHT_DIRECTION){
			positionX += PhoneInfo.getRealWidth(10);
			if(positionX > endX){
				positionX = endX;
				direction = LEFT_DIRECTION;
			}
			++index;
			if(index == 5)
			{
				index = 0;
			}
		}
		else if(direction == LEFT_DIRECTION){
			positionX -= PhoneInfo.getRealWidth(10);
			if(positionX < startX){
				positionX = startX;
				direction = RIGHT_DIRECTION;
			}
			
			++index;
			if(index == 5)
			{
				index = 0;
			}
		}
	}
	
	
	//get the random number
	private int getStartRand(){
		int ran = Math.abs(rand.nextInt());		
		int width = PhoneInfo.getRealWidth(320);
		int returnRan = ran%(width) + PhoneInfo.getRealWidth(200);	
		return returnRan;
	}
	
	private int getEndRand(){
		int returnRan = startX;
		while(returnRan <= startX + PhoneInfo.getRealWidth(100) || returnRan > PhoneInfo.getRealWidth(740)){
			int ran = Math.abs(rand.nextInt());
			int width = PhoneInfo.getRealWidth(400);
			returnRan = ran%(width) + PhoneInfo.getRealWidth(400);	
		}
		return returnRan;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	public int getLevel() {
		return level;
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

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture[index],null,new Rect(positionX,positionY,positionX+PhoneInfo.getFigureWidth(picture[index].getWidth()),positionY+PhoneInfo.getFigureHeight(picture[index].getHeight())),paint);
		
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getWidth(){
		return PhoneInfo.getFigureWidth(picture[0].getWidth());
	}
	
	public int getStartX(){
		return this.startX;
	}
	
	public void setStartX(int startX){
		this.startX = startX;
	}
	
	public int getEndX(){
		return this.endX;
	}
	
	public void setEndX(int endX){
		this.endX = endX;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
}
