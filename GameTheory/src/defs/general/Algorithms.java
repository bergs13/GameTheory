package defs.general;

/*
 * Klasse f�r die Algorithmen (Als statische Methoden)
 */
public class Algorithms {

    // Methoden
    // Statische Methoden
    public static int alphaBeta(Evaluator evaluator, GameState gstate, int depth, int alpha, int beta) {
        int bestValue = Integer.MIN_VALUE;
        if (gstate.isTerminal()|| depth == 0) {
            return evaluator.evaluate(gstate);
        }
        else{
            for(GameState childState : gstate.getChildStates())
            { 
                int value = -alphaBeta(evaluator, childState, depth-1, -beta, -alpha);
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(value, alpha);
                if (alpha >= beta){
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
        }
        else{
            for(GameState childState : gstate.getChildStates()){
                bestValue = Math.max(bestValue, -miniMax(evaluator, childState, depth));                        
            }
        }
        return bestValue;
    }
}
