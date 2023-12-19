package com.neowaze.NWAce;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityFriend {

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}
