package com.se1by.Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import com.se1by.entity.Player;
import com.se1by.gui.GameOver;
import com.se1by.gui.InGame;
import com.se1by.gui.Menu;
import com.se1by.gui.Paused;
import com.se1by.gui.Font;

public class Game extends BasicGame {
	public static Camera camera;
	public static Font font;
	public static SpriteSheet spritesheet;
	public static GameState state;
	public static Player player;

	public Game() {
		super("Mutants");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer con, Graphics g) throws SlickException {
		if(state.equals(GameState.MENU)){
			Menu.render(con, g);
		} else if(state.equals(GameState.INGAME)){
			InGame.render(con, g);
		} else if(state.equals(GameState.PAUSED)){
			Paused.render(con, g);
		} else if(state.equals(GameState.GAMEOVER)){
			GameOver.render(con, g);
		}
	}

	@Override
	public void init(GameContainer con) throws SlickException {		
		state = GameState.MENU;
		font = new Font();
		spritesheet = new SpriteSheet("res/images/spritesheet.png", 64, 64);
		camera = new Camera(con, new TiledMap("res/tilesets/level1.tmx"));
		
		player = new Player();
	}

	@Override
	public void update(GameContainer con, int delta) throws SlickException {
		if(state.equals(GameState.MENU)){
			Menu.update(con, delta);
		} else if(state.equals(GameState.INGAME)){
			InGame.update(con, delta);
		} else if(state.equals(GameState.PAUSED)){
			Paused.update(con, delta);
		} else if(state.equals(GameState.GAMEOVER)){
			GameOver.update(con, delta);
		}

	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Game());
		container.setDisplayMode(800, 600, false);
		container.setMinimumLogicUpdateInterval(25);
		container.setIcon("res/images/icon32.png");
		container.setVSync(true);
		container.setShowFPS(false);
		container.start();
	}

}
