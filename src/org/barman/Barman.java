package org.barman;

import java.util.ArrayList;


import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class Barman extends Activity {
	private CCGLSurfaceView mGLSurfaceView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mGLSurfaceView = new CCGLSurfaceView(this);
        setContentView(mGLSurfaceView);
        
        Global.g_boozerArray = new ArrayList<BoozerAppearInfo>();
        Global.createLevelMgr();
        
        loadSound();
    }
    public void loadSound()
	{
		Global.m_pMediaPlayer = new MediaPlayer[4];
	    for (int i = 0; i < 4; i ++)
	    {
	    	Global.m_pMediaPlayer[i] =  MediaPlayer.create(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.ambient_pub + i);
	    	Global.m_pMediaPlayer[i].setLooping(true);
	    }    
	    
	    Global.m_pSoundSuccess = MediaPlayer.create(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.applause_oye);		 	
	}
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		 setScaleVaue();
		 CCDirector.sharedDirector().attachInView(mGLSurfaceView);      
	     CCScene scene = CCScene.node();
	     scene.addChild(new LoadingLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().runWithScene(scene);
	}
    
    public void removeGame()
    {
    	 Global.g_boozerArray.removeAll(Global.g_boozerArray);
    	 System.gc();    	 
    }
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		super.onDestroy();
		removeGame();
		releaseSound();
		CCDirector.sharedDirector().end();
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		CCDirector.sharedDirector().pause();
		pauseSound();
		//CCActionManager.sharedManager().removeAllActions();
	    //CCTextureCache.sharedTextureCache().removeAllTextures();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CCDirector.sharedDirector().resume();
	}
	
	private void setScaleVaue()
	{
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		Global.camera_width = displayMetrics.widthPixels;
		Global.camera_height = displayMetrics.heightPixels;
		Global.scaled_width =  Global.camera_width / 480.0f;
		Global.scaled_height = Global.camera_height / 320.0f;
		
		Global.g_rX = Global.camera_width / 1024.0f;
		Global.g_rY = Global.camera_height / 768.0f;
	}
	
	public void releaseSound()
	{
		if (Global.m_pMediaPlayer != null)
		{
			 for (int i = 0; i < 4; i ++)
			    {
			        if (Global.m_pMediaPlayer[i] != null)
			            Global.m_pMediaPlayer[i].stop();
			    }
		}
	}
	public void pauseSound()
	{
		if (Global.m_pMediaPlayer != null)
		{
		 for (int i = 0; i < 4; i ++)
		    {
		        if (Global.m_pMediaPlayer[i] != null)
		            Global.m_pMediaPlayer[i].pause();
		    }
		}
	}
}