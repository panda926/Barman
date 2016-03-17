package org.barman;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

//import java.util.ArrayList;

public class WaiterInfo extends CCSprite{
	protected WaiterInfo(CCTexture2D tex) {
		super(tex);
		// TODO Auto-generated constructor stub
	}

	public final int WAITER_SHORT_X_STEP = 32;
	public final int WAITER_MIDDLE_X_STEP = 48;
	public final int WAITER_BIG_X_STEP = 64;
	public final int WAITER_WIDTH = 219;
	public final int WAITER_HEIGHT = 246;
    CGPoint m_pt;

    int m_nIdx;
    int m_nWaiterState;
    int m_nWaiterPoseState;

    int m_nAlphaIdx;    
    int m_nGainCupState;
    
   boolean m_fEndThred;
	    
	public static WaiterInfo createWaiterInfo()
	{		
		//m_sActionSprite = Sprite.sprite("gfx/Waiter_01.png");
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage("gfx/Waiter_01.png");
         WaiterInfo newWaiter = new WaiterInfo(tex);
         CCTextureCache.sharedTextureCache().removeTexture(tex);
         return newWaiter;
	}
	
	public void dealloc()
	{
		
		CCTextureCache.sharedTextureCache().removeTexture(getTexture());
		
	}
	
	public void setGainCupState(int nState)
	{
		m_nGainCupState = nState;
	}
	public String getSpriteString(int nIdx)
	{
		String str;
		
		str = String.format("gfx/Waiter_%02d.png", nIdx + 1);
		
		return str;
	}
	public void setActionSprite(int nIdx)
	{
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage(getSpriteString(nIdx));
		setTexture(tex);
		setVisible(true);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
	}
	
