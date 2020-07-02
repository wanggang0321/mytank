package com.mashibing.tank;

import java.awt.Graphics;

public class Fire {
	
	private int x, y;
	private GameModel gm;
	
	private int step = 0;
	
	public Fire(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.fire[step++], x, y, null);
		
		if(step>=ResourceMgr.fire.length)
			gm.fires.remove(this);
	}

}
