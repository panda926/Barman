package org.barman;

public class SaveUsrInfo{
	int nScore;
	int nBeer;
	    
	String str;
	
	public SaveUsrInfo(){}
	
	public void setusrInfo(int nscore , String string, int nbeer)
	{
		nScore = nscore;
	    str = String.format(string);
	    nBeer = nbeer;
	}
	
	public String getUsrName()
	{
		return str;
	}
}
