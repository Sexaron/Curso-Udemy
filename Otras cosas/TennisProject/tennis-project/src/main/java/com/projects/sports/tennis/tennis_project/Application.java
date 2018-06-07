package com.projects.sports.tennis.tennis_project;


public class Application 
{
	//VARIABLES
	final static int NUM_PARTIDOS = 5; 
	final static int NUM_SETS = 3;
	static int puntoLCL;
	static int puntoVST;
	
	public String arrayPartidos[] = new String[NUM_PARTIDOS];
	
    public static void main( String[] args )
    {
    	matchStart();
        
    }
    
    public static String matchStart(){
    	        
		for ( int i = 0; i < NUM_PARTIDOS; i++ )
        {
			Match partido = new Match();
			for ( int j = 0; j < NUM_SETS; j++ )
			{
				
			}
        }
		
		return "";
    }
    
    public static void calculoJuego()
    {
    	//calcularemos el juego
    	while( (puntoLCL < 4 && puntoVST < 4) || (Math.abs(puntoLCL-puntoVST) < 2) )
    	{
    		
    	}
    }
}
