package com.projects.sports.tennis.tennis_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application 
{
	//VARIABLES
	final static int NUM_PARTIDOS = 1000; 
	final static int NUM_SETS = 5;
	static int puntoLCL, juegoLCL;
	static int puntoVST, juegoVST;
	static boolean partidoTerminado = false;
	
	public static Match arrayPartidos[] = new Match[NUM_PARTIDOS];
	public static Set arraySets[] = new Set[NUM_SETS];
	
    public static void main( String[] args )
    {
    	//Application.print("//--------------Entra en el main");
    	simStart();
    	calculoEstadisticasPartidos();
        
    }



	//**************************************************************************************
    public static void simStart(){
    	        
		for ( int i = 0; i < NUM_PARTIDOS; i++ )
        {
			//Application.print("//--------------COMIENZA NUEVO PARTIDO");
			Match partido = new Match();
			partidoTerminado = false;
			for ( int j = 0; j < NUM_SETS; j++ )
			{
				//Application.print("//-----------VALOR DE J = " + j);
				if( partido.setsWonByLCL < (NUM_SETS/2 + 1) && partido.setsWonByVST < (NUM_SETS/2 + 1 ))
				{
					Set set = new Set();
					calculoSet(partido, set);
					arraySets[j] = set;
					partido.updateSets(set); //hay q ver como metemos los sets al partido.
				}else
				{
					partidoTerminado = true;
					Set set = new Set();
					partido.updateSets(set);					
				}
			}
			arrayPartidos[i] = partido;
        }
				
		printearResultadoDePartidos(arrayPartidos);
    }
//**************************************************************************************    
    
//**************************************************************************************   
    public static void calculoSet(Match partido, Set set)
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
    		partido.setSetsWonByLCL();   
    		juegoLCL = 0;
    		juegoVST = 0;
    	}else
    	{
    		//Set para el visitante
    		partido.setSetsWonByVST();
    		juegoLCL = 0;
    		juegoVST = 0;
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
    	while( ((puntoLCL < 7) && (puntoVST < 7)) || (Math.abs(puntoLCL-puntoVST) < 2) )
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
    public static void jugarPunto(int pLCL, int pVST)
    {
    	//Aquí se ejecutará todo el tema de las estadisticas.
    	double numRandom = Math.random()*100;
    	if ( numRandom < 50 )
    	{
    		pLCL++;
    	}else
    	{
    		pVST++;
    	}
    	puntoLCL = pLCL;
    	puntoVST = pVST;
    }
//************************************************************************************** 
    
//************************************************************************************ 
    public static void printearResultadoDePartidos(Match match[])
    {
    	for ( int i = 0; i < NUM_PARTIDOS; i++)
    	{
    		ArrayList<String> resultadoPartido = new ArrayList<String>(); 
	    	for ( int j = 0; j < NUM_SETS; j++)
	    	{
	    		if( j == 0 )
	    		{
	    			Application.print("//---------PARTIDO " + (i + 1) + "----------//");
	    			resultadoPartido.add( match[i].getSet(j) );
	    		}else
	    		{
	    			resultadoPartido.add( match[i].getSet(j) );
	    		}
	    	}
	    	
	    	Application.print( resultadoPartido.toString() );
    	}
    }
  //************************************************************************************** 
    
  //************************************************************************************ 
    
    /**
     * Para printear por consola
    */
    public static void print(String frase)
    {
    	System.out.println(frase);
    }
  //************************************************************************************** 
   
  //************************************************************************************ 
    private static void calculoEstadisticasPartidos() {
    	Map<String, Integer> listSets = new HashMap<String, Integer>();
    	listSets.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
    	/*for ( Match partido: arrayPartidos )
    	{
    		for ( Set set: arraySets)
    		{
    			for ( Match compPartido: arrayPartidos)
    			{
    				
    			}
    		}
    	}*/
			
	}
}




