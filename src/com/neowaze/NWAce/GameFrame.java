package com.neowaze.NWAce;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame implements KeyListener, ActionListener, Runnable {
	
	public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int GAME_MIN_WIDTH = 640;
	public static final int GAME_MIN_HEIGHT = 640;
	public static Dimension GAME_WINDOW_SIZE;
	public final String GAME_TITLE = "NWAce Game";
	
	//Use for Buffer image
	private BufferedImage imageBackground;
	
	//Texture for all assets in game
	Textures textures; 
	
	Player p;
	Controller c;
	
	public GameFrame() {
		
		//Setting frame and binding listener
		settingFrame();
		
		//Initiate texture
		this.textures =  new Textures();
		this.imageBackground = textures.background;
		
		//Focusing frame
		requestFocus();
		
		//Initialize player and controller
		p = new Player(getWidth()/2-textures.player.getWidth()/2, getHeight()/2, "Player", textures);
		c = new Controller(textures);
		
		addKeyListener(this);
		//setAlwaysOnTop(true);
		//add(new GamePanel());
		
	}
	
	/*
	public void init() {
		
	}
	*/
	
	private void settingFrame() {
		
		setTitle(GAME_TITLE);
		
		setSize(new Dimension((int)Math.max(GAME_MIN_WIDTH, 
											Math.min(SCREEN_WIDTH, SCREEN_HEIGHT)/1.5), 
										(int)Math.max(GAME_MIN_HEIGHT, 
											Math.min(SCREEN_WIDTH, SCREEN_HEIGHT)/1.5)));
		
		setMaximumSize(new Dimension(Math.min(SCREEN_WIDTH, SCREEN_HEIGHT), 
											Math.min(SCREEN_WIDTH, SCREEN_HEIGHT)));
		
		setMinimumSize(new Dimension(GAME_MIN_WIDTH, GAME_MIN_HEIGHT));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, 
						"Do you really want to exit the application?",
						"Confirm Close", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if(choose == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
					System.out.println("close");
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
				}
			}
		});
		
		GAME_WINDOW_SIZE = this.getSize();
		
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	void tick() {
		p.tick();
		c.tick();
	}
	
	void render() {
		
		//Fix problem for non application lagging when using BufferStrategy for non Windows OS
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		
		if(bufferStrategy == null) {
			
			//Implements triple buffering
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		//Graphics g = gameFrame.getGraphics();

		g.drawImage(imageBackground, 0, 0, this.getWidth(), this.getHeight(), this);
		//g.drawImage(playerIcon, this.getWidth()/2, this.getHeight()/2, this);
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		p.render(g); 
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		c.render(g);
		
		g.dispose();
		bufferStrategy.show();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(5);
		}
		if(key == KeyEvent.VK_LEFT) {
			p.setVelX(-5);
		}
		if(key == KeyEvent.VK_DOWN) {
			p.setVelY(5);
		}
		if(key == KeyEvent.VK_UP) {
			p.setVelY(-5);
		}
		if(key == KeyEvent.VK_SPACE && !p.getIsShooting()) {
			try {
				p.setIsShooting(true);
				c.addBullet(new Bullet(p.getX()+p.getPlayerImage().getWidth()/2-12, 
								p.getY()+p.getPlayerImage().getHeight()/2, textures));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		}
		if(key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		}
		if(key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		}
		if(key == KeyEvent.VK_UP) {
			p.setVelY(0);
		}
		if(key == KeyEvent.VK_SPACE) {
				p.setIsShooting(false);		
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void run() {
		//this.tick();
		if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
		this.render();
	}
	
}
