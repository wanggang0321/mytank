package com.mashibing.tank;

import java.awt.Graphics;

public class Fire extends GameObject {
	
	private int step = 0;
	
	private int x, y;
	
	public Fire(int x, int y) {
		this.x = x;
		this.y = y;
		
		GameModel.newInstance().add(this);
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.fire[step++], x, y, null);
		
		if(step>=ResourceMgr.fire.length)
			GameModel.newInstance().remove(this);
	}

}
