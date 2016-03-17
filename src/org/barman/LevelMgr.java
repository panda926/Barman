package org.barman;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;


public class LevelMgr extends CCNode{
	int m_nLevelIdx;
	    
    LevelInfo m_levelInfo = null;    

    int m_nPipeLeftBoozer1;
    int m_nPipeLeftBoozer2;
    int m_nPipeLeftBoozer3;
    int m_nPipeLeftBoozer4;
    
    float m_rTime;
    
    int m_nTotalBoozerNum;
    int m_nLeftBoozerNum;
    
	public LevelMgr()
	{
		 m_nLevelIdx = 1;
	     m_levelInfo = new LevelInfo();
	}
	public void dealloc()
	{
		m_levelInfo.dealloc();			
	}
	public void setLevel(int nLevel)
	{
		if (nLevel < 1 || nLevel > GameConfig.MAX_LEVEL)
		        return;
		    
	    m_nLevelIdx = nLevel;
	    m_rTime = 0.0f;
	    
	    initBoozerLeftNum();
	    makeLevel();
	}
	
	private void initBoozerLeftNum()
	{
		m_nTotalBoozerNum = 0;
	    m_nLeftBoozerNum = 0;
	    
	    m_nPipeLeftBoozer1 = 0;
	    m_nPipeLeftBoozer2 = 0;
	    m_nPipeLeftBoozer3 = 0;
	    m_nPipeLeftBoozer4 = 0;
	}
	
	private void makeLevel()
	{
		if (m_nLevelIdx < 3)
	    {
	        if (m_nLevelIdx == 1)
	            m_levelInfo.setLevelInfo(m_nLevelIdx + 1, 2, 1);
	        else 
	        	m_levelInfo.setLevelInfo(m_nLevelIdx + 1, 2, 2);
	    }
	    else if (m_nLevelIdx < 11)
	    	m_levelInfo.setLevelInfo(m_nLevelIdx + 1, 8, 3);
	    else 
	    	m_levelInfo.setLevelInfo(m_nLevelIdx + 1, 10, 4);
	    
	    getPipeBoozerNum();
	}
	
	private void getPipeBoozerNum()
	{
		ArrayList<BoozerAppearInfo> boozerArray = m_levelInfo.m_apparBoozerArray;
		    
	    int nCount = boozerArray.size();
	    
	    BoozerAppearInfo boozerAppearInfo = null;
	    for (int i = 0; i < nCount; i ++)
	    {
	        boozerAppearInfo = boozerArray.get(i);
	        
	        switch (boozerAppearInfo.nPipeNum) 
	        {
	            case 0:
	                m_nPipeLeftBoozer1 ++;
	                break;
	            case 1:
	                m_nPipeLeftBoozer2 ++;
	                break;
	            case 2:
	                m_nPipeLeftBoozer3 ++;
	                break;
	            case 3:
	                m_nPipeLeftBoozer4 ++;
	                break;
	                
	            default:
	                break;
	        }
	    }
	    
	    m_nTotalBoozerNum = m_nPipeLeftBoozer1 + m_nPipeLeftBoozer2 + m_nPipeLeftBoozer3 + m_nPipeLeftBoozer4;  
	}
	
	public void descreaseLeftBoozerNum(int nPipeNum)
	{
		switch (nPipeNum)
	    {
	        case 0:
	            m_nPipeLeftBoozer1 --;
	            
	            if (m_nPipeLeftBoozer1 < 0)
	                m_nPipeLeftBoozer1 = 0;
	            break;
	        
	        case 1:
	            m_nPipeLeftBoozer2 --;
	            
	            if (m_nPipeLeftBoozer2 < 0)
	                m_nPipeLeftBoozer2 = 0;
	            break;
	            
	        case 2:
	            m_nPipeLeftBoozer3 --;
	            
	            if (m_nPipeLeftBoozer3 < 0)
	                m_nPipeLeftBoozer3 = 0;
	            break;
	            
	        case 3:
	            m_nPipeLeftBoozer4 --;
	            
	            if (m_nPipeLeftBoozer4 < 0)
	                m_nPipeLeftBoozer4 = 0;
	            break;
	            
	        default:
	            break;
	    }
	    
	    m_nLeftBoozerNum ++;
	}
	
	public int getStep()
	{
		Global.g_boozerArray.removeAll(Global.g_boozerArray);
		    
	    ArrayList<BoozerAppearInfo> boozerArray = m_levelInfo.m_apparBoozerArray;
	    
	    BoozerAppearInfo boozerAppearInfo;
	    
	    int nTotalInsertCount = 0;
	    
	    int i;
	    for (i = 0; i < boozerArray.size(); i ++)
	    {
	        boozerAppearInfo = boozerArray.get(i);
	        
	        if ((boozerAppearInfo.rTime <= m_rTime))
	        {
	            Global.g_boozerArray.add(boozerAppearInfo);
	            boozerArray.remove(i);
	            System.gc();
	            
	            i --;
	            
	            nTotalInsertCount ++;
	        }
	    }
	    
	  
	    m_rTime += GameConfig.BOOZER_SLOW_ANI_INTERVAL;
	    
	    if (nTotalInsertCount > 0)
	        return 1;        
	    else 
	        return 0;
	}
	
	public boolean getLevelDone()
	{
		 if (m_nPipeLeftBoozer1 > 0)
		        return false;
		    
		    if (m_nPipeLeftBoozer2 > 0)
		        return false;
		    
		    if (m_nPipeLeftBoozer3 > 0)
		        return false;
		    
		    if (m_nPipeLeftBoozer4 > 0)
		        return false;
	
		    return true;
	}
	
	public int getGamePro()
	{
		return m_nLeftBoozerNum * 100 / m_nTotalBoozerNum;
	}
	
}
