package com.mashibing.tank;

import java.awt.Graphics;
import java.util.List;

public class Tank {
	
	public static int WIDTH = 50, HEIGHT = 50;
	private int x, y;
	private Dir dir;
	private final int SPEED = 5;
	private boolean moving = false;
	private TankFrame tf;
	private boolean living = true;
	private boolean ismine = true;
	
	public Tank(int x, int y, Dir dir, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		
		if(!living) tf.enemys.remove(this);
		
		switch(dir) {
		case UP :
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case DOWN :
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		case LEFT :
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case RIGHT :
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
			
		default :
			break;
		}
		
		move();
	}
	
	private void move() {

		if(!moving) return;

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
	}
	
	public void fire(List<Bullet> bullets) {
		
		int bulletX = getBulletPositionX();
		int bulletY = getBulletPositionY();
		Bullet b = new Bullet(bulletX, bulletY, dir, tf);
		tf.bullets.add(b);
	}

	private int getBulletPositionY() {
		
		int bulletPositionY = 0;
		switch(dir) {
		case UP :
			bulletPositionY = this.y - ResourceMgr.bU.getHeight();
			break;
		case DOWN :
			bulletPositionY = this.y + ResourceMgr.tankD.getHeight();
			break;
		case LEFT :
			bulletPositionY = this.y + ResourceMgr.tankL.getHeight()/2 - ResourceMgr.bL.getHeight()/2;
			break;
		case RIGHT :
			bulletPositionY = this.y + ResourceMgr.tankR.getHeight()/2 - ResourceMgr.bR.getHeight()/2;
			break;
		
		default :
			break;
		}
		
		return bulletPositionY;
	}

	private int getBulletPositionX() {
		
		int bulletPositionX = 0;
		switch(dir) {
		case UP :
			bulletPositionX = this.x + ResourceMgr.tankU.getWidth()/2 - ResourceMgr.bU.getWidth()/2;
			break;
		case DOWN :
			bulletPositionX = this.x + ResourceMgr.tankD.getWidth()/2 - ResourceMgr.bD.getWidth()/2;
			break;
		case LEFT :
			bulletPositionX = this.x - ResourceMgr.bL.getWidth();
			break;
		case RIGHT :
			bulletPositionX = this.x + ResourceMgr.tankR.getHeight();
			break;
		
		default :
			break;
		}
		
		return bulletPositionX;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void die() {
		this.living = false;
	}
	
}
