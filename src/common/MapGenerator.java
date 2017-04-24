package common;

import java.awt.Graphics;

public abstract class MapGenerator implements Drawable {
	public abstract void generateMap(int width, int height);
	public abstract void draw(Graphics g);
}
