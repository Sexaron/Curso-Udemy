/**
 * Con esta clase se quiere poder hacer el recalculo del handicap para posteriormente aplicarlo
 * en otros partidos.
 */

package com.projects.sports.tennis.sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class recalculate extends Application {
	
	private boolean check = false;
	
	public recalculate() throws IllegalArgumentException, IOException {
		new StadisticsInput("N");
		StadisticsInput.handicapLCL = 1;
		StadisticsInput.handicapVST = 1;
		
		double aux_serveLCL_1stServeWon = StadisticsInput.serveLCL_1stServeWon;
		double aux_serveLCL_2ndServeWon = StadisticsInput.serveLCL_2ndServeWon;
		double aux_serveLCL_breakPointsSaved = StadisticsInput.serveLCL_breakPointsSaved;
		double aux_returnLCL_1stSrvReturnWon = StadisticsInput.returnLCL_1stSrvReturnWon;
		double aux_returnLCL_2ndSrvReturnWon = StadisticsInput.returnLCL_2ndSrvReturnWon;
		double aux_returnLCL_breakPointWon = StadisticsInput.returnLCL_breakPointsWon;
		
		double aux_serveVST_1stServeWon = StadisticsInput.serveVST_1stServeWon;
		double aux_serveVST_2ndServeWon = StadisticsInput.serveVST_2ndServeWon;
		double aux_serveVST_breakPointsSaved = StadisticsInput.serveVST_breakPointsSaved;
		double aux_returnVST_1stSrvReturnWon = StadisticsInput.returnVST_1stSrvReturnWon;
		double aux_returnVST_2ndSrvReturnWon = StadisticsInput.returnVST_2ndSrvReturnWon;
		double aux_returnVST_breakPointWon = StadisticsInput.returnVST_breakPointsWon;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pr("Seleccionar forma de recalcular:");
		pr("1 - Por juegos");
		pr("2 - Por victoria");
		pr("3 - Por ambas");
		int optionSelected = Integer.parseInt(br.readLine());
		
		pr("Cálculo para el local o visitante?");
		pr("1 - Local");
		pr("2 - Visitante");
		int lclOvst = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {

					simStart();
					CalculateStadisticsMatch calculate = new CalculateStadisticsMatch(arrayMatches);
					
					pr("handicapLCL = " + StadisticsInput.handicapLCL);
					pr("handicapVST = " + StadisticsInput.handicapVST);					
					
					CalculateStadisticsMatch.recalculateVictory();
					calculate.handicapRecalculate(31.5);
					check = calculate.checkHandicap(optionSelected, lclOvst);
							
					if (check == true) {
						pr("//----------handicapLCL debe tener VALOR ->> " + StadisticsInput.handicapLCL);
						pr("//----------handicapVST debe tener VALOR ->> " + StadisticsInput.handicapVST);
					} else {
						
						double reductionI = (double)i/100; 
						double reductionJ = (double)j/100; 
						
						if (lclOvst == 1) {
							StadisticsInput.handicapLCL = 1 - reductionJ;
							StadisticsInput.handicapVST = 1 - reductionI;
						}else {
							StadisticsInput.handicapVST = 1 - reductionJ;
							StadisticsInput.handicapLCL = 1 - reductionI;
						}

						StadisticsInput.serveLCL_1stServeWon = aux_serveLCL_1stServeWon * StadisticsInput.handicapLCL;
						StadisticsInput.serveLCL_2ndServeWon = aux_serveLCL_2ndServeWon * StadisticsInput.handicapLCL;
						StadisticsInput.serveLCL_breakPointsSaved = aux_serveLCL_breakPointsSaved
								* StadisticsInput.handicapLCL;
						StadisticsInput.returnLCL_1stSrvReturnWon = aux_returnLCL_1stSrvReturnWon
								* StadisticsInput.handicapLCL;
						StadisticsInput.returnLCL_2ndSrvReturnWon = aux_returnLCL_2ndSrvReturnWon
								* StadisticsInput.handicapLCL;
						StadisticsInput.returnLCL_breakPointsWon = aux_returnLCL_breakPointWon
								* StadisticsInput.handicapLCL;

						StadisticsInput.serveVST_1stServeWon = aux_serveVST_1stServeWon * StadisticsInput.handicapVST;
						StadisticsInput.serveVST_2ndServeWon = aux_serveVST_2ndServeWon * StadisticsInput.handicapVST;
						StadisticsInput.serveVST_breakPointsSaved = aux_serveVST_breakPointsSaved
								* StadisticsInput.handicapVST;
						StadisticsInput.returnVST_1stSrvReturnWon = aux_returnVST_1stSrvReturnWon
								* StadisticsInput.handicapVST;
						StadisticsInput.returnVST_2ndSrvReturnWon = aux_returnVST_2ndSrvReturnWon
								* StadisticsInput.handicapVST;
						StadisticsInput.returnVST_breakPointsWon = aux_returnVST_breakPointWon
								* StadisticsInput.handicapVST;
				}
				
				if(check == true){
					break;
				}
			}
			
			if(check == true){
				break;
			}
		}
		
		if(check == false) {
			pr("No se han encontrado valores");
		}
		System.exit(0);
	}
}
