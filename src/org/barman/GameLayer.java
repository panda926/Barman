package org.barman;

import java.util.ArrayList;

import org.cocos2d.actions.CCActionManager;
import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemToggle;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class GameLayer extends CCLayer{
	
	 @Override
		public void onExit() {
			// TODO Auto-generated method stub
			super.onExit();
			dealloc();
			removeAllChildren(true);
		} 
	 
	 Boolean m_fReleaseMessageAction = false, m_fInitMessageAction = false;
		
	CCSprite m_proImg;    
    int m_nProWidth;
    
    CCLabel levelLabel, scoreLabel, liveLabel;   

    boolean m_fGamePlay;
    boolean m_fWaiterPlay;
    boolean m_fBeerPlay;
    boolean m_fGuitarPlay;
    boolean m_fPianoPlay;
    boolean m_fLampPlay;
    boolean m_fLampBarPlay;
    boolean m_fBonusPlay;
    boolean m_fScorePlay;
    
    boolean m_fGameOver;
    boolean m_fFirstLoad;

    boolean m_fPause;
    boolean m_fGameOverSet;
    
    boolean m_fWaiterStrong;
    
    boolean m_fEndState;
    
    boolean m_fBoozerEnd;
    boolean m_fEndWaiterState;
    
    CCSprite m_markSprite = null;
    
    ArrayList<BoozerInfo> m_boozerArray1 = null;
    ArrayList<BoozerInfo> m_boozerArray2 = null;
    ArrayList<BoozerInfo> m_boozerArray3 = null;
    ArrayList<BoozerInfo> m_boozerArray4  = null; 

    ArrayList<BeerInfo> m_beerArray1 = null;
    ArrayList<BeerInfo> m_beerArray2 = null;
    ArrayList<BeerInfo> m_beerArray3 = null;
    ArrayList<BeerInfo> m_beerArray4 = null;
    
    ArrayList<TipInfo> m_tipArray1 = null;
    ArrayList<TipInfo> m_tipArray2 = null;
    ArrayList<TipInfo> m_tipArray3 = null;
    ArrayList<TipInfo> m_tipArray4 = null;
    
    ArrayList<PipeIndexInfo> m_beerArray = null;
    
    WaiterInfo m_waiterInfo = null;
    BonusInfo m_bonusInfo = null;
    
    CCSprite m_lampSprite[][] = new CCSprite[4][2];
    CCSprite m_lampBarSprite[] = new CCSprite[2];
    //CCSprite m_pianoSprite[] = new CCSprite[16];
    CCSprite m_pianoSprite = null;
    CCSprite m_guitarSprite[] = new CCSprite[2];

    int m_nBackSoundIdx;
    
    int m_nPainoState;
    int m_nPianoIdx;
    
    float m_rPianoTime;
    
    int m_nLampBarIdx;
    int m_nGuitarIdx;
    
    int m_nProNum;
    int m_nScore;
    int m_nLive;
    
    boolean m_fMessageLoad;
    
    BoozerInfo m_endBoozer;
    
    boolean m_fSound;
    boolean m_fSetSound;
    
    int m_nBeerNum;   
   
    private final int LEVEL_PRO_X = 300;
    private final int MESSAGE_LAYER = 8;
    private final int BTN_LAYER = 8;
    private final int ANI_LAYER = 1;
    
    float g_rX, g_rY;
    float scaled_width, scaled_height;   
    
    
	public GameLayer()
	{
		System.gc();
		
		 CCMenuItemImage btnPause;
		 CCMenuItemImage btnDown;
		 CCMenuItemImage btnUp;
		 CCMenuItemImage btnCollection;
		 CCMenuItemImage btnbeer;
		 CCMenuItemImage btnExit;
		    
		 CCMenuItemImage soundOn;
		 CCMenuItemImage soundOff;
		 
		 CCSprite backImg;
		 CCSprite bottomLine;
		 		   	
		//initSound();
		
		CCActionManager.sharedManager().removeAllActions();
	    CCTextureCache.sharedTextureCache().removeAllTextures();
	    removeAllChildren(true);
	    
		g_rX = Global.g_rX; g_rY = Global.g_rY;
		scaled_width = Global.scaled_width; scaled_height = Global.scaled_height;
		
		CGSize winSize = CCDirector.sharedDirector().winSize();
		
        m_waiterInfo = WaiterInfo.createWaiterInfo();
        m_waiterInfo.setWaiterInfo();
        addWaiter();
        
       // loadBackSound();
        
        m_fSound = true;
        m_nBeerNum = 0;
        
        m_bonusInfo = BonusInfo.createBonusInfo();
        m_bonusInfo.setBonusInfo();
        addBonus();
        
       m_beerArray = new ArrayList<PipeIndexInfo>();
        
        backImg = CCSprite.sprite("gfx/background.png");
        backImg.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
        backImg.setScaleX(scaled_width); backImg.setScaleY(scaled_height);
        addChild(backImg,0);
        CCTextureCache.sharedTextureCache().removeTexture(backImg.getTexture());
        
        btnPause = CCMenuItemImage.item("gfx/BTN_Pause.png", "gfx/BTN_Pause.png", this, "onPauseGame"); 
        btnPause.setScaleX(scaled_width); btnPause.setScaleY(scaled_height);
        btnPause.setPosition(968 * g_rX, Global.getCocosY(130 * g_rY));
        
        soundOn = CCMenuItemImage.item("gfx/BTN_SoundOn.png", "gfx/BTN_SoundOn.png"); 
        soundOff = CCMenuItemImage.item("gfx/BTN_SoundOff.png", "gfx/BTN_SoundOff.png");
        
       CCMenuItemToggle btnsound = CCMenuItemToggle.item(this, "onSoundOnOff", soundOn, soundOff); 
        btnsound.setScaleX(scaled_width); btnsound.setScaleY(scaled_height);        
        btnsound.setPosition(968 * g_rX, Global.getCocosY(245 * g_rY));
        
        btnDown = CCMenuItemImage.item("gfx/BTN_Round_S_DOWN_i.png", "gfx/BTN_Round_S_DOWN_i.png", this, "onDown"); 
        btnDown.setScaleX(scaled_width); btnDown.setScaleY(scaled_height);        
        btnDown.setPosition(944 * g_rX, Global.getCocosY(692 * g_rY));
        
        btnUp = CCMenuItemImage.item("gfx/BTN_Round_S_UP_i.png", "gfx/BTN_Round_S_UP_i.png", this, "onUp");
        btnUp.setScaleX(scaled_width); btnUp.setScaleY(scaled_height);        
        btnUp.setPosition(944 * g_rX, Global.getCocosY(524 * g_rY));
        
        btnCollection = CCMenuItemImage.item("gfx/BTN_Round_COLLECT_i.png", "gfx/BTN_Round_COLLECT_i.png", this, "onCollection"); 
        btnCollection.setScaleX(scaled_width); btnCollection.setScaleY(scaled_height);        
        btnCollection.setPosition(788 * g_rX, Global.getCocosY(692 * g_rY));
        
        btnbeer =  CCMenuItemImage.item("gfx/BTN_Round_TAP_i.png", "gfx/BTN_Round_TAP_i.png", this, "onBeer");
        btnbeer.setScaleX(scaled_width); btnbeer.setScaleY(scaled_height);        
        btnbeer.setPosition(66 * g_rX, Global.getCocosY(690 * g_rY));
        
        btnExit = CCMenuItemImage.item("gfx/BTN_Exit_i.png", "gfx/BTN_Exit_i.png", this, "onExitGame"); 
        btnExit.setScaleX(scaled_width); btnExit.setScaleY(scaled_height);        
        btnExit.setPosition(944 * g_rX, Global.getCocosY(40 * g_rY));
        
        bottomLine = CCSprite.sprite("gfx/T_line_bottom.png"); 
        bottomLine.setPosition(LEVEL_PRO_X * g_rX, Global.getCocosY(40 * g_rY));
        bottomLine.setScaleX(scaled_width); bottomLine.setScaleY(scaled_height);
        addChild(bottomLine, MESSAGE_LAYER + 1);
        CCTextureCache.sharedTextureCache().removeTexture(bottomLine.getTexture());
               
        m_nProWidth = (int) ((int) bottomLine.getTexture().getWidth());
        
        m_proImg = CCSprite.sprite("gfx/T_line_top.png");
        m_proImg.setPosition(LEVEL_PRO_X * g_rX, Global.getCocosY(720 * g_rY));
        m_proImg.setScaleX(0.0f); m_proImg.setScaleY(scaled_height);
        addChild(m_proImg, MESSAGE_LAYER + 1);
        CCTextureCache.sharedTextureCache().removeTexture(m_proImg.getTexture());
        
        CCSprite piano;
        piano = CCSprite.sprite("gfx/GrandPiano.png");
        piano.setPosition(606 * g_rX, Global.getCocosY(122 * g_rY));
        piano.setScaleX(scaled_width); piano.setScaleY(scaled_height);
        addChild(piano);
        CCTextureCache.sharedTextureCache().removeTexture(piano.getTexture());
          
        String str = String.format("Level:%d", Global.g_nRealLevelNum);
        
        levelLabel = CCLabel.makeLabel(str, CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 20 * scaled_height); 
        levelLabel.setColor(new ccColor3B(231, 71, 41));        
        levelLabel.setPosition(100 * g_rX, Global.getCocosY(40 * g_rY));
//        levelLabel.setScaleX(scaled_width); levelLabel.setScaleY(scaled_height);
        addChild(levelLabel, 6); 
        CCTextureCache.sharedTextureCache().removeTexture(levelLabel.getTexture());
       
        m_nScore = 0;
        
        str = String.format("Score:%d", m_nScore); 
        scoreLabel = CCLabel.makeLabel(str, CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 20 * scaled_height); 
        scoreLabel.setColor(new ccColor3B(231, 71, 41));        
        scoreLabel.setPosition(540 * g_rX, Global.getCocosY(40 * g_rY));
//        scoreLabel.setScaleX(scaled_width); scoreLabel.setScaleY(scaled_height);
        addChild(scoreLabel, 6);
        CCTextureCache.sharedTextureCache().removeTexture(scoreLabel.getTexture());
         
        m_nLive = 5;
        str = String.format("Live:%d", m_nLive); 
        liveLabel = CCLabel.makeLabel(str, CGSize.make(480.0f, 50.0f), TextAlignment.CENTER, "Marker Felt", 20 * scaled_height);
        liveLabel.setColor(new ccColor3B(231, 71, 41));
        liveLabel.setPosition(760 * g_rX, Global.getCocosY(40 * g_rY));
//        liveLabel.setScaleX(scaled_width); liveLabel.setScaleY(scaled_height);
        addChild(liveLabel, 6);
        CCTextureCache.sharedTextureCache().removeTexture(liveLabel.getTexture());
          
        CCMenu menu = CCMenu.menu(btnPause, btnsound, btnUp, btnDown, btnCollection, btnbeer, btnExit); 
		menu.setPosition(0, 0);
        addChild(menu, BTN_LAYER);
        
        loadLamp();
        loadOtherInfo();
        
        m_fFirstLoad = true;
        
        this.isTouchEnabled_ = true;
        
        Global.g_nMessageState = GameConfig.TAP_STATE;
        
        mallocBoozerArrays();
        mallocBeerArrays();
        mallocTipArrays();
        
        onDelay();
        
        this.isTouchEnabled_ = true;
	}
	
	
	 public void playSound(int nType)
	 {	
		 if (nType == Global.APPLAUSE_OYE_SOUND)
		 {
			 Global.m_pSoundSuccess.start();
		 }
		 else 
		 {
			if (Global.m_pSounds != null)
				Global.m_pSounds.release();
			Global.m_pSounds = MediaPlayer.create(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.applause_oye+nType);		 	
			Global.m_pSounds.start();
		 }		 
	 }
	 
	 public void dealloc()
	{
		unscheduleAllSelectors();
		freeBoozerArrays();    
	    freeBeerArrays();
	    freeTipArrays();
	    
	    if (m_waiterInfo != null)
	    {
	    	removeChild(m_waiterInfo, true);
	        m_waiterInfo.dealloc();
	        m_waiterInfo = null;
	    }
	    
	    if (m_bonusInfo != null)
	    {
//	        m_bonusInfo.removeSelf();
	    	removeChild(m_bonusInfo, true);
	    	m_bonusInfo.dealloc();
	    	m_bonusInfo = null;
	    }
	    
	    m_beerArray.removeAll(m_beerArray);
	    m_beerArray = null;
        removeAllChildren(true);
        System.gc();
	}
	
	public void loadOtherInfo()
	{
		int i;
		    
	    String str;
	    
	    for (i = 0; i < 2; i ++)
	    {
	        if (Global.g_nRealLevelNum > 10)
	            str = String.format("gfx/LightBulb_0%d.png", i + 3);
	        else
	            str = String.format("gfx/LightBulb_0%d.png", i + 1); 
	        
	        m_lampBarSprite[i] = CCSprite.sprite(str); 
	        m_lampBarSprite[i].setScaleX(scaled_width); m_lampBarSprite[i].setScaleY(scaled_height);
	        m_lampBarSprite[i].setPosition(71 * g_rX, Global.getCocosY(139 * g_rY));
	        
	        addChild(m_lampBarSprite[i], ANI_LAYER);
	        CCTextureCache.sharedTextureCache().removeTexture(m_lampBarSprite[i].getTexture());
	        
	        if (i == 1)
	            m_lampBarSprite[i].setVisible(true);
	        else 
	            m_lampBarSprite[i].setVisible(false);
	    }
	    
	    m_pianoSprite = CCSprite.sprite("gfx/PianoMan_01.png");
	  // m_pianoSprite.setScale(2.0f);
	    m_pianoSprite.setScaleX(2.0f*scaled_width); m_pianoSprite.setScaleY(2.0f*scaled_height);
        m_pianoSprite.setPosition(543 * g_rX, Global.getCocosY(131 * g_rY));
        addChild(m_pianoSprite, ANI_LAYER);  
       CCTextureCache.sharedTextureCache().removeTexture(m_pianoSprite.getTexture());
        
	    for (i = 0; i < 2; i ++)
	    {
	        str = String.format("gfx/GuitarBoy_0%d.png", i + 1); 
	        
	        m_guitarSprite[i] = CCSprite.sprite(str); 
	        m_guitarSprite[i].setScaleX(scaled_width); m_guitarSprite[i].setScaleY(scaled_height);
	        m_guitarSprite[i].setPosition(852 * g_rX, Global.getCocosY(172 * g_rY));
	        
	        addChild(m_guitarSprite[i], ANI_LAYER);
	        CCTextureCache.sharedTextureCache().removeTexture(m_guitarSprite[i].getTexture());
	        
	        if (i == 1)
	            m_guitarSprite[i].setVisible(true);
	        else 
	            m_guitarSprite[i].setVisible(false);
	    }
	}
	
	private final int BACK_SOUND_NUM = 4;
	public int randBelowInteger(int nLimit)
	{
		double randD = Math.random();
		int randI = (int)Math.round(randD * 10000);
		return (randI %nLimit);
	}
	public void initGame()
	{
		CCActionManager.sharedManager().removeAllActions();
		 //CCTextureCache.sharedTextureCache().removeAllTextures();
		 System.gc();
		    
		 m_fReleaseMessageAction = m_fInitMessageAction = false;
		m_fGameOver = false; 	    
	    m_fPause = false;
	    m_fGameOverSet = false;
	    m_fWaiterStrong = false;	    
	    m_nPianoIdx = 0;    
	    m_rPianoTime = 0.0f;	    
	    m_fEndState = false;
	    m_fBoozerEnd = false;
	    m_fEndWaiterState = false;
	   m_fSetSound = false;	    
	    
	    //progress bar 
	    setGamePro(0);
	    
	    String str;
	    m_nBackSoundIdx = randBelowInteger(BACK_SOUND_NUM);
	    setBackSoundIdx();
	    unscheduleAllSelectors();
	    
	    initBoozerArrays();
	    initBeerArrays();
	    initTipArrays();   	    
	    
	    if (m_beerArray == null)
	    	m_beerArray = new ArrayList<PipeIndexInfo>();
	    m_beerArray.removeAll(m_beerArray);
	    System.gc();
	   
	    str = String.format("Level:%d", Global.g_nRealLevelNum);
	    levelLabel.setString(str);

	    str = String.format("Score:%d", m_nScore); 
	    scoreLabel.setString(str);
	    
	    str = String.format("Live:%d", m_nLive); 
	    liveLabel.setString(str);

	   
//	    Global.g_levelMgr.dealloc();
//	    System.gc();
//	    Global.createLevelMgr();
	    Global.g_levelMgr.setLevel(Global.g_nRealLevelNum);
	    
	    addBoozer();
	    
	    m_waiterInfo.setWaiterState(GameConfig.NON_WAITER_STATE);	  
	   
	    startAction();
	    startWaiterAction();    
	    startLampAction();
	    startLampBarAction();
	    startBeerAction();
	    startBonusAction();
	    startScoreAction();
	    
	    m_fBonusPlay = false;
	    m_fGuitarPlay = false;
	    
	    startGuitarAction();
	    
//	    for(int i = 0; i < 4; i ++)
//	    {
//	    	if(Global.m_pSounds[i] != null)
//	    	{
//	    		Global.m_pSounds[i].release();
//	    	}
//	    	
//	    	Global.m_pSounds[i] = null;
//	    }
	    
	    if (m_fFirstLoad)
	    {
	        m_fFirstLoad = false;
	        setPianoState(GameConfig.SIT_PIANO_STATE);
	        startPianoAction();
	    }
	    else
	    {
	        if (m_fSound)
	            setPianoState(GameConfig.HAPPY_PIANO_STATE);
	        else 
	            setPianoState(GameConfig.UNHAPPY_PIANO_STATE);
	        
	        startPianoAction();
	    }
	    System.gc();
	}
	
	private void mallocBoozerArrays()
	{
		m_boozerArray1 = new ArrayList<BoozerInfo>(); 
	    m_boozerArray2 = new ArrayList<BoozerInfo>();
	    m_boozerArray3 = new ArrayList<BoozerInfo>();
	    m_boozerArray4 = new ArrayList<BoozerInfo>();   
	}
	
	private void freeBoozerArrays()
	{
		initBoozerArrays();
	    
	    if (m_boozerArray1 != null)
	        m_boozerArray1 = null;
	    
	    if (m_boozerArray2 != null)
	    	m_boozerArray2 = null;
	    
	    if (m_boozerArray3 != null)
	    	m_boozerArray3 = null;
	    
	    if (m_boozerArray4 != null)
	    	m_boozerArray4 = null;
	}
	
	private void initBoozerArrays()
	{
		//if(m_boozerArray1 != null && m_boozerArray1.size() > 0)
		{
			for(int i = 0; i < m_boozerArray1.size(); i ++)
			{
				removeChild(m_boozerArray1.get(i), true);
				m_boozerArray1.get(i).dealloc();				
			}
			m_boozerArray1.removeAll(m_boozerArray1);
		}
		
		//if(m_boozerArray2 != null && m_boozerArray2.size() > 0)
		{
			for(int i = 0; i < m_boozerArray2.size(); i ++)
			{
				removeChild(m_boozerArray2.get(i), true);
				m_boozerArray2.get(i).dealloc();
			}
			m_boozerArray2.removeAll(m_boozerArray2);
		}
		
		//if(m_boozerArray3 != null && m_boozerArray3.size() > 0)
		{
			for(int i = 0; i < m_boozerArray3.size(); i ++)
			{
				removeChild(m_boozerArray3.get(i), true);
				m_boozerArray3.get(i).dealloc();
			}
			m_boozerArray3.removeAll(m_boozerArray3);
		}
		
		//if(m_boozerArray4 != null && m_boozerArray4.size() > 0)
		{
			for(int i = 0; i < m_boozerArray4.size(); i ++)
			{
				removeChild(m_boozerArray4.get(i), true);
				m_boozerArray4.get(i).dealloc();
			}
			m_boozerArray4.removeAll(m_boozerArray4);
		}
		System.gc();
	}
	
	private void mallocBeerArrays()
	{
		m_beerArray1 = new ArrayList<BeerInfo>();
		m_beerArray2 = new ArrayList<BeerInfo>();
		m_beerArray3 = new ArrayList<BeerInfo>();
		m_beerArray4 = new ArrayList<BeerInfo>();		
	}
	
	private void freeBeerArrays()
	{
		initBeerArrays();
		
		if(m_beerArray1 != null)
			m_beerArray1 = null;
		if(m_beerArray2 != null)
			m_beerArray2 = null;
		if(m_beerArray3 != null)
			m_beerArray3 = null;
		if(m_beerArray4 != null)
			m_beerArray4 = null;
	}
	
	private void initBeerArrays()
	{
		//if(m_beerArray1 != null && m_beerArray1.size() > 0)
		{
			for(int i = 0; i < m_beerArray1.size(); i ++)
			{
				removeChild(m_beerArray1.get(i), true);
				m_beerArray1.get(i).dealloc();
				
			}
			m_beerArray1.removeAll(m_beerArray1);
		}
		
		//if(m_beerArray2 != null && m_beerArray2.size() > 0)
		{
			for(int i = 0; i < m_beerArray2.size(); i ++)
			{
				removeChild(m_beerArray2.get(i), true);
				m_beerArray2.get(i).dealloc();
				
			}
			m_beerArray2.removeAll(m_beerArray2);
		}
		
		//if(m_beerArray3 != null && m_beerArray3.size() > 0)
		{
			for(int i = 0; i < m_beerArray3.size(); i ++)
			{
				removeChild(m_beerArray3.get(i), true);
				m_beerArray3.get(i).dealloc();
				
			}
			m_beerArray3.removeAll(m_beerArray3);
		}
		
		//if(m_beerArray4 != null && m_beerArray4.size() > 0)
		{
			for(int i = 0; i < m_beerArray4.size(); i ++)
			{
				removeChild(m_beerArray4.get(i), true);
				m_beerArray4.get(i).dealloc();
				
			}
			m_beerArray4.removeAll(m_beerArray4);
		}
		System.gc();
	}
	
	private void mallocTipArrays()
	{
		m_tipArray1 = new ArrayList<TipInfo>();
		m_tipArray2 = new ArrayList<TipInfo>();
		m_tipArray3 = new ArrayList<TipInfo>();
		m_tipArray4 = new ArrayList<TipInfo>();	
	}
	
	private void freeTipArrays()
	{
		initTipArrays();
		
		if(m_tipArray1 != null)
			m_tipArray1 = null;
		if(m_tipArray2 != null)
			m_tipArray2 = null;
		if(m_tipArray3 != null)
			m_tipArray3 = null;
		if(m_tipArray4 != null)
			m_tipArray4 = null;
	}
	
	private void initTipArrays()
	{
		//error 1
		int i;
		//if (m_tipArray1 != null)
		{
			for (i=0;i<m_tipArray1.size();i++)
			{
				removeChild(m_tipArray1.get(i), true);
				m_tipArray1.get(i).dealloc();
			}
		}
		//if (m_tipArray2 != null)
		{
			for (i=0;i<m_tipArray2.size();i++)
			{
				removeChild(m_tipArray2.get(i), true);
				m_tipArray2.get(i).dealloc();
			}
		}
		//if (m_tipArray3 != null)
		{
			for (i=0;i<m_tipArray3.size();i++)
			{
				removeChild(m_tipArray3.get(i), true);
				m_tipArray3.get(i).dealloc();
			}
		}
		//if (m_tipArray4 != null)
		{
			for (i=0;i<m_tipArray4.size();i++)
			{
				removeChild(m_tipArray4.get(i), true);
				m_tipArray4.get(i).dealloc();
			}
		}
		m_tipArray1.removeAll(m_tipArray1);
		m_tipArray2.removeAll(m_tipArray2);
		m_tipArray3.removeAll(m_tipArray3);
		m_tipArray4.removeAll(m_tipArray4);
		System.gc();
	}
	
	public void initMessage()
	{
		if (m_fInitMessageAction == true)
			return;
		
		m_fInitMessageAction = true;
		playSound(Global.MESSAGEBOARD_SOUND);
	    
	    m_fGamePlay = false;	    
	    m_fMessageLoad = true;
	    
	   m_markSprite = CCSprite.sprite(getMessageBoard()); 
	    
	   if (m_markSprite == null)
	    	return;
	    int nHeight = (int) ((int) m_markSprite.getContentSize().getHeight());//ksi
	    
	    m_markSprite.setScaleX(scaled_width); m_markSprite.setScaleY(scaled_height);
	    m_markSprite.setPosition(512 * g_rX, Global.getCocosY((299 - nHeight) * g_rY));
	    addChild(m_markSprite, MESSAGE_LAYER + 2);
	    CCTextureCache.sharedTextureCache().removeTexture(m_markSprite.getTexture());
	    
	    CCFiniteTimeAction actionMove1 = CCMoveTo.action(0.3f, CGPoint.make(512 * g_rX, Global.getCocosY(359 * g_rY)));
	    CCFiniteTimeAction actionMove2 = CCMoveTo.action(0.1f, CGPoint.make(512 * g_rX, Global.getCocosY(299 * g_rY)));
	    m_markSprite.runAction(CCSequence.actions(actionMove1, actionMove2, CCCallFunc.action(this, "finishInitMessage")));
	    this.runAction(CCSequence.actions(CCCallFunc.action(this, "delayInitMessage"), 
				CCDelayTime.action(0.4f)));
	}
	
	public void finishInitMessage()
	{
		m_fInitMessageAction = false;
	}
	private String getMessageBoard()
	{
		if(Global.g_nMessageState == GameConfig.TAP_STATE)
        	return "gfx/MessageBoards_tap.png";
		if(Global.g_nMessageState == GameConfig.GAME_OVER_STATE)
        	return "gfx/MessageBoards_gameover.png";
		if(Global.g_nMessageState == GameConfig.NEXT_LEVEL_STATE)
			return "gfx/MessageBoards_next.png";
		if(Global.g_nMessageState == GameConfig.PAUSED_STATE)
			return "gfx/MessageBoards_paused.png";
		
		if(Global.g_nMessageState == GameConfig.RESTART_STATE)
			return "gfx/MessageBoards_restart.png";
		return null;
	}
	
	public void delayInitMessage()
	{
		this.setIsTouchEnabled(true);
		  
	    if (Global.g_nMessageState == GameConfig.GAME_OVER_STATE)
	    {
	        dispNameAlertView();
	    }
	}
	
	public void releaseMessage()
	{
		if (m_fReleaseMessageAction == true)
			return;
		m_fReleaseMessageAction = true;
		
	    playSound(Global.MESSAGEBOARD_SOUND);
	    int nHeight = (int) ((int) m_markSprite.getContentSize().getHeight() * scaled_height);
	    
	   CCFiniteTimeAction actionMove1 = CCMoveTo.action(0.3f, CGPoint.make(512 * g_rX, Global.getCocosY((299 - nHeight) * g_rY))); 
	    m_markSprite.runAction(CCSequence.actions(actionMove1));
	    this.runAction(CCSequence.actions(CCCallFunc.action(this, "delayReleaseMessageAction"), 
				CCDelayTime.action(0.3f)));
	    
	}

	public void onDelay()
	{
		this.runAction(CCSequence.actions(CCCallFunc.action(this, "delayInitAction"), 
				CCDelayTime.action(GameConfig.MESSAGE_DALAY)));
	}
	
	public void delayInitAction()
	{
		initMessage();
	}

	
	public void delayReleaseMessageAction()
	{
		m_fReleaseMessageAction = false;
		m_fMessageLoad = false;
		
		removeChild(m_markSprite, true);
	    CCTextureCache.sharedTextureCache().removeTexture(m_markSprite.getTexture());		
	    
	    if (Global.g_nMessageState == GameConfig.PAUSED_STATE)
	    {
	        m_fGamePlay = true;
	        m_fPause = false;
	    }
	    else if (Global.g_nMessageState == GameConfig.GAME_OVER_STATE)
	    {
	        m_nLive = 5;
	        m_nScore = 0;
	        m_nBeerNum = 0;
	        Global.g_nRealLevelNum = 1;
	        
	        initGame();
	    }
	    else 
	    {
	        initGame();
	    }    
	}
	
	public void startAction()
	{
		m_fGamePlay = true;
	    
	    if (m_fWaiterStrong)
	    	schedule("startAction", GameConfig.BOOZER_SLOW_ANI_INTERVAL / 2.0f);
	    else 
	    	schedule("startAction", GameConfig.BOOZER_SLOW_ANI_INTERVAL);
	}
	
	public void stopAction()
	{
		unschedule("startAction");
	}
	
	public void startAction(float dt)
	{
		 if (m_fPause || !m_fGamePlay)
		        return;
		    
		    if (m_rPianoTime > 3.0f)
		    {
		        if (!m_fSetSound)
		        {
		            soundPlay();
		           m_fSetSound = true;
		        }
		    }
		    
		    if (Global.g_levelMgr.getLevelDone() && returnGameDone())
		    {
		        if (!m_fEndState)
		        {
		            dispEndPlay();
		            calcScore(GameConfig.NEXT_LEVEL_SCORE);
		            
		            soundStop();
		            setPianoState(GameConfig.HAPPY_PIANO_STATE);
		            
		            return;
		        }
		        
		        if (!m_fWaiterPlay)
		        {
		            gameComplete();
		            m_fGamePlay = false;
		        }        
		        
		        return;
		    }
		    
		    if (m_fGameOver)
		        return;
		    
		    dispBoozerPlay();
		    searchBoozerBeerState();
		    
		    if (Global.g_levelMgr.getStep() != 0)
		    {
		        setActiveBoozer();        
		        return;
		    }

		    m_rPianoTime += GameConfig.BOOZER_SLOW_ANI_INTERVAL;
		    //System.gc();
	}
	
	public void startWaiterAction()
	{
		m_fWaiterPlay = true;
		    
	    if (m_fWaiterStrong)
	    	schedule("startWaiterAction", GameConfig.WAITER_ANI_INTERVAL / 2.0f);
	    else 
	    	schedule("startWaiterAction", GameConfig.WAITER_ANI_INTERVAL);
	}
	
	public void stopWaiterAction()
	{
		unschedule("startWaiterAction");
	}
	
	public void startWaiterAction(float dt)//star
	{
		if (m_fPause || !m_fWaiterPlay)
	        return;
	    
	    if (m_waiterInfo.m_fEndThred)
	    {
	        m_fWaiterPlay = false;
	        return;
	    }
	    
	    int nReturn = m_waiterInfo.getWaiterInfo();
	    
	    if (nReturn == 1)
	    {
	        //PipeIndexInfo pipeInfo;        
	        //pipeInfo = m_beerArray.get(0);
	        
	        addBeer(m_beerArray.get(0).nIdx, GameConfig.BEER_STATE, (int) m_waiterInfo.m_pt.x);
	        m_beerArray.remove(0);	        
	        
	        m_fWaiterPlay = false;	  
	        System.gc();
	        return;
	    }
	    else if (nReturn == 2)
	    {
	    	
	    	ArrayList<BeerInfo> beerArray = null; 
	        switch (m_waiterInfo.getWaiterPoseState())
	        {
	            case 0:
	                beerArray = m_beerArray1;
	                break;	                
	            case 1:
	                beerArray = m_beerArray2;
	                break;	                
	            case 2:
	                beerArray = m_beerArray3;
	                break;	                
	            case 3:
	                beerArray = m_beerArray4;
	                break;	                
	            default:
	                break;
	        }
	        
	        if (beerArray == null)
	        	return;
	        BeerInfo beerInfo;	        
	        for (int i = 0; i < beerArray.size(); i ++)
	        {
	            beerInfo = beerArray.get(i);	            
	            if (beerInfo.getBeerState() == GameConfig.NON_BEER_STATE)
	            {
	            	switch (m_waiterInfo.getWaiterPoseState())
	    	        {
	    	            case 0:
	    	               removeChild(m_beerArray1.get(i), true);
	    	               m_beerArray1.get(i).dealloc();
	    	                
	    	                m_beerArray1.remove(i);
	    	                break;	                
	    	            case 1:
	    	            	 removeChild(m_beerArray2.get(i), true);
	    	            	 m_beerArray2.get(i).dealloc();
	    	            	 
		    	                m_beerArray2.remove(i);
	    	                break;	                
	    	            case 2:
	    	            	 removeChild(m_beerArray3.get(i), true);
	    	            	 m_beerArray3.get(i).dealloc();
	    	            	 
		    	                m_beerArray3.remove(i);
	    	                break;	                
	    	            case 3:
	    	            	removeChild(m_beerArray4.get(i), true);
	    	            	 m_beerArray4.get(i).dealloc();
	    	            	 
		    	                m_beerArray4.remove(i);
	    	                break;	                
	    	            default:
	    	                break;
	    	        }
//	            	beerArray.get(i).dealloc();//ksi
//	                beerArray.remove(i);
	                i --;
	                m_nBeerNum ++;
	                calcScore(GameConfig.NORMAL_SCORE);
	            }
	        }
	    }
	    else if (nReturn == 3)// success
	        m_fEndState = true;
	    else if (nReturn == 4) // fail
	    {
	        if (!m_fBoozerEnd)
	            m_fEndState = true;
	    }
	   // System.gc();
	}
	
	public void startBeerAction()
	{
		m_fBeerPlay = true;
	    
	    if (m_fWaiterStrong)
	    	schedule("startBeerAction", GameConfig.BEER_ANI_INTERVAL / 2.0f);
	    else
	    	schedule("startBeerAction", GameConfig.BEER_ANI_INTERVAL);
	}
	
	public void stopBeerAction()
	{
		unschedule("startBeerAction");
	}
	
	public void startBeerAction(float dt)
	{
	    if (m_fPause || !m_fBeerPlay)
	        return;
	    
	    if (m_fGameOver)
	    {
	        if (!m_fEndState)
	        {
	            dispEndPlay();
	            soundStop();
	            
	            return;
	        }
	        
	        if (!m_fWaiterPlay)
	        {
	            gameOver();
	            m_fBeerPlay = false;
	        }
	        
	        return;
	    }
	   
	    dispBeerPlay();
	    dispTipPlay();
	   // System.gc();
	}
	
	public void startLampAction()
	{
		m_fLampPlay = true;
	    schedule("startLampAction", GameConfig.LAMP_ANI_INTERVAL);
	}
	
	public void stopLampAction()
	{
		unschedule("startLampAction");
	}
	
	public void startLampAction(float dt)
	{
	   if (m_fPause || !m_fLampPlay)
	        return;
	    
	    int i, j;
	    for (i = 0; i < 4; i ++)
	        for (j = 0; j < 2; j ++)
	            m_lampSprite[i][j].setVisible(false);

	    if (Global.g_levelMgr.m_nPipeLeftBoozer1 > 0)
	        m_lampSprite[0][0].setVisible(true);
	    else 
	        m_lampSprite[0][1].setVisible(true);
	    
	    if (Global.g_levelMgr.m_nPipeLeftBoozer2 > 0)
	         m_lampSprite[1][0].setVisible(true);
	    else 
	        m_lampSprite[1][1].setVisible(true);
	     
	    if (Global.g_levelMgr.m_nPipeLeftBoozer3 > 0)
	         m_lampSprite[2][0].setVisible(true);
	    else 
	        m_lampSprite[2][1].setVisible(true);
	     
	    if (Global.g_levelMgr.m_nPipeLeftBoozer4 > 0)
	         m_lampSprite[3][0].setVisible(true);
	    else
	        m_lampSprite[3][1].setVisible(true);
	     
	    if (Global.g_levelMgr.getLevelDone())
	    {
	        m_fLampPlay = false;
	        return;
	    }
	   // System.gc();
	}
	
	public void startLampBarAction()
	{
		m_fLampBarPlay = true;		    
	    m_nLampBarIdx = 0;	    
	    schedule("startLampBarAction", GameConfig.LAMP_BAR_ANI_INTERVAL);
	}
	
	public void stopLampBarAction()
	{
		unschedule("startLampBarAction");
	}
	
	public void startLampBarAction(float dt)
	{
		if (m_fPause || !m_fLampBarPlay)
	        return;

	    for (int i = 0; i < 2; i ++)
	        m_lampBarSprite[i].setVisible(false);
	    
	    m_nLampBarIdx = m_nLampBarIdx % 2;
	    
	    m_lampBarSprite[m_nLampBarIdx].setVisible(true);
	    
	    m_nLampBarIdx ++;
	    
	    if (Global.g_levelMgr.getLevelDone())
	    {
	        for (int i = 0; i < 2; i ++)
	            m_lampBarSprite[i].setVisible(false);
	        
	        m_lampBarSprite[1].setVisible(true);
	        
	        m_fLampBarPlay = false;
	        return;
	    }
	   // System.gc();
	}
	
	public void startGuitarAction()
	{
	    m_nGuitarIdx = 0;
	    schedule("startGuitarAction", GameConfig.GUITRA_ANI_INTERVAL);
	}
	
	public void stopGuitarAction()
	{
		unschedule("startGuitarAction");
	}
	
	public void startGuitarAction(float dt)
	{
	   if (m_fPause || !m_fGuitarPlay)
	        return;

	    for (int i = 0; i < 2; i ++)
	        m_guitarSprite[i].setVisible(false);
	    
	    m_nGuitarIdx = m_nGuitarIdx % 2;
	    
	    m_guitarSprite[m_nGuitarIdx].setVisible(true);
	  
	    if (Global.g_levelMgr.getLevelDone())
	    {
	        m_fGuitarPlay = false;
	        return;
	    }
	    
	    m_nGuitarIdx ++;
	   // System.gc();
	}
	
	public void startPianoAction()
	{
		m_fPianoPlay = true;
		schedule("startPianoAction", GameConfig.PIANO_ANI_INTERVAL);
	}
	
	public void stopPianoAction()
	{
		unschedule("startPianoAction");
	}
	
	public void setPianoState(int nState)
	{
		m_nPainoState = nState;
		    
	    switch (m_nPainoState)
	    {
	        case GameConfig.STAND_PIANO_STATE: 
	            m_nPianoIdx = 0;            
	            break;
	        case GameConfig.SIT_PIANO_STATE: 
	            m_nPianoIdx = 1;
	            break;
	        case GameConfig.PLAY_PIANO_STATE: 
	            m_nPianoIdx = 6;
	            break;
	        case GameConfig.HAPPY_PIANO_STATE: 
	            m_nPianoIdx = 13;
	            break;
	        case GameConfig.UNHAPPY_PIANO_STATE:
	            m_nPianoIdx = 14;
	            break;
	        default:
	            break;
	    }
	}
	
	public void changePianoSprite(int nIdx)
	{
		CCTexture2D texture = CCTextureCache.sharedTextureCache().addImage(String.format("gfx/PianoMan_%02d.png", m_nPianoIdx));
		m_pianoSprite.setTexture(texture);
		CCTextureCache.sharedTextureCache().removeTexture(texture);
	}
	public void startPianoAction(float dt)
	{
		if (m_fPause || !m_fPianoPlay)
		       return;
	    
		changePianoSprite(m_nPianoIdx);
	    if (m_nPainoState == GameConfig.HAPPY_PIANO_STATE || m_nPainoState == GameConfig.UNHAPPY_PIANO_STATE)
	    {
	        m_fPianoPlay = false;
	        return;
	    }
	    
	    if (m_nPainoState == GameConfig.SIT_PIANO_STATE)
	    {
	        boolean fFlag = false;
	        if (m_nPianoIdx >= 6)
	        {
	            if (m_fSound)
	            {
	                m_nPianoIdx = 13;
	                m_nPainoState = GameConfig.HAPPY_PIANO_STATE;
	                fFlag = true;
	            }
	            else 
	            {
	                m_nPianoIdx = 14;
	                m_nPainoState = GameConfig.UNHAPPY_PIANO_STATE;
	                fFlag = true;
	            }
	        }
	        if (!fFlag)
	            m_nPianoIdx ++;
	    }
	    else if (m_nPainoState == GameConfig.PLAY_PIANO_STATE)
	    {
	        m_nPianoIdx ++;
	        
	        if (m_nPianoIdx >= 13)
	            m_nPianoIdx = 6;
	    } 
	    //System.gc();
	}
	
	public void startBonusAction()
	{
		 m_fBonusPlay = true;
		 schedule("startBonusAction", GameConfig.BONUS_ANI_INTERVAL);
	}
	
	public void stopBonusAction()
	{
		unschedule("startBonusAction");
	}
	
	public final float ADD_GAIN_CUP_Y = 90.0f;
	public final float ADD_NORMAL_WAITER_Y = 150.0f;
	
	public void startBonusAction(float dt)
	{
		if (m_fPause || !m_fBonusPlay)
	        return;

	    CGPoint pt = CGPoint.ccp(m_waiterInfo.m_pt.x, m_waiterInfo.m_pt.y);

	    if (m_waiterInfo.isValidGainCupWaiterState())
	        pt.y += ADD_GAIN_CUP_Y * g_rY;
	    else
	        pt.y += ADD_NORMAL_WAITER_Y * g_rY;

	    m_bonusInfo.m_pt = pt;
	    
	    if (m_bonusInfo.getBonusInfo() != 0)
	    {
	        m_fBonusPlay = false;
	        m_bonusInfo.nonVisibleSprites();
	        
	        return;
	    }
	   // System.gc();
	}
	
	public void startScoreAction()
	{
		m_fScorePlay = true;
		schedule("startScoreAction", 0.05f);
	}
	
	public void stopScoreAction()
	{
		unschedule("startScoreAction");
	}
	
	public void startScoreAction(float dt)
	{
		if (m_fPause || !m_fScorePlay)
		       return;
		
		String str = String.format("Score:%d", m_nScore);
	    scoreLabel.setString(str);
	}
	
	public void setActiveBoozer()
	{
	   BoozerInfo boozerInfo;    
	   BoozerAppearInfo boozerAppearInfo;
	    
	    int i;
	    
	    ArrayList<BoozerInfo> boozerArray = null;
	    for (i = 0; i < Global.g_boozerArray.size(); i ++)
	    {      
	        boozerAppearInfo = Global.g_boozerArray.get(i);

	        switch (boozerAppearInfo.nPipeNum) 
	        {
	            case 0:
	                boozerArray = m_boozerArray1;           
	                break;
	                
	            case 1:
	                boozerArray = m_boozerArray2;           
	                break;
	                
	            case 2:
	                boozerArray = m_boozerArray3;           
	                break;
	                
	            case 3:
	                boozerArray = m_boozerArray4;
	                break;
	                
	            default:
	                break;
	        }
	        
	        if (boozerArray == null)
	        	return;
	        boolean fFlag = false;
	        for (int j = 0; j < boozerArray.size(); j ++)
	        {
	            boozerInfo = boozerArray.get(j);
	            
	            if (boozerInfo.getActiveState())
	                continue;
	            else 
	            {
	                boozerInfo.setActiveState();
	                fFlag = true;
	                break;
	            }
	        }
	        
	        if (fFlag)
	        	playSound(Global.ENTER_PUB_SOUND);
	    }
	}
	
	public void addBoozer()
	{
		BoozerInfo boozerInfo;    
	    BoozerAppearInfo boozerAppearInfo;

	    int i;
	    for (i = 0; i < Global.g_levelMgr.m_levelInfo.m_apparBoozerArray.size(); i ++)
	    {
	    	boozerInfo  = BoozerInfo.createBoozerInfo(); 
	        boozerAppearInfo = Global.g_levelMgr.m_levelInfo.m_apparBoozerArray.get(i);
	        boozerInfo.setBoozerInfo( boozerAppearInfo.nPipeNum, boozerAppearInfo.nBoozerKind);
	        
	       // boozerInfo.setScale(2.0f);
	        boozerInfo.setScaleX(2.0f*scaled_width);
	        boozerInfo.setScaleY(2.0f*scaled_height);
	       
	        boozerInfo.setPosition(boozerInfo.m_pt.x, boozerInfo.m_pt.y);
	        if (boozerAppearInfo.nPipeNum == 0)
            	addChild(boozerInfo, GameConfig.BOOZER_LAYER1);
            else if (boozerAppearInfo.nPipeNum == 1)
                addChild(boozerInfo, GameConfig.BOOZER_LAYER2);
            else if (boozerAppearInfo.nPipeNum == 2)
                addChild(boozerInfo, GameConfig.BOOZER_LAYER3);
            else
                addChild(boozerInfo, GameConfig.BOOZER_LAYER4);
	        boozerInfo.setVisible(false);
	        CCTextureCache.sharedTextureCache().removeTexture(boozerInfo.getTexture());

	        switch (boozerAppearInfo.nPipeNum) 
	        {
	            case 0:
	                m_boozerArray1.add(boozerInfo);
	                break;
	                
	            case 1:
	            	m_boozerArray2.add(boozerInfo);
	                break;
	                
	            case 2:
	            	m_boozerArray3.add(boozerInfo);
	                break;
	                
	            case 3:
	            	m_boozerArray4.add(boozerInfo);
	                break;
	                
	            default:
	                break;
	        }
	        
	       
	    }
	}
	
	public void addWaiter()
	{
		if (m_waiterInfo == null)
	    	return;

	   // m_waiterInfo.setScale(2.0f);
	    m_waiterInfo.setScaleX(2.0f*scaled_width);
	    m_waiterInfo.setScaleY(2.0f*scaled_height);
	    addChild(m_waiterInfo, GameConfig.WAITER_LAYER);
	    CCTextureCache.sharedTextureCache().removeTexture(m_waiterInfo.getTexture());
	   
	}
	
	public void addBonus()
	{
		addChild(m_bonusInfo, GameConfig.WAITER_LAYER);
		CCTextureCache.sharedTextureCache().removeTexture(m_bonusInfo.getTexture());

	}
	
	public void addBeer(int npose, int nBeerState, int nX)
	{
		BeerInfo beerInfo = BeerInfo.createBeerInfo();	    
	    beerInfo.setBeerInfo(nBeerState, npose, nX);
	    
	    beerInfo.setScaleX(scaled_width);
	    beerInfo.setScaleY(scaled_height);
	    beerInfo.setPosition(beerInfo.m_pt.x, beerInfo.m_pt.y);
	    
	    int nAddlayer = 0;
		   
	    if (npose == 0)
	    	 nAddlayer = GameConfig.BOOZER_LAYER1;
	    else if (npose == 1)
	    	nAddlayer = GameConfig.BOOZER_LAYER2;
	    else if (npose == 2)
	    	 nAddlayer = GameConfig.BOOZER_LAYER3;
	    else 
	    	 nAddlayer = GameConfig.BOOZER_LAYER4;
	    
        addChild(beerInfo, nAddlayer);
        beerInfo.setVisible(false);
        CCTextureCache.sharedTextureCache().removeTexture(beerInfo.getTexture());
        
	    switch (npose)
	    {
	        case 0:
	        	m_beerArray1.add(beerInfo);
	            break;            
	        case 1:
	            m_beerArray2.add(beerInfo);
	            break;
	        case 2:
	            m_beerArray3.add(beerInfo);
	            break;
	        case 3:
	            m_beerArray4.add(beerInfo);
	            break;
	            
	        default:
	            break;
	    }   
	}
	
	public final int MIN_TIP_BOOZER_NUM = 4;
	
	public void determineTipState(int nboozerState, int nPose)
	{
	    int nTipState;
	    
	    if (nboozerState < MIN_TIP_BOOZER_NUM)
	    {
//	        [self addTip:nPose :1];
	    }
	    else
	    {
	        nTipState = (int) (Math.random() * 100 % 2);        
	        addTip(nPose, nTipState);
	    }
	}
	
	public void addTip(int npose, int nTipState)
	{
		TipInfo tipInfo;
		    
	    tipInfo = TipInfo.createTipnfo();    
	    tipInfo.setTipInfo(nTipState, npose);
	    
	    int nAddlayer = 0;
	    switch (npose)
	    {
	        case 0:
	            m_tipArray1.add(tipInfo);
	            nAddlayer = GameConfig.BOOZER_LAYER1;
	            break;            
	        case 1:
	        	m_tipArray2.add(tipInfo);
	            nAddlayer = GameConfig.BOOZER_LAYER2;
	            break;
	        case 2:
	            m_tipArray3.add(tipInfo);
	            nAddlayer = GameConfig.BOOZER_LAYER3;
	            break;
	        case 3:
	            m_tipArray4.add(tipInfo);
	            nAddlayer = GameConfig.BOOZER_LAYER4;
	            break;
	            
	        default:
	            break;
	    }
	    
	    addChild(tipInfo, nAddlayer);
	    CCTextureCache.sharedTextureCache().removeTexture(tipInfo.getTexture());
	    
	    //error 3
	   // tipInfo.dealloc();
	   // System.gc();
	}
	
	public void dispBoozerPlay()
	{
		int i;
	    int nTemp;
	    BoozerInfo boozerInfo;

	    int nCount = 0;
	    int aTemp[] = new int[5];
	    
	    boolean fSetPro = false;
	    boolean fBonusSound = false;
	    
	    boolean fBoozerBlopSound = false;
	    
	    for (i = 0; i < m_boozerArray1.size(); i ++)
	    {
	        boozerInfo = m_boozerArray1.get(i);        
	        nTemp = boozerInfo.returnGameState();	        
	        if (nTemp == 1)
	        {
	            if (boozerInfo.m_fBeer)
	            {
	                addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);
	                aTemp[nCount] = i;
	                nCount ++;
	            }
	            else 
	            {
	            	aTemp[nCount] = i;
	                nCount ++;
	            }
	            
	            if (boozerInfo.getLoopCount() == 1)
	            {
	                m_bonusInfo.initBonusState();
	                m_fBonusPlay = true;
	                calcScore(GameConfig.ONE_SHORT_SCORE);
	                fBonusSound = true;
	            }
	                
	            Global.g_levelMgr.descreaseLeftBoozerNum(0);
	            determineTipState(boozerInfo.m_nBoozerState, 0);
	            
	            fSetPro = true;
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;	            
	            m_endBoozer = m_boozerArray1.get(i);
	            m_fBoozerEnd = true;	            
	            dispEndPlay();
	            return;
	        }
	        else if (nTemp == 3)
	        {
	            addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);    
	            fBoozerBlopSound = true;
	          
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_boozerArray1.get(aTemp[i]), true);
	    	m_boozerArray1.get(aTemp[i]).dealloc();
	    	
	        m_boozerArray1.remove(aTemp[i]);
	    }

	    nCount = 0;
	    
