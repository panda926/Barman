package org.barman;

import java.util.ArrayList;


public class LevelInfo{
	int m_nBoozerNum;
    int m_nBoozerkind;
    int m_nPipeNum;    
    ArrayList<BoozerAppearInfo> m_apparBoozerArray = null;    
    int aPipe[] = new int[4];    
    float m_rTime1;
    float m_rTime2;
    float m_rTime3;
    float m_rTime4;
    
    private final int PIPE_APPEAR_STATE = 2;
    private final int PIPE_NON_TIME = 0;
    private final int PIPE_TIME_INTEVAL = 1;
    private final float MIN_TIME_INTERVAL = 2.5f;
    private final float MIN_FIRST_TIME_INTERVAL = 0.5f;
    private final int MAX_TIME_INTERVAL = 40;
    
	public LevelInfo()
	{
		m_apparBoozerArray = new ArrayList<BoozerAppearInfo>();
	    resetPipe();
	}
	
	public void dealloc()
	{
		m_apparBoozerArray.removeAll(m_apparBoozerArray);
	    
	    if (m_apparBoozerArray != null)
	        m_apparBoozerArray = null;
	}
	
	public void setLevelInfo(int nBoozerNum, int nBoozerKind, int nPipeNum)
	{
		//m_apparBoozerArray.removeAll(m_apparBoozerArray);	    
	    m_nBoozerNum = nBoozerNum;  
	    m_nBoozerkind = nBoozerKind; 
	    m_nPipeNum = nPipeNum;	    
	    resetTime();
	    resetPipe();
	    makeLevel();
	}
	
	private void resetPipe()
	{
		aPipe[0] = 0;	    
	    for (int i = 1; i < 4; i ++)
	        aPipe[i] = -1;
	}
	
	private void resetTime()
	{
		m_rTime1 = 0.0f;
	    m_rTime2 = 0.0f;
	    m_rTime3 = 0.0f;
	    m_rTime4 = 0.0f;
	}
	
	public void addTime(int nIdx, float rTime)
	{
	   switch (nIdx)
	    {
	        case 0:
	            m_rTime1 += rTime;
	            break;
	        case 1:
	            m_rTime2 += rTime;
	            break;
	        case 2:
	            m_rTime3 += rTime;
	            break;
	        case 3:
	            m_rTime4 += rTime;
	            break;
	        default:
	            break;
	    }
	}
	
	public float getTime(int nIdx)
	{	  
		float rTime = 0.0f;	  
		switch (nIdx)
	    {
	        case 0:
	            rTime = m_rTime1;
	            break;
	        case 1:
	        	rTime = m_rTime2;
	            break;
	        case 2:
	        	rTime = m_rTime3;
	            break;
	        case 3:
	        	rTime = m_rTime4;
	            break;
	        default:
	            break;
	    }
	    
	    return rTime;
	}
	
	public void makeLevel()
	{
		  int i, nNum, nCount, nTemp, nKind;		    
		    float rTime;		    
		    if (m_nPipeNum == 2)
		    {
		        nNum = (int) (Math.random() * 100 % 3 + 1);		        
		        aPipe[1] = nNum;
		    }
		    else if (m_nPipeNum == 3)
		    {
		        nNum = (int) (Math.random() * 100 % 3 + 1);		        
		        nCount = 4;
		        
		        boolean fFlag = false;		        
		        for (i = 1; i < nCount; i ++)
		        {
		            if (i != nNum)
		            {
		                if (fFlag)
		                    aPipe[i-1] = i;
		                else 
		                    aPipe[i] = i;
		            }
		            else 
		                fFlag = true;
		        }
		    }
		    else if (m_nPipeNum == 4)
		    {
		        for (i = 0; i < 4; i ++)
		            aPipe[i] = i;
		    }
		    
		    randPipe();
		    
		    BoozerAppearInfo boozerAppearInfo;    
		    
		    for (i = 0; i < m_nPipeNum; i++)
		    {
		        boozerAppearInfo = new BoozerAppearInfo();
		        
		        nNum = (int) (Math.random() * 1000 % PIPE_APPEAR_STATE);       

		        switch (nNum)
		        {
		            case PIPE_NON_TIME:
		                addTime(aPipe[i], 0.0f);
		                boozerAppearInfo.setBoozerApparInfo(i, 0, aPipe[i], getTime(aPipe[i]));		                
		                break;
		                
		            case PIPE_TIME_INTEVAL:
		                nTemp = (int) (Math.random() * 1000 % 10);
		                rTime = MIN_FIRST_TIME_INTERVAL + (float)nTemp / 5;
		                addTime(aPipe[i], rTime);                
		                boozerAppearInfo.setBoozerApparInfo(i, 0, aPipe[i], getTime(aPipe[i]));
		                break;
		            default:
		                break;
		        }
		        
		        m_apparBoozerArray.add(boozerAppearInfo);
		        boozerAppearInfo = null;
		    }
		   
		    int aTemp[] = new int[4];
		    
		    int nPipe;

		    for (i = m_nPipeNum; i < m_nBoozerNum; i ++)
		    {
		        boozerAppearInfo = new BoozerAppearInfo();
		        nKind = (int) (Math.random() * 1000 % m_nBoozerkind);
		     
		        nTemp = (int) (Math.random() * 1000 % MAX_TIME_INTERVAL);
		        rTime = MIN_TIME_INTERVAL + (float)nTemp / 10;
		        nPipe = (int) (Math.random() * 1000 % m_nPipeNum);
		        
		        addTime(aPipe[nPipe], rTime);
		        boozerAppearInfo.setBoozerApparInfo(nKind, aTemp[nPipe], aPipe[nPipe], getTime(aPipe[nPipe]));
		        
		        m_apparBoozerArray.add(boozerAppearInfo);
		        boozerAppearInfo = null;
		    }
	}
	
	public void randPipe()
	{
		ArrayList<PipeIndexInfo> pipeArray = new ArrayList<PipeIndexInfo>();
	    
	    int i;
	    
	    PipeIndexInfo pipeIndexInfo;
	    for (i = 0; i < m_nPipeNum; i ++)
	    {
	        pipeIndexInfo = new PipeIndexInfo();
	        pipeIndexInfo.nIdx = aPipe[i];
	        pipeArray.add(pipeIndexInfo);
	        pipeIndexInfo = null;
	    }
	    
	    int aTemp[] = new int[4];
	    
	    int nNum;
	    int nCount;
	    
	    for (i = 0; i < m_nPipeNum; i ++)
	    {
	        nCount = pipeArray.size();        
	        nNum = (int) (Math.random() * 1000 % nCount);
	        pipeIndexInfo = pipeArray.get(nNum);
	        aTemp[i] = pipeIndexInfo.nIdx;        
	        pipeArray.remove(nNum);
	    }
	    
	    for (i = 0; i < m_nPipeNum; i ++)
	        aPipe[i] = aTemp[i];
	    
	    pipeArray.removeAll(pipeArray);
	    pipeArray = null;
	}
}
