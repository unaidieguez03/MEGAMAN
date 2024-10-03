package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import player.Player;

public class GamePanel extends JPanel implements Runnable {
	private static final int FPS = 60;
	Thread gameThread;
	long timer = 0;
	int drowCount = 0;
	public final int originalSize = 16;
	final int scale = 3;

	public final int tileSize = scale * originalSize;
	public final int maxCol = 16;
	public final int maxRow = 12;
	public final int screenWith = tileSize * maxCol;
	public final int screenHeight = tileSize * maxRow;

	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	public final int worldWith = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
KeyHandler keyH = new KeyHandler();

	public Player player ;

	public GamePanel() {
		try {
			player = new Player(this, keyH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(screenWith, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drowInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drowInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if (delta >= 1) {
				repaint();
				update();
				delta--;
				drowCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drowCount);
				timer = 0;
				drowCount = 0;
			}
		}
	}

	private void update() {
player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		g2.dispose();

	}

}
