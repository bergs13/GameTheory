package defs.dame;

import java.awt.Color;

public class DameConstants {
	public class DameEventConstants {
		public static final String STARTSTATESET = "STARTSTATESET";
		public static final String STONEMOVED = "STONEMOVED";
	}

	public enum Piece {
		BLACK, WHITE, EMPTY;
	}

	// Constants for the game field
	public static final int SQUARESPERSIDE = 5 /*Number of squares in one row/column*/;
	public static final int SINGLESQUARESIDESIZE = 100;
	public static final int PIECEDIAMETER = 88;
	
	public static final Color LIGHTFIELDCOLOR = new Color(255, 178, 102);
	public static final Color DARKFIELDCOLOR = new Color(153, 76, 0);
}
