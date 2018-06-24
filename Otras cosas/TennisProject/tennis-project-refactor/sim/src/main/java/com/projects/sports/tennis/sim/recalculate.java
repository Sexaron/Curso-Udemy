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
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {

					simStart();
					CalculateStadisticsMatch calculate = new CalculateStadisticsMatch(arrayMatches);
					
					pr("handicapVST = " + StadisticsInput.handicapVST);
					pr("handicapLCL = " + StadisticsInput.handicapLCL);
					
					CalculateStadisticsMatch.recalculateVictory();
					calculate.handicapRecalculate(31.5);
					check = calculate.checkHandicap(optionSelected);
							
					if (check == true) {
						pr("//----------handicapVST debe tener VALOR ->> " + StadisticsInput.handicapVST);
						pr("//----------handicapLCL debe tener VALOR ->> " + StadisticsInput.handicapLCL);
					} else {
						double reductionI = (double)i/100; 
						double reductionJ = (double)j/100; 
						
						StadisticsInput.handicapLCL = 1 - reductionI;
						StadisticsInput.handicapVST = 1 - reductionJ;

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
