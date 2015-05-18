/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NimGame;

import defs.Evaluator;
import defs.GameState;

/**
 *
 * @author Thunderchild
 */
public class NimEvaluator extends Evaluator{
    
    @Override
    public int evaluate(GameState gameState) {
        NimGameState gstate = (NimGameState)gameState;
        if(gstate.matches % 4 == 0){
            return 1;
        }
        else return -1;
    }
}
