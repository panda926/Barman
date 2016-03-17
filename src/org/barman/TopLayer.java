package org.barman;


import org.cocos2d.actions.CCActionManager;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.content.SharedPreferences;

public class TopLayer extends CCLayer{	
	
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		super.onExit();
		removeAllChildren(true);
	}

	private float scaled_width, scaled_height;
	private float g_rX, g_rY;
	
	public TopLayer()
	{
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
		scaled_width = Global.scaled_width;
		scaled_height = Global.scaled_height;
		g_rX = Global.g_rX; g_rY = Global.g_rY;
		
		CGSize winSize = CCDirector.sharedDirector().winSize();
		
		CCSprite backImg;
		CCMenuItemImage btnReturn;
		backImg = CCSprite.sprite("gfx/background_top_i.png");
        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
        backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
        addChild(backImg, 0);
        CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
        
        btnReturn = CCMenuItemImage.item("gfx/BTN_M_BACK.png", "gfx/BTN_M_BACK.png", this, "onReturn");
        btnReturn.setScaleX(scaled_width); btnReturn.setScaleY(scaled_height);
        btnReturn.setPosition(winSize.width / 2.0f, Global.getCocosY(680 * g_rY));
        
        CCMenu menu = CCMenu.menu(btnReturn);
        menu.setPosition(0,0);
        addChild(menu, 1);
        initLoad();
	}
	
	private int LABEL_TAG = 2;
	public void initLoad()
	{
		CCLabel newLabel = CCLabel.makeLabel("Barman Top 10", CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 25 * scaled_height); 
	    newLabel.setColor(new ccColor3B(231, 71, 41));
	    newLabel.setPosition(512 * g_rX, Global.getCocosY(214 * g_rY));
//	    newLabel.setScaleX(scaled_width); newLabel.setScaleY(scaled_height);
	    addChild(newLabel, LABEL_TAG);
	    CCTextureCache.sharedTextureCache().removeTexture(newLabel.getTexture());
	    
	    CCLabel nameLabel = CCLabel.makeLabel("No:", CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 15 * scaled_height); 
	    nameLabel.setColor(new ccColor3B(231, 71, 41));
	    nameLabel.setPosition(180 * g_rX, Global.getCocosY(256 * g_rY));
	    addChild(nameLabel, LABEL_TAG);
	    CCTextureCache.sharedTextureCache().removeTexture(nameLabel.getTexture());
	    
	    CCLabel scoreLabel = CCLabel.makeLabel("Name:", CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 15 * scaled_height);
	    scoreLabel.setColor(new ccColor3B(231, 71, 41));
	    scoreLabel.setPosition(512 * g_rX, Global.getCocosY(256 * g_rY));
//	    scoreLabel.setScaleX(scaled_width); scoreLabel.setScaleY(scaled_height);
	    addChild(scoreLabel, LABEL_TAG);
	    CCTextureCache.sharedTextureCache().removeTexture(scoreLabel.getTexture());
	    
	    CCLabel beerLabel = CCLabel.makeLabel("Score:", CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 15 * scaled_height);
	    beerLabel.setColor(new ccColor3B(231, 71, 41));
	    beerLabel.setPosition(845 * g_rX, Global.getCocosY(256 * g_rY));
//	    beerLabel.setScaleX(scaled_width); beerLabel.setScaleY(scaled_height);
	    addChild(beerLabel, LABEL_TAG);
	    CCTextureCache.sharedTextureCache().removeTexture(beerLabel.getTexture());
	    
	    int nX = 110, nY = 330-30;
	    
   		SharedPreferences p = CCDirector.sharedDirector().getActivity().getSharedPreferences("Info", 0);
   		int nPlayerCount = p.getInt("Count", 0);
	    
	    for(int i = 0; i < nPlayerCount; i ++)
	    {
	    	nX = 160;
	    	String str = String.format("%d", i+1);
	    	CCLabel label = CCLabel.makeLabel(str, "Marker Felt", 15 * scaled_height);
	    	label.setColor(new ccColor3B(255, 255, 255));
	    	label.setPosition(nX * Global.g_rX, Global.getCocosY(nY * Global.g_rY));
	    	addChild(label, 5);
	    	CCTextureCache.sharedTextureCache().removeTexture(label.getTexture());
	    	
	    	nX += 350;
	    	
	    	CCLabel usrNameLabel = CCLabel.makeLabel("PLAYER", "Marker Felt", 15 * scaled_height);
	    	usrNameLabel.setColor(new ccColor3B(255, 255, 255));
	    	usrNameLabel.setPosition(nX * Global.g_rX, Global.getCocosY(nY * Global.g_rY));
	    	addChild(usrNameLabel, 5);
	    	CCTextureCache.sharedTextureCache().removeTexture(usrNameLabel.getTexture());
	    	
	    	str = String.format("score%d", i);
	    	int score =  p.getInt(str, 0);
	    	str = String.format("%d", score);
	    	
	    	nX += 334;
	    	CCLabel scoreLabelItem = CCLabel.makeLabel(str, "Marker Felt", 15 * scaled_height);
	    	scoreLabelItem.setColor(new ccColor3B(255, 255, 255));
	    	scoreLabelItem.setPosition(nX * Global.g_rX, Global.getCocosY(nY * Global.g_rY));
	    	addChild(scoreLabelItem, 5);
	    	CCTextureCache.sharedTextureCache().removeTexture(scoreLabelItem.getTexture());
	    	
	    	nY += 35;
	    }
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
