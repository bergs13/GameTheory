package interfaces;

import defs.dame.DameTable;

public interface UsableAsDameViewModel<T> {
	DameTable getDameTable();

	void restartGame();

	void applyPlayerSettings(boolean firstPlayerIsHuman,
			boolean secondPlayerIsHuman);

	void performManualMove(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex);

	void performCPUMove();
}