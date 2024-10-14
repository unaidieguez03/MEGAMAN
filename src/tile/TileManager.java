package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.Collision;
import main.GamePanel;

public class TileManager {
	public class TileInfo {
		public TileInfo(int tileNum, int x, int y) {
			this.tileNum = tileNum;
			this.x = x * gp.tileSize ;//- gp.tileSize;
			this.y = y * gp.tileSize ;//- gp.tileSize;
		}

	public 	int tileNum;
	public 	int x, y;
	}

	GamePanel gp;
	public List<Tile> tiles;
	public TileInfo mapTileNum[][];

	BufferedImage background;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		mapTileNum = new TileInfo[gp.maxWorldCol][gp.maxWorldRow];
		tiles = new ArrayList<>();
		getTileImages();
		loadMap("res/map/map.txt");
	}

	public void loadMap(String mapPath) {
		try {
			File initialFile = new File(mapPath);
			InputStream is = new FileInputStream(initialFile);

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0, row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					//System.out.println(line);
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = new TileInfo(num, col, row);
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTileImages() {
		try {
			background = ImageIO.read(new FileInputStream("res/tiles/back.png"));
			tiles.add(new Tile());
			tiles.get(0).image = ImageIO.read(new FileInputStream("res/tiles/prob.png"));
			tiles.get(0).collisions = new Collision(false, false, true, true);
			tiles.add(new Tile());
			tiles.get(1).image = ImageIO.read(new FileInputStream("res/tiles/wall.png"));
			tiles.add(new Tile());
			tiles.get(2).image = ImageIO.read(new FileInputStream("res/tiles/prob.png"));
			tiles.get(2).collisions = new Collision(true, true, true, true);
			tiles.add(new Tile());
			tiles.get(3).image = ImageIO.read(new FileInputStream("res/tiles/prob.png"));
			tiles.get(3).collisions = new Collision(true, false, true, true);
			tiles.add(new Tile());
			tiles.get(4).image = ImageIO.read(new FileInputStream("res/tiles/prob.png"));
			tiles.get(4).collisions = new Collision(true, false, false, false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g2) {
		int worldCol = 0, worldRow = 0;
		g2.drawImage(background, 0 - gp.player.worldX / 7,
				-100 - gp.player.worldY / 7, background.getWidth(), background.getHeight(),
				null);
		g2.drawImage(background, 0 - gp.player.worldX / 7 + background.getWidth(),
				-100 - gp.player.worldY / 7, background.getWidth(), background.getHeight(),
				null);

		g2.drawImage(background, 0 - gp.player.worldX / 7 - background.getWidth(),
				-100 - gp.player.worldY / 7, background.getWidth(), background.getHeight(),
				null);
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			if (screenY < (gp.tileSize * gp.maxRow + 1) && screenY > 0 - gp.tileSize
					&& screenX < (gp.tileSize * gp.maxCol + 1) && screenX > 0 - gp.tileSize) {
				if (mapTileNum[worldCol][worldRow].tileNum != 1) {
					g2.drawImage(tiles.get(mapTileNum[worldCol][worldRow].tileNum).image, screenX, screenY,
							gp.tileSize,
							gp.tileSize, null);

					g2.drawRect(screenX, screenY,
							gp.tileSize,
							gp.tileSize);
					g2.setColor(Color.RED);
					g2.drawString("[" + worldCol + "][" + worldRow + "]", screenX,
							screenY + gp.tileSize / 2);
				} else {

					g2.drawRect(screenX, screenY,
							gp.tileSize,
							gp.tileSize);
					g2.setColor(Color.RED);
					g2.drawString("[" + worldCol + "][" + worldRow + "]", screenX,
							screenY + gp.tileSize / 2);
				}
			}
			worldCol++;
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}

		}

	}
}
