package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Enemy {
	private double x;
	private double y;
	private BufferedImage enemyImage;
	
	public Enemy(double x, double y, Textures tex) {
		this.x = x;
		this.y = y;
		this.enemyImage = tex.enemy;
	}
	
	public void tick() {
		y+=5;
	}
	
	public void render(Graphics g) {
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		g.drawImage(enemyImage, (int)x, (int)y, null);
	}
}
