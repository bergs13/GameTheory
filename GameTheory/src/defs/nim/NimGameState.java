/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.nim;

import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;

import java.util.ArrayList;

/**
 *
 * @author Thunderchild
 */
public class NimGameState extends GameState {

    public int matches;

    public NimGameState() {
    }

    public NimGameState(GameState parentState, Move move) {
        super(parentState, move);
        this.matches = ((NimGameState)parentState).matches;
        ((NimMove)move).executeMove(this.matches);
    }

    @Override
    public void findPossibleMoves() {
        for (int i = 1; i <= Math.min(3, matches); i++) {
            super.getChildMoves().add(new NimMove(i));
        }

    }

    @Override
    public void createChildStates() {
        for(Move move : this.getAllMoves()) {
            super.getChildStates().add(new GameState(this, move));
        }
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
    public ArrayList<Move> getAllMoves() {
        return super.getAllMoves();
    }

    public void setStartState(Player firstPlayer, int startMatches) {
        super.setStartState(firstPlayer);
        this.matches = startMatches;
    }

}
