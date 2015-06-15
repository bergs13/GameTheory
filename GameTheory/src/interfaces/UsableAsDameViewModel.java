package interfaces;

import defs.dame.DameTable;
import defs.general.Player;

public interface UsableAsDameViewModel<T> {
	DameTable getDameTable();

	void restartGame(Player playerOne, Player playerTwo);

	void applyPlayerSettings(boolean firstPlayerIsHuman,
			boolean secondPlayerIsHuman);

	void performManualMove(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex);

	void performCPUMove();
}