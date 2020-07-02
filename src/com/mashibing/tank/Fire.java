package com.mashibing.tank;

import java.awt.Graphics;

public class Fire extends GameObject {
	
	private GameModel gm;
	private int step = 0;
	
	private int x, y;
	
	public Fire(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.fire[step++], x, y, null);
		
		if(step>=ResourceMgr.fire.length)
			gm.remove(this);
	}

}
