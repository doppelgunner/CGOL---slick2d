package game.grid;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import boboy.color.CColor;
import game.theme.Theme;

public class Cell {
	private float minX, minY, maxX, maxY;
	private int width, height;
	private Shape body;
	private BoxState state;
	
	private Rectangle bodyR;
	private Circle bodyC;
	
	public static enum BoxState {
		DEAD,
		ALIVE,
		BORN,
		DYING
	}
	
	public Cell(float x, float y, int width, int height) {
		minX = x; minY = y;
		maxX = x + width; maxY = y + height;
		
		bodyR = new Rectangle(x, y, width, height);
		bodyC = new Circle(x + width / 2, y + height / 2, height < width ? height / 2 : width /2);
		
		body = bodyC; //default
		
		state = BoxState.DEAD;
		
		Theme.setAccent(200,100,100); //delete this to return to default colors
	}
	
	public void switchBody() {
		if (body instanceof Circle) {
			body = bodyR;
		} else if (body instanceof Rectangle) {
			body = bodyC;
		}
	}
	
	public boolean isMouseInBounds(Point p) {
		if (p.getX() > minX && p.getX() < maxX && p.getY() > minY && p.getY() < maxY) return true;
		
		return false;
	}
	
	public void setState(BoxState state) {
		this.state = state;
	}
	
	public BoxState getState() {
		return state;
	}
	
	public void render(Graphics g) {
		switch (state) {
		case ALIVE:
			g.setColor(Theme.alive);
			g.fill(body);
			break;
		case DEAD:
			g.setColor(CColor.lightRed);
			g.draw(body);
			break;
		case DYING:
			g.setColor(Theme.dying);
			g.fill(body);
			break;
		case BORN:
			g.setColor(Theme.born);
			g.fill(body);
		default:
			break;
		}
	}
	
}
