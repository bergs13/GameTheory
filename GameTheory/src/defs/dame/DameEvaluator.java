/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.Evaluator;
import defs.general.GameState;

/**
 *
 * @author Thunderchild
 */
public class DameEvaluator extends Evaluator {

	@Override
	public int evaluate(GameState gameState) {
		// DameGameState gstate = (DameGameState)gameState;
		// if(gstate.matches % 4 == 0){
		// return 1;
		// }
		return -1;
	}
}
