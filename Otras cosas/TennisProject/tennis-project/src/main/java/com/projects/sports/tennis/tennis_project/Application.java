package com.projects.sports.tennis.tennis_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Application {
	// VARIABLES
	static Integer NUM_PARTIDOS = 0;
	static Integer NUM_SETS = 0;
	static Integer NUM_MUESTRAS = 1000;
	static int puntoLCL, juegoLCL;
	static int puntoVST, juegoVST;
	static boolean partidoTerminado = false;
	static DecimalFormat df = new DecimalFormat("#.00");
	static String array[][] = new String[NUM_MUESTRAS][2];
	static int contTamañoArray = 0;
	static int tieBreak = 0;
	static int ptieBreak = 0;//si es 0 = no es tie, si es 1 sí que es tieBreak

	// public static Match arrayPartidos[] = new Match[NUM_PARTIDOS];
	public static Match arrayPartidos[];
	public static int arrayTotalDeJuegos[];
	// public static Set arraySets[] = new Set[NUM_SETS];
	public static Set arraySets[];

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Número de partidos a simular");
		NUM_PARTIDOS = Integer.parseInt(br.readLine());
		System.out.println("Número de SETS por partido - ¿3 o 5?");
		NUM_SETS = Integer.parseInt(br.readLine());
		arrayPartidos = new Match[NUM_PARTIDOS];
		arrayTotalDeJuegos = new int[NUM_PARTIDOS];
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
			partido.setInicio();
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
			arrayTotalDeJuegos[i] = partido.totalDeJuegos;
		}

		printearResultadoDePartidos(arrayPartidos);
	}
	// **************************************************************************************

	// **************************************************************************************
	public static void calculoSet(Match partido, Set set) {
		while ((juegoLCL != 6) && (juegoVST != 6) && (juegoLCL + juegoVST < 10)) {
			calculoJuego(partido, set);
		}

		if ((juegoLCL == 5) && (juegoVST == 5)) {
			while ((juegoLCL != 7) && (juegoVST != 7)) {
				if ((juegoLCL == 6) && (juegoVST == 6)) {
					calculoTieBreak(partido, set);
				} else {
					calculoJuego(partido, set);
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
	public static void calculoJuego(Match partido, Set set) {
		// calcularemos el juego
		while (((puntoLCL < 4) && (puntoVST < 4)) || (Math.abs(puntoLCL - puntoVST) < 2)) {
			jugarPunto(partido, set, puntoLCL, puntoVST, tieBreak);
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
	public static void calculoTieBreak(Match partido, Set set) {
		tieBreak = 1;
		ptieBreak = 0;
		while (((puntoLCL < 7) && (puntoVST < 7)) || (Math.abs(puntoLCL - puntoVST) < 2)) {
			jugarPunto(partido, set, puntoLCL, puntoVST, ptieBreak);
			ptieBreak++;
		}

		if (puntoLCL > puntoVST) {
			juegoLCL++;
		} else {
			juegoVST++;
		}

		puntoLCL = 0;
		puntoVST = 0;
		tieBreak = 0;
	}
	// **************************************************************************************

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
		probNumJuegosTotalEnPartido();
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
	// **************************************************************************************

	// ************************************************************************************

	/**
	 * Calculo de la probabilidad de que se produzca un resultado.
	 */
	public static void probResultadosExactos(Match match[]) {
		String auxSet1 = null;
		String auxSet2 = null;
		String auxSet3 = null;
		String auxSet4 = null;
		String auxSet5 = null;
		int cont = 0;
		int var = 0;
		int aux_k = 0;
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

				if (cont + NUM_SETS == NUM_PARTIDOS * NUM_SETS) {
					buscamosLosSets_3(cont, resultadoPartido, auxSet1, auxSet2, auxSet3);
				} else {

					for (int k = NUM_SETS + cont; k < NUM_PARTIDOS * NUM_SETS; k++) {
						// Application.print("//------k = NUM_SETS+cont " + k);
						if (k % NUM_SETS == cont % NUM_SETS) {
							if (auxSet1.equals(resultadoPartido.get(k)) && auxSet2.equals(resultadoPartido.get(k + 1))
									&& auxSet3.equals(resultadoPartido.get(k + 2))) {
								// Application.print("//------COMPROBANDO IF ");
								break; // Salimos el bucle y seguimos buscando
										// el último partido con los mismos sets
							} else if (k + NUM_SETS >= NUM_PARTIDOS * NUM_SETS) {
								// No hemos vuelto a encontra coincidencias, por
								// lo que este
								// es el último partido con estos sets
								// Aqui que hay que realizar el conteo hacia
								// atras.
								// desde cont hacia atrás.
								// Application.print("//------COMPROBANDO ELSE
								// IF ");
								buscamosLosSets_3(cont, resultadoPartido, auxSet1, auxSet2, auxSet3);
							} else {
								// Seguimos buscando
								// Application.print("//------COMPROBANDO ELSE
								// ");
							}
						}
					}

				}
				cont += 3;
			}
		} else if (5 == NUM_SETS) {
			for (int i = 0; i < NUM_PARTIDOS; i++) {
				auxSet1 = resultadoPartido.get(cont);
				auxSet2 = resultadoPartido.get(cont + 1);
				auxSet3 = resultadoPartido.get(cont + 2);
				auxSet4 = resultadoPartido.get(cont + 3);
				auxSet5 = resultadoPartido.get(cont + 4);

				// Buscamos el set en todos los sets simulados

				if (cont + NUM_SETS == NUM_PARTIDOS * NUM_SETS) {
					buscamosLosSets_5(cont, resultadoPartido, auxSet1, auxSet2, auxSet3, auxSet4, auxSet5);
				} else {

					for (int k = NUM_SETS + cont; k < NUM_PARTIDOS * NUM_SETS; k++) {
						// Application.print("//------k = NUM_SETS+cont " + k);
						if (k % NUM_SETS == cont % NUM_SETS) {
							if (auxSet1.equals(resultadoPartido.get(k)) && auxSet2.equals(resultadoPartido.get(k + 1))
									&& auxSet3.equals(resultadoPartido.get(k + 2))
									&& auxSet4.equals(resultadoPartido.get(k + 3))
									&& auxSet5.equals(resultadoPartido.get(k + 4))) {
								// Application.print("//------COMPROBANDO IF ");
								break; // Salimos el bucle y seguimos buscando
										// el último partido con los mismos sets
							} else if (k + NUM_SETS >= NUM_PARTIDOS * NUM_SETS) {
								// No hemos vuelto a encontrar coincidencias,
								// por lo que este
								// es el último partido con estos sets
								// Aqui que hay que realizar el conteo hacia
								// atras.
								// desde cont hacia atrás.
								buscamosLosSets_5(cont, resultadoPartido, auxSet1, auxSet2, auxSet3, auxSet4, auxSet5);
							} else {
								// Seguimos buscando
								// Application.print("//------COMPROBANDO ELSE
								// ");
							}
						}
					}

				}
				cont += 5;
			}
		} else {
			Application.print("//--------------Error: Número de Sets no válido");
		}

		printeamosProbResultadosOrdenados();
	}
	// **************************************************************************************

	// ************************************************************************************
	/**
	 * Buscamos los sets de partidos coincidentes con el último que hemos
	 * encontrado.
	 * 
	 * @param cont
	 * @param resultadoPartido
	 */
	public static void buscamosLosSets_3(int conta, ArrayList<String> resultadoPartido, String auxSet1_2,
			String auxSet2_2, String auxSet3_2) {
		int contador = 0;
		double probResultado = 0;
		for (int i = conta; i >= 0; i--) {
			String auxSet1 = resultadoPartido.get(i);
			String auxSet2 = resultadoPartido.get(i + 1);
			String auxSet3 = resultadoPartido.get(i + 2);

			if (i % NUM_SETS == 0 && auxSet1.equals(auxSet1_2) && auxSet2.equals(auxSet2_2)
					&& auxSet3.equals(auxSet3_2)) {
				contador++;
			}
		}

		probResultado = (double) contador / NUM_PARTIDOS * 100;

		array[contTamañoArray][0] = String.valueOf(probResultado);
		array[contTamañoArray][1] = resultadoPartido.get(conta) + " " + resultadoPartido.get(conta + 1) + " "
				+ resultadoPartido.get(conta + 2);
		contTamañoArray++;
	}
	// **************************************************************************************

	// ************************************************************************************
	/**
	 * Buscamos los sets de partidos coincidentes con el último que hemos
	 * encontrado.
	 * 
	 * @param cont
	 * @param resultadoPartido
	 */
	public static void buscamosLosSets_5(int conta, ArrayList<String> resultadoPartido, String auxSet1_2,
			String auxSet2_2, String auxSet3_2, String auxSet4_2, String auxSet5_2) {
		int contador = 0;
		double probResultado = 0;
		// Application.print("//------HEMOS EntRADO EN EL BUSCAMOSLOSSETS con
		// CONT = " +
		// conta);
		for (int i = conta; i >= 0; i--) {
			String auxSet1 = resultadoPartido.get(i);
			String auxSet2 = resultadoPartido.get(i + 1);
			String auxSet3 = resultadoPartido.get(i + 2);
			String auxSet4 = resultadoPartido.get(i + 3);
			String auxSet5 = resultadoPartido.get(i + 4);

			if (i % NUM_SETS == 0 && auxSet1.equals(auxSet1_2) && auxSet2.equals(auxSet2_2) && auxSet3.equals(auxSet3_2)
					&& auxSet4.equals(auxSet4_2) && auxSet5.equals(auxSet5_2)) {
				contador++;
			}
		}

		probResultado = (double) contador / NUM_PARTIDOS * 100;

		array[contTamañoArray][0] = String.valueOf(probResultado);
		array[contTamañoArray][1] = resultadoPartido.get(conta) + " " + resultadoPartido.get(conta + 1) + " "
				+ resultadoPartido.get(conta + 2) + " " + resultadoPartido.get(conta + 3) + " "
				+ resultadoPartido.get(conta + 4);
		contTamañoArray++;
	}
	// **************************************************************************************

	// ************************************************************************************
	/**
	 * Ordenamos y printeamos todos los resultados obtenidos de las funciones
	 * que buscan coincidencias entre sets de partidos.
	 */
	public static void printeamosProbResultadosOrdenados() {
		Double var = 0.00;
		Double aux1 = 0.00;
		Double aux2 = 0.00;
		String auxArray[][] = new String[NUM_MUESTRAS][2];

		/**
		 * Ordenamos
		 */
		for (int i = 0; i < NUM_MUESTRAS - 1; i++) {
			for (int x = i + 1; x < NUM_MUESTRAS; x++) {
				if (array[i][0] == null || array[x][0] == null) {
					break;
				} else {
					aux1 = Double.parseDouble(array[x][0]);
					aux2 = Double.parseDouble(array[i][0]);

					if (Double.compare(aux1, aux2) > 0) {
						auxArray[x][0] = array[i][0];
						auxArray[x][1] = array[i][1];
						array[i][0] = array[x][0];
						array[i][1] = array[x][1];
						array[x][0] = auxArray[x][0];
						array[x][1] = auxArray[x][1];
					} else {
						// Application.print("///NO hacemos nadA");
					}
				}
			}
		}

		Application.print("\n\n//-----------PROBABILIDAD DE RESULTADOS CON SETS");

		/**
		 * Esto es para pintarlos
		 */
		for (int i = 0; i < 10; i++) {
			if (array[i][0] == null) {
				break;
			} else {
				var = Double.parseDouble(array[i][0]);
				Application.print("///PROBABILIDAD:  " + df.format(var) + "%	--->>>	" + array[i][1]);
			}
		}
	}

	// ************************************************************************************
	/**
	 * Se van a ir clasificando el número de juegos totales en los partidos por
	 * OVERS y UNDERS.
	 */
	public static void probNumJuegosTotalEnPartido() {

		// [0]=12.5...[53]=65.5
		Double printOpciones[] = new Double[53]; // 53 = 65 max de juegos menos
													// 12 el minimo de juegos
		Double var = 12.5;
		int[][] overYunder = new int[53][2]; // [0] = over [1] = under
		Double probUnder = 0.00;
		Double probOver = 0.00;

		for (int i = 0; i < 53; i++) {
			printOpciones[i] = var;
			var++;
		}

		for (int i = 0; i < NUM_PARTIDOS; i++) {
			for (int j = 0; j < 53; j++) {

				if (arrayTotalDeJuegos[i] < printOpciones[j]) { // UNDER
					overYunder[j][1] = overYunder[j][1] + 1;
				} else { // OVER
					overYunder[j][0] = overYunder[j][0] + 1;
				}
			}
		}

		Application.print("\n\n//-----------PROBABILIDAD OVER/UNDER");
		// printeamos los resultados
		for (int i = 0; i < 53; i++) {
			probOver = (double) overYunder[i][0] / NUM_PARTIDOS * 100;
			probUnder = (double) overYunder[i][1] / NUM_PARTIDOS * 100;
			Application.print("//----------Prob OVER/UNDER de " + printOpciones[i] + " ->> " + df.format(probOver)
					+ "% - " + df.format(probUnder) + "%");
		}
	}
	// **************************************************************************************

	// **************************************************************************************
	public static void jugarPunto(Match partido, Set set, int pLCL, int pVST, int ptieBreak) {
		// Aquí se ejecutará todo el tema de las estadisticas.

		int saque = 0; // saque = 0 para el saque del LCL y 1 para el del VST
		double aux_ace = 0; // para el calculo prob. de ace
		double aux_doubleFault = 0;
		double aux_breakPoinsSaved = 0;
		double aux_firstServe = 0;
		double aux_secondServe = 0;

		double serveLCL_ace = 4.10;
		double serveLCL_doubleFault = 2.10;
		double serveLCL_1stServe = 68.70;
		double serveLCL_1stServeWon = 71.80;
		double serveLCL_2ndServeWon = 57.20;
		double serveLCL_breakPoinsSaved = 66.10;
//		double serveLCL_ServicePointsWon = 0.00;
//		double serveLCL_ServiceGamesWon = 0.00;

		double serveVST_ace = 4.10;
		double serveVST_doubleFault = 2.10;
		double serveVST_1stServe = 68.70;
		double serveVST_1stServeWon = 71.80;
		double serveVST_2ndServeWon = 57.20;
		double serveVST_breakPoinsSaved = 66.10;
//		double serveVST_ServicePointsWon = 0.00;
//		double serveVST_ServiceGamesWon = 0.00;

		double returnLCL_aceAgainst = 7.20;
		double returnLCL_doubleFaultAgainst = 3.40;
		double returnLCL_1stSrvReturnWon = 34.20;
		double returnLCL_2stSrvReturnWon = 55.30;
		double returnLCL_breakPointsWon = 45.00;
//		double returnLCL_retunrPointsWon = 0.00;
//		double returnLCL_returnGamesWon = 0.00;

		double returnVST_aceAgainst = 7.20;
		double returnVST_doubleFaultAgainst = 3.40;
		double returnVST_1stSrvReturnWon = 34.20;
		double returnVST_2stSrvReturnWon = 55.30;
		double returnVST_breakPointsWon = 45.00;
//		double returnVST_retunrPointsWon = 0.00;
//		double returnVST_returnGamesWon = 0.00;

//		double handicapLCL = 0.00;
//		double handicapVST = 0.00;

		double numRandom = 0.00;

		// Sorteo de saque al inicio de cada
		// partido//////////////////////////////////////////////
		if (partido.inicio == true) {
			numRandom = Math.random() * 100;
			// Application.print("NUMRANDOM = " + numRandom);

			if (numRandom < 50) {
				saque = 0;
				Application.print("Saque para el jugador LOCAL");
			} else {
				saque = 1;
				Application.print("Saque para el jugador VISITANTE");
			}
		}
		//////////////////////////////////////////////////////////////

		if (tieBreak == 0) {
			if (saque == 0) { // Juego con saque para el LCL////////////////
				Application.print("Saca LOCAL");
				numRandom = Math.random() * 100;
				if (serveLCL_1stServe > numRandom) { // Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (serveLCL_ace + returnVST_aceAgainst) / 2;
					if (aux_ace > numRandom) { // ACE!!!
						pLCL++;
						Application.print("ACE!! por el LCL");
					} else { // No hay ACE, se continua el juego
						if (pVST > 2 && (pVST - pLCL) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveLCL_breakPoinsSaved * 100
									/ (serveLCL_breakPoinsSaved + returnVST_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pLCL++;
							} else { // Pierdes el BREAK
								pVST++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_firstServe = serveLCL_1stServeWon * 100
									/ (serveLCL_1stServeWon + returnVST_1stSrvReturnWon);
							if (aux_firstServe > numRandom) { // Gana el LCL con
																// 1st servicio
								pLCL++;
							} else { // Gana el VST con 1st servicio
								pVST++;
							}
						}
					}
				} else { // Pasamos al segundo servicio
					numRandom = Math.random() * 100;
					aux_doubleFault = (serveLCL_doubleFault + returnVST_doubleFaultAgainst) / 2;
					if (aux_doubleFault < numRandom) { // Entra el segundo
														// servicio
						if (pVST > 2 && (pVST - pLCL) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveLCL_breakPoinsSaved * 100
									/ (serveLCL_breakPoinsSaved + returnVST_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pLCL++;
							} else { // Pierdes el BREAK
								pVST++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_secondServe = serveLCL_2ndServeWon * 100
									/ (serveLCL_2ndServeWon + returnVST_2stSrvReturnWon);
							if (aux_secondServe > numRandom) { // Gana el LCL con
																// 2nd
																// servicio
								pLCL++;
							} else { // Gana el VST con 2nd servicio
								pVST++;
							}
						}
					} else { // Ha realizado doble falta
						pVST++;
					}
				}

				puntoLCL = pLCL;
				puntoVST = pVST;

				if ((pLCL > 3 && (pLCL - pVST) > 1) || pVST > 3 && (pVST - pLCL) > 1) {
					if (saque == 0) {
						saque = 1;
					} else {
						saque = 0;
					}
				}
				partido.setFinal();
				
			} else if (saque == 1) { // Juego con saque para el
										// VST////////////////
				Application.print("Saca VISITANTE");
				numRandom = Math.random() * 100;
				if (serveVST_1stServe > numRandom) { // Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (serveVST_ace + returnLCL_aceAgainst) / 2;
					if (aux_ace > numRandom) { // ACE!!!
						pVST++;
						Application.print("ACE!! por el VST");
					} else { // No hay ACE, se continua el juego
						if (pLCL > 2 && (pLCL - pVST) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveVST_breakPoinsSaved * 100
									/ (serveVST_breakPoinsSaved + returnLCL_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pVST++;
							} else { // Pierdes el BREAK
								pLCL++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_firstServe = serveVST_1stServeWon * 100
									/ (serveVST_1stServeWon + returnLCL_1stSrvReturnWon);
							if (aux_firstServe > numRandom) { // Gana el VST con
																// 1st servicio
								pVST++;
							} else { // Gana el LCL con 1st servicio
								pLCL++;
							}
						}
					}
				} else { // Pasamos al segundo servicio
					numRandom = Math.random() * 100;
					aux_doubleFault = (serveVST_doubleFault + returnLCL_doubleFaultAgainst) / 2;
					if (aux_doubleFault < numRandom) { // Entra el segundo
														// servicio
						if (pLCL > 2 && (pLCL - pVST) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveVST_breakPoinsSaved * 100
									/ (serveVST_breakPoinsSaved + returnLCL_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pVST++;
							} else { // Pierdes el BREAK
								pLCL++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_secondServe = serveVST_2ndServeWon * 100
									/ (serveVST_2ndServeWon + returnLCL_2stSrvReturnWon);
							if (aux_secondServe > numRandom) { // Gana el VST con
																// 2nd servicio
								pVST++;
							} else { // Gana el LCL con 2nd servicio
								pLCL++;
							}
						}
					} else { // Ha realizado doble falta
						pLCL++;
					}
				}

				puntoLCL = pLCL;
				puntoVST = pVST;

				if ((pLCL > 3 && (pLCL - pVST) > 1) || pVST > 3 && (pVST - pLCL) > 1) {
					if (saque == 0) {
						saque = 1;
					} else {
						saque = 0;
					}
				}
				partido.setFinal();
			}
		} else { 										// Calculo del punto cuando es tieBreak////////////////////////////////////////
			if (saque == 0) { // Juego con saque para el LCL////////////////
				numRandom = Math.random() * 100;
				if (serveLCL_1stServe > numRandom) { // Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (serveLCL_ace + returnVST_aceAgainst) / 2;
					if (aux_ace > numRandom) { // ACE!!!
						pLCL++;
						Application.print("ACE!! por el LCL");
					} else { // No hay ACE, se continua el juego
						if (pVST > 2 && (pVST - pLCL) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveLCL_breakPoinsSaved * 100
									/ (serveLCL_breakPoinsSaved + returnVST_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pLCL++;
							} else { // Pierdes el BREAK
								pVST++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_firstServe = serveLCL_1stServeWon * 100
									/ (serveLCL_1stServeWon + returnVST_1stSrvReturnWon);
							if (aux_firstServe > numRandom) { // Gana el LCL con
																// 1st servicio
								pLCL++;
							} else { // Gana el VST con 1st servicio
								pVST++;
							}
						}
					}
				} else { // Pasamos al segundo servicio
					numRandom = Math.random() * 100;
					aux_doubleFault = (serveLCL_doubleFault + returnVST_doubleFaultAgainst) / 2;
					if (aux_doubleFault < numRandom) { // Entra el segundo
														// servicio
						if (pVST > 2 && (pVST - pLCL) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveLCL_breakPoinsSaved * 100
									/ (serveLCL_breakPoinsSaved + returnVST_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pLCL++;
							} else { // Pierdes el BREAK
								pVST++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_secondServe = serveLCL_2ndServeWon * 100
									/ (serveLCL_2ndServeWon + returnVST_2stSrvReturnWon);
							if (aux_secondServe > numRandom) { // Gana el LCL con
																// 2nd
																// servicio
								pLCL++;
							} else { // Gana el VST con 2nd servicio
								pVST++;
							}
						}
					} else { // Ha realizado doble falta
						pVST++;
					}
				}

				puntoLCL = pLCL;
				puntoVST = pVST;

				if (ptieBreak%2 == 0) {
					if (saque == 0) {
						saque = 1;
					} else {
						saque = 0;
					}
				}
				partido.setFinal();
				
			} else if (saque == 1) { // Juego con saque para el
										// VST////////////////
				numRandom = Math.random() * 100;
				if (serveVST_1stServe > numRandom) { // Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (serveVST_ace + returnLCL_aceAgainst) / 2;
					if (aux_ace > numRandom) { // ACE!!!
						pVST++;
						Application.print("ACE!! por el VST");
					} else { // No hay ACE, se continua el juego
						if (pLCL > 2 && (pLCL - pVST) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveVST_breakPoinsSaved * 100
									/ (serveVST_breakPoinsSaved + returnLCL_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pVST++;
							} else { // Pierdes el BREAK
								pLCL++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_firstServe = serveVST_1stServeWon * 100
									/ (serveVST_1stServeWon + returnLCL_1stSrvReturnWon);
							if (aux_firstServe > numRandom) { // Gana el VST con
																// 1st servicio
								pVST++;
							} else { // Gana el LCL con 1st servicio
								pLCL++;
							}
						}
					}
				} else { // Pasamos al segundo servicio
					numRandom = Math.random() * 100;
					aux_doubleFault = (serveVST_doubleFault + returnLCL_doubleFaultAgainst) / 2;
					if (aux_doubleFault < numRandom) { // Entra el segundo
														// servicio
						if (pLCL > 2 && (pLCL - pVST) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPoinsSaved = serveVST_breakPoinsSaved * 100
									/ (serveVST_breakPoinsSaved + returnLCL_breakPointsWon);
							if (aux_breakPoinsSaved > numRandom) { // Salvas el
																	// BREAK
								pVST++;
							} else { // Pierdes el BREAK
								pLCL++;
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_secondServe = serveVST_2ndServeWon * 100
									/ (serveVST_2ndServeWon + returnLCL_2stSrvReturnWon);
							if (aux_secondServe > numRandom) { // Gana el VST con
																// 2nd servicio
								pVST++;
							} else { // Gana el LCL con 2nd servicio
								pLCL++;
							}
						}
					} else { // Ha realizado doble falta
						pLCL++;
					}
				}

				puntoLCL = pLCL;
				puntoVST = pVST;

				if (ptieBreak%2 == 0) {
					if (saque == 0) {
						saque = 1;
					} else {
						saque = 0;
					}
				}
				partido.setFinal();
			}
		}
	}
}
