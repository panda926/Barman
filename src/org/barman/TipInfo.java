package org.barman;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;

//import java.util.ArrayList;


public class TipInfo extends CCSprite{
	protected TipInfo(CCTexture2D tex) {
		super(tex);
		// TODO Auto-generated constructor stub
	}

	public final int TIP_X_STEP = 12;
	public final float TIP_APPEAR_INTERVAL = 3.0f;	
	//Layer selfView;    
    CGPoint m_pt;    
    int m_nIdx;    
    int m_nAppearPlace; // 0 ...3
    int m_nTipState;    
    boolean m_fTipActive; 
    float m_rTime;
	    
	public static TipInfo createTipnfo()
	{
	  // m_tipActionArray = new ArrayList<Sprite>();
      // m_sActionSprite = Sprite.sprite("gfx/Tip_01.png");
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage("gfx/Tip_01.png");
		TipInfo newTip = new TipInfo(tex);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
		return newTip;
	}
	
	public void dealloc()
	{
		CCTextureCache.sharedTextureCache().removeTexture(getTexture());

	}
	
	
	public void setTipInfo(int nTipState, int nApperPlace)
	{
		 m_nIdx = 0;        
	       m_nTipState = GameConfig.COIN_GET_STATE;
	       m_nAppearPlace = 0;        
	         
	    m_nAppearPlace = nApperPlace;
	    m_fTipActive = false;	    
	    setTipState(nTipState);	    
	    m_rTime = 0.0f;
	    
	    switch (m_nAppearPlace)
	    {
	        case 0:
	            m_pt = CGPoint.ccp(GameConfig.FIRST_BEER_X * Global.g_rX, Global.getCocosY(GameConfig.FIRST_BEER_Y * Global.g_rY));
	            break;
	        case 1:
	            m_pt = CGPoint.ccp(GameConfig.SECOND_BEER_X * Global.g_rX, Global.getCocosY(GameConfig.SECOND_BEER_Y * Global.g_rY));
	            break;
	        case 2:
	            m_pt = CGPoint.ccp(GameConfig.THIRD_BEER_X * Global.g_rX, Global.getCocosY(GameConfig.THIRD_BEER_Y * Global.g_rY));
	            break;
	        case 3:
	            m_pt = CGPoint.ccp(GameConfig.FORTH_BEER_X * Global.g_rX, Global.getCocosY(GameConfig.FORTH_BEER_Y * Global.g_rY));
	            break;
	        default:
	            break;
	    }
	}
	
	public void nonVisibleSprites()
	{
		setVisible(true);
	}
	
	public String getSpriteString(int nIdx)
	{
		String str = null;
		// int i;
		    if (m_nTipState == GameConfig.COIN_GET_STATE)
		    {
		    	if (nIdx < 6)
		    		str = String.format("gfx/Tip_0%d.png", nIdx + 1);	         
		    }
		    else 
		    {
		    	if (nIdx < 12 && nIdx >= 6)
		    	{
		    		if (nIdx+1<10)
		    			str = String.format("gfx/Tip_0%d.png", nIdx + 1);
		            else
		            	str = String.format("gfx/Tip_%d.png", nIdx + 1);
		    	}
		       
		    }		
		
		return str;
	}
	public void setActionSprite(int nIdx)
	{
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage(getSpriteString(nIdx));
		setTexture(tex);
		setVisible(true);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
	}
	
//	public Sprite getSprite(int nIdx)
//	{
//		return m_tipActionArray.get(nIdx);
//	}
	
	public int getTipInfo()
	{
	  if (!m_fTipActive)
	    {
	        m_rTime += 0.2;
	        
	        if (m_rTime > TIP_APPEAR_INTERVAL)
	            setActiveTip();
	        
	        return 0;
	    }
	    
	    nonVisibleSprites();
	    
	    switch (m_nAppearPlace)
	    {
	        case 0:
	            if (m_pt.x > GameConfig.FIRST_BEER_LAST_X * Global.g_rX)
	                return 1;
	            
	            break;
	        case 1:
	            if (m_pt.x > GameConfig.SECOND_BEER_LAST_X  * Global.g_rX)
	                return 1;
	            
	            break;
	        case 2:
	            if (m_pt.x > GameConfig.THIRD_BEER_LAST_X * Global.g_rX)
	                return 1;
	            
	            break;
	        case 3:
	            if (m_pt.x > GameConfig.FORTH_BEER_LAST_X * Global.g_rX)
	                return 1;
	            
	            break;
	        default:
	            break;
	    }
	    
	    if (m_nIdx >= 6)
	    {
	        m_nIdx = 5;
	        m_pt.x += TIP_X_STEP * Global.g_rX;
	    }
	    
	    setActionSprite(m_nIdx);
	    setPosition(m_pt.x, m_pt.y);
	    m_nIdx ++;
	    
	    return 0;
	}
	
	public void setTipState(int nState)
	{
	    m_nTipState = nState;
	    m_nIdx = 0;
	}
	
	public void setActiveTip()
	{
		m_fTipActive = true;
	}
}
