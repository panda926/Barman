package org.barman;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class BoozerInfo extends CCSprite{
	protected BoozerInfo(CCTexture2D tex) {
		super(tex);
	}

	public static BoozerInfo createBoozerInfo() 
	{
		CCTexture2D tex= CCTextureCache.sharedTextureCache().addImage("gfx/Boozer1_01.png");
		BoozerInfo newBoozer = new BoozerInfo(tex);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
       return newBoozer;
	}
	
	private final int BOOZER_BACK_X_STEP = 20;
	private final int BOOZER_X_STEP = 12;
	private final int BOOZER_WIDTH = 141;
	private final int BOOZER_HEIGTH = 158;	    
	CGPoint m_pt;	    
    int m_nIdx;
    int m_nLoopCount;
    int m_nAppearPlace; // 0 ...3
    int m_nBoozerState;
    int m_nEndIdx;    
    boolean m_fBeer; // yes beer state: no normal state  
    
    boolean m_fActive;    
    int m_nTempIdx;
    int m_nTmpLoopCount;
    
	public void setBoozerInfo(int nAppearPlace, int nBoozerState)
	{
		m_nIdx = GameConfig.BOOZER_BEER_NUM;
        m_nLoopCount = 0;
        m_fBeer = false;     
        m_fActive  = false;
        m_nEndIdx = GameConfig.BOOZER_BEER_NUM;        
        m_nTempIdx = 0;
        m_nTmpLoopCount = 0; 
        
	    m_nBoozerState = nBoozerState;
	    m_nAppearPlace = nAppearPlace;	    
	    switch (nAppearPlace)
	    {
	        case 0:
	            m_pt = CGPoint.ccp(GameConfig.FIRST_BOOZER_X * Global.g_rX, Global.getCocosY(GameConfig.FIRST_BOOZER_Y * Global.g_rY));
	            break;
	        case 1:
	            m_pt = CGPoint.ccp(GameConfig.SECOND_BOOZER_X * Global.g_rX, Global.getCocosY(GameConfig.SECOND_BOOZER_Y * Global.g_rY));
	            break;
	        case 2:
	            m_pt = CGPoint.ccp(GameConfig.THIRD_BOOZER_X * Global.g_rX, Global.getCocosY(GameConfig.THIRD_BOOZER_Y * Global.g_rY));
	            break;
	        case 3:
	            m_pt = CGPoint.ccp(GameConfig.FORTH_BOOZER_X * Global.g_rX, Global.getCocosY(GameConfig.FORTH_BOOZER_Y * Global.g_rY));
	            break;
	        default:
	            break;
	    }
	    
	}
	
	public int setBeerState()
	{
		 m_nLoopCount++;    
		 m_fBeer = true;
		 m_nIdx = 0;
		 
		 return returnGameState();
	}
	
	public String getSpriteString(int nIdx)
	{
		String str;
		if ((nIdx + 1) < 10)
 	       str = String.format("gfx/Boozer%d_0%d.png", m_nBoozerState + 1, nIdx+ 1); 
 	   	else 
 	       str = String.format("gfx/Boozer%d_%d.png", m_nBoozerState + 1, nIdx + 1);
		return str;
	}
	public void setActionSprite(int nIdx)
	{
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage(getSpriteString(nIdx));
		setTexture(tex);
		setVisible(true);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
	}
	public int returnGameState()
	{
		  if (!m_fActive)
		        return 0;
		    
		    nonVisibleSprites();
		    
		    switch (m_nAppearPlace)
		    {
		        case 0:
		            if (m_pt.x < GameConfig.FIRST_BOOZER_X * Global.g_rX || m_pt.x > GameConfig.FIRST_BOOZER_LAST_X * Global.g_rX)
		            {
		                if (m_pt.x < GameConfig.FIRST_BOOZER_X * Global.g_rX)
		                    return 1;
		                else 
		                    return 2;
		            }
		            
		            break;
		        case 1:
		            if (m_pt.x < GameConfig.SECOND_BOOZER_X * Global.g_rX || m_pt.x > GameConfig.SECOND_BOOZER_LAST_X  * Global.g_rX)
		            {
		                if (m_pt.x < GameConfig.SECOND_BOOZER_X * Global.g_rX)
		                    return 1;
		                else 
		                    return 2;
		            }
		            
		            break;
		        case 2:
		            if (m_pt.x < GameConfig.THIRD_BOOZER_X  * Global.g_rX|| m_pt.x > GameConfig.THIRD_BOOZER_LAST_X * Global.g_rX)
		            {
		                if (m_pt.x < GameConfig.THIRD_BOOZER_X * Global.g_rX)
		                    return 1;
		                else 
		                    return 2;
		            }
		            
		            break;
		        case 3:
		            if (m_pt.x < GameConfig.FORTH_BOOZER_X * Global.g_rX || m_pt.x > GameConfig.FORTH_BOOZER_LAST_X * Global.g_rX)
		            {
		                if (m_pt.x < GameConfig.FORTH_BOOZER_X * Global.g_rX)
		                    return 1;
		                else 
		                    return 2;
		            }
		            
		            break;
		        default:
		            break;
		    }
		    
		    if (m_fBeer)
		        m_pt.x -= BOOZER_BACK_X_STEP * Global.g_rX;
		    else 
		        m_pt.x += BOOZER_X_STEP * Global.g_rX;
		    
		    setActionSprite(m_nIdx);
		    setPosition(m_pt.x, m_pt.y);
		    
		    if (m_fBeer)
		    {
		        m_nIdx ++;
		        
		        if (m_nIdx == GameConfig.BOOZER_BEER_NUM)
		        {
		            m_fBeer = false;
		            return 3;
		        }
		    }
		    else
		    {
		        m_nIdx ++;
		        
		        if (m_nIdx == GameConfig.BOOZER_ANI_IMAGE_NUM)
		            m_nIdx = GameConfig.BOOZER_BEER_NUM;
		    }
		    
		    return 0;
	}
	
	public void nonVisibleSprites()
	{
		setVisible(false);
	}
	
	public int getAppearPlace()
	{
		return m_nAppearPlace;
	}
	
	public CGRect getSpriteRect()
	{
		int nWidth = (int) (BOOZER_WIDTH * Global.g_rX) ;
	    int nHeight = (int) (BOOZER_HEIGTH * Global.g_rY);
	    
	    return CGRect.make(m_pt.x - (nWidth / 2.0f), m_pt.y - (nHeight / 2.0f), nWidth, nHeight);    
	}
	
	public void dealloc()
	{
		nonVisibleSprites();
	    CCTextureCache.sharedTextureCache().removeTexture(getTexture());
	  
	}
	
	public void setActiveState()
	{
		m_fActive = true;
	}
	
	public boolean getActiveState()
	{
		return m_fActive;
	}
	
	public int getLoopCount()
	{
		return m_nLoopCount;
	}
	
	public int dispAngryBoozer()
	{
		nonVisibleSprites();
	    
		setActionSprite(m_nEndIdx);
		setPosition(m_pt.x, m_pt.y);
		
	    m_nTmpLoopCount ++;
	    
	    if (m_nEndIdx == 15)
	    {
	        m_nTmpLoopCount = m_nTmpLoopCount % 2;
	        
	        if (m_nTmpLoopCount == 0)
	            m_nEndIdx = 19;
	    }
	    else 
	    {
	        m_nTmpLoopCount = m_nTmpLoopCount % 2;
	        
	        if (m_nTmpLoopCount == 0)
	            m_nEndIdx = 15;
	    }
	    
	    m_nTempIdx ++;
	    
	    if (m_nTempIdx % 2 == 0)
	        return 2;
	    
	    if (m_nTempIdx == 9)
	        return 1;
	        
	    return 0;
	}
}
