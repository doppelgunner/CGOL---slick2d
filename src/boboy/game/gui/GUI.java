package boboy.game.gui;

import org.newdawn.slick.Graphics;

public interface GUI {
	
	void init(Graphics g);
	
	void draw(Graphics g);
	
	void setText(String text);
	String getText();
	
	void setHoverText(String hoverText);
	String getHoverText();
	
	void setHoverText(boolean hoverTextIsTrue);
}
