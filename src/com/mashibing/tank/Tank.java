package com.mashibing.tank;

import java.awt.Graphics;

public class Tank {
	
	int x, y;
	private Dir dir;
	private final int SPEED = 10;
	
	public Tank(int x, int y, Dir dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void paint(Graphics g) {
		
		g.fillRect(x, y, 50, 50);

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

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
}
