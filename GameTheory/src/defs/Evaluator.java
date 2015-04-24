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
    public int heuristicValue() {
        return gameNodeValue;
    }

    public int simpleHeuristicValue() {
        return gameNodeValue;
    }

    public int getMinValue() {
        return gameNodeValue;
    }

    public int getMaxValue() {
        return gameNodeValue;
    }

    public int exactValue() {
        return gameNodeValue;
    }

    public int evaluate() {
        return gameNodeValue;
    }

}
