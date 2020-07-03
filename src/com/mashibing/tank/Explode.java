package com.mashibing.tank;

import java.awt.Graphics;

public class Explode extends GameObject {
	
	private int x, y;
	private int step = 0;
	
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

}
