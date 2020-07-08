package com.mashibing.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.mashibing.tank.cor.ColliderChain;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	
	static {
		INSTANCE.init();
	}
	
	static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	
	private Tank myTank;
	//public List<Tank> enemys = new ArrayList<Tank>();
	//public List<Bullet> bullets = new ArrayList<Bullet>();
	//public List<Fire> fires = new ArrayList<Fire>();
	//public List<Explode> explodes = new ArrayList<Explode>();
	ColliderChain chain = new ColliderChain();
	
	private List<GameObject> objects = new ArrayList<GameObject>();
	
	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	private GameModel() {
		
	}
	
	private void init() {

		myTank = new Tank(375, 500, Dir.UP, Group.GOOD);
		
		int tankCount = Integer.parseInt((String) PropertyMgr.getValue("initTankCount"));
		
		for(int i=0;i<tankCount;i++) {
			new Tank(235 + i*70, 100, Dir.DOWN, Group.BAD);
		}
		
		add(new Wall(150, 150, 150, 50));
		add(new Wall(400, 150, 150, 50));
		add(new Wall(250, 300, 50, 150));
		add(new Wall(400, 300, 50, 150));
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
		
		for(int i=0; i<objects.size(); i++) {
			for(int j=i+1; j<objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				chain.collide(o1, o2);
			}
		}
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

