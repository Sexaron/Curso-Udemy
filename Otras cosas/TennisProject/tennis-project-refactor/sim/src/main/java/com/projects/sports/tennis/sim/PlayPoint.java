package com.projects.sports.tennis.sim;

public class PlayPoint {

	double numRandom;
	double aux_tieBreakPoint = 0.00;
	double aux_ace = 0.00; // para el calculo prob. de ace
	double aux_doubleFault = 0.00;
	double aux_breakPointsSaved = 0.00;
	double aux_firstServe = 0.00;
	double aux_secondServe = 0.00;

	public PlayPoint(Set thisSet, int gameNumber) {
		
		// if(numRandom < 50.0) {
		// thisSet.getGame(gameNumber).addPtsLCL();
		// } else {
		// thisSet.getGame(gameNumber).addPtsVST();
		// }

		if (Application.tieBreak == false) {
			if (Application.kick == 0) {
				// --Saca LOCAL--
				numRandom = Math.random() * 100;
				if (StadisticsInput.serveLCL_1stServe > numRandom) { // Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (StadisticsInput.serveLCL_ace + StadisticsInput.returnVST_aceAgainst) / 2;
					if (aux_ace > numRandom) { // ACE!!!
						thisSet.getGame(gameNumber).addPtsLCL(); // Punto LCL
					} else { // NO hay ACE, se continúa el juego
						if (thisSet.getGame(gameNumber).getPtsVST() > 2 && (thisSet.getGame(gameNumber).getPtsVST()
								- thisSet.getGame(gameNumber).getPtsLCL()) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPointsSaved = StadisticsInput.serveLCL_breakPointsSaved * 100
									/ (StadisticsInput.serveLCL_breakPointsSaved
											+ StadisticsInput.returnVST_breakPointsWon);
							if (aux_breakPointsSaved > numRandom) { // Salvas el BREAK
								thisSet.getGame(gameNumber).addPtsLCL();
							} else { // Pierdes el BREAK
								thisSet.getGame(gameNumber).addPtsVST();
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_firstServe = StadisticsInput.serveLCL_1stServeWon * 100
									/ (StadisticsInput.serveLCL_1stServeWon
											+ StadisticsInput.returnVST_1stSrvReturnWon);
							if (aux_firstServe > numRandom) { // Gana el LCL el 1st servicio
								thisSet.getGame(gameNumber).addPtsLCL();
							} else { // Gana el VST con 1st servicio
								thisSet.getGame(gameNumber).addPtsVST();
							}
						}
					}

				} else { // Pasamos al segundo servicio
					numRandom = Math.random() * 100;
					aux_doubleFault = (StadisticsInput.serveLCL_doubleFault
							+ StadisticsInput.returnVST_doubleFaultAgainst) / 2;
					if (aux_doubleFault < numRandom) { // Entra el segundo servicio
						if (thisSet.getGame(gameNumber).getPtsVST() > 2 && (thisSet.getGame(gameNumber).getPtsVST()
								- thisSet.getGame(gameNumber).getPtsLCL()) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPointsSaved = StadisticsInput.serveLCL_breakPointsSaved * 100
									/ (StadisticsInput.serveLCL_breakPointsSaved
											+ StadisticsInput.returnVST_breakPointsWon);
							if (aux_breakPointsSaved > numRandom) { // Salva el BREAK
								thisSet.getGame(gameNumber).addPtsLCL();
							} else { // Pierde el BREAK
								thisSet.getGame(gameNumber).addPtsVST();
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_secondServe = StadisticsInput.serveLCL_2ndServeWon * 100
									/ (StadisticsInput.serveLCL_2ndServeWon
											+ StadisticsInput.returnVST_2stSrvReturnWon);
							if (aux_secondServe > numRandom) { // Gana el LCL con 2nd servicio
								thisSet.getGame(gameNumber).addPtsLCL();
							} else { // Gana el VST con 2nd servicio
								thisSet.getGame(gameNumber).addPtsVST();
							}
						}
					} else { // Ha realizado doble falta
						thisSet.getGame(gameNumber).addPtsVST();
					}
				}

				if ((thisSet.getGame(gameNumber).getPtsLCL() > 3
						&& (thisSet.getGame(gameNumber).getPtsLCL() - thisSet.getGame(gameNumber).getPtsVST()) > 1)
						|| thisSet.getGame(gameNumber).getPtsVST() > 3 && (thisSet.getGame(gameNumber).getPtsVST()
								- thisSet.getGame(gameNumber).getPtsLCL()) > 1) {
					if (Application.kick == 0) {
						Application.kick = 1;
					} else {
						Application.kick = 0;
					}
				}

			} else if (Application.kick == 0) {
				// --Saca VISITANTE--
				numRandom = Math.random() * 100;
				if (StadisticsInput.serveVST_1stServe > numRandom) { // Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (StadisticsInput.serveVST_ace + StadisticsInput.returnLCL_aceAgainst) / 2;
					if (aux_ace > numRandom) { // ACE!!!
						thisSet.getGame(gameNumber).addPtsVST(); // Punto VST
					} else { // NO hay ACE, se continúa el juego
						if (thisSet.getGame(gameNumber).getPtsLCL() > 2 && (thisSet.getGame(gameNumber).getPtsLCL()
								- thisSet.getGame(gameNumber).getPtsVST()) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPointsSaved = StadisticsInput.serveVST_breakPointsSaved * 100
									/ (StadisticsInput.serveVST_breakPointsSaved
											+ StadisticsInput.returnLCL_breakPointsWon);
							if (aux_breakPointsSaved > numRandom) { // Salvas el BREAK
								thisSet.getGame(gameNumber).addPtsVST();
							} else { // Pierdes el BREAK
								thisSet.getGame(gameNumber).addPtsLCL();
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_firstServe = StadisticsInput.serveVST_1stServeWon * 100
									/ (StadisticsInput.serveVST_1stServeWon
											+ StadisticsInput.returnLCL_1stSrvReturnWon);
							if (aux_firstServe > numRandom) { // Gana el VST el 1st servicio
								thisSet.getGame(gameNumber).addPtsVST();
							} else { // Gana el VST con 1st servicio
								thisSet.getGame(gameNumber).addPtsLCL();
							}
						}
					}

				} else { // Pasamos al segundo servicio
					numRandom = Math.random() * 100;
					aux_doubleFault = (StadisticsInput.serveVST_doubleFault
							+ StadisticsInput.returnLCL_doubleFaultAgainst) / 2;
					if (aux_doubleFault < numRandom) { // Entra el segundo servicio
						if (thisSet.getGame(gameNumber).getPtsLCL() > 2 && (thisSet.getGame(gameNumber).getPtsLCL()
								- thisSet.getGame(gameNumber).getPtsVST()) >= 1) { // Punto de BREAK
							numRandom = Math.random() * 100;
							aux_breakPointsSaved = StadisticsInput.serveVST_breakPointsSaved * 100
									/ (StadisticsInput.serveVST_breakPointsSaved
											+ StadisticsInput.returnLCL_breakPointsWon);
							if (aux_breakPointsSaved > numRandom) { // Salva el BREAK
								thisSet.getGame(gameNumber).addPtsVST();
							} else { // Pierde el BREAK
								thisSet.getGame(gameNumber).addPtsLCL();
							}
						} else { // NO es punto de BREAK
							numRandom = Math.random() * 100;
							aux_secondServe = StadisticsInput.serveVST_2ndServeWon * 100
									/ (StadisticsInput.serveVST_2ndServeWon
											+ StadisticsInput.returnLCL_2stSrvReturnWon);
							if (aux_secondServe > numRandom) { // Gana el VST con 2nd servicio
								thisSet.getGame(gameNumber).addPtsVST();
							} else { // Gana el LCL con 2nd servicio
								thisSet.getGame(gameNumber).addPtsLCL();
							}
						}
					} else { // Ha realizado doble falta
						thisSet.getGame(gameNumber).addPtsLCL();
					}
				}

				if ((thisSet.getGame(gameNumber).getPtsLCL() > 3
						&& (thisSet.getGame(gameNumber).getPtsLCL() - thisSet.getGame(gameNumber).getPtsVST()) > 1)
						|| thisSet.getGame(gameNumber).getPtsVST() > 3 && (thisSet.getGame(gameNumber).getPtsVST()
								- thisSet.getGame(gameNumber).getPtsLCL()) > 1) {
					if (Application.kick == 0) {
						Application.kick = 1;
					} else {
						Application.kick = 0;
					}
				}

			}

		} else {	//Cálculo del punto cuando es TieBreak. Hacemos medias de la prob. de ganar el servicio con la prob. de salvar o ganar el break
			if(Application.kick == 0) {	//Juego con saque para el LCL/////////
				numRandom = Math.random()*100;
				if(StadisticsInput.serveLCL_1stServe > numRandom) {	// Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (StadisticsInput.serveLCL_ace + StadisticsInput.returnVST_aceAgainst) / 2;
					if(aux_ace > numRandom) {	//ACE!!!
						thisSet.getGame(gameNumber).addPtsLCL();
					} else {	// No hay ACE, se continúa con el juego
						numRandom = Math.random()*100;
						aux_tieBreakPoint =  (StadisticsInput.serveLCL_breakPointsSaved + StadisticsInput.serveLCL_1stServeWon)/2 * 100
								/((StadisticsInput.returnVST_breakPointsWon + StadisticsInput.returnVST_1stSrvReturnWon)/2 
								+ (StadisticsInput.serveLCL_breakPointsSaved + StadisticsInput.serveLCL_1stServeWon)/2);
						if (aux_tieBreakPoint > numRandom) { // Salva el punto de saque
							thisSet.getGame(gameNumber).addPtsLCL();
						} else {	//Pierde el punto de saque
							thisSet.getGame(gameNumber).addPtsVST();
						}
					}
				} else {	// Segundo servicio 
					numRandom = Math.random()*100;
					aux_tieBreakPoint =  (StadisticsInput.serveLCL_breakPointsSaved + StadisticsInput.serveLCL_2ndServeWon)/2 * 100
							/((StadisticsInput.returnVST_breakPointsWon + StadisticsInput.returnVST_2stSrvReturnWon)/2 
							+ (StadisticsInput.serveLCL_breakPointsSaved + StadisticsInput.serveLCL_2ndServeWon)/2);
					if (aux_tieBreakPoint > numRandom) { // Salva el punto de saque
						thisSet.getGame(gameNumber).addPtsLCL();
					} else {	//Pierde el punto de saque
						thisSet.getGame(gameNumber).addPtsVST();
					}
				}
				
			} else if(Application.kick == 1) {
				numRandom = Math.random()*100;
				if(StadisticsInput.serveVST_1stServe > numRandom) {	// Entra el primer servicio
					numRandom = Math.random() * 100;
					aux_ace = (StadisticsInput.serveVST_ace + StadisticsInput.returnLCL_aceAgainst) / 2;
					if(aux_ace > numRandom) {	//ACE!!!
						thisSet.getGame(gameNumber).addPtsVST();
					} else {	// No hay ACE, se continúa con el juego
						numRandom = Math.random()*100;
						aux_tieBreakPoint =  (StadisticsInput.serveVST_breakPointsSaved + StadisticsInput.serveVST_1stServeWon)/2 * 100
								/((StadisticsInput.returnLCL_breakPointsWon + StadisticsInput.returnLCL_1stSrvReturnWon)/2 
								+ (StadisticsInput.serveVST_breakPointsSaved + StadisticsInput.serveVST_1stServeWon)/2);
						if (aux_tieBreakPoint > numRandom) { // Salva el punto de saque
							thisSet.getGame(gameNumber).addPtsVST();
						} else {	//Pierde el punto de saque
							thisSet.getGame(gameNumber).addPtsLCL();
						}
					}
				} else {	// Segundo servicio 
					numRandom = Math.random()*100;
					aux_tieBreakPoint =  (StadisticsInput.serveVST_breakPointsSaved + StadisticsInput.serveVST_2ndServeWon)/2 * 100
							/((StadisticsInput.returnLCL_breakPointsWon + StadisticsInput.returnLCL_2stSrvReturnWon)/2 
							+ (StadisticsInput.serveVST_breakPointsSaved + StadisticsInput.serveVST_2ndServeWon)/2);
					if (aux_tieBreakPoint > numRandom) { // Salva el punto de saque
						thisSet.getGame(gameNumber).addPtsVST();
					} else {	//Pierde el punto de saque
						thisSet.getGame(gameNumber).addPtsLCL();
					}
				}
			}
		}

	}
}
