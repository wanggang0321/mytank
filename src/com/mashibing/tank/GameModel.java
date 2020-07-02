package com.mashibing.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	
	static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	
	private Tank myTank = new Tank(375, 500, Dir.UP, Group.GOOD, this);
	//public List<Tank> enemys = new ArrayList<Tank>();
	//public List<Bullet> bullets = new ArrayList<Bullet>();
	//public List<Fire> fires = new ArrayList<Fire>();
	//public List<Explode> explodes = new ArrayList<Explode>();
	
	private List<GameObject> objects = new ArrayList<GameObject>();
	
	public GameModel() {
		
		int tankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));
		
		for(int i=0;i<tankCount;i++) {
			Tank t = new Tank(235 + i*70, 150, Dir.DOWN, Group.BAD, this);
			objects.add(t);
		}
		
	}
	
	public void paint(Graphics g) {

		Color c = g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("敌人的数量：" + enemys.size(), 10, 40);
//		g.drawString("子弹的数量：" + bullets.size(), 10, 60);
//		g.drawString("开火的数量：" + fires.size(), 10, 80);
//		g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
		
		for(int i=0;i<objects.size();i++) {
			objects.get(i).paint(g);
		}
		
//		for(int m=0;m<bullets.size();m++) {
//			for(int n=0;n<enemys.size();n++)
//				bullets.get(m).collideWith(enemys.get(n));
//		}
	}
	
	public void add(GameObject go) {
		objects.add(go);
	}
	
	public void remove(GameObject go) {
		objects.remove(go);
	}
	
	public Tank getMyTank() {
		return myTank;
	}
	
}
