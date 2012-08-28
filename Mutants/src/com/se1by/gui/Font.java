package com.se1by.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Font {
    public static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ   " + "0123456789-.!?/%$\\=*+,;:()&#\"'";
    public Image spriteSheet;
    
    public Font(){
    	try {
			spriteSheet = new Image("res/fonts/gamefont.png");
		} catch (SlickException e) {
			System.out.println("Unable to load font \"gamefont\"");
			e.printStackTrace();
		}
    }
    public void write(String text, int drawX, int drawY, Graphics g, float scale){
    	text = text.toUpperCase();
    	for(int x = 0; x < text.length(); x++){
    		int i = letters.indexOf(text.charAt(x));
    		int X = i*16;
    		int y = 0;
    		if(i > 29){
    			X = (i-29)*16;
    			y = 16;
    		}
    		Image letter = spriteSheet.getSubImage(X, y, 16, 16).getScaledCopy(scale);
    		g.drawImage(letter, drawX, drawY);
    		drawX = drawX+15;
    	}
    }
    
    public int getTextLenght(String text){
    	return text.length()*16;
    }
}
