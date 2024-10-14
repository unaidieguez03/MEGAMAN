package player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Collision;

public class Entity {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT, STOP
	};

	static final double grabity = 0.8;
	public boolean inAir, onLand;
	public double airSpeed = 0, fallSpeed = 0,jumpSpeed = 0, topSpeed = 0;
	BufferedImage img, up, down, left, right;
	public int worldX, worldY;
	public int solidDefoultX, solidDefoultY;
	public double speed;
	
	public Direction direction;
	public Direction lastDirection;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea;
	public Collision collisions;

	public double defoultSpeed = 5;
	public boolean jumping, stop;

	public int screenX;
	public int screenY;

	public Entity() {
		collisions = new Collision();
	}


	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public BufferedImage getUp() {
		return up;
	}

	public void setUp(BufferedImage up) {
		this.up = up;
	}

	public BufferedImage getDown() {
		return down;
	}

	public void setDown(BufferedImage down) {
		this.down = down;
	}

	public BufferedImage getLeft() {
		return left;
	}

	public void setLeft(BufferedImage left) {
		this.left = left;
	}

	public BufferedImage getRight() {
		return right;
	}

	public void setRight(BufferedImage right) {
		this.right = right;
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	public int getSolidDefoultX() {
		return solidDefoultX;
	}

	public void setSolidDefoultX(int solidDefoultX) {
		this.solidDefoultX = solidDefoultX;
	}

	public int getSolidDefoultY() {
		return solidDefoultY;
	}

	public void setSolidDefoultY(int solidDefoultY) {
		this.solidDefoultY = solidDefoultY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public player.Player.Direction getDirection() {
		return direction;
	}

	public void setDirection(player.Player.Direction direction) {
		this.direction = direction;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}

	public int getSpriteNum() {
		return spriteNum;
	}

	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}


	public int getScreenX() {
		return screenX;
	}

	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}

	

}
