package com.projects.sports.tennis.sim;

import java.util.logging.Logger;

public class CalculateStadisticsMatch extends Application {
	
	Match[] lstMatches;
	int indexM = 1;
	int cntProbWinLCL = 0;
	int cntProbWinVST = 0;
	int cntProb_20 = 0;
	int cntProb_21 = 0;
	int cntProb_02 = 0;
	int cntProb_12 = 0;
	int cntProb_30 = 0;
	int cntProb_31 = 0;
	int cntProb_32 = 0;
	int cntProb_03 = 0;
	int cntProb_13 = 0;
	int cntProb_23 = 0;
	
	double probTotalLCL, probTotalVST;
	
	public CalculateStadisticsMatch(Match[] arrayMatches) {
		this.lstMatches = arrayMatches;
	}

	
	public void probVictoria(){
		for(Match match : lstMatches) {
			if(match.getPtsSetLCL() > match.getPtsSetVST()) {
				cntProbWinLCL++;
			} else {
				cntProbWinVST++;
			}
		}
		
		probTotalLCL = (double) cntProbWinLCL/lstMatches.length*100;
		probTotalVST = (double) cntProbWinVST/lstMatches.length*100;
		
		pr("//---------PROBABILIDAD DE VICTORIA");
		pr("VICTORIAS LCL vs VST 			->> " + cntProbWinLCL + " vs " + cntProbWinVST);		
		pr("Probabilidad de victoria LOCAL		->> " + df.format(probTotalLCL) + "%");
		pr("Probabilidad de victoria VISITANTE	->> " + df.format(probTotalVST) + "%");
		pr("\\---------------------------------\n\n");
	}
	
	public void probRestuladoDePartido() {

		if (MAX_NUM_GAMES == 3) {			//Partido a 3 SETS
			for (Match match : lstMatches) {
				if (match.getPtsSetLCL() == 2 && match.getPtsSetVST() == 0) {
					cntProb_20++;
				} else if (match.getPtsSetLCL() == 2 && match.getPtsSetVST() == 1) {
					cntProb_21++;
				} else if (match.getPtsSetLCL() == 0 && match.getPtsSetVST() == 2) {
					cntProb_02++;
				} else if (match.getPtsSetLCL() == 1 && match.getPtsSetVST() == 2) {
					cntProb_12++;
				} else
					pr("**********ERROR en el partido " + indexM);
				indexM++;
			}
			
			cntProb_20
			
			pr("//---------PROBABILIDAD DE RESULTADOS");
			pr("RESULTADO 2-0 ->> " + )
			
		} else if (MAX_NUM_GAMES == 5) {	//Partido a 5 SETS
			for (Match match : lstMatches) {
				if (match.getPtsSetLCL() == 3 && match.getPtsSetVST() == 0) {
					cntProb_30++;
				} else if (match.getPtsSetLCL() == 3 && match.getPtsSetVST() == 1) {
					cntProb_31++;
				} else if (match.getPtsSetLCL() == 3 && match.getPtsSetVST() == 2) {
					cntProb_32++;
				} else if (match.getPtsSetLCL() == 0 && match.getPtsSetVST() == 3) {
					cntProb_03++;
				} else if (match.getPtsSetLCL() == 1 && match.getPtsSetVST() == 3) {
					cntProb_13++;
				} else if (match.getPtsSetLCL() == 2 && match.getPtsSetVST() == 3) {
					cntProb_23++;
				} else
					pr("**********ERROR en el partido " + indexM);
				indexM++;
			}
		}		
	}

	
	public void probResultadosExactos() {
		
	}
	
	public void probNumJuegosTotalEnPartido() {
		
	}
}
