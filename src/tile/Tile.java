package tile;
import java.awt.image.BufferedImage;

import main.Collision;

public class Tile {
	int x, y;
	public BufferedImage image;
	public boolean collision = false;
	public Collision collisions = new Collision();
}
