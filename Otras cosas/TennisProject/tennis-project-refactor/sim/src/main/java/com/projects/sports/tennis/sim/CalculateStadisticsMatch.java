package com.projects.sports.tennis.sim;



public class CalculateStadisticsMatch extends Application {
	
	static Match[] lstMatches;
	int indexM = 1;
	static int cntProbWinLCL = 0;
	static int cntProbWinVST = 0;
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
	double probResult = 0.00;
	Double probUnder = 0.00;
	Double probOver = 0.00;
	
	static double probTotalLCL;
	static double probTotalVST;
	
	public CalculateStadisticsMatch(Match[] arrayMatches) {
		this.lstMatches = arrayMatches;
	}

	
	public static void probVictoria(){
		cntProbWinLCL = 0;
		cntProbWinVST = 0;
		
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
	
	public void probResultadoDePartido() {

		if (MAX_NUM_SETS == 3) {			//Partido a 3 SETS
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
			
			probResult = (double)cntProb_20 / lstMatches.length * 100;			
			pr("//---------PROBABILIDAD DE RESULTADOS");
			pr("RESULTADO 2-0 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_21 / lstMatches.length * 100;
			pr("RESULTADO 2-1 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_02 / lstMatches.length * 100;
			pr("RESULTADO 0-2 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_12 / lstMatches.length * 100;
			pr("RESULTADO 1-2 ->> " + df.format(probResult) + "%");	
			
			pr("\\---------------------------------\n\n");
			
		} else if (MAX_NUM_SETS == 5) {	//Partido a 5 SETS
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
			
			probResult = (double)cntProb_30 / lstMatches.length * 100;			
			pr("//---------PROBABILIDAD DE RESULTADOS");
			pr("RESULTADO 3-0 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_31 / lstMatches.length * 100;
			pr("RESULTADO 3-1 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_32 / lstMatches.length * 100;
			pr("RESULTADO 3-2 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_03 / lstMatches.length * 100;
			pr("RESULTADO 0-3 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_13 / lstMatches.length * 100;
			pr("RESULTADO 1-3 ->> " + df.format(probResult) + "%");
			
			probResult = (double)cntProb_23 / lstMatches.length * 100;
			pr("RESULTADO 2-3 ->> " + df.format(probResult) + "%");
			
			pr("\\---------------------------------\n\n");
		}		
	}

	
	public void probResultadosExactos() {
		
	}
	
	public void probNumJuegosTotalEnPartido() {

		// [0]=12.5...[53]=65.5
		Double printOpciones[] = new Double[53]; // 53 = 65 max de juegos menos
													// 12 el minimo de juegos
		Double var = 12.5;
		int[][] overYunder = new int[53][2]; // [0] = over [1] = under
		Double probUnder = 0.00;
		Double probOver = 0.00;
		int sumSets = 0;

		for (int i = 0; i < 53; i++) {
			printOpciones[i] = var;
			var++;
		}

		for (Match match : lstMatches) {
			sumSets = 0;
			for (Set set : match.set) {
				sumSets = sumSets + set.getPtsGameLCL() + set.getPtsGameVST();
			}
			for (int i = 0; i < 53; i++) {
				if (sumSets < printOpciones[i]) { // UNDER
					overYunder[i][1] = overYunder[i][1] + 1;
				} else { // OVER
					overYunder[i][0] = overYunder[i][0] + 1;
				}
			}
		}
		
		pr("//---------PROBABILIDAD OVER/UNDER");
		// printeamos los resultados
		for (int i = 0; i < 53; i++) {
			probOver = (double) overYunder[i][0] / lstMatches.length * 100;
			probUnder = (double) overYunder[i][1] / lstMatches.length * 100;
			pr("Prob OVER/UNDER de " + printOpciones[i] + " ->> " + df.format(probOver)
					+ "% - " + df.format(probUnder) + "%");
		}
		
		pr("\\---------------------------------\n\n");
	}
	
	/**
	 * Para realizar el recalculo del handicap	
	 * @param recalculate
	 */
	public void handicapRecalculate(Double numGames) {
		// [0]=12.5...[53]=65.5
		Double printOpciones[] = new Double[53]; // 53 = 65 max de juegos menos
													// 12 el minimo de juegos
		Double var = 12.5;
		int[][] overYunder = new int[53][2]; // [0] = over [1] = under
		int sumSets = 0;
		int pos = (int)(numGames - var);

//		for (int i = 0; i < 53; i++) {
//			printOpciones[i] = var;
//			var++;
//		}

		printOpciones[pos] = numGames;
		
		for (Match match : lstMatches) {
			sumSets = 0;
			for (Set set : match.set) {
				sumSets = sumSets + set.getPtsGameLCL() + set.getPtsGameVST();
			}
				if (sumSets < printOpciones[pos]) { // UNDER
					overYunder[pos][1] = overYunder[pos][1] + 1;
				} else { // OVER
					overYunder[pos][0] = overYunder[pos][0] + 1;
				}
		}
		
		probOver = (double) overYunder[pos][0] / lstMatches.length * 100;
		probUnder = (double) overYunder[pos][1] / lstMatches.length * 100;
		pr("Prob OVER/UNDER de " + printOpciones[pos] + " ->> " + df.format(probOver) + "% - " + df.format(probUnder)
				+ "%\n\n");
	}
	
	public static void recalculateVictory(){
		cntProbWinLCL = 0;
		cntProbWinVST = 0;
		
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
		pr("\\---------------------------------");
	}
	
	public boolean checkHandicap(int option) {
		
		if(1 == option){
			if (probUnder < 55.00 && probUnder > 60.00) {
				return true;
			} else {
				return false;
			}
		} else if(2 == option){
			if (probTotalLCL > 43.00 && probTotalLCL < 57.00) { 
				return true;
			} else {
				return false;
			}
		} else if(3== option){
			if ((probUnder < 55.00 && probUnder > 60.00) || (probTotalLCL > 43.00 && probTotalLCL < 57.00)) { 
				return true;
			} else {
				return false;
			}
		} else {
			pr("NO SE HA SELECCIONADO UNA DE LAS OPCIONES");
			System.exit(0);
		}
		
		return false;
		
	}
}
