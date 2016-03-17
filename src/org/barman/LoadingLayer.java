package org.barman;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;



public class LoadingLayer extends CCLayer{
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		super.onExit();
		removeAllChildren(true);
	}

	 CCSprite m_proImg;
	    
    private float scaled_width = 1.0f;
    private float scaled_height = 1.0f;
   // private float g_rX = 1.0f;
    private float g_rY = 1.0f;
    
	public LoadingLayer()
	{
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
	   CCSprite bottomLine;
	    CCSprite backImg;
	    
	    scaled_width = Global.scaled_width;
		scaled_height = Global.scaled_height;
		//g_rX = Global.g_rX;
		g_rY = Global.g_rY;
		
        
        CGSize winSize = CCDirector.sharedDirector().winSize();
        
        backImg = CCSprite.sprite("gfx/Splash_i.png");
        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
        backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
        addChild(backImg, 0);
        CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
        
        CCLabel loadingLabel = CCLabel.makeLabel("Loading...", "Marker Felt", 25 * Global.scaled_height); 
        loadingLabel.setColor(new ccColor3B(231, 71, 41));        
        loadingLabel.setPosition(winSize.width / 2.0f, Global.getCocosY(700 * g_rY));
//      loadingLabel.setScaleX(scaled_width); loadingLabel.setScaleY(scaled_height);
        addChild(loadingLabel, 1);        
        CCTextureCache.sharedTextureCache().removeTexture(loadingLabel.getTexture());
        
        bottomLine = CCSprite.sprite("gfx/T_line_bottom.png");
        bottomLine.setPosition(winSize.width / 2.0f, Global.getCocosY(656 * g_rY));
        bottomLine.setScaleX(scaled_width); bottomLine.setScaleY(scaled_height);
        addChild(bottomLine, 1);
        CCTextureCache.sharedTextureCache().removeTexture(bottomLine.getTexture());
        
        m_proImg = CCSprite.sprite("gfx/T_line_top.png");
        m_proImg.setPosition(winSize.width / 2.0f, Global.getCocosY(656 * g_rY));
        m_proImg.setScaleX(0); m_proImg.setScaleY(scaled_height);
        addChild(m_proImg, 2);
        CCTextureCache.sharedTextureCache().removeTexture(m_proImg.getTexture());
        
        schedule("loadingAction", 0.05f);
	}
	
	
	public void loadingAction(float dt) 
	{
		m_proImg.setScaleX(m_proImg.getScaleX() + 0.01f * scaled_width);
		
		CGSize winSize = CCDirector.sharedDirector().winSize();
		m_proImg.setPosition(winSize.width/2 - m_proImg.getTexture().getWidth()*scaled_width/2 + m_proImg.getTexture().getWidth()*m_proImg.getScaleX()/2 , Global.getCocosY(656 * g_rY));
		
	    if (m_proImg.getScaleX() >= scaled_width) {
	        unschedule("loadingAction");
	        
//	        CCScene scene = CCScene.node();
//		     scene.addChild(new MenuLayer());
//		     CCDirector.sharedDirector().replaceScene(scene);
		     
	        Class<?> transition = Global.transitions[3];
	        CCScene scene = CCScene.node();
	        scene.addChild(new MenuLayer());
	        
	        try {
	        	Constructor<?> c = transition.getConstructor(Float.TYPE, CCScene.class);
				CCScene s = (CCScene) c.newInstance(0.5f, scene);
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
	    
	}
}
