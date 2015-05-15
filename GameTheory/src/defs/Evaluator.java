package defs;

/* 
 * Basisklasse für Evaluatoren
 */
public class Evaluator {
	// Members
    private int gameNodeValue;
    // Konstruktoren
    public Evaluator() {
    }

    // Methoden
    public int heuristicValue(GameState gameState) {
        return gameNodeValue;
    }

    public int simpleHeuristicValue(GameState gameState) {
        return gameNodeValue;
    }

    public int getMinValue(GameState gameState) {
        return gameNodeValue;
    }

    public int getMaxValue(GameState gameState) {
        return gameNodeValue;
    }

    public int exactValue(GameState gameState) {
        return gameNodeValue;
    }

    public int evaluate(GameState gameState) {
        return gameNodeValue;
    }

}
