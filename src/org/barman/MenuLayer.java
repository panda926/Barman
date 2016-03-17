package org.barman;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;



public class MenuLayer extends CCLayer{
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		super.onExit();
		removeAllChildren(true);
	}

	
    private float scaled_width = 1.0f;
    private float scaled_height = 1.0f;
    private float g_rX = 1.0f;
    private float g_rY = 1.0f;
	public final int FONT_SIZE = 25;

	public MenuLayer()
	{
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
	    CCSprite backImg;
	    CCSprite backImg1;
	    CCSprite bottles;
	    CCSprite chair;
	    CCSprite label;
	    CCSprite cat;
	    
	    CCMenuItemImage btnFact;
	    CCMenuItemImage btnNew;
	    CCMenuItemImage btnHelp;
	    CCMenuItemImage btnLoad;
	    CCMenuItemImage btnTop;
	    CCMenuItemImage btnAbout;
	    
		scaled_width = Global.scaled_width;
		scaled_height = Global.scaled_height;
		g_rX = Global.g_rX;
		g_rY = Global.g_rY;
		
		   CGSize winSize = CCDirector.sharedDirector().winSize();
	        
	        backImg = CCSprite.sprite("gfx/background_pub_i.png");
	        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
	        backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
	        addChild(backImg, 0);
	        CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
	        
	        backImg1 = CCSprite.sprite("gfx/bg_menu_barman_i.png");
	        backImg1.setPosition(winSize.width / 2.0f, Global.getCocosY(216 * g_rY));
	        backImg1.setScaleX(scaled_width); backImg1.setScaleY(scaled_height);
	        addChild(backImg1, 0);
	        CCTextureCache.sharedTextureCache().removeTexture(backImg1.getTexture());
	        
	        btnFact = CCMenuItemImage.item("gfx/BTN_FACTS_i.png", "gfx/BTN_FACTS_i.png", this, "onFact");
	        btnFact.setScaleX(scaled_width); btnFact.setScaleY(scaled_height);        
	        btnFact.setPosition(85 * g_rX, Global.getCocosY(672 * g_rY));
	        
	        btnHelp = CCMenuItemImage.item("gfx/BTN_HELP_i.png", "gfx/BTN_HELP_i.png", this, "onHelp");
	        btnHelp.setScaleX(scaled_width); btnHelp.setScaleY(scaled_height);        
	        btnHelp.setPosition(939 * g_rX, Global.getCocosY(672 * g_rY));
	        
	        bottles = CCSprite.sprite("gfx/decor_bottles.png");
	        bottles.setPosition(918 * g_rX, Global.getCocosY(371 * g_rY));
	        bottles.setScaleX(scaled_width); bottles.setScaleY(scaled_height);
	        addChild(bottles, 1);
	        CCTextureCache.sharedTextureCache().removeTexture(bottles.getTexture());
	        
	        chair = CCSprite.sprite("gfx/decor_chair.png");
	        chair.setPosition(192 * g_rX, Global.getCocosY(649 * g_rY));
	        chair.setScaleX(scaled_width); chair.setScaleY(scaled_height);
	        addChild(chair);
	        CCTextureCache.sharedTextureCache().removeTexture(chair.getTexture());
	        
	        btnNew = CCMenuItemImage.item("gfx/BTN_M_NEWGAME_i.png", "gfx/BTN_M_NEWGAME_i.png", this, "onNew");
	        btnNew.setScaleX(scaled_width); btnNew.setScaleY(scaled_height);        
	        btnNew.setPosition(170 * g_rX, Global.getCocosY(515 * g_rY));
	        
	        btnLoad = CCMenuItemImage.item("gfx/BTN_M_LOAD_i.png", "gfx/BTN_M_LOAD_i.png", this, "onLoad");
	        btnLoad.setScaleX(scaled_width); btnLoad.setScaleY(scaled_height);        
	        btnLoad.setPosition(397 * g_rX, Global.getCocosY(515 * g_rY));
	        
	        btnTop = CCMenuItemImage.item("gfx/BTN_M_TOP_i.png", "gfx/BTN_M_TOP_i.png", this, "onTop");
	        btnTop.setScaleX(scaled_width); btnTop.setScaleY(scaled_height);       
	        btnTop.setPosition(629 * g_rX, Global.getCocosY(515 * g_rY));
	        
	        btnAbout = CCMenuItemImage.item("gfx/BTN_M_ABOUT_i.png", "gfx/BTN_M_ABOUT_i.png", this, "onAbout");
	        btnAbout.setScaleX(scaled_width); btnAbout.setScaleY(scaled_height);        
	        btnAbout.setPosition(868 * g_rX, Global.getCocosY(515 * g_rY));
	        
	        label = CCSprite.sprite("gfx/bg_menu_antitalent.png");
	        label.setPosition(535 * g_rX, Global.getCocosY(662 * g_rY));
	        label.setScaleX(scaled_width); label.setScaleY(scaled_height);
	        addChild(label);
	        CCTextureCache.sharedTextureCache().removeTexture(label.getTexture());
	        
	        cat = CCSprite.sprite("gfx/bg_menu_cat.png");
	        cat.setPosition(512 * g_rX, Global.getCocosY(616 * g_rY));
	        cat.setScaleX(scaled_width); cat.setScaleY(scaled_height);
	        addChild(cat);
	        CCTextureCache.sharedTextureCache().removeTexture(cat.getTexture());
	        
	        CCLabel newLabel = CCLabel.makeLabel("new", "Marker Felt", FONT_SIZE * scaled_height);
	        newLabel.setColor(new ccColor3B(231, 71, 41));
	        newLabel.setPosition(170 * g_rX, Global.getCocosY(640 * g_rY));
//	        newLabel.setScaleX(scaled_width); newLabel.setScaleY(scaled_height);
	        addChild(newLabel, 3);
	        CCTextureCache.sharedTextureCache().removeTexture(newLabel.getTexture());
	        
	        CCLabel loadLabel = CCLabel.makeLabel("load", "Marker Felt", FONT_SIZE * scaled_height); 
	        loadLabel.setColor(new ccColor3B(231, 71, 41));        
	        loadLabel.setPosition(398 * g_rX, Global.getCocosY(640 * g_rY));
//	        loadLabel.setScaleY(scaled_height); loadLabel.setScaleX(scaled_width);
	        addChild(loadLabel, 3);
	        CCTextureCache.sharedTextureCache().removeTexture(loadLabel.getTexture());
	        
	        CCLabel topLabel = CCLabel.makeLabel("top", "Marker Felt", FONT_SIZE * scaled_height); 
	        topLabel.setColor(new ccColor3B(231, 71, 41));        
	        topLabel.setPosition(630 * g_rX, Global.getCocosY(640 * g_rY));
//	        topLabel.setScaleX(scaled_width); topLabel.setScaleY(scaled_height);
	        addChild(topLabel, 3);
	        CCTextureCache.sharedTextureCache().removeTexture(topLabel.getTexture());
	        
	        CCLabel aboutLabel = CCLabel.makeLabel("about", "Marker Felt", FONT_SIZE * scaled_height);
	        aboutLabel.setColor(new ccColor3B(231, 71, 41));
	        aboutLabel.setPosition(868 * g_rX, Global.getCocosY(640 * g_rY));
//	        aboutLabel.setScaleX(scaled_width); aboutLabel.setScaleY(scaled_height);
	        addChild(aboutLabel, 3);
	        CCTextureCache.sharedTextureCache().removeTexture(aboutLabel.getTexture());
	        
	        CCMenu menu = CCMenu.menu(btnFact, btnHelp, btnNew, btnLoad, btnTop, btnAbout);//btnMail, btnFaceBook, btnWeb 
			menu.setPosition(0, 0);
			addChild(menu, 1);
	        
	        if (Global.g_nRealLevelNum > Global.g_nCompleteLevelNum)
	            Global.g_nCompleteLevelNum = Global.g_nRealLevelNum;        
	}
	public void onFact(Object sender)
	{
	}
	
	public void onHelp(Object sender)
	{
		 CCScene scene = CCScene.node();
	     scene.addChild(new HelpLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     removeAllChildren(true);
	     CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
	
	public void onNew(Object sender)
	{
        Global.g_nRealLevelNum = 1;
        
        CCScene scene = CCScene.node();
	     scene.addChild(new GameLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     removeAllChildren(true);
	     CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
	
	public void onLoad(Object sender)
	{
		 CCScene scene = CCScene.node();
	     scene.addChild(new LoadLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     removeAllChildren(true);
	     CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
	
	public void onTop(Object sender)
	{
		 CCScene scene = CCScene.node();
	     scene.addChild(new TopLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     removeAllChildren(true);
	    CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
	
	public void onAbout(Object sender)
	{
		 CCScene scene = CCScene.node();
	     scene.addChild(new AboutLayer());
	     // Make the Scene active
	     CCDirector.sharedDirector().replaceScene(scene);
	     removeAllChildren(true);
	     CCTextureCache.sharedTextureCache().removeUnusedTextures();
	}
}
