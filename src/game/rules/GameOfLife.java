package game.rules;

import game.grid.Cell;
import game.grid.Cell.BoxState;

public class GameOfLife extends Rules {
	
	@Override
	public BoxState check(int row, int col, Cell[][] boxes) {
		
		int[][] neighbors = {
				{Math.floorMod(row - 1, boxes.length), Math.floorMod(col - 1, boxes[row].length)},
				{Math.floorMod(row - 1, boxes.length), col},
				{Math.floorMod(row - 1, boxes.length), Math.floorMod(col + 1, boxes[row].length)},
				{row, Math.floorMod(col - 1, boxes[row].length)},
				{row, Math.floorMod(col + 1, boxes[row].length)},
				{Math.floorMod(row + 1, boxes.length), Math.floorMod(col - 1, boxes[row].length)},
				{Math.floorMod(row + 1, boxes.length), col},
				{Math.floorMod(row + 1, boxes.length), Math.floorMod(col + 1, boxes[row].length)}
		};
		
		
		int alive = 0;
		BoxState state = boxes[row][col].getState();
		
		for (int i = 0; i < neighbors.length; i++) {
			if (boxes[neighbors[i][0]][neighbors[i][1]].getState() == BoxState.ALIVE ||
				boxes[neighbors[i][0]][neighbors[i][1]].getState() == BoxState.BORN) {
				alive++;
			}
		}
	
		
		if (alive == 3 && (state == BoxState.DEAD || state == BoxState.DYING)) {
			return BoxState.BORN;
		} else if ((alive == 2 || alive == 3) && (state == BoxState.ALIVE || state == BoxState.BORN )) {
			return BoxState.ALIVE;
		} else if ((alive > 3 || alive < 2) && (state == BoxState.ALIVE || state == BoxState.BORN)) {
			return BoxState.DYING;
		} else {
			return BoxState.DEAD;
		}
		
	}
	
}
