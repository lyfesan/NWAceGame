package com.neowaze.NWAce;

import java.awt.Rectangle;

public class GameObject extends Physics {

	public double x, y, width, height;
	Game game;
	Controller controller;
	
	public GameObject(double x, double y, Game game, Controller controller) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.controller = controller;
	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x, (int)y, (int)width, (int)height);
	}
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
		
	public double getY() {
		return this.y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
