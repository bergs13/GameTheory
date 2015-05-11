package defs;

import java.util.ArrayList;

public class Algorithms2 {
	public static int miniMax(Evaluator evaluator, GameState gstate) {
		//Berechnung und Rückgabe Wert auf Blattknoten
		if (gstate.isTerminal()) {
			return evaluator.evaluate();
		}
		
		//Max bzw. Min. Kindknoten ermitteln
		int returnValue = Integer.MIN_VALUE;
		ArrayList<GameState> childStates = gstate.getChildStates();
		int childValue = Integer.MIN_VALUE;
		for (GameState childState : childStates) {
			childValue = miniMax(evaluator, childState);
			//Ist min. bzw. max. Wert?
//			if(childValue > returnValue bzw..)
//			{
//				
//			}
		}	
		return returnValue;
	}
}
