package com.mashibing.tank;

import java.awt.Graphics;

public class Explode {
	
	private int x, y;
	private GameModel gm;
	private int step = 0;
	
	public Explode(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explode[step++], x, y, null);
		
		if(step>=ResourceMgr.explode.length)
			gm.explodes.remove(this);
			
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}

}
