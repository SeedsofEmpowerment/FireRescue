/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : GameEngineActivity.java
* description : This class stores the information of the game                          
* 
* created by Wang Shiliang at 6/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;


public class GameInfo {
	public static String name;
	public static int age;
	public static int gender;
	public static Vibrate vibrate;
	public static BackGroundMusic backGroundMusic;
	public static SoundEffect[] soundEffect;
	public static Boolean isStartAllowed = false;	
	public static int difficulty;
	public static final int TRAINEE = 0;
	public static final int FIREFIGHTER = 1;
	public static final int CAPTAIN = 2;
	public static final int CHIEF = 3;
	public static int doorLevel;
	public static boolean isLoad = false;
	public static boolean isLadderSurfaceView = false;
	public static boolean isCreateSurfaceView = false;
	public static long roundStartTime;
	public static long roundTime;
	public static int remainPeople = 5;
	public static int startlevel; //¿ªÊ¼Â¥²ã
	public static int endlevel; //½áÊøÂ¥²ã
	public static String RescueProcess;//
	public static int isrescuing;
	public static int randomLadderNumber = 3;
	public static int returnNumber = 3;
	public static int ladderNumber;
	public static int jumpSpringNumber;
	public static int round = 1;
	public static boolean firstStart = true;
	public static String calculation;
	public static int remainTimes = 2;
	public static void initNewLevelResources()
	{
		doorLevel = 0;
		roundStartTime = System.currentTimeMillis();
		roundTime = 60;
		remainTimes = 2;
		if(difficulty == TRAINEE){
			ladderNumber = 4;
			jumpSpringNumber = 0;
		}
		else if(difficulty == FIREFIGHTER){
			ladderNumber = 6;
			jumpSpringNumber = 0;
		}
		else if(difficulty == CAPTAIN){
			ladderNumber = 1;
			jumpSpringNumber = 4;
		}
		else if(difficulty == CHIEF){
			ladderNumber = 4;
			jumpSpringNumber = 4;
		}
	}
	public static void initGameResources(){
		isStartAllowed = false;
		doorLevel = 0;
		isLoad = false;
		isLadderSurfaceView = false;
		roundStartTime = System.currentTimeMillis();
		remainPeople = 5;
		randomLadderNumber = 3;
		returnNumber = 3;
		roundTime = 60;
		remainTimes = 2;
		if(difficulty == TRAINEE){
			ladderNumber = 4;
			jumpSpringNumber = 0;
		}
		else if(difficulty == FIREFIGHTER){
			ladderNumber = 6;
			jumpSpringNumber = 0;
		}
		else if(difficulty == CAPTAIN){
			ladderNumber = 1;
			jumpSpringNumber = 4;
		}
		else if(difficulty == CHIEF){
			ladderNumber = 4;
			jumpSpringNumber = 4;
		}
	}
}
