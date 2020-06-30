package com.mashibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame {
	
	private Tank myTank = new Tank(375, 500, Dir.UP, Group.GOOD, this);
	public List<Tank> enemys = new ArrayList<Tank>();
	public List<Bullet> bullets = new ArrayList<Bullet>();
	public List<Fire> fires = new ArrayList<Fire>();
	public List<Explode> explodes = new ArrayList<Explode>();
	static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	
	public TankFrame() {
		
		setSize(GAME_WIDTH, GAME_HEIGHT);
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
	
	//消除闪烁
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("敌人的数量：" + enemys.size(), 10, 40);
		g.drawString("子弹的数量：" + bullets.size(), 10, 60);
		g.drawString("开火的数量：" + fires.size(), 10, 80);
		g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
		
		for(int i=0;i<enemys.size();i++) {
			enemys.get(i).paint(g);
		}
		
		for(int i=0;i<fires.size();i++) {
			fires.get(i).paint(g);
		}
		
		for(int i=0;i<explodes.size();i++) {
			explodes.get(i).paint(g);
		}
		
		for(int i=0;i<bullets.size();i++) {
			bullets.get(i).paint(g);
		}
		
		for(int m=0;m<bullets.size();m++) {
			for(int n=0;n<enemys.size();n++)
				bullets.get(m).collideWith(enemys.get(n));
		}
		
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
				myTank.fire();
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
