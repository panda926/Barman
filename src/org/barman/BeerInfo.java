package org.barman;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

//import java.util.ArrayList;



public class BeerInfo extends CCSprite{
	
	protected BeerInfo(CCTexture2D tex) {
		super(tex);
	}

	public static BeerInfo createBeerInfo() {		     
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage("gfx/mug_01.png");
		BeerInfo newBeer = new BeerInfo(tex);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
		return newBeer;
	}

	private int BEER_X_STEP = 12;
	private int BEER_WIDTH = 95;
	private int BEER_HEIGHT = 137;
	private float MUL_STEP = 1.0f;   
    CGPoint m_pt;    
    int m_nIdx;    
    int m_nAppearPlace; // 0 ...3
    int m_nBeerState;
    boolean m_fPoseInsert;    
    int m_nInsertIdx;	
    final int static_coordinate[] = {14, 14, 18, 18};
    
	public void dealloc()
	{
		nonVisibleSprites();
		CCTextureCache.sharedTextureCache().removeTexture(getTexture());
	}
	
	public void setBeerInfo(int nBeerState, int nApperPlace, int xPos)
	{
		m_nIdx = 0;
        m_nBeerState = GameConfig.BEER_STATE;
        m_nAppearPlace = 0;   
        
	    m_fPoseInsert = false;
	    m_nAppearPlace = nApperPlace;
	    m_nInsertIdx = 0;	    
	    setBeerState(nBeerState);	    
	    if (m_nBeerState == GameConfig.NON_BEER_STATE)
	    {
	        switch (m_nAppearPlace)
	        {
	            case 0:
	                m_pt = CGPoint.ccp(xPos, Global.getCocosY(GameConfig.FIRST_BEER_Y * Global.g_rY));
	                break;
	            case 1:
	                m_pt = CGPoint.ccp(xPos, Global.getCocosY(GameConfig.SECOND_BEER_Y * Global.g_rY));
	                break;
	            case 2:
	                m_pt = CGPoint.ccp(xPos, Global.getCocosY(GameConfig.THIRD_BEER_Y * Global.g_rY));
	                break;
	            case 3:
	                m_pt = CGPoint.ccp(xPos, Global.getCocosY(GameConfig.FORTH_BEER_Y * Global.g_rY));
	                break;
	            default:
	                break;
	        }
	    }
	    else if (m_nBeerState == GameConfig.BEER_STATE)
	    {
	        switch (m_nAppearPlace)
	        {
	            case 0:
	                m_pt = CGPoint.ccp(GameConfig.FIRST_BEER_LAST_X * Global.g_rX, Global.getCocosY(GameConfig.FIRST_BEER_Y * Global.g_rY));
	                break;
	            case 1:
	                m_pt = CGPoint.ccp(GameConfig.SECOND_BEER_LAST_X *Global.g_rX, Global.getCocosY(GameConfig.SECOND_BEER_Y * Global.g_rY));
	                break;
	            case 2:
	                m_pt = CGPoint.ccp(GameConfig.THIRD_BEER_LAST_X * Global.g_rX, Global.getCocosY(GameConfig.THIRD_BEER_Y * Global.g_rY));
	                break;
	            case 3:
	                m_pt = CGPoint.ccp(GameConfig.FORTH_BEER_LAST_X * Global.g_rX, Global.getCocosY(GameConfig.FORTH_BEER_Y *Global.g_rY));
	                break;
	            default:
	                break;
	        }
	    }
	    
	}
	
	public void nonVisibleSprites()
	{
		setVisible(false);
	}
	
	public String getSpriteString(int nIdx)
	{
		String str;
		str = String.format("gfx/mug_0%d.png", nIdx+ 1); 
 	   	
		return str;
	}
	public void setActionSprite(int nIdx)
	{
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage(getSpriteString(nIdx));
		setTexture(tex);
		setVisible(true);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
	}
	public void setBeerState(int nState)
	{
		m_nBeerState = nState;
	    
	    switch (m_nBeerState) 
	    {
	        case GameConfig.BEER_STATE:
	            m_nIdx = 0;            
	            break;            
	        case GameConfig.NON_BEER_STATE:
	            m_nIdx = 5;     
	            break;            
	        case GameConfig.BREAK_BEER_STATE:
	            m_nIdx = 1;            
	            break;            
	        default:
	            break;
	    }
	}
	
	public int getBeerState()
	{
		 return m_nBeerState;
	}
	
