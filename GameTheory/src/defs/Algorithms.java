package defs;

/*
 * Klasse f�r die Algorithmen (Als statische Methoden)
 */
public class Algorithms {

    // Methoden
    // Statische Methoden
    public static int alphaBeta(Evaluator evaluator, GameState gstate) {
        Evaluator eval = evaluator;
        if (gstate.isTerminalState()) {
            return eval.heuristicValue();
        }
        return 1;
    }

    public static int miniMax(Evaluator evaluator, GameState gstate) {
        Evaluator eval = evaluator;
        if (gstate.isTerminalState()) {
            return eval.heuristicValue();
        }
        return 1;
    }
}
