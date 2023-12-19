package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject implements EntityEnemy {
	
	private BufferedImage enemyImage;
	Random rand = new Random();
	
	private int speed = rand.nextInt(3) + 1;
	
	public Enemy(double x, double y, Textures tex, Game game, Controller controller) {
		super(x,y, game, controller);
		this.height = 64;
		this.width = 64;
		this.enemyImage = tex.enemy;
	}
	
	@Override
	public void tick() {
		y += speed;
		
		if(y>GameFrame.GAME_WINDOW_SIZE.height) {
			speed = rand.nextInt(3)+1;
			x = rand.nextInt(GameFrame.GAME_WINDOW_SIZE.width);
			y = 0;
		}
		
		for(int i=0;i<game.gameFrame.entityFriend.size();i++) {
			
			EntityFriend tmpEnt = game.gameFrame.entityFriend.get(i);
			
			if(Physics.Collision(this, tmpEnt)) {
				controller.removeEntity(tmpEnt);
				controller.removeEntity(this);
				game.setEnemyKilled(game.getEnemyKilled() + 1);
			}
		}
		
		
	}
	
	@Override
	public void render(Graphics g) {
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		g.drawImage(enemyImage, (int)x, (int)y, null);
	}

}
