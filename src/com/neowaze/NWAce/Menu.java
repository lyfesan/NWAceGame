package com.neowaze.NWAce;

//import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JEditorPane;
import javax.swing.JRadioButton;

public class Menu {

	
	public Rectangle playButton = new Rectangle(GameFrame.GAME_WINDOW_SIZE.width/2-50, GameFrame.GAME_WINDOW_SIZE.height/4+50, 100, 50);
	public Rectangle quitButton = new Rectangle(GameFrame.GAME_WINDOW_SIZE.width/2-50, GameFrame.GAME_WINDOW_SIZE.height/4+130, 100, 50);
	//public Rectangle quitButton = new Rectangle(GameFrame.GAME_WINDOW_SIZE.width/2-50, GameFrame.GAME_WINDOW_SIZE.height/4+210, 100, 50);
	/**
	 * Create the panel.
	 */
	public Menu() {
		

	}

	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font titleFont = new Font("Arial", Font.BOLD, 50);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("NWAce Game", GameFrame.GAME_WINDOW_SIZE.width/4, GameFrame.GAME_WINDOW_SIZE.height/4);
	
		Font menuFont = new Font("Arial", Font.BOLD, 30);
		g.setFont(menuFont);
		g2d.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
		//g2d.fillRect(helpButton.x, helpButton.y, helpButton.width, helpButton.height);
		g2d.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
		g.setColor(Color.BLACK);
		g.drawString("Play", playButton.x + playButton.width/5, playButton.y + playButton.height/2+10);
		//g.drawString("Help", helpButton.x + helpButton.width/5, helpButton.y + helpButton.height/2+10);
		g.drawString("Quit", quitButton.x + quitButton.width/5, quitButton.y + quitButton.height/2+10);
		//g2d.setBackground(Color.WHITE);
		g.setColor(Color.WHITE);
		
		g2d.draw(playButton);
		//g2d.draw(helpButton);
		g2d.draw(quitButton);
	}
	
}
