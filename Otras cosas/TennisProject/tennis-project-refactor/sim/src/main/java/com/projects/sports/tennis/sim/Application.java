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
	
	public static Match arrayMatchs[];
	public static boolean tieBreak = false; 
	public static String select;
	public static int kick = 0; //saque
	
	
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
		
		arrayMatchs = new Match[NUM_PARTIDOS];
		
		new StadisticsInput(select);
		simStart();
		CalculateStadisticsMatch calculate = new CalculateStadisticsMatch();
		calculate.probVictoria();
		calculate.probRestuladoDePartido();
		calculate.probResultadosExactos(arrayMatchs);
		calculate.probNumJuegosTotalEnPartido();
		
		pr(arrayMatchs[0].getSet(0).getPtsGameLCL() + " - " + arrayMatchs[0].getSet(0).getPtsGameVST());
		pr(arrayMatchs[0].getSet(1).getPtsGameLCL() + " - " + arrayMatchs[0].getSet(1).getPtsGameVST());
		pr(arrayMatchs[0].getSet(2).getPtsGameLCL() + " - " + arrayMatchs[0].getSet(2).getPtsGameVST());
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
			arrayMatchs[i] = new Match(MAX_NUM_SETS);

			for (int j = 0; j < MAX_NUM_SETS; j++) {
				pr("----------Set " + (j+1));
				if (arrayMatchs[i].getPtsSetLCL() < (MAX_NUM_SETS/2 + 1) && arrayMatchs[i].getPtsSetVST() < (MAX_NUM_SETS/2 + 1)){
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
		while ((arrayMatchs[i].getSet(j).getPtsGameLCL() != 6) && (arrayMatchs[i].getSet(j).getPtsGameVST() != 6)
				&& (arrayMatchs[i].getSet(j).getPtsGameLCL() + arrayMatchs[i].getSet(j).getPtsGameVST() < 10)) {
			calculateGame(i, j, gameNumber);
			gameNumber++;
		}

		if ((arrayMatchs[i].getSet(j).getPtsGameLCL() == 5) && (arrayMatchs[i].getSet(j).getPtsGameVST() == 5)) {
			while((arrayMatchs[i].getSet(j).getPtsGameLCL() !=7) && (arrayMatchs[i].getSet(j).getPtsGameVST() != 7)) {
				if((arrayMatchs[i].getSet(j).getPtsGameLCL() == 6) && (arrayMatchs[i].getSet(j).getPtsGameVST() == 6)) {
					calculateTieBreak(i, j, gameNumber);
				}else {
					calculateGame(i, j, gameNumber);
					gameNumber++;
				}
			}
		}
		
		if (arrayMatchs[i].getSet(j).getPtsGameLCL() > arrayMatchs[i].getSet(j).getPtsGameVST()) {
			arrayMatchs[i].addPtsSetLCL();	//Set para el local		
		} else {
			arrayMatchs[i].addPtsSetVST();	//Set para el visitante
		}
	}

	// **************************************************************************************

	// **************************************************************************************

	private static void calculateTieBreak(int i, int j, int gameNumber) {
		// TODO Auto-generated method stub
		tieBreak = true;
		
		while (((arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsLCL() < 7) && (arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsVST() < 7))
			|| (Math.abs(arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsLCL()-arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsVST()) < 2)) {
			new PlayPoint(arrayMatchs[i].getSet(j), gameNumber);
//			playPoint(arrayMatchs[i].getSet(j), gameNumber);
		}
		
		if(arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsLCL() > arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsVST()) {
			arrayMatchs[i].getSet(j).addPtsGameLCL();  //juego para el jugador LOCAL
		} else {
			arrayMatchs[i].getSet(j).addPtsGameVST();  //juego para el jugador VISITANTE
		}
		
		tieBreak = false;
	}

	// **************************************************************************************

	// **************************************************************************************
	
	private static void calculateGame(int i, int j, int gameNumber) {
		// TODO Auto-generated method stub
		while (((arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsLCL()) < 4 && (arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsVST() < 4)) 
				|| (Math.abs(arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsLCL() - arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsVST()) < 2)) {
			new PlayPoint(arrayMatchs[i].getSet(j), gameNumber);
//			playPoint(arrayMatchs[i].getSet(j), gameNumber);
		}
		
		if(arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsLCL() > arrayMatchs[i].getSet(j).getGame(gameNumber).getPtsVST()) {
			arrayMatchs[i].getSet(j).addPtsGameLCL();  //juego para el jugador LOCAL
		} else {
			arrayMatchs[i].getSet(j).addPtsGameVST();  //juego para el jugador VISITANTE
		}
	}
	
	// **************************************************************************************

	// **************************************************************************************
}
