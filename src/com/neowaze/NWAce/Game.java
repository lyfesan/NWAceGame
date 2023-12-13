package com.neowaze.NWAce;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
	
	private static String OS = System.getProperty("os.name");
	private boolean running = false;
	private Thread thread;
	private static GameFrame gameFrame;
	
	public Game() {
		gameFrame = new GameFrame();
	}

	private synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) {
			return;
		}
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() {
		//gameFrame.init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				gameFrame.tick();
				updates++;
				delta--;
			}
			gameFrame.render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}	
		
	
	public static boolean isWindows() {
		return (OS.toLowerCase().indexOf("win") >= 0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
	
		gameFrame.setVisible(true);
		game.start();
		/*
		java.awt.EventQueue.invokeLater(() -> {
			new GameFrame().setVisible(true);
		});
		*/
	}
}

