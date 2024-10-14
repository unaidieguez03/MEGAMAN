package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
	BufferedImage lastImg;
	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) throws FileNotFoundException, IOException {
		topSpeed = 20f;
		airSpeed = topSpeed;
		jumpSpeed = topSpeed;
		jumping = false;
		fallSpeed = 0;
		inAir = true;
		this.gp = gp;
		lastDirection = Direction.UP;
		screenX = gp.screenWith / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		this.keyH = keyH;
		img = ImageIO.read(new FileInputStream("res/tiles/floor.png"));
		up = img;
		right = img;
		down = img;
		left = img;
		// right = Player.rotate(img);
		// down = Player.rotate(right);
		// left = Player.rotate(down);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 8;
		solidDefoultX = solidArea.x;
		solidDefoultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		initValues();

	}

	public void initValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21 - solidArea.y;
		speed = 0;
		direction = Direction.STOP;
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
		getDirectionInput();
		gp.collisionChequer.checkTile(this);
		getCollision();
	}

	public void getCollision() {
		if (this.collisions.left != true && direction == Direction.LEFT) {
			this.setWorldX(worldX -= speed);
		}
		if (this.collisions.right != true && direction == Direction.RIGHT) {
			this.setWorldX(worldX += speed);
		}
		if (inAir) {
			if (jumpSpeed >= 0 && jumping) {
				jumpSpeed = jumpSpeed - grabity;
				onLand = false;
				this.setWorldY(worldY -= (int) jumpSpeed);
			} else {
				jumping = false;
				fallSpeed = fallSpeed > topSpeed ? topSpeed : fallSpeed + grabity;
				this.setWorldY(worldY += (int) fallSpeed);
				if (onLand) {
					this.setWorldY(worldY - (int) fallSpeed);
					jumpSpeed = topSpeed;
					onLand = false;
					fallSpeed = 0;
					inAir = false;
				}
			}
		}

	}

	private double aproach(double goal, double current, double delta) {
		if (current > goal + 0.1) {
			return current - delta;
		} else if (current < goal - 1) {
			return current + delta;
		} else {
			return goal;
		}
	}

	public void getDirectionInput() {
		speed = keyH.isShiftPressed() && (direction == Direction.LEFT || direction == Direction.RIGHT)
				? aproach(15, speed, 0.9)
				: direction != Direction.STOP ? aproach(5, speed, 0.9) : speed;
		if (keyH.isSpacePressed() && onLand == true) {
			jumping = true;
			inAir = true;
		}
		if (keyH.isLeftPressed()) {
			direction = Direction.LEFT;
			lastDirection = direction;
			stop = !collisions.left ? false : true;
		} else if (keyH.isRightPressed()) {
			direction = Direction.RIGHT;
			lastDirection = direction;
			stop = !collisions.right ? false : true;
		} else {
			speed = aproach(0, speed, 0.9);
			stop = speed == 0 ? true : false;
			if (stop != true) {
				switch (lastDirection) {

					case LEFT:
						if (this.collisions.left != true) {
							this.setWorldX(worldX -= speed);
						}
						break;
					case RIGHT:
						if (this.collisions.right != true) {
							this.setWorldX(worldX += speed);
						}
						break;
					default:
						break;
				}
			}
			direction = Direction.STOP;
		}
	}

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
				lastImg = right;
				break;
			default:
				break;
		}
		g2.drawImage(lastImg, screenX, screenY, gp.tileSize, gp.tileSize, null);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		g2.setColor(Color.RED);
	}
}
