package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.LinkedList;

public class Controller {

	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
	private LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	
	Enemy tmpEnemy;
	Bullet tmpBullet;
	
	public Controller(Textures tex) {
		for(int x = 0; x < GameFrame.GAME_WINDOW_SIZE.width; x += 64) {
			addEnemy(new Enemy(x, 0, tex));
		}
	}
	
	public void tick() {
		//Move the bullet
		for(int i=0;i<bulletList.size();i++) {
			tmpBullet = bulletList.get(i);
			
			if(tmpBullet.getY() < 0) {
				removeBullet(tmpBullet);
			}
			
			tmpBullet.tick();
		}
		//Move the enemy
		for(int i=0;i<enemyList.size();i++) {
			tmpEnemy = enemyList.get(i);
			tmpEnemy.tick();
		}
	}
	
	public void render(Graphics g) {
		
		//Rendering all bullets
		for(int i=0;i<bulletList.size();i++) {
			tmpBullet = bulletList.get(i);
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			tmpBullet.render(g);
		}
		
		//Rendering all enemy
		for(int i=0;i<enemyList.size();i++) {
			tmpEnemy = enemyList.get(i);
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			tmpEnemy.render(g);
		}
	}
	
	public void addBullet(Bullet block) {
		bulletList.add(block);
	}
	
	public void removeBullet(Bullet block) {
		bulletList.remove(block);
		block = null;
	}
	
	public void addEnemy(Enemy enemy) {
		enemyList.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		enemyList.remove(enemy);
	}
}
