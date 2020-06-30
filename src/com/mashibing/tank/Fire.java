package com.mashibing.tank;

import java.awt.Graphics;

public class Fire {
	
	private int x, y;
	private TankFrame tf;
	
	private int step = 0;
	
	public Fire(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.fire[step++], x, y, null);
		
		if(step>=ResourceMgr.fire.length)
			tf.fires.remove(this);
	}

}
