package com.neowaze.NWAce;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.neowaze.NWAce.Game.STATE;

public class GameFrame extends JFrame implements KeyListener, ActionListener, Runnable {
	
	public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int GAME_MIN_WIDTH = 640;
	public static final int GAME_MIN_HEIGHT = 640;
	public static Dimension GAME_WINDOW_SIZE;
	public final String GAME_TITLE = "NWAce Game";
	
	private Game game;
	//Use for Buffer image
	private BufferedImage imageBackground;
	private Menu mainMenu;
	
	//Texture for all assets in game
	Textures textures; 
	
	Player p;
	Controller c;
	
	
	public LinkedList<EntityFriend> entityFriend;
	public LinkedList<EntityEnemy> entityEnemy;
	
	
	public GameFrame(Game game) {
		
		this.game = game;
		//Setting frame and binding listener
		settingFrame();
		
		//Initiate texture
		this.textures =  new Textures();
		this.imageBackground = textures.background;
		
		//Focusing frame
		requestFocus();
		
		//Initialize player and controller
		c = new Controller(textures, game);
		p = new Player(getWidth()/2-textures.player.getWidth()/2, getHeight()/2, "Player", textures, game, c);
		mainMenu = new Menu();
		
		entityFriend = c.getEntityFriendList();
		entityEnemy = c.getEntityEnemyList();
		
		c.createEnemy(game.getEnemyCount());
		addKeyListener(this);
		addMouseListener(new MouseInput(game));
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
				// TODO Auto-generated catch block
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
		
		if(p.getHealth()<=0) {
			game.setState(STATE.GAMEOVER);
		}
		else if(game.getState() == STATE.GAME) {
		
			p.tick();
			c.tick();
			
			if(game.getEnemyKilled() >= game.getEnemyCount()) {
				game.setEnemyCount(game.getEnemyCount()+1);
				game.setEnemyKilled(0);
				c.createEnemy(game.getEnemyCount());
			}
		}
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
		
		if(game.getState() == STATE.MENU) {
			mainMenu.render(g);
		}
		else if(game.getState()==STATE.GAMEOVER) {
			GameOver.render(g);
		}
		else if(game.getState() == STATE.GAME) {
			//g.drawImage(playerIcon, this.getWidth()/2, this.getHeight()/2, this);
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			p.render(g); 
			if (!Game.isWindows()) Toolkit.getDefaultToolkit().sync();
			c.render(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Health: " + p.getHealth(), 10, 55);
			
		}
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
		
		if(key == KeyEvent.VK_SPACE && !p.getIsShooting()) {
			try {
				p.setIsShooting(true);
				//p.timeElapsedShooting = System.currentTimeMillis();
				c.addEntity(new Bullet(p.getX()+p.getPlayerImage().getWidth()/2-12, 
								p.getY()+p.getPlayerImage().getHeight()/2, textures, game, c));
			} catch (IOException e1) {
				e1.printStackTrace();
			}		
		}
		else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			p.setVelX(5);
		}
		else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			p.setVelX(-5);
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			p.setVelY(5);
		}
		else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			p.setVelY(-5);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			p.setIsShooting(false);		
		}
		else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			p.setVelY(0);
		}
		else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			p.setVelY(0);
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
