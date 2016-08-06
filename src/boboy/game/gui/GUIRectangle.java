package boboy.game.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class GUIRectangle extends Rectangle implements GUI {

	private Color backColor;
	private Color foreColor;
	
	private Color hoverBackColor;
	private Color hoverForeColor;
	
	private String text = "";
	private String hoverText = "";
	private boolean hoverTextIsTrue;
	
	private int textWidth;
	private int textHeight;
	
	private int hoverTextWidth;
	private int hoverTextHeight;
	private boolean hover;

	private Color defaultColor;
	
	private Font font;
	
	
	
	public GUIRectangle(float x, float y, float width, float height,
			Color backColor, Color foreColor, Color hoverBackColor, Color hoverForeColor) {
		
		super(x, y, width, height);
		
		this.backColor = backColor;
		this.foreColor = foreColor;
		this.hoverBackColor = hoverBackColor;
		this.hoverForeColor = hoverForeColor;
	}

	@Override
	public void draw(Graphics g) {
		defaultColor = g.getColor();
		
		//background
		g.setColor((hover) ? hoverBackColor : backColor);
		g.fill(this);
		
		//foreground
		g.setColor((hover) ? hoverForeColor : foreColor);
		g.draw(this);
		
		//text
		if (!text.equals("")) {
			if (hoverTextIsTrue) {
				g.drawString((hover) ? hoverText : text, x + width / 2 - 
						((hover) ? hoverTextWidth : textWidth) / 2,
						y + height / 2 - ((hover) ? hoverTextHeight : textHeight) / 2);
			} else {
				g.drawString(text, x + width / 2 - textWidth / 2,
					y + height / 2 - textHeight / 2);
			}
		} else {
			if (hover) {
				if (!hoverText.equals("")) {
					if (hoverTextIsTrue) {
						g.drawString(hoverText, x + width / 2 - hoverTextWidth / 2,
								y + height / 2 - hoverTextHeight / 2);
					}
				}
			}
		}
		
		g.setColor(defaultColor);
	}
	
	public void init(Graphics g) {
		font = g.getFont();
	}
	
	public void setText(String text) {
		this.text = text;
		textWidth = font.getWidth(text);
		textHeight = font.getHeight(text);
	}
	
	public String getText() {
		return text;
	}
	
	public void setHoverText(String hoverText) {
		this.hoverText = hoverText;
		hoverTextWidth = font.getWidth(hoverText);
		hoverTextHeight = font.getHeight(hoverText);
		setHoverText(true);
	}
	
	public void setHoverText(boolean hoverTextIsTrue) {
		this.hoverTextIsTrue = hoverTextIsTrue;
	}
	
	public String getHoverText() {
		return hoverText;
	}
	
	public boolean isMouseInside(float x, float y) {
		if (x < minX || x > maxX) {
			hover = false;
			return false;
		}
		if (y < minY || y > maxY) {
			hover = false;
			return false;
		}
		else {
			hover = true;
			return true;
		}
	}
	
	
}
