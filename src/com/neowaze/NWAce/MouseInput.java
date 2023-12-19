package com.neowaze.NWAce;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	Game game;
	
	public MouseInput(Game game) {
		this.game = game;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		public Rectangle playButton = new Rectangle(GameFrame.GAME_WINDOW_SIZE.width/2-50, GameFrame.GAME_WINDOW_SIZE.height/4+50, 100, 50);
		public Rectangle quitButton = new Rectangle(GameFrame.GAME_WINDOW_SIZE.width/2-50, GameFrame.GAME_WINDOW_SIZE.height/4+130, 100, 50);
		 */
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		if(mx >= GameFrame.GAME_WINDOW_SIZE.width/2-50 && mx <= GameFrame.GAME_WINDOW_SIZE.width/2+50) {
			if(my>=GameFrame.GAME_WINDOW_SIZE.height/4+50 && my<=GameFrame.GAME_WINDOW_SIZE.height/4+100) {
				Game.State = Game.STATE.GAME;
			}
		}
		
		//Quit Button
		if(mx >= GameFrame.GAME_WINDOW_SIZE.width/2-50 && mx <= GameFrame.GAME_WINDOW_SIZE.width/2+50) {
			if(my>=GameFrame.GAME_WINDOW_SIZE.height/4+130 && my<=GameFrame.GAME_WINDOW_SIZE.height/4+180) {
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
