package interfaces;

import defs.general.GenericTable;

public interface UsableAsDameViewModel<T> {
	GenericTable<T> getGameTable();
	void moveStone(int sourceRowIndex, int sourceColumnIndex, int targetRowIndex, int targetColumnIndex);
}
