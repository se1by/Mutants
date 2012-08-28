package com.se1by.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.se1by.Game.Game;
import com.se1by.gui.InGame;
import com.se1by.util.Vector2;

public class Projectile implements LivingEntity {
	private int updates;
	private float speed;
	private Image image;
	private Vector2 position;
	private Sound sound;
	private Direction direction;
	private Vector2 targetPosition;

	public Projectile() {
		setImage(Game.spritesheet.getSubImage(0, 5));
		setPosition(new Vector2(Game.player.getPosition(), 16));
		setSpeed(6f);
		setDirection(Game.player.getDirection());
		try {
			setSound(new Sound("res/sound/shot.wav"));
		} catch (SlickException e1) {
			System.out.println("Unable to load sound \"shot.wav\"");
			e1.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer con, Graphics g) {
			g.drawImage(getImage(), getPosition().getX(), getPosition().getY());
	}

	@Override
	public void update(GameContainer con, int delta) {
		switch (getDirection()) {
		case UP:
			getPosition().setY(getPosition().getY() - getSpeed() * delta * 0.1f);
			break;
		case DOWN:
			getPosition().setY(getPosition().getY() + getSpeed() * delta * 0.1f);
			break;
		case LEFT:
			getPosition().setX(getPosition().getX() - getSpeed() * delta * 0.1f);
			break;
		case RIGHT:
			getPosition().setX(getPosition().getX() + getSpeed() * delta * 0.1f);
			break;
		default:
			System.out.println("UNKOWN DIRECTION!");
			break;
		}
		for(Entity e : InGame.getEntities()){
			if(!(e instanceof Creature)){
				continue;
			}
			if(collidesWith(e)){
				Creature c = (Creature)e;
				c.setHealth(c.getHealth() - 40);
				InGame.removeEntity(e);
			}
		}
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

	public int getUpdates() {
		return updates;
	}

	public void setUpdates(int updates) {
		this.updates = updates;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
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

	@Override
	public boolean collidesWith(Entity e) {
		float x = e.getPosition().getX();
		float y = e.getPosition().getY();
		float pX = getPosition().getX();
		float pY = getPosition().getY();
		
		if((x + 1) == pX || (x -1) == pX){
			return false;
		}
		
		if((x - 3) <= pX && (x + 35) >= pX){
			if((y - 3) <= pY && (y + 35) <= pY){
				return true;
			}
		}
		return false;
	}
	
	public Vector2 getTargetPosition(){
		return targetPosition;
	}

	public void setTargetPosition(Vector2 vector2) {
		targetPosition = vector2;
	}
}
