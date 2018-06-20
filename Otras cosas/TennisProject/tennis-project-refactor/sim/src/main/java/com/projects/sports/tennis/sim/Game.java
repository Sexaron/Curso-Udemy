package com.projects.sports.tennis.sim;

public class Game {

	private int ptsLCL;
	private int ptsVST;
	
	public Game() {
		// TODO Auto-generated constructor stub
		this.ptsLCL = 0;
		this.ptsVST = 0;
	}

	public int getPtsLCL() {
		return ptsLCL;
	}
	
	public void addPtsLCL() {
		this.ptsLCL++;
	}

	public int getPtsVST() {
		return ptsVST;
	}
	
	public void addPtsVST() {
		this.ptsVST++;
	}
}
