package game.rules;

import game.grid.Cell;
import game.grid.Cell.BoxState;

public abstract class Rules {
	
	public abstract BoxState check(int row, int col, Cell[][] boxes);
}
