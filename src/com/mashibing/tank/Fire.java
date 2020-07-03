package com.mashibing.tank;

import java.awt.Graphics;

public class Fire extends GameObject {
	
	private int step = 0;
	
	private static int WIDTH = ResourceMgr.fire[0].getWidth();
	private static int HEIGHT = ResourceMgr.fire[0].getHeight();
	
	public Fire(int x, int y) {
		this.x = x;
		this.y = y;
		
		GameModel.getInstance().add(this);
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.fire[step++], x, y, null);
		
		if(step>=ResourceMgr.fire.length)
			GameModel.getInstance().remove(this);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
