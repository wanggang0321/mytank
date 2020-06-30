package com.mashibing.tank;

import java.awt.Graphics;

public class Explode {
	
	private int x, y;
	private TankFrame tf;
	
	private int step = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explode[step++], x, y, null);
		
		if(step>=ResourceMgr.explode.length)
			tf.explodes.remove(this);
			
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}

}
