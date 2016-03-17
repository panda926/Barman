package org.barman;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;




public class BonusInfo extends CCSprite{
	protected BonusInfo(CCTexture2D tex) {
		super(tex);
		// TODO Auto-generated constructor stub
	}

	private final int DALAY_VIEW_FRAME = 3;	
	//Layer selfView;    
    CGPoint m_pt;    
    int m_nTempIdx;
    int m_nIdx;      
    boolean m_fConvert;    
   
    public static BonusInfo createBonusInfo()
    {
    	CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage("gfx/Bonus_01.png");
    	BonusInfo newBonus = new BonusInfo(tex);
    	CCTextureCache.sharedTextureCache().removeTexture(tex);
	  //m_sActionSprite = Sprite.sprite("gfx/Bonus_01.png");
    	return newBonus;
    }
    
    public void dealloc()
    {
    	CCTextureCache.sharedTextureCache().removeTexture(getTexture());
    }
    
    public void setBonusInfo()
    {
    	//selfView = sview;
	    m_nIdx = 0;    
	    m_nTempIdx = 0;	    
	    m_fConvert = false;	    
//	    Sprite sprite;	    
	    m_pt = CGPoint.zero();
//	    for (int i = 0; i < 5; i ++)
//	    {
//	    	String str = String.format("gfx/Bonus_0%d.png", i + 1);
//	    	sprite  = Sprite.sprite(str);
//	        m_bonusActionArray.add(sprite);
//	        CCTextureCache.sharedTextureCache().removeTexture(sprite.getTexture());
//	    }
    }
    
    public void initBonusState()
    {
    	m_nIdx = 0;    
	    m_nTempIdx = 0;
	    m_fConvert = false;
	    nonVisibleSprites();
    }
    
    public void nonVisibleSprites()
    {
    	setVisible(false);
    }
    public String getSpriteString(int nIdx)
	{
		String str;
		str = String.format("gfx/Bonus_0%d.png", nIdx+ 1); 
 	   	
		return str;
	}
	public void setActionSprite(int nIdx)
	{
		CCTexture2D tex = CCTextureCache.sharedTextureCache().addImage(getSpriteString(nIdx));
		setTexture(tex);
		setVisible(true);
		CCTextureCache.sharedTextureCache().removeTexture(tex);
	}
	
    public int getBonusInfo()
    {
    	nonVisibleSprites();
    	
    	//Sprite sprite = m_bonusActionArray.get(m_nIdx);
        //sprite.setVisible(true);
    	setActionSprite(m_nIdx);
        setPosition(m_pt.x, m_pt.y);
        
        if (m_fConvert)
        {
            m_nIdx --;             
            if (m_nIdx < 0)
            {
                m_nIdx = 0;
                return 1;
            }
        }
        else 
        {            
            m_nIdx ++;            
            if (m_nIdx >= 5)
            {
                m_nIdx = 4; 
                m_nTempIdx ++;                
                if (m_nTempIdx == DALAY_VIEW_FRAME)
                {
                    m_fConvert = true;
                }
            }
        }
        
        return 0;
    }
}
