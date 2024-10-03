package player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	private static final int grabity = 10;
	enum Direction {
		UP, DOWN, LEFT, RIGHT, STOP
	};

	BufferedImage img, up, down, left, right;
	public int worldX, worldY;
	public int solidDefoultX, solidDefoultY;
	public int speed;

	public player.Player.Direction direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea;
	public boolean collision = false;

	int screenX;
	int screenY;

}
