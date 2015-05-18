/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.Game;

/**
 *
 * @author Thunderchild
 */
public class DameGame extends Game{

    DameEvaluator evaluator = new DameEvaluator();
    
    public DameGame(String name) {
        super(name);
    }
    
}
