package com.projects.sports.tennis.sim;

public class Match extends Application{

	private Set[] set;
	private int ptsSetLCL; // de 0 a 2 o de 0 a 5
	private int ptsSetVST; // de 0 a 2 o de 0 a 5
	
	public Match(int MAX_NUM_SETS) {
		// TODO Auto-generated constructor stub
		this.ptsSetLCL = 0;
		this.ptsSetVST = 0;
		this.set = new Set[MAX_NUM_SETS];
		
		for (int i=0; i < set.length; i++) {
			set[i] = new Set(Match.MAX_NUM_GAMES);
		}
	}

	public Set getSet(int i) {
		return set[i];
	}

	public int getPtsSetLCL() {
		return ptsSetLCL;
	}
	
	public void addPtsSetLCL() {
		this.ptsSetLCL++;
	}

	public int getPtsSetVST() {
		return ptsSetVST;
	}
	
	public void addPtsSetVST() {
		this.ptsSetVST++;
	}
	
	

}
