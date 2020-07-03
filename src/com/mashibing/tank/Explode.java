package com.mashibing.tank;

import java.awt.Graphics;

public class Explode extends GameObject {
	
	private int step = 0;
	private static int WIDTH = ResourceMgr.explode[0].getWidth();
	private static int HEIGHT = ResourceMgr.explode[0].getHeight();
	
	public Explode(int x, int y) {
		this.x = x;
		this.y = y;

		new Thread(()->new Audio("audio/explode.wav").play()).start();
		GameModel.getInstance().add(this);
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explode[step++], x, y, null);
		
		if(step>=ResourceMgr.explode.length)
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
