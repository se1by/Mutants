package com.se1by.util;

public class MathHelper {
	public static float getAtan(float x1, float x2, float y1, float y2){
		
		float tanX;
		float tanY;
		float tan;
		
		if(x1 > x2){
			tanX = x1-x2;
		} else{
			tanX = x2-x1;
		}
		
		if(y1 > y2){
			tanY = y1-y2;
		} else{
			tanY = y2-y1;
		}
		
		if(tanX > tanY){
			tan = tanX/tanY;
		} else{
			tan = tanY/tanX;
		}
		
		return (float) Math.atan(tan);
	}
	
	public static float getAtanAdjacent(float x, float y, float x2, float y2){
		float xLength;
		float yLength;
		if(x > x2){
			xLength = x-x2;
		} else{
			xLength = x2-x;
		}
		
		if(y > y2){
			yLength = y-y2;
		} else{
			yLength = y2-y;
		}
		
		if(xLength > yLength){
			return x;
		} else if(yLength > xLength){
			return y;
		} else{
			return 0f;
		}
	}
	
	public static float getDistance(float x, float x1){
		boolean negativeX = false;
		boolean negativeX1 = false;
		
		if(x < 0){
			negativeX = true;
		}
		if(x1 < 0){
			negativeX1 = true;
		}
		
		if(!negativeX && !negativeX1){
				return x1 - x;
		}if(negativeX && negativeX1){
			if(x >= x1){
				return x1 - x;
			} else{
				return x - x1;
			}
		} else if(negativeX && !negativeX1){
			if(x <= x1){
				return x1 - x;
			} else{
				return x - x1;
			}
		} else{
			if(x >= x1){
				return x1 - x;
			} else{
				return x - x1;
			}
		}
	}
}
