package com.projects.sports.tennis.sim;

public class PlayPoint {
	
	public PlayPoint(Set thisSet, int gameNumber) {
		double numRandom = Math.random()*100;
		if(numRandom < 50.0) {
			thisSet.getGame(gameNumber).addPtsLCL();
		} else {
			thisSet.getGame(gameNumber).addPtsVST();
		}
	}

}
