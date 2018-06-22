package com.projects.sports.tennis.sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StadisticsInput extends Application{
	
//	public static double aux_ace; // para el calculo prob. de ace
//	public static double aux_doubleFault;
//	public static double aux_breakPointsSaved;
//	public static double aux_firstServe;
//	public static double aux_secondServe;
	public static double handicapLCL; 		//-Math.log10(24-2)/9.5;
	public static double handicapVST;
	public static double rankingLCL;
	public static double rankingVST;
	public static double CONS = 9.5;
	
	public static double serveLCL_ace;
	public static double serveLCL_doubleFault;
	public static double serveLCL_1stServe;
	public static double serveLCL_1stServeWon;
	public static double serveLCL_2ndServeWon;
	public static double serveLCL_breakPointsSaved;

	public static double returnLCL_aceAgainst;
	public static double returnLCL_doubleFaultAgainst;
	public static double returnLCL_1stSrvReturnWon;
	public static double returnLCL_2stSrvReturnWon;	
	public static double returnLCL_breakPointsWon;
	
	public static double serveVST_ace;
	public static double serveVST_doubleFault;
	public static double serveVST_1stServe;
	public static double serveVST_1stServeWon;
	public static double serveVST_2ndServeWon;
	public static double serveVST_breakPointsSaved;

	public static double returnVST_aceAgainst;
	public static double returnVST_doubleFaultAgainst;
	public static double returnVST_1stSrvReturnWon;
	public static double returnVST_2stSrvReturnWon;
	public static double returnVST_breakPointsWon;
	
	
	public StadisticsInput(String opSelected) throws IllegalArgumentException, IOException {

		this.rankingLCL = 0;
		this.rankingVST = 0;
		
		switch (String.valueOf(opSelected)) {
			case "S":
				manualInputOfStadistics();
				break;
			case "N":
				//Estadísticas por defecto
				defaultStadistics();				
				break;
			default:
				pr("NO se ha seleccionado ni \"S\" ni \"N\"");
				break;
		}
			
	}
	
	// **************************************************************************************

	// *****************************POR DEFECTO**********************************************

	private void defaultStadistics() {
		// TODO Auto-generated method stub
		this.handicapLCL = 1; 		//-Math.log10(24-2)/9.5;
		this.handicapVST = 1;
		
		this.serveLCL_ace 					= 	100.00;	//11.1;
		this.serveLCL_doubleFault 			= 	00.0;
		this.serveLCL_1stServe 				=	100.00;		//64.7;
		this.serveLCL_1stServeWon 			= 	100.00;			//74.5	*	handicapLCL;
		this.serveLCL_2ndServeWon 			= 	100.00	*	handicapLCL;
		this.serveLCL_breakPointsSaved 		=	100.00	*	handicapLCL;

		this.returnLCL_aceAgainst 			=	00.0;	//06.8;
		this.returnLCL_doubleFaultAgainst 	= 	00.0;
		this.returnLCL_1stSrvReturnWon 		=	100.00;		//29.4	*	handicapLCL;
		this.returnLCL_2stSrvReturnWon 		=	100.00;			//54.7	*	handicapLCL;	
		this.returnLCL_breakPointsWon 		=	100.00	*	handicapLCL;
		
		this.serveVST_ace 					=	100.00;	//14.3;
		this.serveVST_doubleFault 			=	00.0;
		this.serveVST_1stServe 				=  	100.00;
		this.serveVST_1stServeWon 			=	100.00	*	handicapVST;
		this.serveVST_2ndServeWon 			=	100.00	*	handicapVST;
		this.serveVST_breakPointsSaved 		=	100.00	*	handicapVST;

		this.returnVST_aceAgainst 			=	00.00;	//08.5;
		this.returnVST_doubleFaultAgainst 	= 	00.00;
		this.returnVST_1stSrvReturnWon 		=	100.00 	*	handicapVST;
		this.returnVST_2stSrvReturnWon 		=	100.00	*	handicapVST;
		this.returnVST_breakPointsWon 		=	100.00	*	handicapVST;
	}
	
	// **************************************************************************************

	// *************************************MANUAL*******************************************

	private void manualInputOfStadistics() throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		pr("LOCAL - Ranking");
		this.rankingLCL = Double.parseDouble(br.readLine());
		pr("VISITANTE - Ranking");
		this.rankingVST = Double.parseDouble(br.readLine());
		pr("Ranking LOCAL ->> " + this.rankingLCL + " VS " + rankingVST + " <-- Ranking VISITANTE\n\n");
		
		if(this.rankingLCL > this.rankingVST) {
			this.handicapVST = 1-Math.log10(this.rankingLCL-this.rankingVST)/CONS;
			this.handicapLCL = 1;
		} else {
			this.handicapLCL = 1-Math.log10(this.rankingVST-this.rankingLCL)/CONS;
			this.handicapVST = 1;
		}
		
		//-------------------------LOCAL----------------------
		
		pr("//LOCAL - Prob de ACE");
		this.serveLCL_ace = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de ACE ->> " + this.serveLCL_ace + "%");
		
		pr("//LOCAL - Prob de doble falta");
		this.serveLCL_doubleFault = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de doble falta ->> " + this.serveLCL_doubleFault + "%");
		
		pr("//LOCAL - Prob de meter el 1er Servicio");
		this.serveLCL_1stServe = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de meter el 1er Servicio ->> " + this.serveLCL_1stServe + "%");
		
		pr("//LOCAL - Prob de ganar el 1er Servicio");
		this.serveLCL_1stServeWon = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de ganar el 1er Servicio ->> " + this.serveLCL_1stServeWon + "%");
		
		pr("//LOCAL - Prob de ganar el 2o Servicio");
		this.serveLCL_2ndServeWon = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de ganar el 2o Servicio ->> " + this.serveLCL_2ndServeWon + "%");
		
		pr("//LOCAL - Prob de salvar un BREAK");
		this.serveLCL_breakPointsSaved = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de salvar un BREAK ->> " + this.serveLCL_breakPointsSaved + "%\n\n");
		
		
		pr("//LOCAL - Prob de recibir un ACE");
		this.returnLCL_aceAgainst = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de recibir un ACE ->> " + this.returnLCL_aceAgainst + "%");
		
		pr("//LOCAL - Prob de recibir una doble falta");
		this.returnLCL_doubleFaultAgainst = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de recibir una doble falta ->> " + this.returnLCL_doubleFaultAgainst + "%");
		
		pr("//LOCAL - Prob de contrarrestar el 1er Servicio ");
		this.returnLCL_1stSrvReturnWon = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de contrarrestar el 1er Servicio ->> " + this.returnLCL_1stSrvReturnWon + "%");
		
		pr("//LOCAL - Prob de contrarrestar el 2o Servicio");
		this.returnLCL_2stSrvReturnWon = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de contrarrestar el 2o Servicio ->> " + this.returnLCL_2stSrvReturnWon + "%");
		
		pr("//LOCAL - Prob de hacer BREAK");
		this.returnLCL_breakPointsWon = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de hacer BREAK ->> " + this.returnLCL_breakPointsWon + "%");
		
		//-------------------------VISITANTE----------------------
		
		pr("//VISITANTE - Prob de ACE");
		this.serveVST_ace = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de ACE ->> " + this.serveVST_ace + "%");
		
		pr("//VISITANTE - Prob de doble falta");
		this.serveVST_doubleFault = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de doble falta ->> " + this.serveVST_doubleFault + "%");
		
		pr("//VISITANTE - Prob de meter el 1er Servicio");
		this.serveVST_1stServe = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de meter el 1er Servicio ->> " + this.serveVST_1stServe + "%");
		
		pr("//VISITANTE - Prob de ganar el 1er Servicio");
		this.serveVST_1stServeWon = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de ganar el 1er Servicio ->> " + this.serveVST_1stServeWon + "%");
		
		pr("//VISITANTE - Prob de ganar el 2o Servicio");
		this.serveVST_2ndServeWon = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de ganar el 2o Servicio ->> " + this.serveVST_2ndServeWon + "%");
		
		pr("//VISITANTE - Prob de salvar un BREAK");
		this.serveVST_breakPointsSaved = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de salvar un BREAK ->> " + this.serveVST_breakPointsSaved + "%\n\n");
		
		
		pr("//VISITANTE - Prob de recibir un ACE");
		this.returnVST_aceAgainst = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de recibir un ACE ->> " + this.returnVST_aceAgainst + "%");
		
		pr("//VISITANTE - Prob de recibir una doble falta");
		this.returnVST_doubleFaultAgainst = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de recibir una doble falta ->> " + this.returnVST_doubleFaultAgainst + "%");
		
		pr("//VISITANTE - Prob de contrarrestar el 1er Servicio ");
		this.returnVST_1stSrvReturnWon = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de contrarrestar el 1er Servicio ->> " + this.returnVST_1stSrvReturnWon + "%");
		
		pr("//VISITANTE - Prob de contrarrestar el 2o Servicio");
		this.returnVST_2stSrvReturnWon = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de contrarrestar el 2o Servicio ->> " + this.returnVST_2stSrvReturnWon + "%");
		
		pr("//VISITANTE - Prob de hacer BREAK");
		this.returnVST_breakPointsWon = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de hacer BREAK ->> " + this.returnVST_breakPointsWon + "%");
	}

}
