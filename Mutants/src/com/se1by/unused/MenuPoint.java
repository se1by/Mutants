package com.se1by.unused;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

import com.se1by.util.ResourceManager;

public class MenuPoint {
	Font georgia;
	
	public MenuPoint(String text, int x, int y){
		this(text, x, y, Color.black);
	}
	
	public MenuPoint(String text, int x, int y, Color color){
		georgia = ResourceManager.getInstance().getFont();
		georgia.drawString(x, y, text, color);
	}
}