	public void setWaiterInfo()
	{
		m_nIdx = 0;
		   
		 m_nWaiterState = GameConfig.NON_WAITER_STATE;
	        m_nWaiterPoseState = 0;
	        
	        m_fEndThred = false;
	        
	        m_nGainCupState = GameConfig.SHORT_GAIN_STATE;

	    switch (m_nWaiterPoseState)
	    {
	        case 0:
	            m_pt = CGPoint.make(GameConfig.FIRST_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.FIRST_WAITER_Y * Global.g_rY));
	            break;
	        case 1:
	            m_pt = CGPoint.make(GameConfig.SECOND_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.SECOND_WAITER_Y * Global.g_rY));
	            break;
	        case 2:
	            m_pt = CGPoint.make(GameConfig.THIRD_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.THIRD_WAITER_Y * Global.g_rY));
	            break;
	        case 3:
	            m_pt = CGPoint.make(GameConfig.FORTH_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.FORTH_WAITER_Y * Global.g_rY));
	            break;
	            
	        default:
	            break;
	    }
	    
	}
	
	public void setWaiterState(int nState)
	{
		 m_nWaiterState = nState;
		    
	    switch (nState)
	    {
	        case GameConfig.NON_WAITER_STATE:
	            m_nIdx = 0;
	            break;
	        case GameConfig.WELCOME_WAITER_STATE:
	            m_nIdx = 0;
	            break;
	        case GameConfig.BEER_WAITER_STATE:
	            m_nIdx = 10;
	            break;
	        case GameConfig.FAIL_WAITER_STATE:
	            m_nIdx = 5;
	            break;
	        case GameConfig.GAIN_CUP_WAITER_STATE:
	            m_nIdx = 15;
	            break;
	        case GameConfig.LIGHTED_WAITER_STATE:
	            m_nIdx = 24;
	            break;
	        case GameConfig.UP_WAITER_STATE:
	            m_nIdx = 0;
	            break;
	        case GameConfig.DOWN_WAITER_STATE:
	            m_nIdx = 0;
	            break;
	        case GameConfig.GET_WAITER_NORMAL_STATE:
	            m_nIdx = 13;
	            break;
	        case GameConfig.GET_WAITER_SPECIAL_STATE:
	            m_nIdx = 23;
	            break;
	        case GameConfig.LIGHT_BEER_STATE:
	            m_nIdx = 20;
	            break;
	        default:
	            break;
	    }
		    
	    m_nAlphaIdx = 0;
	    m_fEndThred = false;
	}
	
	public void moveWaiterState(int nState)
	{
		//  nonVisibleSprites();
		    
		    switch (nState)
		    {
		        case GameConfig.UP_WAITER_STATE:
		            m_nWaiterPoseState--;
		            
		            if (m_nWaiterPoseState < 0)
		                m_nWaiterPoseState = 3;
		            break;

		        case GameConfig.DOWN_WAITER_STATE:
		            m_nWaiterPoseState ++;
		            m_nWaiterPoseState = m_nWaiterPoseState % 4;
		            break;
		            
		        default:
		            break;
		    }
		    
		    if (m_nIdx >= 24)
		        m_nIdx = 24;
		    
		   // Sprite sprite = m_waiterActionArray.get(m_nIdx);
		    //setWaiterPlace(sprite);
		    //sprite.setVisible(true);
		    setActionSprite(m_nIdx);
		    setWaiterPlace();
	}
	
	public void nonVisibleSprites()
	{
		setVisible(false);
		setOpacity(255);
	}
	
	public int getWaiterInfo()
	{
		// nonVisibleSprites();
		    
		    int nReturn = 0;
		    
		   // Sprite sprite;
		    if (m_nWaiterState == GameConfig.NON_WAITER_STATE || m_nWaiterState == GameConfig.UP_WAITER_STATE 
		    		|| m_nWaiterState == GameConfig.DOWN_WAITER_STATE)
		    {
		       // sprite = m_waiterActionArray.get(m_nIdx);
		        
		        if (m_nWaiterState == GameConfig.NON_WAITER_STATE)
		        {
		            
		        }
		        else
		        {
		            if (m_nWaiterState == GameConfig.UP_WAITER_STATE)
		            {
		                m_nWaiterPoseState--;
		                
		                if (m_nWaiterPoseState < 0)
		                    m_nWaiterPoseState = 3;
		            }
		            else
		            {
		                m_nWaiterPoseState ++;
		                m_nWaiterPoseState = m_nWaiterPoseState % 4;
		            }
		        }
		        
		        setActionSprite(m_nIdx);
		        setWaiterPlace();
		       // sprite.setVisible(true);
		        
		        m_fEndThred = true;
		    }
		    else
		    {       
		        if (m_nWaiterState == GameConfig.GAIN_CUP_WAITER_STATE)
		        {
		            if (m_nIdx >= 20)
		            {             
		                if (m_nAlphaIdx == 0)
		                    nReturn = 2;
		                
		                if (m_nAlphaIdx <= 3)
		                {
		                    float rOpacity = 1.0f - m_nAlphaIdx * 0.3f;
		                    if (rOpacity < 0.0f)
		                        rOpacity = 0.0f;
		                    
		                    rOpacity *= 255.0f;
		                    //sprite = m_waiterActionArray.get(19);
		                    setActionSprite(19);
		                    setOpacity((int)(rOpacity));
		                    //sprite.setOpacity((int)(rOpacity));
		                }
		                else 
		                {
		                    if (m_nAlphaIdx > 6)
		                    {
		                        m_fEndThred = true;
		                        m_nIdx = 0;
		                        
		                       // sprite = m_waiterActionArray.get(m_nIdx);
		                        setActionSprite(m_nIdx);
		                        m_nWaiterState = GameConfig.NON_WAITER_STATE;
		                    }
		                    else
		                    {
		                        float rOpacity = (m_nAlphaIdx - 3) * 0.3f;
		                        
		                        if (rOpacity > 1.0)
		                            rOpacity = 1.0f;
		                        
		                        rOpacity *= 255.0;
		                       // sprite = m_waiterActionArray.get(0);
		                        setActionSprite(0);
		                        setOpacity((int)(rOpacity));
		                       // sprite.setOpacity((int)(rOpacity));
		                    }
		                    setWaiterPlace();
		                    //setWaiterPlace(sprite);
		                }
		                
		                m_nAlphaIdx ++;
		            }
		            else 
		            {
		                int nStepX = 0;
		                
		                switch (m_nGainCupState) 
		                {
		                    case GameConfig.SHORT_GAIN_STATE:
		                        nStepX = WAITER_SHORT_X_STEP;
		                        break;
		                        
		                    case GameConfig.MIDDLE_GAIN_STATE:
		                        nStepX = WAITER_MIDDLE_X_STEP;
		                        break;
		                        
		                    case GameConfig.LARGE_GAIN_STATE:
		                        nStepX = WAITER_BIG_X_STEP;
		                        break;
		                    default:
		                        break;
		                }
		                
		                m_pt.x -= nStepX * Global.g_rX;
		                //sprite = m_waiterActionArray.get(m_nIdx);
		                setActionSprite(m_nIdx);
		            }            
		            
		            setPosition(m_pt.x, m_pt.y);
		            //sprite.setPosition(m_pt.x, m_pt.y);
		            //sprite.setVisible(true);
		        }
		        else 
		        {
		            switch (m_nWaiterState)
		            {
		                case GameConfig.WELCOME_WAITER_STATE:
		                    if (m_nIdx >= 4)
		                    {
		                        m_nIdx = 4;
		                        m_fEndThred = true;
		                        
		                        nReturn = 3;
		                    }
		                    break;
		                case GameConfig.FAIL_WAITER_STATE:
		                    if (m_nIdx >= 9)
		                    {
		                        m_nIdx = 9;
		                        m_fEndThred = true;
		                        
		                        nReturn = 4;
		                    }
		                    break;
		                case GameConfig.BEER_WAITER_STATE:
		                    if (m_nIdx >= 14)
		                    {
		                        m_nIdx = 14;
		                        m_fEndThred = true;
		                        
		                        nReturn = 1;
		                    }
		                    break;
		                case GameConfig.LIGHTED_WAITER_STATE:
		                    if (m_nIdx >= 24)
		                    {
		                        m_nIdx = 24;
		                        m_fEndThred = true;
		                        
		                        
		                    }
		                   break;
		                case GameConfig.GET_WAITER_NORMAL_STATE:
		                    if (m_nIdx >= 14)
		                    {
		                        m_nIdx = 14;
		                        m_fEndThred = true;
		                    }
		                    
		                    break;
		                    
		                case GameConfig.GET_WAITER_SPECIAL_STATE:
		                    if (m_nIdx >= 24)
		                    {
		                        m_nIdx = 24;
		                        m_fEndThred = true;
		                    }
		                    break;//
		                case GameConfig.LIGHT_BEER_STATE:
		                    if (m_nIdx > 24)
		                    {
		                        m_nIdx = 24;
		                        m_fEndThred = true;
		                        
		                        nReturn = 1;
		                    }
		                    
		                    break;
		                default:
		                    break;
		            }
		            
		            if (m_nIdx >= 24)
		                m_nIdx = 24;//
		            
		            //sprite = m_waiterActionArray.get(m_nIdx);
		            
		            //sprite.setVisible(true);
		           // setWaiterPlace(sprite);  
		            setActionSprite(m_nIdx);
		            setWaiterPlace();
		        }
		        
		        if (!m_fEndThred)
		        {
		            m_nIdx ++;
		        }
		    }
		    
		    return nReturn;
	}
	
	public void setWaiterPlace()
	{
		switch (m_nWaiterPoseState)
	    {
	        case 0:
	            m_pt = CGPoint.make(GameConfig.FIRST_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.FIRST_WAITER_Y * Global.g_rY));
	            break;
	        case 1:
	            m_pt = CGPoint.make(GameConfig.SECOND_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.SECOND_WAITER_Y * Global.g_rY));
	            break;
	        case 2:
	            m_pt = CGPoint.make(GameConfig.THIRD_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.THIRD_WAITER_Y * Global.g_rY));
	            break;
	        case 3:
	            m_pt = CGPoint.make(GameConfig.FORTH_WAITER_X * Global.g_rX, Global.getCocosY(GameConfig.FORTH_WAITER_Y * Global.g_rY));
	            break;
	        default:
	            break;
	    }
	    
	    setPosition(m_pt.x, m_pt.y);
	}
	
	public int getWaiterPoseState()
	{
		return m_nWaiterPoseState;
	}
	
	public CGRect getSpriteRect()
	{
		int nWidth = (int) (WAITER_WIDTH * Global.g_rX) ;
	    int nHeight = (int) (WAITER_HEIGHT * Global.g_rY);
	    
	    CGRect rect = CGRect.make(m_pt.x - (nWidth / 2.0f), m_pt.y - (nHeight / 2.0f), nWidth, nHeight);
	    
	    return rect;
	}
	
	public boolean isValidGainCupWaiterState()
	{
		return (m_nWaiterState == GameConfig.GAIN_CUP_WAITER_STATE);
	}
}
