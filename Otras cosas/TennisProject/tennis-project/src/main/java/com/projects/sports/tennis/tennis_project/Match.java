package com.projects.sports.tennis.tennis_project;

public class Match {

	/**
	 * Definimos las variables usadas en la clase
	**/
	
	public final int NUM_DE_SETS_POR_PARTIDO = 3; 
	public Set set[];
	
	
	public Set[] getSet() {
		return set;
	}
	public void setSet(Set[] set) {
		this.set = set;
	}
	public int getNUM_DE_SETS_POR_PARTIDO() {
		return NUM_DE_SETS_POR_PARTIDO;
	}
	
	
}
