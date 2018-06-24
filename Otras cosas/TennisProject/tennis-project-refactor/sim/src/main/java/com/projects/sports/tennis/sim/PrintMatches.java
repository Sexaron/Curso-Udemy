package com.projects.sports.tennis.sim;

public class PrintMatches extends Application {
	
	public PrintMatches(Match[] arrayPartidos) {

		int indexM = 1;
		
		for (Match match : arrayPartidos) {
			pr("//-------PARTIDO " + indexM);
			prl("Sets:	");
			for (Set set : match.set) {
				prl(set.getPtsGameLCL() + "-" + set.getPtsGameVST());
				prl(" ");
			}
			prl("\n");
			indexM++;
		}
		
		pr("\n\n");
	}

}
