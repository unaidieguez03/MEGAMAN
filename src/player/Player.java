package player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
	int screenX;
	int screenY;
	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) throws FileNotFoundException, IOException {
		this.gp = gp;
		screenX = gp.screenWith / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		this.keyH = keyH;
		img = ImageIO.read(new FileInputStream("res/direction.png"));
		up = img;
		right = Player.rotate(img);
		down = Player.rotate(right);
		left = Player.rotate(down);

		// solidArea = new Rectangle();
		// solidArea.x = 8;
		// solidArea.y = 16;
		// solidDefoultX = solidArea.x;
		// solidDefoultY = solidArea.y;
		// solidArea.width = 32;
		// solidArea.height = 32;

	}
	public static BufferedImage rotate(BufferedImage img) {

		// Getting Dimensions of image
		int width = img.getWidth();
		int height = img.getHeight();

		// Creating a new buffered image
		BufferedImage newImage = new BufferedImage(
				img.getWidth(), img.getHeight(), img.getType());

		// creating Graphics in buffered image
		Graphics2D g2 = newImage.createGraphics();

		// Rotating image by degrees using toradians()
		// method
		// and setting new dimension t it
		g2.rotate(Math.toRadians(90), width / 2,
				height / 2);
		g2.drawImage(img, null, 0, 0);

		// Return rotated buffer image
		return newImage;
	}


	public void update() {

		move();
	}

	public void getDirectionInput() {
		if (keyH.isUpPressed()) {
			direction = Direction.UP;
		} else if (keyH.isDownPressed()) {
			direction = Direction.DOWN;
		} else if (keyH.isLeftPressed()) {
			direction = Direction.LEFT;
		} else if (keyH.isRightPressed()) {
			direction = Direction.RIGHT;
		} else {
			direction = Direction.STOP;
		}
	}

	public void move() {
		getDirectionInput();
		switch (direction) {
			case UP:
				screenY -= 10;
				break;
			case DOWN:

				screenY += 10;
				break;
			case LEFT:

			//	screenX -= 10;
				break;
			case RIGHT:

			//	screenX += 10;
				break;
			default:
				break;
		}
	}
	BufferedImage lastImg;
	public void draw(Graphics g2) {
		switch (direction) {
			case UP:
				lastImg = up;
				break;
			case DOWN:
				lastImg = down;
				break;
			case LEFT:
				lastImg = left;
				break;
			case RIGHT:
				lastImg = right;
				break;
			case STOP:
				break;
			default:
				break;
		}
		g2.drawImage(lastImg, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
