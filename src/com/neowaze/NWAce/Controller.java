package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	private LinkedList<EntityFriend> entityListFriend = new LinkedList<EntityFriend>();
	private LinkedList<EntityEnemy> entityListEnemy = new LinkedList<EntityEnemy>();
	//private LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	
	Random rand = new Random();
	Textures tex;
	EntityEnemy entityEnemy;
	EntityFriend entityFriend;
	Game game;
	
	public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
		/*
		for(int x = 0; x < GameFrame.GAME_WINDOW_SIZE.width; x += 64) {
			addEntity(new Enemy(rand.nextInt(GameFrame.GAME_WINDOW_SIZE.width), 0, tex));
		}
		*/
	}
	
	public void createEnemy(int enemyCount) {
		for(int i=0;i<enemyCount;i++) {
			this.addEntity(new Enemy(rand.nextInt(15,GameFrame.GAME_WINDOW_SIZE.width-15), 
								0, tex, game, this));
		}
	}
	
	public void tick() {
		//Move the bullet
		for(int i=0;i<entityListFriend.size();i++) {
			entityFriend = entityListFriend.get(i);
			  
			if(entityFriend.getY() < 0 && entityFriend.getClass()==Bullet.class) {
				//System.out.println("Bullet removed");
				removeEntity(entityFriend);
			}
			
			entityFriend.tick();
		}
		for(int i=0;i<entityListEnemy.size();i++) {
			entityEnemy = entityListEnemy.get(i);
			  
			if(entityEnemy.getY() < 0 && entityEnemy.getClass()==Bullet.class) {
				//System.out.println("Bullet removed");
				removeEntity(entityEnemy);
			}
			
			entityEnemy.tick();
		}
	}
	
	public void render(Graphics g) {
		
		//Rendering all entity
		for(int i=0;i<entityListFriend.size();i++) {
			entityFriend = entityListFriend.get(i);
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			entityFriend.render(g);
		}
		
		for(int i=0;i<entityListEnemy.size();i++) {
			entityEnemy = entityListEnemy.get(i);
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			entityEnemy.render(g);
		}
		
	}
		
	public void addEntity(EntityEnemy block) {
		entityListEnemy.add(block);
	}
	
	public void removeEntity(EntityEnemy block) {
		entityListEnemy.remove(block);
	}
	
	public void addEntity(EntityFriend block) {
		entityListFriend.add(block);
	}
	
	public void removeEntity(EntityFriend block) {
		entityListFriend.remove(block);
	}
	
	public LinkedList<EntityFriend> getEntityFriendList(){
		return entityListFriend;
	}
	
	public LinkedList<EntityEnemy> getEntityEnemyList(){
		return entityListEnemy;
	}
}
