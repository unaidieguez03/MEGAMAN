package player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Collision;

public class Entity {
	public enum Direction {
		UP, DOWN, LEFT, RIGHT, STOP
	};
	static final double grabity = 0.8;
	public boolean inAir= true, onLand;
	public double airSpeed = 0, fallSpeed = 0,jumpSpeed = 0, topSpeed = 0;
	BufferedImage lastImg, img, up_left, up_right, down_left, down_right, left_1, left_2, right_1, right_2;
	public int worldX, worldY;
	public double speed;
	
	public Direction direction;
	public Direction lastDirection;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea;
	public Collision collisions;
	public int solidDefoultX, solidDefoultY;

	public double defoultSpeed = 5;
	public boolean jumping=false, falling= false, stop=true;

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
