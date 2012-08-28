package com.se1by.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.se1by.Game.Game;
import com.se1by.Game.GameState;

public class Menu {
	private static float startScale = 0.9f;
	private static float settingsScale = 0.9f;
	private static float scoreScale = 0.9f;
	private static float quitScale = 0.9f;
	private static boolean mood = true;

	public static void render(GameContainer con, Graphics g) {
		try {
			g.drawImage(new Image("res/images/logo.png").getScaledCopy(0.8f),
					70, 10);
		} catch (SlickException e) {
			System.out.println("Unable to show logo!");
			e.printStackTrace();
		}
		Game.font.write("Start game", 300, 200, g, startScale);
		Game.font.write("settings(Not now)", 270, 230, g, settingsScale);
		Game.font.write("HighScore", 310, 260, g, scoreScale);
		Game.font.write("Quit", 330, 290, g, quitScale);
		if(mood){
			g.drawImage(Game.spritesheet.getSubImage(0, 0), 500, 330);
		} else{
			g.drawImage(Game.spritesheet.getSubImage(0, 2), 500, 330);
		}
	}

	public static void update(GameContainer con, int delta) {
		Input input = con.getInput();
		int x = input.getMouseX();
		int y = input.getMouseY();
		boolean onStart;
		boolean onSettings;
		boolean onScore;
		boolean onQuit;
		
		
		//if mouse is over start game
		if ((x > 300) && (x < 300 + ("start game".length() * 16)) && (y > 200) && (y < 216)) {
			startScale = 1f;
			onStart = true;
		} else{
			onStart = false;
			startScale = 0.9f;
		}
		
		//if mouse is over settings
		if ((x > 270) && (x < 270 + ("settings(not now)".length() * 16)) && (y > 230) && (y < 246)) {
			settingsScale = 1f;
			onSettings = true;
		} else{
			onSettings = false;
			settingsScale = 0.9f;
		}
		
		//if mouse is over highscore
		if ((x > 310) && (x < 310 + ("highscore".length() * 16)) && (y > 260) && (y < 276)) {
			scoreScale = 1f;
			onScore = true;
		} else{
			onScore = false;
			scoreScale = 0.9f;
		}
		
		//if mouse is over quit
		if ((x > 330) && (x < 330 + ("quit".length() * 16)) && (y > 290) && (y < 306)) {
			mood = false;
			quitScale = 1f;
			onQuit = true;
		} else{
			mood = true;
			quitScale = 0.9f;
			onQuit = false;
		}
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(onStart){
				Game.state = GameState.INGAME;
			} else if(onSettings){
				Game.state = GameState.SETTINGS;
			} else if(onScore){
				Game.state = GameState.HIGHSCORE;
			} else if(onQuit){
				con.exit();
			}
		}
		
		
	}

}
