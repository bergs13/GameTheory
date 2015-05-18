package interfaces;

import defs.general.Table;

public interface UsableAsDameViewModel<T> {
	Table<T> getGameTable();
	void moveStone(int sourceRowIndex, int sourceColumnIndex, int targetRowIndex, int targetColumnIndex);
}
