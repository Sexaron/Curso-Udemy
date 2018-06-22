package com.projects.sports.tennis.sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import com.projects.sports.tennis.sim.Match;

public class Application 
{
	
	private static int NUM_PARTIDOS;
	private static int MAX_NUM_SETS;
	public static int MAX_NUM_GAMES = 13;
	
	public static Match arrayMatches[];
	public static boolean tieBreak = false; 
	public static String select;
	public static int kick = 0; //saque
	static DecimalFormat df = new DecimalFormat("#.00");
	
	
    public static void main( String[] args ) throws IllegalArgumentException, IOException
    {
    	//Petición de datos
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pr("Número de partidos a simular");
		NUM_PARTIDOS = Integer.parseInt(br.readLine());
		pr("Número de SETS por partido - ¿3 o 5?");
		MAX_NUM_SETS = Integer.parseInt(br.readLine());
		
		pr("¿Introducir a mano las estadísticas? S/N");
		select = br.readLine().toUpperCase();
		
		arrayMatches = new Match[NUM_PARTIDOS];
		
		new StadisticsInput(select);
		simStart();
		new PrintMatches(arrayMatches);
		CalculateStadisticsMatch calculate = new CalculateStadisticsMatch(arrayMatches);
		calculate.probVictoria();
		calculate.probRestuladoDePartido();
		calculate.probResultadosExactos();
		calculate.probNumJuegosTotalEnPartido();
    }    

	// **************************************************************************************

	// **************************************************************************************

	/**
	 * Para printear por consola
	 */
	public static void pr(String frase) {
		System.out.println(frase);
	}
	
	public static void prl(String frase) {
		System.out.print(frase);
	}
	
	// **************************************************************************************

	// **************************************************************************************
	
	/**
	 * Simulación de todo los partidos
	 */

	private static void simStart() {
		// TODO Auto-generated method stub
		for (int i = 0; i < NUM_PARTIDOS; i++) {
			arrayMatches[i] = new Match(MAX_NUM_SETS);

			for (int j = 0; j < MAX_NUM_SETS; j++) {
				if (arrayMatches[i].getPtsSetLCL() < (MAX_NUM_SETS/2 + 1) && arrayMatches[i].getPtsSetVST() < (MAX_NUM_SETS/2 + 1)){
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
		while ((arrayMatches[i].getSet(j).getPtsGameLCL() != 6) && (arrayMatches[i].getSet(j).getPtsGameVST() != 6)
				&& (arrayMatches[i].getSet(j).getPtsGameLCL() + arrayMatches[i].getSet(j).getPtsGameVST() < 10)) {
			calculateGame(i, j, gameNumber);
			gameNumber++;
		}

		if ((arrayMatches[i].getSet(j).getPtsGameLCL() == 5) && (arrayMatches[i].getSet(j).getPtsGameVST() == 5)) {
			while((arrayMatches[i].getSet(j).getPtsGameLCL() !=7) && (arrayMatches[i].getSet(j).getPtsGameVST() != 7)) {
				if((arrayMatches[i].getSet(j).getPtsGameLCL() == 6) && (arrayMatches[i].getSet(j).getPtsGameVST() == 6)) {
					calculateTieBreak(i, j, gameNumber);
				}else {
					calculateGame(i, j, gameNumber);
					gameNumber++;
				}
			}
		}
		
		if (arrayMatches[i].getSet(j).getPtsGameLCL() > arrayMatches[i].getSet(j).getPtsGameVST()) {
			arrayMatches[i].addPtsSetLCL();	//Set para el local		
		} else {
			arrayMatches[i].addPtsSetVST();	//Set para el visitante
		}
	}

	// **************************************************************************************

	// **************************************************************************************

	private static void calculateTieBreak(int i, int j, int gameNumber) {
		// TODO Auto-generated method stub
		tieBreak = true;
		
		while (((arrayMatches[i].getSet(j).getGame(gameNumber).getPtsLCL() < 7) && (arrayMatches[i].getSet(j).getGame(gameNumber).getPtsVST() < 7))
			|| (Math.abs(arrayMatches[i].getSet(j).getGame(gameNumber).getPtsLCL()-arrayMatches[i].getSet(j).getGame(gameNumber).getPtsVST()) < 2)) {
			new PlayPoint(arrayMatches[i].getSet(j), gameNumber);
//			playPoint(arrayMatchs[i].getSet(j), gameNumber);
		}
		
		if(arrayMatches[i].getSet(j).getGame(gameNumber).getPtsLCL() > arrayMatches[i].getSet(j).getGame(gameNumber).getPtsVST()) {
			arrayMatches[i].getSet(j).addPtsGameLCL();  //juego para el jugador LOCAL
		} else {
			arrayMatches[i].getSet(j).addPtsGameVST();  //juego para el jugador VISITANTE
		}
		
		tieBreak = false;
	}

	// **************************************************************************************

	// **************************************************************************************
	
	private static void calculateGame(int i, int j, int gameNumber) {
		// TODO Auto-generated method stub
		while (((arrayMatches[i].getSet(j).getGame(gameNumber).getPtsLCL()) < 4 && (arrayMatches[i].getSet(j).getGame(gameNumber).getPtsVST() < 4)) 
				|| (Math.abs(arrayMatches[i].getSet(j).getGame(gameNumber).getPtsLCL() - arrayMatches[i].getSet(j).getGame(gameNumber).getPtsVST()) < 2)) {
			new PlayPoint(arrayMatches[i].getSet(j), gameNumber);
//			playPoint(arrayMatchs[i].getSet(j), gameNumber);
		}
		
		if(arrayMatches[i].getSet(j).getGame(gameNumber).getPtsLCL() > arrayMatches[i].getSet(j).getGame(gameNumber).getPtsVST()) {
			arrayMatches[i].getSet(j).addPtsGameLCL();  //juego para el jugador LOCAL
		} else {
			arrayMatches[i].getSet(j).addPtsGameVST();  //juego para el jugador VISITANTE
		}
	}
	
	// **************************************************************************************

	// **************************************************************************************
}
