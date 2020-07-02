package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Dir;
import com.mashibing.tank.Explode;
import com.mashibing.tank.Group;
import com.mashibing.tank.Tank;
import com.mashibing.tank.TankFrame;

public abstract class GameFactory {

	public abstract Tank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
	
	public abstract Bullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
	
	public abstract Explode createExplode(int x, int y, TankFrame tf);
	
}
