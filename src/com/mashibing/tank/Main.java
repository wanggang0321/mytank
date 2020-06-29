package com.mashibing.tank;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		for(int i=0;i<5;i++) {
			Tank t = new Tank(235 + i*70, 150, Dir.DOWN, tf);
			tf.enemys.add(t);
		}
		
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}
	
	
}