	public int getBeerInfo()
	{
		 int nReturn = 0;
		    
		    if (m_nBeerState != GameConfig.BREAK_BEER_STATE)
		    {
		        switch (m_nAppearPlace)
		        {
		            case 0:
		                if (m_pt.x < GameConfig.FIRST_BEER_X * Global.g_rX || m_pt.x > GameConfig.FIRST_BEER_LAST_X * Global.g_rX)
		                {
		                    if (m_pt.x < GameConfig.FIRST_BEER_X * Global.g_rX)
		                    {
		                        if (m_nBeerState != GameConfig.NON_BEER_STATE)
		                            setBeerState(GameConfig.BREAK_BEER_STATE);
		                    }
		                    else 
		                    {
		                        if (!m_fPoseInsert)
		                        {
		                            m_fPoseInsert = true;                  
		                            nReturn = 1;
		                        }                        
		                    }
		                }
		                
		                break;
		            case 1:
		                if (m_pt.x < GameConfig.SECOND_BEER_X * Global.g_rX || m_pt.x > GameConfig.SECOND_BEER_LAST_X  * Global.g_rX)
		                {
		                    if (m_pt.x < GameConfig.SECOND_BEER_X * Global.g_rX)
		                    {
		                        if (m_nBeerState != GameConfig.NON_BEER_STATE)
		                            setBeerState(GameConfig.BREAK_BEER_STATE);
		                    }
		                    else 
		                    {
		                        if (!m_fPoseInsert)
		                        {
		                            m_fPoseInsert = true;                  
		                            nReturn = 1;
		                        }
		                    }
		                }
		                
		                break;
		            case 2:
		                if (m_pt.x < GameConfig.THIRD_BEER_X  * Global.g_rX|| m_pt.x > GameConfig.THIRD_BEER_LAST_X * Global.g_rX)
		                {
		                    if (m_pt.x < GameConfig.THIRD_BEER_X * Global.g_rX)
		                    {
		                        if (m_nBeerState != GameConfig.NON_BEER_STATE)
		                            setBeerState(GameConfig.BREAK_BEER_STATE);
		                    }
		                    else 
		                    {
		                        if (!m_fPoseInsert)
		                        {
		                            m_fPoseInsert = true;                  
		                            nReturn = 1;
		                        }
		                    }
		                }
		                
		                break;
		            case 3:
		                if (m_pt.x < GameConfig.FORTH_BEER_X * Global.g_rX || m_pt.x > GameConfig.FORTH_BEER_LAST_X * Global.g_rX)
		                {
		                    if (m_pt.x < GameConfig.FORTH_BEER_X * Global.g_rX)
		                    {
		                        if (m_nBeerState != GameConfig.NON_BEER_STATE)
		                            setBeerState(GameConfig.BREAK_BEER_STATE);
		                    }
		                    else 
		                    {
		                        if (!m_fPoseInsert)
		                        {
		                            m_fPoseInsert = true;                  
		                            nReturn = 1;
		                        }
		                    }
		                }
		                
		                break;
		            default:
		                break;
		        }
		    }
		    
		    if (m_fPoseInsert)
		    {
		        if (m_nInsertIdx < 2)
		        {
		            m_pt.x += static_coordinate[m_nInsertIdx] * Global.g_rX;
		        }
		        else 
		        {
		            m_pt.y -= static_coordinate[m_nInsertIdx] * Global.g_rY;
		        }
		        
		        m_nInsertIdx ++;
		        
		        if (m_nInsertIdx == 4)
		        {
		            m_fPoseInsert = false;
		            setBeerState(GameConfig.BREAK_BEER_STATE);
		        }
		    }
		    else
		    {
		        if (m_nBeerState == GameConfig.BEER_STATE)
		        {
		            m_pt.x -= BEER_X_STEP * Global.g_rX;
		        }
		        else if (m_nBeerState == GameConfig.NON_BEER_STATE)
		        {
		            m_pt.x += BEER_X_STEP * Global.g_rX;
		        }
		        else if (m_nBeerState == GameConfig.BREAK_BEER_STATE)
		        {
		            m_nIdx ++;
		            
		            if (m_nIdx > 4)
		            {
		                m_nIdx = 4;
		                nReturn = 2;
		            }
		        }
		    }    
		    
		    nonVisibleSprites();
		    setActionSprite(m_nIdx);
		    setPosition(m_pt.x, m_pt.y);
		    
		    return nReturn;
	}
	
	public CGRect getSpriteRect()
	{
		int nWidth = (int) (BEER_WIDTH* Global.g_rX * MUL_STEP);
	    int nHeight = (int) (BEER_HEIGHT * Global.g_rY * MUL_STEP);
	    
	    CGRect rect = CGRect.make(m_pt.x - (nWidth / 2.0f), m_pt.y - (nHeight / 2.0f), nWidth, nHeight);
	    
	    return rect;
	}
}
