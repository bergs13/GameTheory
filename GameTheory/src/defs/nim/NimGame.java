/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.nim;

import defs.general.Game;

/**
 *
 * @author Thunderchild
 */
public class NimGame extends Game{

    NimEvaluator evaluator = new NimEvaluator();
    
    public NimGame(String name) {
        super(name);
    }
    
}
