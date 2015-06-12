package defs.general;

import java.util.HashSet;
import java.util.Set;

/*
 * Klasse f�r die Algorithmen (Als statische Methoden)
 */
public class Algorithms {

    // Methoden
    // Statische Methoden
    public static int alphaBeta(Evaluator evaluator, GameState gstate, int depth, int alpha, int beta) {
        int bestValue = Integer.MIN_VALUE;
        if (gstate.isTerminal() || depth == 0) {
            return evaluator.evaluate(gstate);
        } else {
            for (GameState childState : gstate.getChildStates()) {
                int value = -alphaBeta(evaluator, childState, depth - 1, -beta, -alpha);
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(value, alpha);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        return bestValue;
    }

    public static int miniMax(Evaluator evaluator, GameState gstate, int depth) {
        int bestValue = Integer.MIN_VALUE;
        if (gstate.isTerminal() || depth == 0) {
            return evaluator.evaluate(gstate);
        } else {
            for (GameState childState : gstate.getChildStates()) {
                bestValue = Math.max(bestValue, -miniMax(evaluator, childState, depth));
            }
        }
        return bestValue;
    }

    //

//    public static int alphaBeta2(Evaluator evaluator, GameState gstate, int depth, int alpha, int beta) {
//        GameState bestGameState = new GameState();
//        bestGameState.setValue(Integer.MIN_VALUE);
//        if (gstate.isTerminal() || depth == 0) {
//            gstate.setValue(evaluator.evaluate(gstate));
//            return gstate.getValue();
//        } else {
//            for (GameState childState : gstate.getChildStates()) {
//                int value= -alphaBeta(evaluator, childState, depth - 1, -beta, -alpha);
//                bestGameState.setValue(Math.max(bestGameState.getValue(), value));
//                
//                }
//                alpha = Math.max(childState.getValue(), alpha);
//                if (alpha >= beta) {
//                    break;
//                }
//            }
//        }
//        System.out.println(bestGameState.getValue());
//        return bestGameState.getValue();
//    }

}
