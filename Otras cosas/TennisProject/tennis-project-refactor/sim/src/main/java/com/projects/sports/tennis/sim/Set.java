package com.projects.sports.tennis.sim;

public class Set {
	
	private Game[] game;
	private int ptsGameLCL;
	private int ptsGameVST;
	private boolean tiebreak;
	
	public Set(int MAX_NUM_GAMES) {
		// TODO Auto-generated constructor stub
		this.ptsGameLCL = 0;
		this.ptsGameVST = 0;
		this.tiebreak = false;
		this.game = new Game[MAX_NUM_GAMES];
		
		for(int i=0; i < game.length; i++) {
			game[i] = new Game();
		}
	}

	public Game getGame(int i) {
		return game[i];
	}

	public int getPtsGameLCL() {
		return ptsGameLCL;
	}
	
	public void addPtsGameLCL() {
		this.ptsGameLCL++;
	}

	public int getPtsGameVST() {
		return ptsGameVST;
	}
	
	public void addPtsGameVST() {
		this.ptsGameVST++;
	}

	public boolean isTiebreak() {
		return tiebreak;
	}

	public void setTiebreak(boolean tiebreak) {
		this.tiebreak = tiebreak;
	}

}
