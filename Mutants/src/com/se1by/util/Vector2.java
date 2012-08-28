package com.se1by.util;

import org.newdawn.slick.geom.Vector2f;

public class Vector2 extends Vector2f {
	public Vector2(int i, int j) {
		super((float) i, (float) j);
	}
	public Vector2(float i, float j){
		super(i, j);
	}
	public Vector2(Vector2 vec, int i){
		super(vec.getX() + i, vec.getY() + i);
	}
	public Vector2(Vector2 vec, float i){
		super(vec.getX() + i, vec.getY() + i);
	}
	public void setX(float x){
		set(x, getY());
	}
	public void setY(float y){
		set(getX(), y);
	}
}
