package com.se1by.unused;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.se1by.Game.Game;
import com.se1by.entity.Direction;
import com.se1by.entity.Entity;
import com.se1by.entity.LivingEntity;
import com.se1by.util.Vector2;

public class Projectile implements LivingEntity{
	private float alpha;
	private Vector2 targetPosition;
	private float speed;
	private Image image;
	private Vector2 position;
	private Direction direction;
	public Projectile(){
		setSpeed(10f);
		setImage(Game.spritesheet.getSubImage(1, 5));
		setPosition(new Vector2(Game.player.getPosition(), 32));
	}
	@Override
	public void render(GameContainer con, Graphics g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(GameContainer con, int delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	@Override
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	@Override
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	@Override
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getAlpha() {
		return alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public Vector2 getTargetPosition() {
		return targetPosition;
	}
	public void setTargetPosition(Vector2 targetPosition) {
		this.targetPosition = targetPosition;
	}
	@Override
	public boolean collidesWith(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
