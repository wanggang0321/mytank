package com.mashibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.mashibing.tank.abstractfactory.DefaultFactory;

public class TankFrame extends Frame {
	
	private GameModel gm = new GameModel();
	
	DefaultFactory df = new DefaultFactory();
	
	public TankFrame() {
		
		setSize(gm.GAME_WIDTH, gm.GAME_HEIGHT);
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
	
	//Ïû³ýÉÁË¸
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(gm.GAME_WIDTH, gm.GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, gm.GAME_WIDTH, gm.GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		
		gm.paint(g);
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
			case KeyEvent.VK_SPACE :
				gm.getMyTank().fire();
				break;
			default :
				break;
			}
			
			setMainTankDir();
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
			
			setMainTankDir();
		}
		
		public void setMainTankDir() {
			
			Tank myTank = gm.getMyTank();
			
			if(!bL && !bR && !bU && !bD) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if(bL) myTank.setDir(Dir.LEFT);
				if(bR) myTank.setDir(Dir.RIGHT);
				if(bU) myTank.setDir(Dir.UP);
				if(bD) myTank.setDir(Dir.DOWN);
			}
		}
		
	}

}
