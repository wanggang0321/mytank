package com.mashibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	
	private int x, y;
	private Dir dir;
	private final int SPEED = 10;
	private boolean living = true;
	private Group group;
	private Rectangle rect = null;
	private GameModel gm;
	
	public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;
		
		int width = 0;
		int height = 0;
		
		switch(dir) {
		case UP :
			width = ResourceMgr.bU.getWidth();
			height = ResourceMgr.bU.getHeight();
			break;
		case DOWN :
			width = ResourceMgr.bD.getWidth();
			height = ResourceMgr.bD.getHeight();
			break;
		case LEFT :
			width = ResourceMgr.bL.getWidth();
			height = ResourceMgr.bL.getHeight();
			break;
		case RIGHT :
			width = ResourceMgr.bR.getWidth();
			height = ResourceMgr.bR.getHeight();
			break;
		
		default :
			break;
		}
		
		rect = new Rectangle(width, height);
		
		rect.x = this.x;
		rect.y = this.y;
	}
	
	public void paint(Graphics g) {
		
		if(!living) gm.remove(this);
		
		switch(dir) {
		case UP :
			g.drawImage(ResourceMgr.bU, x, y, null);
			break;
		case DOWN :
			g.drawImage(ResourceMgr.bD, x, y, null);
			break;
		case LEFT :
			g.drawImage(ResourceMgr.bL, x, y, null);
			break;
		case RIGHT :
			g.drawImage(ResourceMgr.bR, x, y, null);
			break;
			
		default :
			break;
		}
		
		move();
	}
	
	private void move() {

		switch(dir) {
		case UP :
			y -= SPEED;
			break;
		case DOWN :
			y += SPEED;
			break;
		case LEFT :
			x -= SPEED;
			break;
		case RIGHT :
			x += SPEED;
			break;
			
		default :
			break;
		}
		
		rect.x = this.x;
		rect.y = this.y;
		
		if(x<0 || y<0 || x>gm.GAME_WIDTH || y>gm.GAME_HEIGHT) living = false;
		
	}
	
	public void collideWith(Tank tank) {
		
		if(this.group == tank.getGroup()) return;
		
		if(rect.intersects(tank.rect)) {
			
			tank.die();
			this.die();
			
			int explodeX = tank.getX() + ResourceMgr.badTankU.getWidth()/2 - ResourceMgr.explode[0].getWidth()/2;
			int explodeY = tank.getY() + ResourceMgr.badTankU.getHeight()/2 - ResourceMgr.explode[0].getHeight()/2;
			gm.add(new Explode(explodeX, explodeY, gm));
		}

	}

	private void die() {
		this.living = false;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
