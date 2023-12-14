package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {

	private double x;
	private double y;
	
	BufferedImage image;
	
	public Bullet(double x, double y, Textures tex) throws IOException {
		this.x = x;
		this.y = y;
		this.image = tex.missile;
	}
	
	public void tick() {
		y -= 10;
	}
	
	public void render(Graphics g) {
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
}
