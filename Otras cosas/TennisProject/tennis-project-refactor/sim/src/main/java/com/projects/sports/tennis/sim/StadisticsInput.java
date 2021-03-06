package com.projects.sports.tennis.sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StadisticsInput extends Application{
	
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
	public static double returnLCL_2ndSrvReturnWon;	
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
	public static double returnVST_2ndSrvReturnWon;
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
			case "R":
				//Se ha escogido recalcular handicap
				new recalculate();
			default:
				pr("NO se ha seleccionado ni \"S\" ni \"N\" ni \"R\"");
				break;
		}
			
	}
	
	// **************************************************************************************

	// *****************************POR DEFECTO**********************************************

	public void defaultStadistics() {
		// TODO Auto-generated method stub
		this.handicapLCL = 0.9125; 		//-Math.log10(24-2)/9.5;
		this.handicapVST = 0.9575;
		
		this.serveLCL_ace 					= 	04.4;
		this.serveLCL_doubleFault 			= 	02.7;
		this.serveLCL_1stServe 				=	54.7;
		this.serveLCL_1stServeWon 			= 	71.4	*	handicapLCL;
		this.serveLCL_2ndServeWon 			= 	57.0	*	handicapLCL;
		this.serveLCL_breakPointsSaved 		=	75.0	*	handicapLCL;

		this.returnLCL_aceAgainst 			=	11.3;
		this.returnLCL_doubleFaultAgainst 	= 	04.1;
		this.returnLCL_1stSrvReturnWon 		=	31.1	*	handicapLCL;
		this.returnLCL_2ndSrvReturnWon 		=	55.1	*	handicapLCL;	
		this.returnLCL_breakPointsWon 		=	43.3	*	handicapLCL;

		this.serveVST_ace 					=	08.6;
		this.serveVST_doubleFault 			=	01.6;
		this.serveVST_1stServe 				=  	69.0;
		this.serveVST_1stServeWon 			=	78.3	*	handicapVST;
		this.serveVST_2ndServeWon 			=	60.3	*	handicapVST;
		this.serveVST_breakPointsSaved 		=	66.7	*	handicapVST;

		this.returnVST_aceAgainst 			=	19.8;
		this.returnVST_doubleFaultAgainst 	= 	02.2;
		this.returnVST_1stSrvReturnWon 		=	19.4 	*	handicapVST;
		this.returnVST_2ndSrvReturnWon 		=	43.1	*	handicapVST;
		this.returnVST_breakPointsWon 		=	25.0	*	handicapVST;
	}
	
	// **************************************************************************************

	// *************************************MANUAL*******************************************

	private void manualInputOfStadistics() throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		pr("LOCAL - Handicap");
//		pr("LOCAL - Ranking");
//		this.rankingLCL = Double.parseDouble(br.readLine());
		this.handicapLCL = Double.parseDouble(br.readLine());
		pr("VISITANTE - Handicap");
//		pr("VISITANTE - Ranking");
//		this.rankingVST = Double.parseDouble(br.readLine());
		this.handicapVST = Double.parseDouble(br.readLine());
		pr("Handicap LOCAL ->> " + this.handicapLCL + " VS " + this.handicapVST + " <-- Handicap VISITANTE\n\n");
		
