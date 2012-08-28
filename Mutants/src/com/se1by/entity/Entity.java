package com.se1by.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import com.se1by.util.Vector2;

public interface Entity {
	public void render(GameContainer con, Graphics g);
	
	public void update(GameContainer con, int delta);
	
	public Image getImage();
	
	public Vector2 getPosition();
	
	public boolean collidesWith(Entity e);
}
