package game;

import game.state.ControlsState;
import game.state.MenuState;
import game.state.PlayState;
import game.state.SplashState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import boboy.color.CColor;

public class Game extends StateBasedGame {
	
	public static final GameState SPLASH = new SplashState(0);
	public static final GameState MENU = new MenuState(1);
	public static final GameState PLAY = new PlayState(2);
	public static final GameState CONTROL = new ControlsState(3);
	
	public static final String TITLE = "Game Of Life";
	public static final int WIDTH = 640, HEIGHT = 480;
	public static final int FPS = 60;
	
	public static Sound BUTTON_SOUND;

	public Game(String name) throws SlickException {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(SPLASH);
		addState(MENU);
		addState(PLAY);
		addState(CONTROL);
		container.getGraphics().setBackground(CColor.darkRed);
		
		BUTTON_SOUND = new Sound("sounds/bubbles.wav");
	}
	
	public static void main (String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game(TITLE));
		app.setDisplayMode(WIDTH,HEIGHT, false);
		app.setTargetFrameRate(FPS);
		app.setShowFPS(false);
		app.setIcons(new String[] {"resources/icon-32.png",
				"resources/icon-24.png", "resources/icon-16.png"});
		app.start();
		
		
	}
	
}
