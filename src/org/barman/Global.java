package org.barman;

import java.util.ArrayList;

import org.cocos2d.layers.CCScene;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCJumpZoomTransition;
import org.cocos2d.transitions.CCRotoZoomTransition;
import org.cocos2d.transitions.CCZoomFlipYTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;



//import android.media.AudioManager;
import android.media.MediaPlayer;
//import android.media.SoundPool;

public class Global {
	
	static Class<?> transitions[] = {
        CCJumpZoomTransition.class,
        CCFadeTransition.class,
        CCRotoZoomTransition.class,
        ZoomFlipYUpOver.class,
	};
	static class ZoomFlipYUpOver extends CCZoomFlipYTransition {
		public ZoomFlipYUpOver(float t, CCScene s) {
			super(t, s, tOrientation.kOrientationUpOver);
		}
	};
	public static float scaled_width = 1.0f;
	public static float scaled_height = 1.0f;
	public static float camera_width = 480.0f;
	public static float camera_height = 320.0f;
	public static float g_rY = 1.0f;
	public static float g_rX = 1.0f;
	
	
	public static int g_nRealLevelNum = 0;
	public static int g_nCompleteLevelNum = 0;
	public static int g_nMessageState = 0;
	
	public static LevelMgr g_levelMgr;
	public static ArrayList<BoozerAppearInfo> g_boozerArray = null;
	
	static float getCocosY(float y)
	{
		return Global.camera_height - y;
	}
	
	static void createLevelMgr()
	{
		releaseLevelMgr();	    
	    g_levelMgr = new LevelMgr();
	}
	
	static void releaseLevelMgr()
	{
//		if (g_levelMgr != null)
//	    {
////	        [g_levelMgr release];
//	        g_levelMgr = null;
//	    }
		//g_levelMgr.dealloc();
	}
	
	static CGPoint getCCP(CGRect rect)
	{
		int x, y, width, height;
		
		x = (int) rect.origin.x;
		y = (int) rect.origin.y;
		
		width = (int) rect.size.width;
		height = (int) rect.size.height;
		
		return CGPoint.make(x + width / 2.0f, y - height / 2.0f);
	}
		
	public static final int APPLAUSE_OYE_SOUND = 0;
    public static final int BONUS_SOUND = 1;
    public static final int BOOZER_ANGRY_SOUND = 2;
    public static final int BOOZER_BLOP_SOUND = 3;
    public static final int BOOZER_DRINK_SOUND = 4;
    public static final int CATCH_COINS_SOUND = 5;
    public static final int CATCH_MUG_SOUND = 6;
    public static final int CATCH_TURBO_SOUND = 7;
    public static final int DROUGH_BEER_SOUND = 8;
    public static final int ENTER_PUB_SOUND = 9;
    public static final int FAIL_BOOZERWAITER_SOUND = 10;
    public static final int FAIL_MUGBREAK_SOUND = 11;
    public static final int LOAD_SOUND = 12;
    public static final int MESSAGEBOARD_SOUND = 13;
    public static final int TIPFAIL_SOUND = 14;
    
	 public static MediaPlayer m_pMediaPlayer[] = null;
	 public static MediaPlayer m_pSounds = null;
	 public static MediaPlayer m_pSoundSuccess=null;
}
