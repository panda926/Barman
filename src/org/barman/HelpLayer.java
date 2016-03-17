package org.barman;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGSize;


public class HelpLayer extends CCLayer{
	
	
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		super.onExit();
		removeAllChildren(true);
	}

	private float scaled_width = 1.0f;
	private float scaled_height = 1.0f;
	//private float g_rX = 1.0f;
	private float g_rY = 1.0f;
	
	public HelpLayer()
	{
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
		scaled_width = Global.scaled_width;
		scaled_height = Global.scaled_height;
		//g_rX = Global.g_rX;
		g_rY = Global.g_rY;
		
		CGSize winSize = CCDirector.sharedDirector().winSize();
		CCSprite backImg;
		CCMenuItemImage btnReturn;	
	    backImg = CCSprite.sprite("gfx/help.png");
	    backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
	    backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
	    addChild(backImg, 0);
	    CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
	    
	    btnReturn = CCMenuItemImage.item("gfx/BTN_M_BACK.png", "gfx/BTN_M_BACK.png", this, "onReturn"); 
	    btnReturn.setScaleX(scaled_width); btnReturn.setScaleY(scaled_height);
	    btnReturn.setPosition(winSize.width / 2.0f, Global.getCocosY(717 * g_rY));
	    
	    CCMenu menu = CCMenu.menu(btnReturn);
	    menu.setPosition(0,0);
	    addChild(menu, 1);
	}
	
	
	public void onReturn(Object sender)
	{
		 CCScene scene = CCScene.node();
	     scene.addChild(new MenuLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     removeAllChildren(true);
	    CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
	
}
