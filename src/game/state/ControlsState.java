package game.state;

import game.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import boboy.color.CColor;
import boboy.game.gui.GUIRectangle;

public class ControlsState extends BasicGameState {

	private final int id;
	
	private final GUIRectangle goBackBox;
	
	private final static String[] controls = {
		"ENTER: to toggle into PLAY / DRAW mode",
		"LEFT_MOUSE BUTTON (hold): add living cells when in DRAW mode",
		"RIGHT_MOUSE BUTTON (hold): remove living cells when in DRAW mode",
		"SPACE: clear cells and go to DRAW mode",
		"Z: to change between circular and rectangular cells"
	};
	
	public ControlsState(final int id) {
		this.id = id;
		
		//pause button
		int goBackWidth = 70;
		int goBacHeight = 40;
		goBackBox = new GUIRectangle(50 - goBackWidth / 2, 30 - goBacHeight / 2,
				goBackWidth, goBacHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightRed);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		Graphics g = container.getGraphics();
		
		//goBack
		goBackBox.init(g);
		goBackBox.setText("Back");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(CColor.lightRed);
		for (int i = 0; i < controls.length; i++) {
			g.drawString(controls[i], 10, 100 + i * 25);
		}
		
		goBackBox.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		if (goBackBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			//play sound
			Game.BUTTON_SOUND.play(1.2f,.3f);
			
			game.enterState(Game.PLAY.getID());
		}
	}

	@Override
	public int getID() {
		
		return id;
	}

}
