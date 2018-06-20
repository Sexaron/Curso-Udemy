package com.projects.sports.tennis.sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.projects.sports.tennis.sim.Match;

public class Application 
{
	
	private static int NUM_PARTIDOS;
	private static int MAX_NUM_SETS;
	public static int MAX_NUM_GAMES = 13;
	
	public static Match arrayPartidos[];
	public static boolean tieBreak = false; 
	
	
    public static void main( String[] args ) throws IllegalArgumentException, IOException
    {
    	//Petición de datos
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pr("Número de partidos a simular");
		NUM_PARTIDOS = Integer.parseInt(br.readLine());
		pr("Número de SETS por partido - ¿3 o 5?");
		MAX_NUM_SETS = Integer.parseInt(br.readLine());
		
		arrayPartidos = new Match[NUM_PARTIDOS];
		
		simStart();
		calculateStadisticsMatch();
    }    

	// **************************************************************************************

	// **************************************************************************************

	/**
	 * Para printear por consola
	 */
	public static void pr(String frase) {
		System.out.println(frase);
	}
	
	// **************************************************************************************

	// **************************************************************************************
	
	/**
	 * Simulación de todo los partidos
	 */

	private static void simStart() {
		// TODO Auto-generated method stub
		for (int i = 0; i < NUM_PARTIDOS; i++) {
			pr("//----------------PARTIDO " + (i+1));
			arrayPartidos[i] = new Match(MAX_NUM_SETS);

			for (int j = 0; j < MAX_NUM_SETS; j++) {
				pr("----------Set " + (j+1));
				if (arrayPartidos[i].getPtsSetLCL() < (MAX_NUM_SETS/2 + 1) && arrayPartidos[i].getPtsSetVST() < (MAX_NUM_SETS/2 + 1)){
					calculateSet(i, j);
				}				
			}
		}
	}

	// **************************************************************************************

	// **************************************************************************************
	
	private static void calculateSet(int i, int j) {
		// TODO Auto-generated method stub
		int gameNumber = 0;
		while ((arrayPartidos[i].getSet(j).getPtsGameLCL() != 6) && (arrayPartidos[i].getSet(j).getPtsGameVST() != 6)
				&& (arrayPartidos[i].getSet(j).getPtsGameLCL() + arrayPartidos[i].getSet(j).getPtsGameVST() < 10)) {
			calculateGame(i, j, gameNumber);
			gameNumber++;
		}

		if ((arrayPartidos[i].getSet(j).getPtsGameLCL() == 5) && (arrayPartidos[i].getSet(j).getPtsGameVST() == 5)) {
			while((arrayPartidos[i].getSet(j).getPtsGameLCL() !=7) && (arrayPartidos[i].getSet(j).getPtsGameVST() != 7)) {
				if((arrayPartidos[i].getSet(j).getPtsGameLCL() == 6) && (arrayPartidos[i].getSet(j).getPtsGameVST() == 6)) {
					calculateTieBreak(i, j, gameNumber);
				}else {
					calculateGame(i, j, gameNumber);
					gameNumber++;
				}
			}
		}
		
		if (arrayPartidos[i].getSet(j).getPtsGameLCL() > arrayPartidos[i].getSet(j).getPtsGameVST()) {
			arrayPartidos[i].addPtsSetLCL();	//Set para el local		
		} else {
			arrayPartidos[i].addPtsSetVST();	//Set para el visitante
		}
	}

	// **************************************************************************************

	// **************************************************************************************

	private static void calculateTieBreak(int i, int j, int gameNumber) {
		// TODO Auto-generated method stub
		tieBreak = true;
		
		while (((arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsLCL() < 7) && (arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsVST() < 7))
			|| (Math.abs(arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsLCL()-arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsVST()) < 2)) {
			playPoint(arrayPartidos[i].getSet(j), gameNumber);
		}
		
		if(arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsLCL() > arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsVST()) {
			arrayPartidos[i].getSet(j).addPtsGameLCL();  //juego para el jugador LOCAL
		} else {
			arrayPartidos[i].getSet(j).addPtsGameVST();  //juego para el jugador VISITANTE
		}
		
		tieBreak = false;
	}

	// **************************************************************************************

	// **************************************************************************************
	
	private static void calculateGame(int i, int j, int gameNumber) {
		// TODO Auto-generated method stub
		while (((arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsLCL()) < 4 && (arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsVST() < 4)) 
				|| (Math.abs(arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsLCL() - arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsVST()) < 2)) {
			playPoint(arrayPartidos[i].getSet(j), gameNumber);
		}
		
		if(arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsLCL() > arrayPartidos[i].getSet(j).getGame(gameNumber).getPtsVST()) {
			arrayPartidos[i].getSet(j).addPtsGameLCL();  //juego para el jugador LOCAL
		} else {
			arrayPartidos[i].getSet(j).addPtsGameVST();  //juego para el jugador VISITANTE
		}
	}

	// **************************************************************************************

	// **************************************************************************************
	
	private static void playPoint(Set thisSet, int gameNumber) {
		// TODO Auto-generated method stub
		double numRandom = Math.random()*100;
		if(numRandom < 50.0) {
			thisSet.getGame(gameNumber).addPtsLCL();
		} else {
			thisSet.getGame(gameNumber).addPtsVST();
		}
	}
	
	// **************************************************************************************

	// **************************************************************************************

	/**
	 * Realizamos el cálculo según las estadísticas de cada jugador
	 */
	
	private static void calculateStadisticsMatch() {
		// TODO Auto-generated method stub
		
	}
	
}
