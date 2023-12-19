package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet extends GameObject implements EntityFriend {
	
	BufferedImage image;
	
	public Bullet(double x, double y, Textures tex, Game game, Controller controller) throws IOException {
		super(x,y, game, controller);
		this.height = 24;
		this.width = 24;
		this.image = tex.missile;
		this.game = game;
	}
	
	public void tick() {
		y -= 10;
		
		/*
		if(Physics.Collision(this, )) {
			System.out.println("COLLISION DETECTED");
		}
		*/
	}
	
	public void render(Graphics g) {
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		g.drawImage(image, (int)x, (int)y, null);
	}

}
