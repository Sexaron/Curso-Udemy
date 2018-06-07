package com.projects.sports.tennis.tennis_project;


public class Application 
{
	//VARIABLES
	final static int NUM_PARTIDOS = 5; 
	final static int NUM_SETS = 3;
	static int puntoLCL, juegoLCL;
	static int puntoVST, juegoVST;
	
	public static Match arrayPartidos[] = new Match[NUM_PARTIDOS];
	public static Set arraySets[] = new Set[NUM_SETS];
	
    public static void main( String[] args )
    {
    	simStart();
        
    }

    
//**************************************************************************************
    public static String simStart(){
    	        
		for ( int i = 0; i < NUM_PARTIDOS; i++ )
        {
			Match partido = new Match();
			for ( int j = 0; j < NUM_SETS; j++ )
			{
				
				Set set = new Set();
				calculoSet(set);
				arraySets[j] = set;
				partido.updateSets(set); //hay q ver como metemos los sets al partido.
			}
			arrayPartidos[i] = partido;			
        }
		
		return "";
    }
//**************************************************************************************    
    
//**************************************************************************************   
    public static void calculoSet(Set set)
    {
    	while( (juegoLCL != 6) && (juegoVST != 6) && (juegoLCL + juegoVST < 10) )
    	{
    		calculoJuego();
    	}
    	
    	if ( (juegoLCL == 5) && (juegoVST == 5) )
    	{
    		while( (juegoLCL != 7) && (juegoVST != 7) )
    		{
    			if( (juegoLCL == 6) && (juegoVST == 6) )
    			{
    				calculoTieBreak();
    			}else
    			{
    				calculoJuego();    				
    			}
    		}
    	}
    	
		set.setSetLCL(juegoLCL);
		set.setSetVST(juegoVST);
		    	
    	if( juegoLCL > juegoVST )
    	{
    		//Set para el local
    		set.setSetsWonByLCL();    		
    	}else
    	{
    		//Set para el visitante
    		set.setSetsWonByVST();
    	}
    }
//**************************************************************************************    

//**************************************************************************************    
    public static void calculoJuego()
    {
    	//calcularemos el juego
    	while( ((puntoLCL < 4) && (puntoVST < 4)) || (Math.abs(puntoLCL-puntoVST) < 2) )
    	{
    		jugarPunto(puntoLCL, puntoVST);
    	}
    	
    	if ( puntoLCL > puntoVST )
    	{
    		juegoLCL++;
    	}else
    	{
    		juegoVST++;
    	}
    	
    	puntoLCL = 0;
		puntoVST = 0;
    }
//**************************************************************************************    

//**************************************************************************************
    public static void calculoTieBreak()
    {
    	
    }
//************************************************************************************** 
    
//**************************************************************************************
    public static void jugarPunto(int pLCL, int pVST)
    {
    	//Aquí se ejecutará todo el tema de las estadisticas.
    }
}
