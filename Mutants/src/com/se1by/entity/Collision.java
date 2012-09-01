package com.se1by.entity;

import com.se1by.util.Vector2;

public class Collision {
	public static boolean collides(Entity source, Entity target){
		//float sourceX = source.getPosition().getX();
		//float sourceY = source.getPosition().getY();
		
		int sourceWidth = source.getImage().getWidth();
		int sourceHeight = source.getImage().getHeight();
		
		//float targetX = target.getPosition().getX();
		//float targetY = target.getPosition().getY();
		
		int targetWidth = target.getImage().getWidth();
		int targetHeight = target.getImage().getHeight();
		
		
		/*if((sourceX + sourceWidth >= targetX) && (sourceX <= targetX + targetWidth)){
			if((sourceY + sourceHeight >= targetY) && (sourceY <= targetY + targetHeight)){
				return true;
			}
		}*/
		
		if(collides(source.getPosition(), sourceWidth, sourceHeight, target.getPosition(), targetWidth, targetHeight)){
			return true;
		}
		
		return false;
	}
	
	public static boolean collides(Vector2 source, int sourceWidth, int sourceHeight, Vector2 target, int targetWidth, int targetHeight){
		float sourceX = source.getX();
		float sourceY = source.getY();
		
		float targetX = target.getX();
		float targetY = target.getY();
		
		if((sourceX + sourceWidth >= targetX) && (sourceX <= targetX + targetWidth)){
			if((sourceY + sourceHeight >= targetY) && (sourceY <= targetY + targetHeight)){
				return true;
			}
		}
		
		return false;
	}
}
