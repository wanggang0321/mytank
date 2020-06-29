package com.mashibing.tank;

import java.awt.Graphics;

public class Bullet {
	
	private int x, y;
	private Dir dir;
	private final int SPEED = 10;
	private TankFrame tf = null;
	private boolean living = true;
	
	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		
		if(!living) tf.bullets.remove(this);
		
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
		
		if(x<0 || y<0 || x>tf.GAME_WIDTH || y>tf.GAME_HEIGHT) living = false;
		
	}

	public boolean isLiving() {
		return living;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}
	
}
