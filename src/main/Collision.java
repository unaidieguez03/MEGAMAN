package main;

public class Collision {
	public boolean up, down, left, right;

	public Collision() {
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;

	}

	public Collision(boolean up, boolean down, boolean left, boolean right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "-up: " + up + " -down: " + down + " -left: " + left + " -right: " + right;
	}
}
