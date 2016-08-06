package game.theme;

import org.newdawn.slick.Color;

public class Theme {
	public static Color alive = Color.green;
	public static Color dying = Color.red;
	public static Color born = Color.yellow;
	
	public static void setDefault() {
		alive = Color.green;
		dying = Color.red;
		born = Color.yellow;
	}
	
	public static void setAccent(int r, int g, int b) {
		
		dying = new Color(r,g,b);
		
		r += 50;
		g += 50;
		b += 50;
		
		if (r > 240) r = 240;
		if (g > 240) g = 240;
		if (b > 240) b = 240;
		
		born = new Color(r,g,b);
		
		r += 40;
		g += 40;
		b -= 40;
		
		if (r > 240) r = 240;
		if (g > 240) g = 240;
		if (b > 240) b = 240;
		if (b < 0) b = 0;
		
		alive = new Color(r,g,b);
	}
}
