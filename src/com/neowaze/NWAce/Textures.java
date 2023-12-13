package com.neowaze.NWAce;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {

	public BufferedImage player, missile, enemy, background;

	public Textures() {
		getTextures();
	}
	
	private void getTextures() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			player = loader.loadImage("/player-new-64px.png");
			background = loader.loadImage("/background-square.jpeg");
			enemy = loader.loadImage("/enemy-new-64px.png");
			missile = loader.loadImage("/bullet-1-24px.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
