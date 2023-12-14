package com.neowaze.NWAce;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
	
	private static boolean running = false;
	private Thread thread, frameThread;
	private static GameFrame gameFrame;
	
	static String OS = System.getProperty("os.name");
	
	
	public Game() {
		gameFrame = new GameFrame();
	}

	private synchronized void start() {
		if(running) return;
		running = true;
		frameThread = new Thread(gameFrame);
		thread = new Thread(this);
		frameThread.start();
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) return;
		
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
		//gameFrame.run();
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
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			gameFrame.run();
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
		//Game game = new Game();
	
		//gameFrame.setVisible(true);
		//game.start();
		
		java.awt.EventQueue.invokeLater(() -> {
			Game game = new Game();
			
			//new GameFrame().setVisible(true);
			gameFrame.setVisible(true);
			game.start();
			
		});
		
	}
}

