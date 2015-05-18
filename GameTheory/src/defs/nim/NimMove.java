/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.nim;

import defs.general.Move;

/**
 *
 * @author Thunderchild
 */
public class NimMove extends Move{
    int value;

    public NimMove(Object move) {
        super(move);
        value = (int)move;
    }

    public void executeMove(int state) {
        state =- value;
    }
    
    
    
    
}
