/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : AccountAlreadyExistActivity.java
* description : The class for Fireman                              
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
import android.util.Log;

public class Fireman {
	private Bitmap[] picture;
	private int positionX;
	private int positionY;
	private int direction;       // the orientation where the fireman moves to 
	private int index;
	private int orientation;     // the orientation where the fireman heads to 
	private int width;
	private int height;
	private int jumpTime;
	private int jumpTime2;
	private int level;
	private int currentLevel;
	private int life;
	private int water;
	private Random rand;
	private int startlevel;
	private int accrosslevel;//0表示未通过，1表示通过一关
	private int climblevelstate;	//0表示还未爬梯子，1表示正在爬梯子
	private int jumplevelstate;	//0表示还未爬梯子，1表示正在爬梯子
	public boolean isCollideState = false;
	public boolean isJumpState = false;
	
	public final static int LEFT_DIRECTION = 0;
	public final static int UP_DIRECTION = 1;
	public final static int RIGHT_DIRECTION = 2;
	public final static int DOWN_DIRECTION = 3;
	public final static int MOTIONLESS = 4;
	
	public Fireman(Bitmap[] picture){
		this.picture = picture;
		direction = MOTIONLESS;
		orientation = RIGHT_DIRECTION;
		index = 0;
		jumpTime = 0;
		jumpTime2 = 0;
		accrosslevel = 0;
		life = 5;
		water = 6;
	    rand = new Random();
	    climblevelstate = 0;
	    jumplevelstate = 0;
		if(GameInfo.difficulty == GameInfo.TRAINEE){
			level = getTraineeRand();
			startlevel = level;
			GameInfo.doorLevel = getTraineeDoorLevel();
		}
		else if(GameInfo.difficulty == GameInfo.FIREFIGHTER){
			level = getFireFighterRand();
			startlevel = level;
			GameInfo.doorLevel = getFireFighterDoorLevel();
			
		}
		else if(GameInfo.difficulty == GameInfo.CAPTAIN){
			level = getCaptainRand();
			startlevel = level;
			GameInfo.doorLevel = getCaptainDoorLevel();
		}
		else if(GameInfo.difficulty == GameInfo.CHIEF){
			level = getChiefRand();
			startlevel = level;
			GameInfo.doorLevel = getChiefDoorLevel();
		}
		
		GameInfo.startlevel = level; //设置fireman的起始level为doorLevel
		currentLevel = 0;
	}
	
	public int getClimblevelstate() {
		return climblevelstate;
	}

	public void setClimblevelstate(int climblevelstate) {
		this.climblevelstate = climblevelstate;
	}
	
	public int getJumplevelstate(){
		return jumplevelstate;
	}
	
	public void setJumplevelstate(int jumplevelstate){
		this.jumplevelstate = jumplevelstate;
	}
	
	public int getAccrosslevel() {
		return accrosslevel;
	}

