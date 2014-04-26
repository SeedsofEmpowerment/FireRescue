/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : Object.java
* description : This class for object                        
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Object {
	private Bitmap[] picture;
	private int positionX;
	private int positionY;
	private int color;
	private int width;
	private boolean isExist;
	public static int BLUE = 0;
	public static int GREEN = 1;
	public static int PURPLE = 2;
	public static int RED = 3;
	public static int WHITE = 4;
	public static int YELLOW = 5;
	private Random rand;
	
	public Object(Bitmap[] picture){
		this.picture = picture;
		rand = new Random();
		isExist = true;
		produceColor();
		setPosition();	
		width = PhoneInfo.getFigureWidth(picture[color].getWidth());
	}
	
	private void produceColor(){
		int returnRand = 0;
		int ran = Math.abs(rand.nextInt());
		returnRand = ran % 20;
		if(returnRand == 0){
			isExist = false;
		}
		else if(returnRand == 1){
			color = GREEN;
		}
		else if(returnRand == 2){
			color = PURPLE;
		}
		else if(returnRand == 3){
			color = RED;
		}
		else if(returnRand == 4){
			color = WHITE;
		}
		else if(returnRand == 5){
			color = YELLOW;
		}
		else{
			isExist = false;
		}
	}
	
	private void setPosition(){
		int returnRand = 0;
		int ran = Math.abs(rand.nextInt());
		returnRand = ran % 400 + 300;
		positionX = PhoneInfo.getRealWidth(returnRand);
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture[color],null,new Rect(positionX,positionY,positionX+PhoneInfo.getFigureWidth(picture[color].getWidth()),positionY+PhoneInfo.getFigureHeight(picture[color].getHeight())),paint);
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
	
	public boolean isExist(){
		return isExist;
	}
	
	public void setNoneExist(){
		isExist = false;
	}
	
	public int getColor(){
		return color;
	}
	
	public int getWidth() {
		return width;
	}
}
