/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : PhoneInfo.java
* description : This class is for storing the phone's information                           
* 
* created by Wang Shiliang at 5/2/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

public class PhoneInfo {
	public static int resolutionWidth;
	public static int resolutionHeight;
	public static double widthRatio;
	public static double heightRatio;
	public static double figureWidthRatio;
	public static double figureHeightRatio;
	public static int densityDpi;
	
	public static int getRealWidth(int width){
		return (int) (width * PhoneInfo.widthRatio);
	}
	
	public static int getRealHeight(int height){
		return (int) (height * PhoneInfo.heightRatio);
	}
	
	public static int getFigureWidth(int width){
		return (int) (width * PhoneInfo.figureWidthRatio);
	}
	
	public static int getFigureHeight(int height){
		return (int) (height * PhoneInfo.figureHeightRatio);
	}
}
