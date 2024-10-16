package main;

import java.util.List;

import player.*;

public class CollisionChequer {
	GamePanel gp;
	public int lastX;

	public CollisionChequer(GamePanel gp) {
		this.gp = gp;
	}

	// public int chechObject(Entity entity, boolean player) {
	// int index = 999, listPos = 0;
	// for (Object n : gp.oList.objectList) {
	// entity.solidArea.x = entity.worldX + entity.solidArea.x;
	// entity.solidArea.y = entity.worldY + entity.solidArea.y;
	// n.solidArea.x = n.worldX + n.solidArea.x;
	// n.solidArea.y = n.worldY + n.solidArea.y;
	// switch (entity.direction) {
	// case UP:
	// entity.solidArea.y -= entity.speed;
	// if (entity.solidArea.intersects(n.solidArea)) {
	// if (player) {
	// index = listPos;
	// }
	// }
	// break;
	// case DOWN:
	// entity.solidArea.y += entity.speed;
	// if (entity.solidArea.intersects(n.solidArea)) {
	// if (player) {
	// index = listPos;
	// }
	// }
	// break;
	// case LEFT:
	// entity.solidArea.x -= entity.speed;
	// if (entity.solidArea.intersects(n.solidArea)) {
	// if (player) {
	// index = listPos;
	// }
	// }
	// break;
	// case RIGHT:
	// entity.solidArea.x += entity.speed;
	// if (entity.solidArea.intersects(n.solidArea)) {
	// if (player) {
	// index = listPos;
	// }
	// }
	// break;
	// default:
	// break;
	// }
	// n.solidArea.x = n.solidDefoultX;
	// n.solidArea.y = n.solidDefoultY;
	// entity.solidArea.x = entity.solidDefoultX;
	// entity.solidArea.y = entity.solidDefoultY;
	// listPos++;
	//
	// }
	// return index;
	//
	// }

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;
		switch (entity.lastDirection) {
			case LEFT:
				entityLeftCol = (entityLeftWorldX - entity.solidArea.x - (int)entity.speed) / gp.tileSize;
				tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow].tileNum;
				tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow].tileNum;
				if (gp.tileManager.tiles.get(tileNum1).collisions.left == true
						||
						gp.tileManager.tiles.get(tileNum2).collisions.left == true) {
					entity.collisions.left = true;
				} else {
					entity.collisions.left = false;
				}
				break;
			case RIGHT:
				entityRightCol = (entityRightWorldX + entity.solidArea.x + (int)entity.speed) / gp.tileSize;
				tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow].tileNum;
				tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow].tileNum;
				if (gp.tileManager.tiles.get(tileNum1).collisions.right == true
						||
						gp.tileManager.tiles.get(tileNum2).collisions.right == true) {
					entity.collisions.right = true;
				} else {
					entity.collisions.right = false;
				}
				break;
			default:
				break;
		}
		entityBottomRow = (entityBottomWorldY + entity.solidArea.y + (int) entity.fallSpeed) / gp.tileSize;
		tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow].tileNum;
		tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow].tileNum;
//	System.out.println("player x:"+(entity.worldX+( gp.tileSize/2))+"--"+"[" + entityLeftCol + "][" + entityBottomRow + "]"+gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow].x);
//	System.out.println("player x:"+(entity.worldX+( gp.tileSize/2))+"--"+"[" + entityRightCol + "][" + entityBottomRow + "]"+gp.tileManager.mapTileNum[entityRightCol][entityBottomRow].x);
//	System.out.println("player y:"+(entity.worldY+( gp.tileSize/2))+"--"+"[" + entityLeftCol + "][" + entityBottomRow + "]"+gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow].y);
//	System.out.println("player y:"+(entity.worldY)+"--"+"[" + entityRightCol + "][" + entityBottomRow + "]"+gp.tileManager.mapTileNum[entityRightCol][entityBottomRow].y);

		if (gp.tileManager.tiles.get(tileNum1).collisions.up == true && entity.worldY +( gp.tileSize-1)< gp.tileManager.mapTileNum[entityRightCol][entityBottomRow].y
				|| gp.tileManager.tiles.get(tileNum2).collisions.up == true && entity.worldY+( gp.tileSize-1) < gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow].y) {
			entity.collisions.down = true;
			entity.onLand = true;
			lastX =  gp.tileManager.mapTileNum[entityLeftCol][entityTopRow].y;//+gp.tileSize;
			//System.out.println("onLand");
		} else {
			entity.inAir = true;
			System.out.println("air");
			//lastX =  gp.tileManager.mapTileNum[entityLeftCol][entityTopRow].y +gp.tileSize;
			//System.out.println("onAir"+entity.inAir);
			entity.collisions.down = false;
		}
		entityTopRow = (entityTopWorldY - entity.solidArea.y + (int) entity.jumpSpeed) / gp.tileSize;
		tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow].tileNum;
		tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow].tileNum;
		if (gp.tileManager.tiles.get(tileNum1).collisions.down == true
				|| gp.tileManager.tiles.get(tileNum2).collisions.down == true) {
			System.out.println("up");
			entity.collisions.up = true;
		} else {

			entity.collisions.up = false;
		}

	}
}
