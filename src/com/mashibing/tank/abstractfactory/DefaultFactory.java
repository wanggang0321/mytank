package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Dir;
import com.mashibing.tank.Explode;
import com.mashibing.tank.Group;
import com.mashibing.tank.Tank;
import com.mashibing.tank.TankFrame;

public class DefaultFactory extends GameFactory {

	@Override
	public Tank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Tank(x, y, dir, group, tf);
	}

	@Override
	public Bullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Bullet(x, y, dir, group, tf);
	}

	@Override
	public Explode createExplode(int x, int y, TankFrame tf) {
		return new Explode(x, y, tf);
	}

}
