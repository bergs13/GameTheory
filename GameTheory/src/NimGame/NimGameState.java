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
    private int matches;
    
    public NimGameState() {
    }

    public NimGameState(ArrayList moves, ArrayList states, Move move) {
        super(moves, states, move);
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
    public ArrayList getStateHistory() {
        return super.getStateHistory(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getMoveHistory() {
        return super.getMoveHistory(); //To change body of generated methods, choose Tools | Templates.
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
    public void undoMove() {
        super.undoMove(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doMove(Move move) {
        super.doMove(move); //To change body of generated methods, choose Tools | Templates.
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
    
    
}
