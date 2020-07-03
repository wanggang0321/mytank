package com.mashibing.tank.cor;

import java.util.ArrayList;
import java.util.List;

import com.mashibing.tank.GameObject;

public class ColliderChain implements Collider {
	
	List<Collider> colliders = new ArrayList<Collider>();
	
	public ColliderChain() {
		
		BulletTankCollider collider = new BulletTankCollider();
		TankTankCollider collider2 = new TankTankCollider();
		BulletWallCollider collider3 = new BulletWallCollider();

		add(collider);
		add(collider2);
		add(collider3);
	}
	
	private void add(Collider collider) {
		colliders.add(collider);
	}
	
	@Override
	public boolean collide(GameObject o1, GameObject o2) {

		for(int i=0; i<colliders.size(); i++) {
			colliders.get(i).collide(o1, o2);
		}
		
		return true;
	}

}
