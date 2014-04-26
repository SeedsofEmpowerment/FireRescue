/*
*
* Copyright (C) 2011-2014 Wang Shiliang
* All rights reserved
* filename : SelectLadderActivity.java
* description : When the user press the ladder button                          
* 
* created by Wang Shiliang at 5/22/2012 21:19:50
*
*/
package org.seedsofempowerment.firerescue;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SelectLadderActivity extends Activity{
	public static Activity instance;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
      //hidden the battery flag and any other parts
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hidden the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.selectladderlayout);
        //this.getWindow().setBackgroundDrawable(new ColorDrawable(0));//…Ë÷√Activity±≥æ∞Õ∏√˜
    }
	
	@Override
	public void onBackPressed() {
		GameInfo.isLadderSurfaceView = false;
		SelectLadderActivity.instance.finish();
	}
}