//	    memset(aTemp, 0, sizeof(int) * 5);
	    for(int k = 0; k < 5; k ++)
	    	aTemp[k] = 0;
	    
	    for (i = 0; i < m_boozerArray2.size(); i ++)
	    {
	        boozerInfo = m_boozerArray2.get(i);	        
	        nTemp = boozerInfo.returnGameState();	        
	        if (nTemp == 1)
	        {
	            if (boozerInfo.m_fBeer)
	            {
	                addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);
	                aTemp[nCount] = i;
	                nCount ++;
	            }
	            else 
	            {
	            	aTemp[nCount] = i;
	                nCount ++;
	            }
	            
	            if (boozerInfo.getLoopCount() == 1)
	            {
	                m_bonusInfo.initBonusState();
	                m_fBonusPlay = true;
	                calcScore(GameConfig.ONE_SHORT_SCORE);
	                fBonusSound = true;
	            }

	            Global.g_levelMgr.descreaseLeftBoozerNum(1);
	            determineTipState(boozerInfo.m_nBoozerState, 1);	            
	            fSetPro = true;
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;	            
	            m_endBoozer = m_boozerArray2.get(i);
	            m_fBoozerEnd = true;	            
	            dispEndPlay();
	            return;
	        }
	        else if (nTemp == 3)
	        {
	            addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);
	            fBoozerBlopSound = true;
	          
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_boozerArray2.get(aTemp[i]), true);
	    	m_boozerArray2.get(aTemp[i]).dealloc();
	    	
	        m_boozerArray2.remove(aTemp[i]);