//		if(this.rankingLCL < this.rankingVST) {
//			this.handicapVST = 1-Math.log10(this.rankingLCL-this.rankingVST)/CONS;
//			this.handicapLCL = 1;
//		} else {
//			this.handicapLCL = 1-Math.log10(this.rankingVST-this.rankingLCL)/CONS;
//			this.handicapVST = 1;
//		}
		
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
		this.serveLCL_1stServeWon = Double.parseDouble(br.readLine()) * handicapLCL;
		pr("LOCAL - Prob de ganar el 1er Servicio ->> " + this.serveLCL_1stServeWon + "%");
		
		pr("//LOCAL - Prob de ganar el 2o Servicio");
		this.serveLCL_2ndServeWon = Double.parseDouble(br.readLine()) * handicapLCL;
		pr("LOCAL - Prob de ganar el 2o Servicio ->> " + this.serveLCL_2ndServeWon + "%");
		
		pr("//LOCAL - Prob de salvar un BREAK");
		this.serveLCL_breakPointsSaved = Double.parseDouble(br.readLine()) * handicapLCL;
		pr("LOCAL - Prob de salvar un BREAK ->> " + this.serveLCL_breakPointsSaved + "%\n\n");
		
		
		pr("//LOCAL - Prob de recibir un ACE");
		this.returnLCL_aceAgainst = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de recibir un ACE ->> " + this.returnLCL_aceAgainst + "%");
		
		pr("//LOCAL - Prob de recibir una doble falta");
		this.returnLCL_doubleFaultAgainst = Double.parseDouble(br.readLine());
		pr("LOCAL - Prob de recibir una doble falta ->> " + this.returnLCL_doubleFaultAgainst + "%");
		
		pr("//LOCAL - Prob de contrarrestar el 1er Servicio ");
		this.returnLCL_1stSrvReturnWon = Double.parseDouble(br.readLine()) * handicapLCL;
		pr("LOCAL - Prob de contrarrestar el 1er Servicio ->> " + this.returnLCL_1stSrvReturnWon + "%");
		
		pr("//LOCAL - Prob de contrarrestar el 2o Servicio");
		this.returnLCL_2ndSrvReturnWon = Double.parseDouble(br.readLine()) * handicapLCL;
		pr("LOCAL - Prob de contrarrestar el 2o Servicio ->> " + this.returnLCL_2ndSrvReturnWon + "%");
		
		pr("//LOCAL - Prob de hacer BREAK");
		this.returnLCL_breakPointsWon = Double.parseDouble(br.readLine()) * handicapLCL;
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
		this.serveVST_1stServeWon = Double.parseDouble(br.readLine()) * handicapVST;
		pr("VISITANTE - Prob de ganar el 1er Servicio ->> " + this.serveVST_1stServeWon + "%");
		
		pr("//VISITANTE - Prob de ganar el 2o Servicio");
		this.serveVST_2ndServeWon = Double.parseDouble(br.readLine()) * handicapVST;
		pr("VISITANTE - Prob de ganar el 2o Servicio ->> " + this.serveVST_2ndServeWon + "%");
		
		pr("//VISITANTE - Prob de salvar un BREAK");
		this.serveVST_breakPointsSaved = Double.parseDouble(br.readLine()) * handicapVST;
		pr("VISITANTE - Prob de salvar un BREAK ->> " + this.serveVST_breakPointsSaved + "%\n\n");
		
		
		pr("//VISITANTE - Prob de recibir un ACE");
		this.returnVST_aceAgainst = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de recibir un ACE ->> " + this.returnVST_aceAgainst + "%");
		
		pr("//VISITANTE - Prob de recibir una doble falta");
		this.returnVST_doubleFaultAgainst = Double.parseDouble(br.readLine());
		pr("VISITANTE - Prob de recibir una doble falta ->> " + this.returnVST_doubleFaultAgainst + "%");
		
		pr("//VISITANTE - Prob de contrarrestar el 1er Servicio ");
		this.returnVST_1stSrvReturnWon = Double.parseDouble(br.readLine()) * handicapVST;
		pr("VISITANTE - Prob de contrarrestar el 1er Servicio ->> " + this.returnVST_1stSrvReturnWon + "%");
		
		pr("//VISITANTE - Prob de contrarrestar el 2o Servicio");
		this.returnVST_2ndSrvReturnWon = Double.parseDouble(br.readLine()) * handicapVST;
		pr("VISITANTE - Prob de contrarrestar el 2o Servicio ->> " + this.returnVST_2ndSrvReturnWon + "%");
		
		pr("//VISITANTE - Prob de hacer BREAK");
		this.returnVST_breakPointsWon = Double.parseDouble(br.readLine()) * handicapVST;
		pr("VISITANTE - Prob de hacer BREAK ->> " + this.returnVST_breakPointsWon + "%");
	}

}
