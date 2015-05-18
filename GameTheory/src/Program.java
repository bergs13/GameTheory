import defs.*;
import defs.general.Evaluator;
import defs.general.GameState;

public class Program {

	public static void main(String[] args) {
		System.out.println("the programm.");
	}
        public static int alphaBeta(Evaluator evaluator, GameState gstate, int depth, int alpha, int beta) {
        if (gstate.isTerminal()|| depth == 0) {
            return evaluator.evaluate(gstate);
        }
        else{
            for(GameState childState : gstate.getChildStates())
            { 
                int value = -alphaBeta(evaluator, childState, depth-1, -beta, -alpha);
                if (alpha >= beta){
                    return beta;
                }
                else{
                   alpha = Math.max(value, alpha);
                }
            }
        }
        return alpha;
    }
}