//	    	m_boozerArray2.get(i).dealloc();
//	        m_boozerArray2.remove(i);
	    }
	    
	    nCount = 0;
	    
	    for(int k = 0; k < 5; k ++)
	    	aTemp[k] = 0;
	    
	    for (i = 0; i < m_boozerArray3.size(); i ++)
	    {
	        boozerInfo = m_boozerArray3.get(i);	        
	        nTemp = boozerInfo.returnGameState();	        
	        if (nTemp == 1)
	        {
	            if (boozerInfo.m_fBeer)
	            {
	                addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);
	                aTemp[nCount] = i;
	                nCount ++;
	            }
	            else 
	            {
	            	aTemp[nCount] = i;
	                nCount ++;
	            }
	            
	            if (boozerInfo.getLoopCount() == 1)
	            {
	                m_bonusInfo.initBonusState();
	                m_fBonusPlay = true;
	                calcScore(GameConfig.ONE_SHORT_SCORE);
	                fBonusSound = true;
	            }
	            
	            Global.g_levelMgr.descreaseLeftBoozerNum(2);
	            determineTipState(boozerInfo.m_nBoozerState, 2);
	            
	            fSetPro = true;
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;	            
	            m_endBoozer = m_boozerArray3.get(i);
	            m_fBoozerEnd = true;	            
	            dispEndPlay();
	            return;
	        }
	        else if (nTemp == 3)
	        {
	            addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);
	            fBoozerBlopSound = true;
	          
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_boozerArray3.get(aTemp[i]), true);
	    	m_boozerArray3.get(aTemp[i]).dealloc();//ksi
	    	
	        m_boozerArray3.remove(aTemp[i]);
	    }
	    
	    nCount = 0;
	    
	    for(int k = 0; k < 5; k ++)
	    	aTemp[k] = 0;
	    
	    for (i = 0; i < m_boozerArray4.size(); i ++)
	    {
	        boozerInfo = m_boozerArray4.get(i);	        
	        nTemp = boozerInfo.returnGameState();	        
	        if (nTemp == 1)
	        {
	            if (boozerInfo.m_fBeer)
	            {
	                addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE, (int) boozerInfo.m_pt.x);
	                aTemp[nCount] = i;
	                nCount ++;
	            }
	            else 
	            {
	            	aTemp[nCount] = i;
	                nCount ++;
	            }
	            
	            if (boozerInfo.getLoopCount() == 1)
	            {
	                m_bonusInfo.initBonusState();
	                m_fBonusPlay = true;
	                calcScore(GameConfig.ONE_SHORT_SCORE);
	                fBonusSound = true;
	            }
	            
	            Global.g_levelMgr.descreaseLeftBoozerNum(3);
	            determineTipState(boozerInfo.m_nBoozerState, 3);
	            
	            fSetPro = true;
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;	            
	            m_endBoozer = m_boozerArray4.get(i);
	            m_fBoozerEnd = true;	            
	            dispEndPlay();
	            return;
	        }
	        else if (nTemp == 3)
	        {
	            addBeer(boozerInfo.getAppearPlace(), GameConfig.NON_BEER_STATE , (int) boozerInfo.m_pt.x);
	            fBoozerBlopSound = true;
	         
	        }
	    }    
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	//error 2
	    	removeChild(m_boozerArray4.get(aTemp[i]), true);
	    	m_boozerArray4.get(aTemp[i]).dealloc();
	    	
	        m_boozerArray4.remove(aTemp[i]);
	    }
	    
	    if (fSetPro)
	        setGamePro(Global.g_levelMgr.getGamePro());
	    
	    if (fBonusSound)
	    	playSound(Global.BONUS_SOUND);
	    if (fBoozerBlopSound)
	    	playSound(Global.BOOZER_BLOP_SOUND);
	    //System.gc();
	}
	
	public void dispBeerPlay()
	{
		BeerInfo beerInfo;
	    int i;
	    int nTemp;
	    boolean fWaiter = false;	    
	    boolean fBeerSound = false;
	    boolean fMugCatch = false;
	    
	    for (i = 0; i < m_beerArray1.size(); i ++)
	    {
	        beerInfo = m_beerArray1.get(i);        
	        nTemp = beerInfo.getBeerInfo();

	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 0)
	            {
	            	 m_beerArray1.get(i).dealloc();
	                m_beerArray1.remove(i);
	                i--;
	                
	                fWaiter = true;
	                
	                calcScore(GameConfig.NORMAL_SCORE);
	                fMugCatch = true;
	                
	                m_nBeerNum++;
//		                NSLog(@" Beer Num %d", m_nBeerNum);
	            }
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;
	            fBeerSound = true;
	        }
	    }
	    
	    for (i = 0; i < m_beerArray2.size(); i ++)
	    {
	        beerInfo = m_beerArray2.get(i);        
	        nTemp = beerInfo.getBeerInfo();
	        
	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 1)
	            {
	            	m_beerArray2.get(i).dealloc();
	                m_beerArray2.remove(i);
	                i--;
	                
	                fWaiter = true;
	                
	                calcScore(GameConfig.NORMAL_SCORE);
	                fMugCatch = true;
	                m_nBeerNum++;
//		                NSLog(@" Beer Num %d", m_nBeerNum);
	            }
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;
	            fBeerSound = true;
	        }
	    }
	    
	    for (i = 0; i < m_beerArray3.size(); i ++)
	    {
	        beerInfo = m_beerArray3.get(i);        
	        nTemp = beerInfo.getBeerInfo();
	        
	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 2)
	            {
	            	m_beerArray3.get(i).dealloc();
	                m_beerArray3.remove(i);
	                i--;
	                
	                fWaiter = true;
	                
	                calcScore(GameConfig.NORMAL_SCORE);
	                fMugCatch = true;
	                m_nBeerNum++;
//		                NSLog(@" Beer Num %d", m_nBeerNum);
	            }
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;
	            fBeerSound = true;
	        }
	    }
	    
	    for (i = 0; i < m_beerArray4.size(); i ++)
	    {
	        beerInfo = m_beerArray4.get(i);        
	        nTemp = beerInfo.getBeerInfo();
	        
	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 3)
	            {
	            	m_beerArray4.get(i).dealloc();
	                m_beerArray4.remove(i);
	                i--;
	                
	                fWaiter = true;
	                
	                calcScore(GameConfig.NORMAL_SCORE);
	                fMugCatch = true;
	                m_nBeerNum++;
//		                NSLog(@" Beer Num %d", m_nBeerNum);
	            }
	        }
	        else if (nTemp == 2)
	        {
	            m_fGameOver = true;
	            fBeerSound = true;
	        }
	    }
	    
	    if (fWaiter)
	    {
	        if (m_fWaiterPlay)
	            return;
	        
	        if (m_fWaiterStrong)
	        {
	            m_waiterInfo.setWaiterState(GameConfig.GET_WAITER_SPECIAL_STATE);
	            m_fWaiterPlay = true;            
	        }
	        else 
	        {
	            m_waiterInfo.setWaiterState(GameConfig.GET_WAITER_NORMAL_STATE);
	            m_fWaiterPlay = true;
	        }
	    }
	    
	    if (fBeerSound)
	    	playSound(Global.FAIL_MUGBREAK_SOUND);
	    
	    if (fMugCatch)
	    	playSound(Global.CATCH_MUG_SOUND);
	   // System.gc();
	}
	
	public void dispTipPlay()
	{
		TipInfo tipInfo;
	    int i;
	    int nTemp;
	    
	    int nType = 0;
	    
	    boolean fTipFail = false;
	    
	    for (i = 0; i < m_tipArray1.size(); i ++)
	    {
	        tipInfo = m_tipArray1.get(i);        
	        nTemp = tipInfo.getTipInfo();
	        
	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 0)
	            {
	                if (tipInfo.m_nTipState == GameConfig.COIN_GET_STATE)
	                    nType = 1;
	                else 
	                    nType = 2;
	            }
	            else 
	            {
	                fTipFail = true;
	            }
	            removeChild(m_tipArray1.get(i), true);
	            m_tipArray1.get(i).dealloc();
	            m_tipArray1.remove(i);
	            i--;
	        }
	    }
	    
	    for (i = 0; i < m_tipArray2.size(); i ++)
	    {
	        tipInfo = m_tipArray2.get(i);        
	        nTemp = tipInfo.getTipInfo();
	        
	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 1)
	            {
	                if (tipInfo.m_nTipState == GameConfig.COIN_GET_STATE)
	                    nType = 1;
	                else 
	                    nType = 2;
	            }
	            else 
	            {
	                fTipFail = true;
	            }
	            removeChild(m_tipArray2.get(i), true);
	            m_tipArray2.get(i).dealloc();
	            m_tipArray2.remove(i);
	            i--;
	        }
	    }
	    
	    for (i = 0; i < m_tipArray3.size(); i ++)
	    {
	        tipInfo = m_tipArray3.get(i);        
	        nTemp = tipInfo.getTipInfo();
	        
	        if (nTemp == 1)
	        {
	            if (m_waiterInfo.getWaiterPoseState() == 2)
	            {
	                if (tipInfo.m_nTipState == GameConfig.COIN_GET_STATE)
	                    nType = 1;
	                else 
	                    nType = 2;
	            }
	            else 
	            {
	                fTipFail = true;
	            }
	            removeChild(m_tipArray3.get(i), true);
	            m_tipArray3.get(i).dealloc();
	            m_tipArray3.remove(i);
	            i--;
	        }
	    }
	    
	    for (i = 0; i < m_tipArray4.size(); i ++)
	    {
	        tipInfo = m_tipArray4.get(i);        
	        nTemp = tipInfo.getTipInfo();
	        
	        //if (nTemp == 1)
	        {
	            if (nTemp == 1)
	            {
	                if (m_waiterInfo.getWaiterPoseState() == 3)
	                {
	                    if (tipInfo.m_nTipState == GameConfig.COIN_GET_STATE)
	                        nType = 1;
	                    else 
	                        nType = 2;
	                }
	                else 
	                {
	                    fTipFail = true;
	                }
	                
	                removeChild(m_tipArray4.get(i), true);
	                m_tipArray4.get(i).dealloc();
	                m_tipArray4.remove(i);
	                i--;
	            }
	        }
	    }
	    
	    if (fTipFail)
	    	playSound(Global.TIPFAIL_SOUND);
	    
	    if (nType != 0)
	    {
	        if (m_fWaiterPlay)
	            return;
	        
	        if (m_fWaiterPlay)
	        {
	            if (m_fWaiterStrong)
	            {
	                
	            }
	            else
	            {
	                if (m_waiterInfo.m_nWaiterState == GameConfig.BEER_WAITER_STATE)
	                {
	                    setStrongWaiter();
	                    
	                    PipeIndexInfo pipeInfo;        
	                    
	                    if (m_beerArray.size() > 0)
	                    {
	                    	pipeInfo = m_beerArray.get(0);	                        
	                        addBeer(pipeInfo.nIdx, GameConfig.BEER_STATE, (int) m_waiterInfo.m_pt.x);
	                        m_beerArray.remove(0);
	                        System.gc();
	                    }                    
	                }
	            }
	        }
	        else 
	        {
	            if (nType == 1)
	            {
	                if (m_fWaiterStrong)
	                    m_waiterInfo.setWaiterState(GameConfig.GET_WAITER_SPECIAL_STATE);
	                else 
	                    m_waiterInfo.setWaiterState(GameConfig.GET_WAITER_NORMAL_STATE);
	                
	                m_fWaiterPlay = true;
	                
	                calcScore(GameConfig.TIP_SCORE);
	                
	                playSound(Global.CATCH_COINS_SOUND);
	            }
	            else
	            {
	                if (m_fWaiterStrong)
	                {
	                    m_waiterInfo.setWaiterState(GameConfig.GET_WAITER_SPECIAL_STATE);
	                    m_fWaiterPlay = true;
	                }
	                else 
	                    setStrongWaiter();

	                playSound(Global.CATCH_TURBO_SOUND);
	            }
	        }        
	    }
	   // System.gc();
	}
	
	public void loadLamp()
	{
		int i, j;
	    String str;
	
	    for (i = 0; i < 4; i ++)
	    {
	        for (j = 0; j < 2; j ++)
	        {
	            str = String.format("gfx/LightBulbBar_0%d.png", j + 1); 
	            
	            m_lampSprite[i][j] = CCSprite.sprite(str);
	            m_lampSprite[i][j].setScaleX(scaled_width); m_lampSprite[i][j].setScaleY(scaled_height);
	            
	            if (i == 0)
	                addChild(m_lampSprite[i][j], GameConfig.BOOZER_LAYER1);
	            else if (i == 1)
	                addChild(m_lampSprite[i][j], GameConfig.BOOZER_LAYER2);
	            else if (i == 2)
	                addChild(m_lampSprite[i][j], GameConfig.BOOZER_LAYER3);
	            else if (i == 3)
	                addChild(m_lampSprite[i][j], GameConfig.BOOZER_LAYER4);
	            
	            CCTextureCache.sharedTextureCache().removeTexture(m_lampSprite[i][j].getTexture());
	            if (j == 1)
	                m_lampSprite[i][j].setVisible(true);
	            else
	                m_lampSprite[i][j].setVisible(false);
	        }
	    }
	    
	    for (i = 0; i < 2; i ++)
	    {
	        m_lampSprite[0][i].setPosition(203 * g_rX, Global.getCocosY(104 * g_rY));
	        m_lampSprite[1][i].setPosition(167 * g_rX, Global.getCocosY(204 * g_rY));
	        m_lampSprite[2][i].setPosition(128 * g_rX, Global.getCocosY(315 * g_rY));
	        m_lampSprite[3][i].setPosition(86 * g_rX, Global.getCocosY(436 * g_rY));
	    }    
	}
	
	public void searchBoozerBeerState()
	{
		int i, j;
		    
	    BeerInfo beerInfo;
	    BoozerInfo boozerInfo;
	    
	    int aTemp[] = new int[5];
	    
	    int nCount = 0;
	    
	    boolean fBoozerDrinkAlienSound = false;
	    
	    for (i = 0; i < m_beerArray1.size(); i ++)
	    {
	        beerInfo = m_beerArray1.get(i);     
	        
	        if (beerInfo.getBeerState() == GameConfig.NON_BEER_STATE)
	            continue;
	        
	        for (j = 0; j < m_boozerArray1.size(); j++)
	        {
	            boozerInfo = m_boozerArray1.get(j);            
	            
	            if (boozerInfo.m_fBeer)
	                continue;
	               
	            if (CGRect.intersects(beerInfo.getSpriteRect(), boozerInfo.getSpriteRect()))
	            {
	                aTemp[nCount] = i;
	                nCount ++;
	                
	                boozerInfo.setBeerState();
	                fBoozerDrinkAlienSound = true;
	                break;
	            }
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_beerArray1.get(aTemp[i]), true);
	    	m_beerArray1.get(aTemp[i]).dealloc();
	    	m_beerArray1.remove(aTemp[i]);
	    }
	    for(int k = 0; k < 5; k ++)
	    	aTemp[k] = 0;
	    nCount = 0;
	    
	    for (i = 0; i < m_beerArray2.size(); i ++)
	    {
	        beerInfo = m_beerArray2.get(i);                
	        
	        if (beerInfo.getBeerState() == GameConfig.NON_BEER_STATE)
	            continue;
	        
	        for (j = 0; j < m_boozerArray2.size(); j++)
	        {
	            boozerInfo = m_boozerArray2.get(j);            
	            
	            if (boozerInfo.m_fBeer)
	                continue;
	            
	            if (CGRect.intersects(beerInfo.getSpriteRect(), boozerInfo.getSpriteRect()))
	            {
	                aTemp[nCount] = i;
	                nCount ++;
	                
	                boozerInfo.setBeerState();
	                fBoozerDrinkAlienSound = true;
	                break;
	            }
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_beerArray2.get(aTemp[i]), true);
	    	m_beerArray2.get(aTemp[i]).dealloc();
	    	m_beerArray2.remove(aTemp[i]);
	    }
	    
	    for(int k = 0; k < 5; k ++)
	    	aTemp[k] = 0;

	    nCount = 0;
	    
	    for (i = 0; i < m_beerArray3.size(); i ++)
	    {
	        beerInfo = m_beerArray3.get(i);                
	        
	        if (beerInfo.getBeerState() == GameConfig.NON_BEER_STATE)
	            continue;
	        
	        for (j = 0; j < m_boozerArray3.size(); j++)
	        {
	            boozerInfo = m_boozerArray3.get(j);
	            
	            if (boozerInfo.m_fBeer)
	                continue;
	            
	            if (CGRect.intersects(beerInfo.getSpriteRect(), boozerInfo.getSpriteRect()))
	            {
	                aTemp[nCount] = i;
	                nCount ++;
	                
	                boozerInfo.setBeerState();
	                fBoozerDrinkAlienSound = true;
	                break;
	            }
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_beerArray3.get(aTemp[i]), true);
	    	m_beerArray3.get(aTemp[i]).dealloc();
	    	m_beerArray3.remove(aTemp[i]);
	    }
	    
	    for(int k = 0; k < 5; k ++)
	    	aTemp[k] = 0;
	    nCount = 0;
	    
	    for (i = 0; i < m_beerArray4.size(); i ++)
	    {
	        beerInfo = m_beerArray4.get(i);                
	        
	        if (beerInfo.getBeerState() == GameConfig.NON_BEER_STATE)
	            continue;
	        
	        for (j = 0; j < m_boozerArray4.size(); j++)
	        {
	            boozerInfo = m_boozerArray4.get(j);            
	            
	            if (boozerInfo.m_fBeer)
	                continue;
	            
	            if (CGRect.intersects(beerInfo.getSpriteRect(), boozerInfo.getSpriteRect()))
	            {
	                aTemp[nCount] = i;
	                nCount ++;
	                
	                boozerInfo.setBeerState();
	                fBoozerDrinkAlienSound = true;
	                break;
	            }
	        }
	    }
	    
	    for (i = 0; i < nCount; i ++)
	    {
	    	removeChild(m_beerArray4.get(aTemp[i]), true);
	    	m_beerArray4.get(aTemp[i]).dealloc();
	    	m_beerArray4.remove(aTemp[i]);
	    }
	    
	    if (fBoozerDrinkAlienSound)
	    	playSound(Global.BOOZER_DRINK_SOUND);
	}
	
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) 
	{
		CGPoint pt = CGPoint.ccp(event.getX(), event.getY());
		
		CGRect m_rect;	    
        m_rect = CGRect.make(223 * g_rX, Global.getCocosY(626 * g_rY), 623 * g_rX, 433 * g_rY);
       
		if (CGRect.containsPoint(m_rect, pt) && m_fMessageLoad)
		   releaseMessage();
		return CCTouchDispatcher.kEventHandled;
	}
	
	public void gameOver()
	{
	    if (m_fGameOverSet)
	        return;
	    
	    m_fGameOverSet = true;	    
	    playSound(Global.FAIL_BOOZERWAITER_SOUND);	    
	    unscheduleAllSelectors();
	    m_nLive --;
	    if (m_nLive < 0)
	    {
	        Global.g_nMessageState = GameConfig.GAME_OVER_STATE;
	        initMessage();
	    }
	    else 
	    {
	    			    
	        Global.g_nMessageState = GameConfig.RESTART_STATE;        
	        initMessage();
	    }
	}
	
	public void setGamePro(int nProNum)
	{
		if (nProNum > 100)
	        nProNum = 100;
	
	   // int nWidth = m_nProWidth * nProNum / 100;
	    
		m_proImg.setScaleX(nProNum * scaled_width/100);//nWidth//m_proImg.getScaleX() + 
		
//		CCSize winSize = Director.sharedDirector().winSize();
		m_proImg.setPosition(this.LEVEL_PRO_X * g_rX - m_proImg.getTexture().getWidth()*scaled_width/2 + m_proImg.getTexture().getWidth()*m_proImg.getScaleX()/2 , Global.getCocosY(40 * g_rY));

		
//	    m_proImg = Sprite.sprite("gfx/T_line_top.png");
//	    m_proImg.setPosition((232 + nWidth) * g_rX, Global.getCocosY(40 * g_rY));
//	    m_proImg.setScaleX(scaled_width * nProNum / 100); m_proImg.setScaleY(scaled_height);
	    
//	    addChild(m_proImg, GameConfig.MESSAGE_LAYER + 1);
		    
	}
	
	public boolean returnGameDone()
	{
		if (m_beerArray1.size() > 0)
	        return false;    
	    if (m_beerArray2.size() > 0)
	        return false;    
	    if (m_beerArray3.size() > 0)
	        return false;    
	    if (m_beerArray4.size() > 0)
	        return false;
	    
	    if (m_boozerArray1.size() > 0)
	        return false;    
	    if (m_boozerArray2.size() > 0)
	        return false;
	    if (m_boozerArray3.size() > 0)
	        return false;
	    if (m_boozerArray4.size() > 0)
	        return false;
	    
	    if (m_tipArray1.size() > 0)
	        return false;	    
	    if (m_tipArray2.size() > 0)
	        return false;	    
	    if (m_tipArray3.size() > 0)
	        return false;	    
	    if (m_tipArray4.size() > 0)
	        return false;
	    
	    if (m_fWaiterPlay)
	        return false;
	    
	    return true;
	}
	
	public void gameComplete()
	{
		unscheduleAllSelectors();
		playSound(Global.APPLAUSE_OYE_SOUND);

		Global.g_nRealLevelNum ++;
		 if (Global.g_nRealLevelNum > Global.g_nCompleteLevelNum)
	            Global.g_nCompleteLevelNum = Global.g_nRealLevelNum;       
	    Global.g_nMessageState = GameConfig.NEXT_LEVEL_STATE;
	    
	    initMessage();
	}
	
	public void setStrongWaiter()
	{
	  if (m_fWaiterStrong)
	        return;
	    
	    stopAction();
	    stopWaiterAction();
	    stopBeerAction();
	    stopBonusAction();
	    
	    m_fWaiterStrong = true;
	    
	    startAction();
	    
	    boolean fFlag;
	    
	    m_waiterInfo.setWaiterState(GameConfig.LIGHTED_WAITER_STATE);
	    startWaiterAction();
	    m_fWaiterPlay = true;
	    
	    fFlag = m_fBeerPlay;
	    
	    startBeerAction();
	    
	    m_fBeerPlay = fFlag;
	    startBonusAction();    
	}
	
	public void dispNameAlertView()
	{
		saveScoreInformation();	
	}
	

	public void saveScoreInformation()
	{
		SharedPreferences p = CCDirector.sharedDirector().getActivity().getSharedPreferences("Info", 0);
		int nPlayer = p.getInt("Count", 0);
		Editor editor = p.edit();
		
		String str;
		int nScore;
		if (nPlayer >= 10)
		{
			for (int i=0; i <= nPlayer-2; i++)
			{
				str = String.format("score%d", i+1);
				nScore = p.getInt(str, 0);
				editor.putInt(String.format("score%d", i), nScore);
			}
			editor.putInt(String.format("score%d", nPlayer-1), m_nScore);
		}
		else
		{
			editor.putInt(String.format("score%d", nPlayer), m_nScore);
			editor.putInt("Count", nPlayer + 1);
		}
		editor.commit();
	}
	
	
	public void dispEndPlay()
	{
		int nState;
	    
	    if (m_fGameOver)
	    {
	        if (m_fBoozerEnd)
	        {
	            BoozerInfo info = m_endBoozer;
	            
	            nState = info.dispAngryBoozer();
	            
	            if (nState == 1)
	                m_fEndState = true;
	            else if (nState == 2) // sound effect
	            {
	            	playSound(Global.BOOZER_ANGRY_SOUND);
	            }
	            
	            if (!m_fEndWaiterState)
	            {
	                if (m_fWaiterPlay || m_fBonusPlay)
	                    return;
	                else 
	                {
	                    m_waiterInfo.setWaiterState(GameConfig.FAIL_WAITER_STATE);    
	                    m_fWaiterPlay = true;
	                    
	                    m_fEndWaiterState = true;
	                }
	            }
	        }
	        else
	        {
	            if (m_fWaiterPlay || m_fBonusPlay)
	                return;
	            else 
	            {
	                m_waiterInfo.setWaiterState(GameConfig.FAIL_WAITER_STATE);    
	                m_fWaiterPlay = true;
	            }            
	        }
	    }
	    else  // Success
	    {
	        if (m_fWaiterPlay || m_fBonusPlay)
	            return;
	        else 
	        {
	            m_waiterInfo.setWaiterState(GameConfig.WELCOME_WAITER_STATE);
	            m_fWaiterPlay = true;
	        }
	    }
	}
	
	public void calcScore(int nstate)
	{
		int nScore = 0;
	    
	    if (nstate == GameConfig.NORMAL_SCORE)
	    {
	        nScore = 10;
	    }
	    else if (nstate == GameConfig.TIP_SCORE)
	    {
	        nScore =  5 * Global.g_nRealLevelNum;
	    }
	    else if (nstate == GameConfig.ONE_SHORT_SCORE)
	    {
	        nScore = 500;        
	    }
	    else if (nstate == GameConfig.NEXT_LEVEL_SCORE)
	    {
	        if (Global.g_nRealLevelNum == 1)
	            return;
	        
	        nScore = ((Global.g_nRealLevelNum - 1) * 50);
	    }
	    
	    if (m_fWaiterStrong)
	        nScore *= 2;
	    
	    m_nScore += nScore;
	}
	public void setBackSoundIdx()
	{
		//player[m_nBackSoundIdx].delegate = self;
	}
	
	
	
	public void soundPlay()
	{
		 if (!m_fSound)
		        return;
		 if (Global.m_pMediaPlayer == null)
			 return;
		
		 if (Global.m_pMediaPlayer[m_nBackSoundIdx] != null)
		 {
			 Global.m_pMediaPlayer[m_nBackSoundIdx].start();
		 }
	 
	    m_fPianoPlay = true;
	    setPianoState(GameConfig.PLAY_PIANO_STATE);

	    m_fGuitarPlay = true;   
	}
	
	public void soundStop()
	{
		if (Global.m_pMediaPlayer == null)
			return;
		
		if (Global.m_pMediaPlayer[m_nBackSoundIdx] != null)
			Global.m_pMediaPlayer[m_nBackSoundIdx].pause();
		
	    m_fGuitarPlay = false;    
	    m_fPianoPlay = true;
	    
	    setPianoState(GameConfig.UNHAPPY_PIANO_STATE);    
	}
	
	public void onPauseGame(Object sender)
	{
		if (!m_fGamePlay)
		      return;
		    
	    m_fPause = true;    
	    Global.g_nMessageState = GameConfig.PAUSED_STATE;    
	    
	    initMessage();
	}
	
	public void onSoundOnOff(Object sender)
	{
		m_fSound = !m_fSound;
		    
	    if (!m_fGamePlay || !m_fSetSound)
	        return;
	    
	    if (m_fSound)
	        soundPlay();
	    else 
	        soundStop();
	}
	
	public void onDown(Object sender)
	{
		if (!m_fGamePlay)
	        return;
	    
	    if (m_waiterInfo.m_nWaiterState == GameConfig.GAIN_CUP_WAITER_STATE)
	    {
	        if (!m_waiterInfo.m_fEndThred)
	            return;
	    }
	    
	    m_waiterInfo.moveWaiterState(GameConfig.DOWN_WAITER_STATE);
	}
	
	public void onUp(Object sender)
	{
		if (!m_fGamePlay)
	        return;
	    
	    if (m_waiterInfo.m_nWaiterState == GameConfig.GAIN_CUP_WAITER_STATE)
	    {
	        if (!m_waiterInfo.m_fEndThred)
	            return;
	    }
	    
	    m_waiterInfo.moveWaiterState(GameConfig.UP_WAITER_STATE);
	}
	
	public void onCollection(Object sender)
	{
		if (m_fWaiterPlay || !m_fGamePlay || m_fWaiterStrong)
	        return;
	    
	    int nPipeNum = m_waiterInfo.getWaiterPoseState();
	    
	    int nCount = 0;
	    
	    ArrayList<BeerInfo> beerArray = null;
	    
	    switch (nPipeNum)
	    {
	        case 0:
	            nCount = m_beerArray1.size();            
	            beerArray = m_beerArray1;
	            break;

	        case 1:
	            nCount = m_beerArray2.size();            
	            beerArray = m_beerArray2;
	            break;

	        case 2:
	            nCount = m_beerArray3.size();            
	            beerArray = m_beerArray3;
	            break;

	        case 3:
	            nCount = m_beerArray4.size();
	            beerArray = m_beerArray4;
	            break;

	        default:
	            break;
	    }
	    
	    if (nCount == 0)
	        return;        
	  
	    BeerInfo beerInfo;
	    
	    boolean fFlag = false;
	    
	    boolean fLargeState = false;
	    boolean fMiddleState = false;
	    boolean fShortState = false;
	    
	    for (int i = 0; i < beerArray.size(); i ++)
	    {
	        beerInfo = beerArray.get(i);
	        
	        if (beerInfo.m_nBeerState != GameConfig.NON_BEER_STATE)
	            continue;
	        
	        if (beerInfo.m_pt.x < GameConfig.LARGE_TH_X * g_rX)
	            fLargeState = true;
	        else if (beerInfo.m_pt.x < GameConfig.MIDDLE_TH_X * g_rX)
	            fMiddleState = true;
	        else if (beerInfo.m_pt.x < GameConfig.SHORT_TH_X * g_rX)
	            fShortState = true;
	        
	        fFlag = true;
	    }
	    
	    if (!fFlag)
	        return;

	    if (fLargeState)
	        m_waiterInfo.setGainCupState(GameConfig.LARGE_GAIN_STATE);
	    else if (fMiddleState)
	        m_waiterInfo.setGainCupState(GameConfig.MIDDLE_GAIN_STATE);
	    else if (fShortState)
	        m_waiterInfo.setGainCupState(GameConfig.SHORT_GAIN_STATE);
	    else 
	        return;
	    
	    m_waiterInfo.setWaiterState(GameConfig.GAIN_CUP_WAITER_STATE);    
	    m_fWaiterPlay = true;
	}
	
	public void onBeer(Object sender)
	{
	   if (m_fWaiterPlay || !m_fGamePlay)
	        return;
	    
	    PipeIndexInfo pipeInfo = new PipeIndexInfo();
	    
	    m_beerArray.add(pipeInfo);
	    pipeInfo.nIdx = m_waiterInfo.getWaiterPoseState();
//	    pipeInfo release];

	    if (m_fWaiterStrong)
	        m_waiterInfo.setWaiterState(GameConfig.LIGHT_BEER_STATE);
	    else 
	        m_waiterInfo.setWaiterState(GameConfig.BEER_WAITER_STATE);
	   
	    playSound(Global.DROUGH_BEER_SOUND);
	    m_fWaiterPlay = true;
	}
	
	
	public void onExitGame(Object sender)
	{
		saveScoreInformation();
		soundStop();
		CCScene scene = CCScene.node();
	     scene.addChild(new MenuLayer());
	     CCDirector.sharedDirector().replaceScene(scene);
	    removeAllChildren(true);
	    CCTextureCache.sharedTextureCache().removeUnusedTextures();
	    
	}
}
