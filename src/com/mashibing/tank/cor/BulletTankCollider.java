package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Explode;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.ResourceMgr;
import com.mashibing.tank.Tank;

public class BulletTankCollider implements Collider {

	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			
			Bullet b = (Bullet) o1;
			Tank t = (Tank) o2;
			
			if(b.getGroup() == t.getGroup()) return true;
			
			if(b.getRect().intersects(t.rect)) {
				
				t.die();
				b.die();
				
				int explodeX = t.getX() + ResourceMgr.badTankU.getWidth()/2 - ResourceMgr.explode[0].getWidth()/2;
				int explodeY = t.getY() + ResourceMgr.badTankU.getHeight()/2 - ResourceMgr.explode[0].getHeight()/2;
				b.gm.add(new Explode(explodeX, explodeY, b.gm));
				
				return false;
			}
		} else if(o1 instanceof Tank && o2 instanceof Bullet) {
			collide(o2, o1);
		}
		
		return true;
	}

}
