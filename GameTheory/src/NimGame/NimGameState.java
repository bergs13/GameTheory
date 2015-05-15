/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NimGame;

import defs.GameState;
import defs.Move;
import defs.Player;
import java.util.ArrayList;

/**
 *
 * @author Thunderchild
 */
public class NimGameState extends GameState{
    public int matches;
    
    public NimGameState() {
    }

    public NimGameState(GameState parentState, Move move) {
        super(parentState, move);
        move.executeMove(matches);
    }

    @Override
    public void findPossibleMoves() {
        super.findPossibleMoves(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createChildStates() {
        super.createChildStates(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw() {
        super.draw(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void secondPlayerToWin() {
        super.secondPlayerToWin(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void firstPlayerToWin() {
        super.firstPlayerToWin(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTerminal() {
        return super.isTerminal(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void secondPlayerToMove() {
        super.secondPlayerToMove(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void firstPlayerToMove() {
        super.firstPlayerToMove(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getChild() {
        super.getChild(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getChildStates() {
        return super.getChildStates(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameState undoMove() {
        return super.undoMove(); 
    }

    @Override
    public GameState doMove(Move move) {
        return super.doMove(move); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean possibleMove(Move move) {
        return super.possibleMove(move); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getAllMoves() {
        return super.getAllMoves(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStartState(Player firstPlayer) {
        super.setStartState(firstPlayer); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class NimMove extends Move{
        int matchesRemoved;
        public NimMove(Object move) {
            super(move);
            matchesRemoved = (int)move;
        }

        public void executeMove(int state) {
            state =- matchesRemoved;
        }         
    }
        
        
    
}
