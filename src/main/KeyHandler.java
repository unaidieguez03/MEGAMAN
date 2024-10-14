package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

	public boolean spacePressed, upPressed, downPressed, leftPressed, rightPressed;

	public KeyHandler() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				spacePressed = true;
				break;
			case KeyEvent.VK_W:
				upPressed = true;
				break;
			case KeyEvent.VK_A:
				leftPressed = true;
				break;
			case KeyEvent.VK_S:
				downPressed = true;
				break;
			case KeyEvent.VK_D:
				rightPressed = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				spacePressed = false;
				break;
			case KeyEvent.VK_W:
				upPressed = false;
				break;
			case KeyEvent.VK_A:
				leftPressed = false;
				break;
			case KeyEvent.VK_S:
				downPressed = false;
				break;
			case KeyEvent.VK_D:
				rightPressed = false;
				break;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public boolean isSpacePressed() {
		return spacePressed;
	}

	public void setSpacePressed(boolean spacePressed) {
		this.spacePressed = spacePressed;
	}
}
