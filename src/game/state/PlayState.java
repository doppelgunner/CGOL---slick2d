package game.state;

import game.Game;
import game.grid.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import boboy.color.CColor;
import boboy.game.gui.GUIRectangle;

public class PlayState extends BasicGameState {

	private final int id;
	
	private World world;
	private final float X = 0, Y = 30;
	private final int WIDTH = 500, HEIGHT = 400;
	
	private final GUIRectangle pauseBox;
	private final GUIRectangle resumeBox;
	private final GUIRectangle controlBox;
	private final GUIRectangle menuBox;
	
	private boolean pause;
	
	private Color defaultColor;
	
	private float counter;
	private final int DELAY = 300;
	
	public PlayState(final int id) {
		this.id = id;
		
		//pause button
		int pauseWidth = 70;
		int pauseHeight = 40;
		pauseBox = new GUIRectangle(50 - pauseWidth / 2, 30 - pauseHeight / 2,
				pauseWidth, pauseHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightRed);
		
		//resume button
		int resumeWidth = 150;
		int resumeHeight = 60;
		resumeBox = new GUIRectangle(
				Game.WIDTH / 2 - resumeWidth / 2,Game.HEIGHT / 2 - resumeHeight / 2 - 80,
				resumeWidth,resumeHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightRed);
		
		//instruction button
		int controlWidth = 150;
		int controlHeight = 60;
		
		controlBox = new GUIRectangle(
				Game.WIDTH / 2 - controlWidth / 2,Game.HEIGHT / 2 - controlHeight / 2,
				controlWidth,controlHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightRed);
		
		//menu button
		int menuWidth = 130;
		int menuHeight = 55;
		menuBox = new GUIRectangle(
				Game.WIDTH / 2 - menuWidth / 2, Game.HEIGHT / 2 - menuHeight / 2 + 80,
				menuWidth, menuHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightRed);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
		Input input = container.getInput();
		input.clearControlPressedRecord();
		input.clearKeyPressedRecord();
		input.clearMousePressedRecord();
		
		world = new World(Game.WIDTH / 2 - WIDTH / 2 + X, Game.HEIGHT / 2 - HEIGHT / 2 + Y,
				WIDTH, HEIGHT,
				20,20);
		
		defaultColor = CColor.normalWhite;
		Graphics g = container.getGraphics();
		g.setColor(defaultColor);
		
		//pause
		pauseBox.init(g);
		pauseBox.setText("Pause");
		
		//resume
		resumeBox.init(g);
		resumeBox.setText("Resume");
		
		//instruction
		controlBox.init(g);
		controlBox.setText("Controls");
		
		//menu
		menuBox.init(g);
		menuBox.setText("Menu");
		
		counter = 0;
		pause = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		world.render(g);
		
		g.setColor(CColor.lightRed);
		g.drawString(!world.isPlay() ? "mode: DRAW" : "mode: PLAY", Game.WIDTH / 2 - 50,20);
		if (!pause) {
			
			//gui
			 pauseBox.draw(g);
		} else {
			g.setColor(CColor.alphaDarkRed);
			g.fillRect(10,10, Game.WIDTH - 20, Game.HEIGHT - 20);
			g.setColor(CColor.lightRed);
			g.drawRect(10,10, Game.WIDTH - 20, Game.HEIGHT - 20);
			
			//gui
			resumeBox.draw(g);
			controlBox.draw(g);
			menuBox.draw(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		Input input = container.getInput();
		
		if (!container.hasFocus()) {
			pause = true;
		}
		
		if (!pause) {
			
			//when pause is clicked
			if (pauseBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				pause = true;
			}
			if (world.isPlay()) {
				if (counter < DELAY) {
					counter += delta;
				} else {
					counter = 0;
					world.update(container, delta);
				}
			} else {
				world.update(container, delta);
			}
		} else {
			
			//when resume is clicked
			if (resumeBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				//resume game
				pause = false;
			}
			
			//when control is clicked
			if (controlBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				game.enterState(Game.CONTROL.getID());
			}
			
			//when menu is clicked
			if (menuBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				game.enterState(Game.MENU.getID());
			}
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
