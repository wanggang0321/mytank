package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Dir;
import com.mashibing.tank.Explode;
import com.mashibing.tank.Group;
import com.mashibing.tank.Tank;

public class DefaultFactory extends GameFactory {

	@Override
	public Tank createTank(int x, int y, Dir dir, Group group) {
		return new Tank(x, y, dir, group);
	}

	@Override
	public Bullet createBullet(int x, int y, Dir dir, Group group) {
		return new Bullet(x, y, dir, group);
	}

	@Override
	public Explode createExplode(int x, int y) {
		return new Explode(x, y);
	}

}
