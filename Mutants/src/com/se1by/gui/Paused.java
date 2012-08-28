package com.se1by.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.se1by.Game.Game;
import com.se1by.Game.GameState;

public class Paused {
	private static float returnScale = 0.9f;
	private static float quitScale = 0.9f;
	
	private static boolean onReturn;
	private static boolean onQuit;
	
	public static void render(GameContainer con, Graphics g){
		Game.font.write("Return", 350, 250, g, returnScale);
		Game.font.write("Quit", 365, 280, g, quitScale);
	}
	
	public static void update(GameContainer con, int delta){
		if(con.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			Game.state = GameState.INGAME;
		}
		int x = con.getInput().getMouseX();
		int y = con.getInput().getMouseY();
		
		//if mouse is over return
		if ((x > 350) && (x < 350 + ("return".length() * 16)) && (y > 250) && (y < 266)) {
			returnScale = 1f;
			onReturn = true;
		} else{
			onReturn = false;
			returnScale = 0.9f;
		}
		//if mouse is over quit
		if ((x > 365) && (x < 365 + ("quit".length() * 16)) && (y > 280) && (y < 296)) {
			quitScale = 1f;
			onQuit = true;
		} else{
			onQuit = false;
			quitScale = 0.9f;
		}	
		
		if(con.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(onReturn){
				Game.state = GameState.INGAME;
			} else if(onQuit){
				con.exit();
			}
		}
	}
}
