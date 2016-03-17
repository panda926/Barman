package org.barman;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;


//import android.view.MenuItem;

public class LoadLayer extends CCLayer{
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		super.onExit();
		removeAllChildren(true);
	}


	private final int MAX_LEVEL = 20;
	private final int FISRT_LOAD_BTN_X = 64;
	private final int FIRST_LOAD_LABEL_Y = 370;
	private final int BTN_DIFF_X = 98;
	private final int SECOND_LOAD_LABEL_Y = 550;
	private final int FIRST_LOAD_BTN_Y = 283;
	private final int SECOND_LOAD_BTN_Y = 460;
	
	public CCMenuItemImage m_btnLevel[] = new CCMenuItemImage[MAX_LEVEL];
    public CCMenuItemImage btnReturn;
    
    private float scaled_width, scaled_height;
    private float g_rX, g_rY;
    
	public LoadLayer()
	{
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
	    CCSprite backImg;
	    CCSprite backImg1;
	    CCSprite backImg2;
	    CCSprite backImg3;
	    
	   	scaled_width = Global.scaled_width; scaled_height = Global.scaled_height;
		g_rX = Global.g_rX; g_rY = Global.g_rY;
		
		CGSize winSize = CCDirector.sharedDirector().winSize();
	        
        backImg = CCSprite.sprite("gfx/background_pub_i.png");
        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
        backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
        addChild(backImg, 0);
        CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
        
        backImg1 = CCSprite.sprite("gfx/bg_menu_piano_i.png");
        backImg1.setPosition(winSize.width / 2.0f, Global.getCocosY(161 * g_rY));
        backImg1.setScaleX(scaled_width); backImg1.setScaleY(scaled_height);
        addChild(backImg1, 1);
        CCTextureCache.sharedTextureCache().removeTexture(backImg1.getTexture());
        
        backImg2 = CCSprite.sprite("gfx/bg_menu_cat.png");
        backImg2.setPosition(winSize.width / 2.0f, Global.getCocosY(675 * g_rY));
        backImg2.setScaleX(scaled_width); backImg2.setScaleY(scaled_height);
        addChild(backImg2, 1);
        CCTextureCache.sharedTextureCache().removeTexture(backImg2.getTexture());
        
        backImg3 = CCSprite.sprite("gfx/bg_menu_antitalent.png");
        backImg3.setPosition(winSize.width / 2.0f, Global.getCocosY(738 * g_rY));
        backImg3.setScaleX(scaled_width); backImg3.setScaleY(scaled_height);
        addChild(backImg3, 1);
        CCTextureCache.sharedTextureCache().removeTexture(backImg3.getTexture());
        
        int i;
        CGPoint pt;
        
        CCLabel levelLabel[] = new CCLabel[MAX_LEVEL];
        
        pt = CGPoint.make(FISRT_LOAD_BTN_X * g_rX, Global.getCocosY(FIRST_LOAD_LABEL_Y * g_rY));
        
        String str;
        for (i = 0; i < 10; i ++)
        {
            str = String.format("%d", i + 1);
            levelLabel[i] = CCLabel.makeLabel(str, CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 25.0f * scaled_width); 
            levelLabel[i].setColor(new ccColor3B(231, 71, 41));
            levelLabel[i].setPosition(pt.x, pt.y);
            addChild(levelLabel[i], 2);         
            CCTextureCache.sharedTextureCache().removeTexture(levelLabel[i].getTexture());
            pt.x += BTN_DIFF_X * g_rX;
        }
        
        pt = CGPoint.make(FISRT_LOAD_BTN_X * g_rX, Global.getCocosY(SECOND_LOAD_LABEL_Y * g_rY));
        for (i = 10; i < MAX_LEVEL; i ++)
        {
            str = String.format("%d", i + 1);
            levelLabel[i] = CCLabel.makeLabel(str, CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 25 * scaled_width);
            levelLabel[i].setColor(new ccColor3B(231, 71, 41));
            levelLabel[i].setPosition(pt.x, pt.y);
            addChild(levelLabel[i], 2);     
            CCTextureCache.sharedTextureCache().removeTexture(levelLabel[i].getTexture());
            pt.x += BTN_DIFF_X * g_rX;
        }

        for (i = 0; i < MAX_LEVEL; i ++)
        {
          //  String funcName = String.format("onLevel_%d", i);

            if (i < Global.g_nCompleteLevelNum)
            {
                m_btnLevel[i] = CCMenuItemImage.item("gfx/BTN_Unlocked.png", "gfx/BTN_Unlocked.png", this, "onLevel");
            }
            else
            {
                m_btnLevel[i] = CCMenuItemImage.item("gfx/BTN_Locked.png", "gfx/BTN_Locked.png", this, "onLevel");
            }
            
            m_btnLevel[i].setScaleX(scaled_width); m_btnLevel[i].setScaleY(scaled_height);
        }
        
        pt = CGPoint.make(FISRT_LOAD_BTN_X * g_rX, Global.getCocosY(FIRST_LOAD_BTN_Y * g_rY));
        for (i = 0; i < 10; i ++)
        {
            m_btnLevel[i].setPosition(pt.x, pt.y);
            pt.x += BTN_DIFF_X * g_rX;
        }
        
        pt = CGPoint.make(FISRT_LOAD_BTN_X * g_rX, Global.getCocosY(SECOND_LOAD_BTN_Y * g_rY));
        for (i = 10; i < MAX_LEVEL; i ++)
        {
            m_btnLevel[i].setPosition(pt.x, pt.y);
            pt.x += BTN_DIFF_X * g_rX;
        }
        
        btnReturn = CCMenuItemImage.item("gfx/BTN_M_BACK.png", "gfx/BTN_M_BACK.png", this, "onReturn"); 
        btnReturn.setScaleX(scaled_width); btnReturn.setScaleY(scaled_height);
        btnReturn.setPosition(winSize.width / 2.0f, Global.getCocosY(650 * g_rY));
        
        CCMenu menu = CCMenu.menu(m_btnLevel[0], m_btnLevel[1], m_btnLevel[2], m_btnLevel[3],m_btnLevel[4],m_btnLevel[5],m_btnLevel[6],m_btnLevel[7],m_btnLevel[8],m_btnLevel[9],m_btnLevel[10],m_btnLevel[11],m_btnLevel[12],m_btnLevel[13],m_btnLevel[14],m_btnLevel[15],m_btnLevel[16],m_btnLevel[17],m_btnLevel[18],m_btnLevel[19], btnReturn); 
        menu.setPosition(0,0);
        addChild(menu, 3);
	}
	
	public void onLevel(Object sender)
	{
	    CCMenuItem menuItem = (CCMenuItem)sender;
	    
	    for (int i = 0; i < GameConfig.MAX_LEVEL; i ++)
	    {
	        if (menuItem == m_btnLevel[i])
	        {
	            if ((i+1) > Global.g_nCompleteLevelNum)
	                return;
	            else 
	            {
	                Global.g_nRealLevelNum = i+1;
	                break;
	            }
	        }
	    }
	    
//	    CCScene scene = CCScene.node();
//	     scene.addChild(new GameLayer());
//	     CCDirector.sharedDirector().replaceScene(scene);
	     
	    Class<?> transition = Global.transitions[3];
        CCScene scene = CCScene.node();
        scene.addChild(new GameLayer());
        
        try {
        	Constructor<?> c = transition.getConstructor(Float.TYPE, CCScene.class);
			CCScene s = (CCScene) c.newInstance(GameConfig.MESSAGE_DALAY, scene);
        	CCDirector.sharedDirector().replaceScene(s);
        	removeAllChildren(true);
        	CCTextureCache.sharedTextureCache().removeUnusedTextures();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	public void onReturn(Object sender)
	{
	    CCScene scene = CCScene.node();
	     scene.addChild(new MenuLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     //removeAllChildren(true);
	    // CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
}
