package com.projects.sports.tennis.tennis_project;


public class Match {

	/**
	 * Definimos las variables usadas en la clase
	**/
	
	public final int LCLset = 0;
	public final int VSTset = 0;
	public final static String resultado = "";
	public int i = 0;
	String setTotal[];
	public int setsWonByLCL = 0;
	public int setsWonByVST = 0;
	public int wonLCL = 0;
	public int wonVST = 0;
	public static int totalDeJuegos = 0;
	boolean inicio = false;
	
	//public final static String setTotal[] = new String[Application.NUM_SETS];
	
	public Match()
	{
		setTotal = new String[Application.NUM_SETS];
		i = 0;
		setsWonByLCL = 0;
		setsWonByVST = 0;
		wonLCL = 0;
		wonVST = 0;
		totalDeJuegos = 0;
	}
	
	
	public String getSet(int i)
	{
		return this.setTotal[i];
	}
	
	public void updateSets(Set set){
		if (  setTotal[i] == null && i < Application.NUM_SETS )
		{
			if ( Application.partidoTerminado == false ){
				//Application.print(setTotal[i]);
				//setTotal[i] = ("Set " + (i+1) + ": " + set.psetLCL + "-" + set.psetVST);
				setTotal[i] = (set.psetLCL + "-" + set.psetVST);
				//Application.print(setTotal[i]);
				i++;
				totalDeJuegos = set.psetLCL + set.psetVST + totalDeJuegos;
			}else
			{
				set.setSetLCL(0);
				set.setSetVST(0);
				//setTotal[i] = ("Set " + (i+1) + ": " + set.psetLCL + "-" + set.psetVST);
				setTotal[i] = (set.psetLCL + "-" + set.psetVST);
				i++;
				totalDeJuegos = set.psetLCL + set.psetVST + totalDeJuegos;
			}
		}
	}
	
	public void setSetsWonByLCL(){
		setsWonByLCL++;
	}
	
	public int getSetsWonByLCL(){
		return setsWonByLCL;
	}
	
	public void setSetsWonByVST(){
		setsWonByVST++;
	}
	
	public int getSetsWonByVST(){
		return setsWonByVST;
	}
	
	public void setWonLCL(){
		wonLCL++;		
	}
	
	public void setWonVST(){
		wonVST++;
	}
		
	public void setInicio(){
		inicio = true;
	}
	
	public void setFinal(){
		inicio = false;
	}
}
