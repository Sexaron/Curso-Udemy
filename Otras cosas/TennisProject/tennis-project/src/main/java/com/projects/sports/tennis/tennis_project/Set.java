package com.projects.sports.tennis.tennis_project;

public class Set {
	
	public static int psetLCL = 0;
	public static int psetVST = 0;
	public static int setsWonByLCL = 0;
	public static int setsWonByVST = 0;
	
	public Set(){}
	
	public int getSetLCL() {
		return psetLCL;
	}
	
	public void setSetLCL(int pSetLCL) {
		psetLCL = pSetLCL;
	}
	
	public int getSetVST() {
		return psetVST;
	}
	
	public void setSetVST(int pSetVST) {
		psetVST = pSetVST;
	}
	
	public void setSetsWonByLCL(){
		setsWonByLCL++;
	}
	
	public int getSetsWonByLCL(){
		return setsWonByLCL;
	}
	
	public void setSetsWonByVST(){
		setsWonByVST++;
	}
	
	public int getSetsWonByVST(){
		return setsWonByVST;
	}

}