	public void setAccrosslevel(int accrosslevel) {
		this.accrosslevel = accrosslevel;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public int getWater(){
		return this.water;
	}
	
	public void logic(){
		if(direction == LEFT_DIRECTION){
			if(index < 2)
			{
				index = 2;
			}
			else{
				++index;
				if(index == 4){
					index = 2;
				}
			}
			positionX -= PhoneInfo.getRealWidth(15);
			if(positionX < 0){
				positionX = 0;
			}
		}
		else if(direction == UP_DIRECTION){
			orientation = RIGHT_DIRECTION;
			if(index < 2){
				++index;
				if(index == 2){
					index = 0;
				}
			}
			else{
				index = 0;
			}
			positionY -= PhoneInfo.getFigureHeight(20);
		}
		else if(direction == RIGHT_DIRECTION){
			if(index < 2){
				++index;
				if(index == 2){
					index = 0;
				}
			}
			else{
				index = 0;
			}
			positionX += PhoneInfo.getRealWidth(15);
			if(positionX > GameSurfaceView.myView.getWidth() - PhoneInfo.getFigureWidth(picture[0].getWidth())){
				positionX = GameSurfaceView.myView.getWidth() - PhoneInfo.getFigureWidth(picture[0].getWidth());
			}
		}
		else if(direction == DOWN_DIRECTION){
			orientation = RIGHT_DIRECTION;
			if(index < 2){
				++index;
				if(index == 2){
					index = 0;
				}
			}
			else{
				index = 0;
			}
			positionY += PhoneInfo.getFigureHeight(20);
		}
		else if(direction == MOTIONLESS){
			;
		}
	}
	
	public int getStartlevel() {
		return startlevel;
	}

	public void setStartlevel(int startlevel) {
		this.startlevel = startlevel;
	}
	
	public int getCurrentLevel(){
		return this.currentLevel;
	}
	
	public void setCurrentLevel(int currentLevel){
		this.currentLevel = currentLevel;
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
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public void setLife(int life){
		this.life = life;
	}
	
	public void setWater(int water){
		this.water = water;
	}
	
	public int getPositionX(){
		return this.positionX;
	}
	
	public int getPositionY(){
		return this.positionY;
	}
	
	public int getWidth(){
		width =  PhoneInfo.getFigureWidth(picture[0].getWidth());
		return width;
	}
	
	public int getHeight(){
		height = PhoneInfo.getFigureHeight(picture[0].getHeight());
		return height;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public void setOrientation(int orientation){
		this.orientation = orientation;
	}
	
	public int getOrientation(){
		return this.orientation;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void jumpMove(){
		if(jumpTime2 == 0){
			positionY -= PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 1){
			positionY -= PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 2){
			positionY -= PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 3){
			positionY -= PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 4){
			positionY -= PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 5){
			positionY += PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 6){
			positionY += PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 7){
			positionY += PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 8){
			positionY +=  PhoneInfo.getRealHeight(20);
		}
		else if(jumpTime2 == 9){
			positionY += PhoneInfo.getRealHeight(20);
		}
		++jumpTime2;
		if(jumpTime2 == 10){
			jumpTime2 = 0;
			isJumpState = false;
		}
	}
	
	public void parabolaMove(int direction){
		//if left
		if(direction == 0){
			positionX -= PhoneInfo.getRealWidth(20);
			if(positionX < 0){
				positionX = 0;
			}
		}
		//if right
		else if(direction == 1){
			positionX += PhoneInfo.getRealWidth(20);
			if(positionX > GameSurfaceView.myView.getWidth() - PhoneInfo.getFigureWidth(picture[0].getWidth())){
				positionX = GameSurfaceView.myView.getWidth() - PhoneInfo.getFigureWidth(picture[0].getWidth());
			}
		}
		if(jumpTime == 0){
			positionY -= PhoneInfo.getRealHeight(2);
		}
		else if(jumpTime == 1){
			positionY -= PhoneInfo.getRealHeight(8);
		}
		else if(jumpTime == 2){
			positionY -= PhoneInfo.getRealHeight(12);
		}
		else if(jumpTime == 3){
			positionY -= PhoneInfo.getRealHeight(18);
		}
		else if(jumpTime == 4){
			positionY -= PhoneInfo.getRealHeight(24);
		}
		else if(jumpTime == 5){
			positionY += PhoneInfo.getRealHeight(24);
		}
		else if(jumpTime == 6){
			positionY += PhoneInfo.getRealHeight(18);
		}
		else if(jumpTime == 7){
			positionY += PhoneInfo.getRealHeight(12);
		}
		else if(jumpTime == 8){
			positionY +=  PhoneInfo.getRealHeight(8);
		}
		else if(jumpTime == 9){
			positionY += PhoneInfo.getRealHeight(2);
		}
		++jumpTime;
		if(jumpTime == 10){
			jumpTime = 0;
			isCollideState = false;
		}
	}
	
	public int getTraineeRand(){
		int returnRand = 0;
		if(GameInfo.round == 1){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 9;
		}
		else if(GameInfo.round == 2){
			int ran = Math.abs(rand.nextInt());
			returnRand = ran % 9 + 1;
		}
		else if(GameInfo.round == 3){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 198 - 99;
		}
		else if(GameInfo.round == 4){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 198 - 98;
		}
		return returnRand;
	}
	
	public int getTraineeDoorLevel(){
		int returnRand = level;
		if(GameInfo.round == 1){
			while(returnRand <= level){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 9 + 1;
				Log.v("Fireman.java","getTraineeDoorLevel");
			}
		}
		else if(GameInfo.round == 2){
			while(returnRand >= level){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 9;
				Log.v("Fireman.java","getTraineeDoorLevel");
			}
		}
		else if(GameInfo.round == 3){
			while(returnRand >= level){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 198 - 98;
				Log.v("Fireman.java","getTraineeDoorLevel");
			}
		}
		else if(GameInfo.round == 4){
			while(returnRand >= level){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 198 - 99;
				Log.v("Fireman.java","getTraineeDoorLevel");
			}
		}
		return returnRand;
	}
	
	public int getFireFighterRand(){
		int returnRand = 0;
		if(GameInfo.round == 1){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 50 - 25;
		}
		else if(GameInfo.round == 2){
			int ran = Math.abs(rand.nextInt());
			returnRand = ran % 50 - 25;
		}
		else if(GameInfo.round == 3){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 198 - 99;
		}
		else if(GameInfo.round == 4){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 198 - 98;
		}
		return returnRand;
	}
	
	public int getFireFighterDoorLevel(){
		int returnRand = level;
		if(GameInfo.round == 1){
			while(returnRand <= level + 1){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 198 - 97;
				Log.v("Fireman.java","getFireFighterDoorLevel");
			}
		}
		else if(GameInfo.round == 2){
			while(returnRand >= level - 1){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 198 - 100;
				Log.v("Fireman.java","getFireFighterDoorLevel");
			}
		}
		else if(GameInfo.round == 3){
			while(returnRand <= level + 2){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 198 - 96;
				Log.v("Fireman.java","getFireFighterDoorLevel");
			}
		}
		else if(GameInfo.round == 4){
			while(returnRand >= level - 2){
				int ran = Math.abs(rand.nextInt());		
				returnRand = ran % 198 - 101;
				Log.v("Fireman.java","getFireFighterDoorLevel");
			}
		}
		return returnRand;
	}
	
	public int getCaptainRand(){
		if(GameInfo.round == 1 || GameInfo.round == 2){
			return 0;
		}
		else{
			int ran = Math.abs(rand.nextInt());		
			int returnRand = ran % 20 + 1;
			return returnRand;
		}
	}
	
	public int getCaptainDoorLevel(){
		int returnRand = level;
		int num1 = 0;
		int num2 = 0;
		int ran = Math.abs(rand.nextInt());		
		if(GameInfo.round == 1){
			num1 = ran % 9 + 1;
			ran = Math.abs(rand.nextInt());
			num2 = ran % 9 + 1;
			returnRand = num1 * num2;
		}
		else if(GameInfo.round == 2){
			num1 = ran % 30 + 1;
			ran = Math.abs(rand.nextInt());
			num2 = ran % 5 + 1;
			returnRand = num1 * num2;
		}
		else if(GameInfo.round == 3){
			while(returnRand <= level){
				num1 = ran % 30 + 1;
				ran = Math.abs(rand.nextInt());
				num2 = ran % 9 + 1;
				returnRand = level + num1 * num2;
				Log.v("Fireman.java","getCaptainDoorLevel");
			}
		}
		else if(GameInfo.round == 4){
			while(returnRand <= level){
				num1 = ran % 50 + 1;
				ran = Math.abs(rand.nextInt());
				num2 = ran % 5 + 1;
				returnRand = level + num1 * num2;
				Log.v("Fireman.java","getCaptainDoorLevel");
			}
		}
		return returnRand;
	}
	
	public int getChiefRand(){
		int returnRand = 0;
		if(GameInfo.round == 1){	
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 19 - 9;
		}
		else if(GameInfo.round == 2){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 19 - 9;
		}
		else if(GameInfo.round == 3){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 199 - 99;
		}
		else if(GameInfo.round == 4){
			int ran = Math.abs(rand.nextInt());		
			returnRand = ran % 199 - 99;
		}
		return returnRand;
	}
	
	public int getChiefDoorLevel(){
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int returnRand = level;
		if(GameInfo.round == 1){
			while(returnRand <= level){
				int ran = Math.abs(rand.nextInt());		
				num1 = ran % 19 - 9;
				ran = Math.abs(rand.nextInt());	
				num2 = ran % 19 - 9;
				ran = Math.abs(rand.nextInt());	
				num3 = ran % 19 - 9;
				returnRand = num1 + num2 * num3;
				Log.v("Fireman.java","getChiefDoorLevel");
			}
		}
		else if(GameInfo.round == 2){
			while(returnRand >= level){
				int ran = Math.abs(rand.nextInt());		
				num1 = ran % 199 - 99;
				ran = Math.abs(rand.nextInt());	
				num2 = ran % 19 - 9;
				ran = Math.abs(rand.nextInt());	
				num3 = ran % 19 - 9;
				returnRand = num1 + num2 * num3;
				Log.v("Fireman.java","getChiefDoorLevel");
			}
		}
		else if(GameInfo.round == 3){
			while(returnRand <= level){
				int ran = Math.abs(rand.nextInt());		
				num1 = ran % 19 - 9;
				ran = Math.abs(rand.nextInt());	
				num2 = ran % 39 - 19;
				ran = Math.abs(rand.nextInt());	
				num3 = ran % 39 - 19;
				returnRand = num1 + num2 * num3;
				Log.v("Fireman.java","getChiefDoorLevel");
			}
		}
		else if(GameInfo.round == 4){
			while(returnRand >= level){
				int ran = Math.abs(rand.nextInt());		
				num1 = ran % 199 - 9;
				ran = Math.abs(rand.nextInt());	
				num2 = ran % 39 - 19;
				ran = Math.abs(rand.nextInt());	
				num3 = ran % 39 - 19;
				returnRand = num1 + num2 * num3;
				Log.v("Fireman.java","getChiefDoorLevel");
			}
		}
		return returnRand;
	}
	
	public int getJumpTime(){
		return this.jumpTime;
	}
	
	public void setJumpTime(int jumpTime){
		this.jumpTime = jumpTime;
	}
}