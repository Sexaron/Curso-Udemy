package com.projects.sports.tennis.tennis_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application {
	// VARIABLES
	static Integer NUM_PARTIDOS = 0;
	static Integer NUM_SETS = 0;
	static int puntoLCL, juegoLCL;
	static int puntoVST, juegoVST;
	static boolean partidoTerminado = false;
	static DecimalFormat df = new DecimalFormat("#.00");

	// public static Match arrayPartidos[] = new Match[NUM_PARTIDOS];
	public static Match arrayPartidos[];
	// public static Set arraySets[] = new Set[NUM_SETS];
	public static Set arraySets[];

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Número de partidos a simular");
		NUM_PARTIDOS = Integer.parseInt(br.readLine());
		System.out.println("Número de SETS por partido - ¿3 o 5?");
		NUM_SETS = Integer.parseInt(br.readLine());
		arrayPartidos = new Match[NUM_PARTIDOS];
		arraySets = new Set[NUM_SETS];

		// Application.print("//--------------Entra en el main");
		simStart();
		calculoEstadisticasPartidos(arrayPartidos);

	}

	// **************************************************************************************
	public static void simStart() {

		for (int i = 0; i < NUM_PARTIDOS; i++) {
			// Application.print("//--------------COMIENZA NUEVO PARTIDO");
			Match partido = new Match();
			partidoTerminado = false;
			for (int j = 0; j < NUM_SETS; j++) {
				// Application.print("//-----------VALOR DE J = " + j);
				if (partido.setsWonByLCL < (NUM_SETS / 2 + 1) && partido.setsWonByVST < (NUM_SETS / 2 + 1)) {
					Set set = new Set();
					calculoSet(partido, set);
					arraySets[j] = set; // diria q no sirve para nada de momento
										// arraySets
					partido.updateSets(set); // hay q ver como metemos los sets
												// al partido.
				} else {
					partidoTerminado = true;
					Set set = new Set();
					partido.updateSets(set);
				}
			}

			if (partido.setsWonByLCL > partido.setsWonByVST) {
				partido.setWonLCL();
			} else if (partido.setsWonByLCL < partido.setsWonByVST) {
				partido.setWonVST();
			} else {
				Application.print("//-------------ERROR: no ha ganado nadie.");
			}
			arrayPartidos[i] = partido;
		}

		printearResultadoDePartidos(arrayPartidos);
	}
	// **************************************************************************************

	// **************************************************************************************
	public static void calculoSet(Match partido, Set set) {
		while ((juegoLCL != 6) && (juegoVST != 6) && (juegoLCL + juegoVST < 10)) {
			calculoJuego();
		}

		if ((juegoLCL == 5) && (juegoVST == 5)) {
			while ((juegoLCL != 7) && (juegoVST != 7)) {
				if ((juegoLCL == 6) && (juegoVST == 6)) {
					calculoTieBreak();
				} else {
					calculoJuego();
				}
			}
		}

		set.setSetLCL(juegoLCL);
		set.setSetVST(juegoVST);

		if (juegoLCL > juegoVST) {
			// Set para el local
			partido.setSetsWonByLCL();
			juegoLCL = 0;
			juegoVST = 0;
		} else {
			// Set para el visitante
			partido.setSetsWonByVST();
			juegoLCL = 0;
			juegoVST = 0;
		}
	}
	// **************************************************************************************

	// **************************************************************************************
	public static void calculoJuego() {
		// calcularemos el juego
		while (((puntoLCL < 4) && (puntoVST < 4)) || (Math.abs(puntoLCL - puntoVST) < 2)) {
			jugarPunto(puntoLCL, puntoVST);
		}

		if (puntoLCL > puntoVST) {
			juegoLCL++;
		} else {
			juegoVST++;
		}

		puntoLCL = 0;
		puntoVST = 0;
	}
	// **************************************************************************************

	// **************************************************************************************
	public static void calculoTieBreak() {
		while (((puntoLCL < 7) && (puntoVST < 7)) || (Math.abs(puntoLCL - puntoVST) < 2)) {
			jugarPunto(puntoLCL, puntoVST);
		}

		if (puntoLCL > puntoVST) {
			juegoLCL++;
		} else {
			juegoVST++;
		}

		puntoLCL = 0;
		puntoVST = 0;
	}
	// **************************************************************************************

	// **************************************************************************************
	public static void jugarPunto(int pLCL, int pVST) {
		// Aquí se ejecutará todo el tema de las estadisticas.
		double numRandom = Math.random() * 100;
		if (numRandom < 50) {
			pLCL++;
		} else {
			pVST++;
		}
		puntoLCL = pLCL;
		puntoVST = pVST;
	}
	// **************************************************************************************

	// ************************************************************************************
	public static void printearResultadoDePartidos(Match match[]) {
		for (int i = 0; i < NUM_PARTIDOS; i++) {
			ArrayList<String> resultadoPartido = new ArrayList<String>();
			for (int j = 0; j < NUM_SETS; j++) {
				if (j == 0) {
					Application.print("//---------PARTIDO " + (i + 1) + "----------//");
					resultadoPartido.add(match[i].getSet(j));
				} else {
					resultadoPartido.add(match[i].getSet(j));
				}
			}

			Application.print(resultadoPartido.toString());
		}
	}
	// **************************************************************************************

	// ************************************************************************************

	/**
	 * Para printear por consola
	 */
	public static void print(String frase) {
		System.out.println(frase);
	}
	// **************************************************************************************

	// ************************************************************************************
	/**
	 * Calculo de todas las probabilidades
	 */
	private static void calculoEstadisticasPartidos(Match match[]) {
		probVictoria();
		probRestuladoDePartido();
		probResultadosExactos(match);
	}
	// **************************************************************************************

	// ************************************************************************************

	/**
	 * Calculo de la probabilidad de que gane uno u otro según los resultados de
	 * los partidos.
	 */
	public static void probVictoria() {
		Application.print("\n\n//------PROBABILIDAD DE VICTORIA-------//");
		int victoriasLCL = 0;
		int victoriasVST = 0;
		double probWinLCL = 0;
		double probWinVST = 0;

		for (int i = 0; i < NUM_PARTIDOS; i++) {
			victoriasLCL += arrayPartidos[i].wonLCL;
			victoriasVST += arrayPartidos[i].wonVST;
		}

		probWinLCL = (double) victoriasLCL / NUM_PARTIDOS * 100;
		probWinVST = (double) victoriasVST / NUM_PARTIDOS * 100;

		Application.print("//------Victorias del jugador LOCAL: " + victoriasLCL);
		Application.print("//------Victorias del jugador VISITANTE: " + victoriasVST);
		Application.print("//------PROBABILIDAD DE VICTORIA LOCAL: " + df.format(probWinLCL) + "%");
		Application.print("//------PROBABILIDAD DE VICTORIA VISITANTE: " + df.format(probWinVST) + "%");
	}
	// **************************************************************************************

	// ************************************************************************************

	/**
	 * Calculo de la probabilidad de que se produzca un resultado.
	 */
	public static void probRestuladoDePartido() {
		Application.print("\n\n//------PROBABILIDAD DE RESULTADO-------//");
		int auxResultadoLCL, auxResultadoVST;

		if (5 == NUM_SETS) {
			int result_3_0 = 0;
			int result_3_1 = 0;
			int result_3_2 = 0;
			int result_0_3 = 0;
			int result_1_3 = 0;
			int result_2_3 = 0;
			double prob_3_0 = 0;
			double prob_3_1 = 0;
			double prob_3_2 = 0;
			double prob_0_3 = 0;
			double prob_1_3 = 0;
			double prob_2_3 = 0;

			for (int i = 0; i < NUM_PARTIDOS; i++) {
				auxResultadoLCL = arrayPartidos[i].setsWonByLCL;
				auxResultadoVST = arrayPartidos[i].setsWonByVST;
				int auxResult = auxResultadoLCL - auxResultadoVST;

				switch (auxResult) {
				case 3:
					result_3_0++;
					break;
				case 2:
					result_3_1++;
					break;
				case 1:
					result_3_2++;
					break;
				case -3:
					result_0_3++;
					break;
				case -2:
					result_1_3++;
					break;
				case -1:
					result_2_3++;
					break;
				default:
					Application.print("Default value");
					break;
				}
			}

			prob_3_0 = (double) result_3_0 / NUM_PARTIDOS * 100;
			prob_3_1 = (double) result_3_1 / NUM_PARTIDOS * 100;
			prob_3_2 = (double) result_3_2 / NUM_PARTIDOS * 100;
			prob_0_3 = (double) result_0_3 / NUM_PARTIDOS * 100;
			prob_1_3 = (double) result_1_3 / NUM_PARTIDOS * 100;
			prob_2_3 = (double) result_2_3 / NUM_PARTIDOS * 100;

			Application.print("PROBABILIDAD DE UN 3-0: " + df.format(prob_3_0) + "%");
			Application.print("PROBABILIDAD DE UN 3-1: " + df.format(prob_3_1) + "%");
			Application.print("PROBABILIDAD DE UN 3-2: " + df.format(prob_3_2) + "%");
			Application.print("PROBABILIDAD DE UN 0-3: " + df.format(prob_0_3) + "%");
			Application.print("PROBABILIDAD DE UN 1-3: " + df.format(prob_1_3) + "%");
			Application.print("PROBABILIDAD DE UN 2-3: " + df.format(prob_2_3) + "%");
		} else if (3 == NUM_SETS) {
			int result_2_0 = 0;
			int result_2_1 = 0;
			int result_0_2 = 0;
			int result_1_2 = 0;
			double prob_2_0 = 0;
			double prob_2_1 = 0;
			double prob_0_2 = 0;
			double prob_1_2 = 0;

			for (int i = 0; i < NUM_PARTIDOS; i++) {
				auxResultadoLCL = arrayPartidos[i].setsWonByLCL;
				auxResultadoVST = arrayPartidos[i].setsWonByVST;
				int auxResult = auxResultadoLCL - auxResultadoVST;

				switch (auxResult) {
				case 2:
					result_2_0++;
					break;
				case 1:
					result_2_1++;
					break;
				case -2:
					result_0_2++;
					break;
				case -1:
					result_1_2++;
					break;
				default:
					Application.print("Default value");
					break;
				}
			}

			prob_2_0 = (double) result_2_0 / NUM_PARTIDOS * 100;
			prob_2_1 = (double) result_2_1 / NUM_PARTIDOS * 100;
			prob_0_2 = (double) result_0_2 / NUM_PARTIDOS * 100;
			prob_1_2 = (double) result_1_2 / NUM_PARTIDOS * 100;

			Application.print("PROBABILIDAD DE UN 2-0: " + df.format(prob_2_0) + "%");
			Application.print("PROBABILIDAD DE UN 2-1: " + df.format(prob_2_1) + "%");
			Application.print("PROBABILIDAD DE UN 0-2: " + df.format(prob_0_2) + "%");
			Application.print("PROBABILIDAD DE UN 1-2: " + df.format(prob_1_2) + "%");
		} else {
			Application.print("//------------------ERROR: Los sets son diferentes a 3 y 5!!");
		}

	}
	//************************************************************************************** 
		
	//************************************************************************************ 
		  
		  /**
		   * Calculo de la probabilidad de que se produzca un resultado.
		  */
	public static void probResultadosExactos(Match match[]) {
		String auxSet1 = null;
		String auxSet2 = null;
		String auxSet3 = null;
		int cont = 0;
		int var = 0;
		boolean varRepite = false;
		ArrayList<String> resultadoPartido = new ArrayList<String>();

		for (int i = 0; i < NUM_PARTIDOS; i++) {
			for (int j = 0; j < NUM_SETS; j++) {
				// Creamos nuestra arrayList con todos los sets seguidos
				// para a continuación recorrerlo en busca de coincidencias
				resultadoPartido.add(match[i].getSet(j));
			}
		}

		if (3 == NUM_SETS) {
			for (int i = 0; i < NUM_PARTIDOS; i++) {
				auxSet1 = resultadoPartido.get(cont);
				auxSet2 = resultadoPartido.get(cont + 1);
				auxSet3 = resultadoPartido.get(cont + 2);

				// Buscamos el set en todos los sets simulados
				for (int k = NUM_SETS+cont; k < NUM_PARTIDOS * NUM_SETS; k++) {
					if (k%NUM_SETS == cont%NUM_SETS){
						if (auxSet1 == resultadoPartido.get(k) 
								&& auxSet2 == resultadoPartido.get(k+1)
								&& auxSet3 == resultadoPartido.get(k+2)){
							break; //Salimos el bucle y seguimos buscando el último partido con los mismos sets						
						}else if (k+NUM_SETS > NUM_PARTIDOS*NUM_SETS){
							//No hemos vuelto a encontra coincidencias, por lo que este
							//es el último partido con estos sets
							//Aqui que hay que realizar el conteo hacia atras.
							//desde cont hacia atrás.
							buscamosLosSets(cont, resultadoPartido);
						}else{
							//Seguimos buscando
						}
					}
				}
				cont += 3;
			}
		}
	}
	//************************************************************************************** 
	
	//************************************************************************************ 
	/**
	 * Buscamos los sets de partidos coincidentes con el último que hemos encontrado.
	 * @param cont
	 * @param resultadoPartido
	 * @return
	 */
	public static int buscamosLosSets(int cont, ArrayList<String> resultadoPartido){
		int contador = 0;
		for(int i=cont; i >= 0; i--){
			String auxSet1 = resultadoPartido.get(i);
			String auxSet2 = resultadoPartido.get(i+1);
			String auxSet3 = resultadoPartido.get(i+2);
			
			if(i%NUM_SETS == 0 
					&& auxSet1 == resultadoPartido.get(i) 
					&& auxSet2 == resultadoPartido.get(i+1)
					&& auxSet3 == resultadoPartido.get(i+2)){
				contador++;
			}
		}
		return 0;
	}

}
