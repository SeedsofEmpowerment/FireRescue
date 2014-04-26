/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : JumpSpring.java
* description : This class for jumpSpring                            
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class JumpSpring {
	private Bitmap picture;
	private int positionX;
	private int positionY;
	private int startLevel;
	private int jumpTimes;
	private int jumpSize;
	private int endLevel;
	private int index;
	private int width;
	private int height;
	private int gap;
	
	public JumpSpring(Bitmap picture){
		this.picture = picture;
		index = 0;
		gap = 0;
		jumpTimes = 0;
		jumpSize = 0;
		startLevel = 1000;
		endLevel = 1000;
	}
	
	public void logic(){
		++index;
		if(index > 0 && index < 10){
			FireRescueGamming.fireman.setPositionY(FireRescueGamming.fireman.getPositionY()+PhoneInfo.getRealHeight(4));
			gap += PhoneInfo.getRealHeight(4);
		}
		else if(index >= 10 && index < 20){
			FireRescueGamming.fireman.setPositionY(FireRescueGamming.fireman.getPositionY()-PhoneInfo.getRealHeight(4));
			gap -= PhoneInfo.getRealHeight(4);
		}
		if(index == 20){
			FireRescueGamming.fireman.setJumplevelstate(0);
			FireRescueGamming.fireman.setPositionY(FireRescueGamming.ladderY[0]);
			FireRescueGamming.fireman.setLevel(FireRescueGamming.jumpSpring[FireRescueGamming.jumpSpringCurrentNumber-1].getEndLevel());
			FireRescueGamming.fireman.setCurrentLevel(0);
			index = 0;
		}
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(picture,null,new Rect(positionX,positionY+gap,positionX+PhoneInfo.getFigureWidth(picture.getWidth()),positionY+PhoneInfo.getFigureHeight(picture.getHeight())),paint);
	}
	
	public void setJumpTimes(int jumpTimes){
		this.jumpTimes = jumpTimes;
	}
	
	public void setJumpSize(int jumpSize){
		this.jumpSize = jumpSize;
	}
	
	public int getJumpTimes(){
		return this.jumpTimes;
	}
	
	public int getJumpSize(){
		return this.jumpSize;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
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
	
	public int getWidth(){
		width =  PhoneInfo.getFigureWidth(picture.getWidth());
		return width;
	}
	
	public int getHeight(){
		height = PhoneInfo.getFigureHeight(picture.getHeight());
		return height;
	}
}
