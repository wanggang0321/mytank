package com.mashibing.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	
	private Tank tank = new Tank(200, 200, Dir.DOWN);
	
	public TankFrame() {
		
		setSize(800, 600);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addKeyListener(new MyKeyListener());
	}
	
	@Override
	public void paint(Graphics g) {

		tank.paint(g);
		
	}
	
	class MyKeyListener extends KeyAdapter {
		
		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_W :
				bU = true;
				break;
			case KeyEvent.VK_A :
				bL = true;
				break;
			case KeyEvent.VK_S :
				bD = true;
				break;
			case KeyEvent.VK_D :
				bR = true;
				break;
				
			default :
				break;
			}
			
			setMainDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_W :
				bU = false;
				break;
			case KeyEvent.VK_A :
				bL = false;
				break;
			case KeyEvent.VK_S :
				bD = false;
				break;
			case KeyEvent.VK_D :
				bR = false;
				break;
			default :
				break;
			}
			
			setMainDir();
		}
		
		public void setMainDir() {
			if(bL) tank.setDir(Dir.LEFT);
			if(bR) tank.setDir(Dir.RIGHT);
			if(bU) tank.setDir(Dir.UP);
			if(bD) tank.setDir(Dir.DOWN);
		}
		
	}

}
