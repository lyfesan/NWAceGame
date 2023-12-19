package com.neowaze.NWAce;

public class Physics {

	public static boolean Collision(EntityFriend entF, EntityEnemy entE) {
			
		if(entF.getBounds().intersects(entE.getBounds())) {
			return true;
		}

		return false;
	}
	
	public static boolean Collision(EntityEnemy entE, EntityFriend entF) {
		
		if(entE.getBounds().intersects(entF.getBounds())) {
			return true;
		}
		
		return false;
	}
}
