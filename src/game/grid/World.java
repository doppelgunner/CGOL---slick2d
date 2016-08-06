package game.grid;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import game.Game;
import game.grid.Cell.BoxState;
import game.rules.GameOfLife;
import game.rules.Rules;

public class World {
	private Cell[][] boxes;
	private GameOfLife rule;
	private int row, col;
	private boolean play = false; 
	private BoxState[][] tempStates;
	private float x, y;
	private int width, height;
	
	public boolean isPlay() {
		return play;
	}
	
	public World(float x, float y, int width, int height, int row, int col) {
		this.x = x; this.y = y;
		this.width = width; this.height = height;
		this.row = row; this.col = col;
		boxes = new Cell[row][col];
		tempStates = new BoxState[row][col];
		
		float cellWidth = width / col;
		float cellHeight = height / row;
		
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				boxes[i][j] = new Cell(x + j * cellWidth,y + i * cellHeight,
						(int)cellWidth, (int)cellHeight);
			}
		}
		
		rule = new GameOfLife();
	}
	
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		Point mouse = new Point(input.getMouseX(), input.getMouseY());
		
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			
			for (int i = 0; i < boxes.length; i++) {
				for (int j = 0; j < boxes[i].length; j++) {
					if (!play) {
						if (boxes[i][j].isMouseInBounds(mouse) && boxes[i][j].getState() == BoxState.DEAD) {
							boxes[i][j].setState(BoxState.ALIVE);
						}
					}
				}
			}
		}
		
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				if(play) {
					tempStates[i][j] = rule.check(i, j, boxes);
				}
			}
		}
		
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				if(play) {
					boxes[i][j].setState(tempStates[i][j]);
				}
			}
		}
		
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			for (int i = 0; i < boxes.length; i++) {
				for (int j = 0; j < boxes[i].length; j++) {
					if (boxes[i][j].isMouseInBounds(mouse) && boxes[i][j].getState() == BoxState.ALIVE) {
						boxes[i][j].setState(BoxState.DEAD);
					}
				}
			}
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			play = !play;
		}
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			for (int i = 0; i < boxes.length; i++) {
				for (int j = 0; j < boxes[i].length; j++) {
					boxes[i][j].setState(BoxState.DEAD);	
				}
			}
			if (play) {
				play = !play;
			}
		}
		
		if (input.isKeyPressed(Input.KEY_Z)) {
			for (int i = 0; i < boxes.length; i++) {
				for (int j = 0; j < boxes[i].length; j++) {
					boxes[i][j].switchBody();
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				boxes[i][j].render(g);
			}
		}
		/*stops 
		if (play) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
	}
}
