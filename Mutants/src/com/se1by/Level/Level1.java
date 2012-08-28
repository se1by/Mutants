package com.se1by.Level;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level1{
	private static ArrayList<String> entities = new ArrayList<>();
	private static TiledMap map;
	
	public static void init(){
		try {
			setMap(new TiledMap("res/tilesets/level1.tmx"));
		} catch (SlickException e) {
			System.out.println("Unable to load map \"level1.tmx\"");
			e.printStackTrace();
		}
		entities.add("Crawler");
	}
	
	public static ArrayList<String> getEntities() {
		return entities;
	}
	public static void setEntities(ArrayList<String> entities) {
		Level1.entities = entities;
	}
	public static TiledMap getMap() {
		return map;
	}
	public static void setMap(TiledMap map) {
		Level1.map = map;
	}
	
}
