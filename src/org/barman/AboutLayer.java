package org.barman;

import java.lang.reflect.Constructor;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.transitions.CCTransitionScene;
import org.cocos2d.types.CGSize;



import android.content.Intent;
import android.net.Uri;

public class AboutLayer extends CCLayer{
	
    private float scaled_width;
    private float scaled_height;
    private float g_rX, g_rY;
    
   
    @Override
	public void onExit() {
		// TODO Auto-generated method stub
		super.onExit();
		removeAllChildren(true);
	}    
	public AboutLayer()
	{
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
		scaled_width = Global.scaled_width; scaled_height = Global.scaled_height;
		g_rX = Global.g_rX; g_rY = Global.g_rY;
		
		CGSize winSize = CCDirector.sharedDirector().winSize();
		
		CCSprite backImg;	    
		CCSprite backImg1;
		CCSprite backImg2;
		CCSprite backImg3;	    
	    CCMenuItemImage btnReturn;
	    CCMenuItemImage btnMoreApp;
	       
        backImg = CCSprite.sprite("gfx/about.png");
        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
        backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
        addChild(backImg, 0); 
        CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
        
        btnReturn = CCMenuItemImage.item("gfx/BTN_M_BACK.png", "gfx/BTN_M_BACK.png",this, "onReturn"); 
        btnReturn.setScaleX(scaled_width); btnReturn.setScaleY(scaled_height);
        btnReturn.setPosition(winSize.width / 2.0f, Global.getCocosY(599 * g_rY));
        
        btnMoreApp = CCMenuItemImage.item("gfx/MillsAndMoreAdv_i.png", "gfx/MillsAndMoreAdv_i.png", this, "onMoreApp");
        btnMoreApp.setScaleX(scaled_width); btnMoreApp.setScaleY(scaled_height);
        btnMoreApp.setPosition(868 * g_rX, Global.getCocosY(494 * g_rY));
        
        CCMenu menu = CCMenu.menu(btnReturn, btnMoreApp);
        menu.setPosition(0,0);
        addChild(menu, 2);
        
        backImg1 = CCSprite.sprite("gfx/decor_chair.png");
        backImg1.setPosition(126 * g_rX, Global.getCocosY(613 * g_rY));
        backImg1.setScaleX(scaled_width); backImg1.setScaleY(scaled_height);
        addChild(backImg1, 1);
        CCTextureCache.sharedTextureCache().removeTexture(backImg1.getTexture());
        
        backImg2 = CCSprite.sprite("gfx/bg_menu_cat.png");
        backImg2.setPosition(winSize.width / 2.0f, Global.getCocosY(689 * g_rY));
        backImg2.setScaleX(scaled_width); backImg2.setScaleY(scaled_height);
        addChild(backImg2, 1);
        CCTextureCache.sharedTextureCache().removeTexture(backImg2.getTexture());
        
        backImg3 = CCSprite.sprite("gfx/bg_menu_antitalent.png");
        backImg3.setPosition(winSize.width / 2.0f, Global.getCocosY(738 * g_rY));
        backImg3.setScaleX(scaled_width); backImg3.setScaleY(scaled_height);
        addChild(backImg3, 1);
        CCTextureCache.sharedTextureCache().removeTexture(backImg3.getTexture());
	}
	
	public void onReturn(Object sender)
	{
		CCScene scene = CCScene.node();
        scene.addChild(new MenuLayer(), 0);
    	CCDirector.sharedDirector().replaceScene(newScene(1.0f, scene));
    	removeAllChildren(true);
	     CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
	
	public void onMoreApp(Object sender)
	{
		Intent sendIntent  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oxegenentertainment.com/androidgames"));
		CCDirector.sharedDirector().getActivity().startActivity(sendIntent);
	}
	
	 static CCTransitionScene newScene(float d, CCScene s)
	    {
	    	 try {
	             Class<?> c = Global.transitions[1];
	             Class<?> partypes[] = new Class[2];
	             partypes[0] = Float.TYPE;
	             partypes[1] = s.getClass();
	             Constructor<?> ctor = c.getConstructor(partypes);
	             Object arglist[] = new Object[2];
	             arglist[0] = d;
	             arglist[1] = s;
	             return (CCTransitionScene) ctor.newInstance(arglist);
	         } catch (Exception e) {
	             return null;
	         }
	    }
}
