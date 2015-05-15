package defs;

/*
 * Klasse f�r die Algorithmen (Als statische Methoden)
 */
public class Algorithms {

    // Methoden
    // Statische Methoden
    public static int alphaBeta(Evaluator evaluator, GameState gstate, int depth, int alpha, int beta) {
        if (gstate.isTerminal()|| depth == 0) {
            return evaluator.evaluate();
        }
        else{
            for(GameState childState : gstate.getChildStates())
            { 
                alpha = -alphaBeta(evaluator, childState, depth-1, -beta, -alpha);
                if (alpha >= beta){
                    return beta;
                }
            }
        }
        return alpha;
    }

    public static int miniMax(Evaluator evaluator, GameState gstate, int depth) {
        int bestValue = Integer.MIN_VALUE;
        if (gstate.isTerminal() || depth == 0) {
            return evaluator.evaluate();
        }
        else{
            for(GameState childState : gstate.getChildStates()){
                bestValue = Math.max(bestValue, -miniMax(evaluator, gstate, depth));                        
            }
        }
        return bestValue;
    }
}
