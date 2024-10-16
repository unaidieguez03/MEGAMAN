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
	GamePanel gp;
	KeyHandler keyH;
	public int spriteSpeed = 8;

	public Player(GamePanel gp, KeyHandler keyH) throws FileNotFoundException, IOException {
		this.gp = gp;

		topSpeed = 20f;
		airSpeed = topSpeed;
		jumpSpeed = topSpeed;
		fallSpeed = 0;
		lastDirection = Direction.UP;
		screenX = gp.screenWith / 2 - (gp.tileSize / 2);
		screenY = (gp.screenHeight * 4 / 6) - (gp.tileSize / 2);
		this.keyH = keyH;
		img = ImageIO.read(new FileInputStream("res/player/player_standing.png"));
		down_right = ImageIO.read(new FileInputStream("res/player/player_falling.png"));
		down_left = mirrir(down_right);
		up_right = ImageIO.read(new FileInputStream("res/player/player_jumping.png"));
		up_left = mirrir(up_right);
		right_1 = ImageIO.read(new FileInputStream("res/player/player_runing1.png"));
		right_2 = ImageIO.read(new FileInputStream("res/player/player_runing.png"));
		left_1 = mirrir(right_1);
		left_2 = mirrir(right_2);
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

	private static BufferedImage mirrir(BufferedImage img) {
		int height = img.getHeight();
		int width = img.getWidth();
		// Creating Buffered Image to store the output
		BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int j = 0; j < height; j++) {
			for (int i = 0, w = width - 1; i < width; i++, w--) {
				int p = img.getRGB(i, j);
				// set mirror image pixel value - both left and right
				res.setRGB(w, j, p);
			}
		}
		return res;

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
		affectGravity();
		spriteAnimation();
	}

	public void affectGravity() {
		if (inAir) {
			if (jumpSpeed >= 0 && jumping) {
				jumpSpeed = jumpSpeed - grabity;
				onLand = false;
				jumping = true;
				this.setWorldY(worldY -= (int) jumpSpeed);
				if (this.collisions.up) {
					jumping = false;
					fallSpeed = jumpSpeed;
				}
			} else {
				fallSpeed = fallSpeed > topSpeed ? topSpeed : fallSpeed + grabity;
				jumping = false;
				this.setWorldY(worldY += (int) fallSpeed);
				if (onLand) {
					System.out.println(this.worldY + " ostiaaa " + gp.collisionChequer.lastX);
					System.out.println(gp.collisionChequer.lastX>this.worldY+gp.tileSize);
					this.setWorldY(gp.collisionChequer.lastX+gp.tileSize<this.worldY?gp.collisionChequer.lastX+gp.tileSize:gp.collisionChequer.lastX);
					System.out.println(this.worldY + " ostiaaa " + gp.collisionChequer.lastX);
					jumpSpeed = topSpeed;
					onLand = false;
					fallSpeed = 0;
					inAir = false;
				}
			}
		}
	}

	public void getCollision() {
		if (this.collisions.left != true && direction == Direction.LEFT) {
			this.setWorldX(worldX -= speed);
		}
		if (this.collisions.right != true && direction == Direction.RIGHT) {
			this.setWorldX(worldX += speed);
		}
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
		spriteSpeed = keyH.isShiftPressed() ? 4 : 8;
		speed = keyH.isShiftPressed() && (direction == Direction.LEFT || direction == Direction.RIGHT)
				? aproach(15, speed, 0.9)
				: direction != Direction.STOP ? aproach(5, speed, 0.75) : speed;
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
			speed = aproach(0, speed, 0.6);
			stop = speed == 0 ? true : false;
			direction = Direction.STOP;
		}
	}

	public void spriteAnimation() {
		spriteCounter++;
		if (spriteCounter > spriteSpeed) {
			if (spriteNum == 1) {
				spriteNum = 0;

			} else if (spriteNum == 0) {

				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	public void draw(Graphics g2) {
		switch (direction) {
			case UP:

				break;
			case DOWN:
				break;
			case LEFT:
				if (spriteNum == 1) {
					lastImg = left_1;
				} else {
					lastImg = left_2;
				}
				break;
			case RIGHT:
				if (spriteNum == 1) {
					lastImg = right_1;
				} else {
					lastImg = right_2;
				}
				break;
			case STOP:
				lastImg = img;
				break;
			default:
				break;
		}
		if (inAir) {
			if (jumping) {
				System.out.println("jojooo");
				switch (direction) {
					case LEFT:
						lastImg = up_left;
						break;

					case RIGHT:
						lastImg = up_right;
						break;
					default:
						break;
				}
			} else {
				switch (direction) {
					case LEFT:
						lastImg = down_left;
						break;

					case RIGHT:
						lastImg = down_right;
						break;
					default:
						break;
				}
			}
		}
		g2.drawImage(lastImg, screenX, screenY, gp.tileSize, gp.tileSize, null);
		// g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
		// solidArea.height);
		// g2.setColor(Color.RED);
	}
}
