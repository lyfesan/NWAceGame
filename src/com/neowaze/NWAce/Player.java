package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Player {
	
	private double x;
	private double y;
	private double velX;
	private double velY;
	private boolean isShooting; 
	private String name;
	private BufferedImage playerImage;
	
	public Player(double x, double y, String name, Textures tex) {
		this.setX(x);
		this.setY(y);
		this.name = name;
		this.playerImage = tex.player;
	}
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(x<=0) {
			x=0;
		}
		if(x>GameFrame.GAME_WINDOW_SIZE.getWidth() - playerImage.getWidth()/2) {
			x = GameFrame.GAME_WINDOW_SIZE.width - playerImage.getWidth()/2;
			System.out.println(x);
		}
		if(y<=0) {
			y=0;
		}
		if(y>GameFrame.GAME_WINDOW_SIZE.getHeight() - playerImage.getHeight()) {
			y = GameFrame.GAME_WINDOW_SIZE.height - playerImage.getHeight();
			System.out.println(x);
		}
	}
	
	public void render(Graphics g) {
		
		//Fix problem for non application lagging when using BufferStrategy for non Windows OS
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		g.drawImage(playerImage, (int)this.getX(), (int)this.getY(), null);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}
	
	public BufferedImage getPlayerImage() {
		return this.playerImage;
	}
	
	public boolean getIsShooting() {
		return this.isShooting;
	}

	public void setY(double y) {
		this.y = y;
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
	
}
