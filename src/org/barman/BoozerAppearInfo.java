package org.barman;


public class BoozerAppearInfo{
	
	int nBoozerKind;
    int nAppearNum; // how much nums
    int nPipeNum;
    float rTime;
    
	public BoozerAppearInfo(){}
	
	public void setBoozerApparInfo(int nboozerKind, int napparnum, int npipenum, float rtime)
	{
		nBoozerKind = nboozerKind;
	    nAppearNum = napparnum;
	    nPipeNum = npipenum;
	    rTime = rtime;
	}
}
