package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityFriend {
	
	private double velX;
	private double velY;
	private boolean isShooting; 
	private String name;
	private BufferedImage playerImage;
	private int enemyKilled;
	private int health;
	
	//long timeElapsedShooting;
	
	public Player(double x, double y, String name, Textures tex, Game game, Controller controller) {
		super(x,y, game, controller);
		this.height = 64;
		this.width = 64;
		this.name = name;
		this.playerImage = tex.player;
		this.setHealth(100);
		//this.timeElapsedShooting = System.currentTimeMillis();
	}
	
	//@Override
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<0) {
			x=0;
		}
		if(x>GameFrame.GAME_WINDOW_SIZE.getWidth() - playerImage.getWidth()/1.5) {
			x = GameFrame.GAME_WINDOW_SIZE.width - playerImage.getWidth()/1.5;
			System.out.println(x);
		}
		if(y<0) {
			y=0;
		}
		if(y>GameFrame.GAME_WINDOW_SIZE.getHeight() - playerImage.getHeight()) {
			y = GameFrame.GAME_WINDOW_SIZE.height - playerImage.getHeight();
			System.out.println(x);
		}
		
		for(int i=0;i<game.gameFrame.entityEnemy.size();i++) {
			EntityEnemy tmpEnt = game.gameFrame.entityEnemy.get(i);
			
			if(Physics.Collision(this, tmpEnt)){
				this.controller.removeEntity(tmpEnt);
				this.health-=10;
				this.game.setEnemyKilled(this.game.getEnemyKilled()+1);
			}
		}
		
	}
	
	//@Override
	public void render(Graphics g) {
		
		//Fix problem for non application lagging when using BufferStrategy for non Windows OS
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		g.drawImage(playerImage, (int)this.getX(), (int)this.getY(), null);
	}
	
	
	
	public BufferedImage getPlayerImage() {
		return this.playerImage;
	}
	
	public boolean getIsShooting() {
		return this.isShooting;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void setIsShooting(boolean y) {
		this.isShooting = y;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
