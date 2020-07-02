package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Explode;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.ResourceMgr;
import com.mashibing.tank.Tank;

public class BulletTankCollider implements Collider {

	public void collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			
			Bullet bo1 = (Bullet) o1;
			Tank to2 = (Tank) o2;
			
			bo1.collideWith(to2);
//			if(bo1.getGroup() == to2.getGroup()) return;
//			
//			if(bo1.getRect().intersects(to2.rect)) {
//				
//				to2.die();
//				bo1.die();
//				
//				int explodeX = to2.getX() + ResourceMgr.badTankU.getWidth()/2 - ResourceMgr.explode[0].getWidth()/2;
//				int explodeY = to2.getY() + ResourceMgr.badTankU.getHeight()/2 - ResourceMgr.explode[0].getHeight()/2;
//				bo1.gm.add(new Explode(explodeX, explodeY, bo1.gm));
//			}
		} else if(o1 instanceof Tank && o2 instanceof Bullet) {
			collide(o2, o1);
		} else {
			return;
		}
	}

}
