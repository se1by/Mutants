package com.se1by.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.se1by.Game.Game;
import com.se1by.Game.GameState;
import com.se1by.gui.InGame;
import com.se1by.util.Vector2;

public class Player implements Creature{
	private int killstreak;
	private Animation animationUP;
	private Animation animationDOWN;
	private Animation animationLEFT;
	private Animation animationRIGHT;
	private Direction direction;
	private float health;
	private int score;
	private float speed;
	private float visibility;
	private boolean walking;
	private Vector2 position;

	boolean debug = false;
	
	public Player(){
		setAnimationUP(new Animation(Game.spritesheet, 3, 0, 5, 0, true, 100, true));
		setAnimationDOWN(new Animation(Game.spritesheet, 0, 0, 2, 0, true, 100, true));
		setAnimationRIGHT(new Animation(Game.spritesheet, 0, 1, 2, 1, true, 100, true));
		setAnimationLEFT(new Animation(Game.spritesheet, 3, 1, 5, 1, true, 100, true));
		setDirection(Direction.RIGHT);
		setHealth(100);
		setScore(0);
		setSpeed(1.5f);
		setVisibility(20*64f);
		setPosition(new Vector2(1200, 3252));
	}

	@Override
	public Animation getAnimationUP() {
		return animationUP;
	}

	public void setAnimationUP(Animation animationUP) {
		this.animationUP = animationUP;
	}

	@Override
	public Animation getAnimationDOWN() {
		return animationDOWN;
	}

	public void setAnimationDOWN(Animation animationDOWN) {
		this.animationDOWN = animationDOWN;
	}

	@Override
	public Animation getAnimationLEFT() {
		return animationLEFT;
	}

	public void setAnimationLEFT(Animation animationLEFT) {
		this.animationLEFT = animationLEFT;
	}

	@Override
	public Animation getAnimationRIGHT() {
		return animationRIGHT;
	}

	public void setAnimationRIGHT(Animation animationRIGHT) {
		this.animationRIGHT = animationRIGHT;
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
	public void render(GameContainer con, Graphics g) {
		if(!walking){
			switch (getDirection()){
			case UP:
				g.drawImage(getAnimationUP().getImage(0), getPosition().getX(), getPosition().getY());
				break;
			case DOWN:
				g.drawImage(getAnimationDOWN().getImage(0), getPosition().getX(), getPosition().getY());
				break;
			case LEFT:
				g.drawImage(getAnimationLEFT().getImage(0), getPosition().getX(), getPosition().getY());
				break;
			case RIGHT:
				g.drawImage(getAnimationRIGHT().getImage(0), getPosition().getX(), getPosition().getY());
				break;
			default:
				System.out.println("WTF NO DIRECTION?!");
				break;
			}
		} else{
			switch (getDirection()){
			case UP:
				g.drawAnimation(animationUP, getPosition().getX(), getPosition().getY());
				break;
			case DOWN:
				g.drawAnimation(animationDOWN, getPosition().getX(), getPosition().getY());
				break;
			case LEFT:
				g.drawAnimation(animationLEFT, getPosition().getX(), getPosition().getY());
				break;
			case RIGHT:
				g.drawAnimation(animationRIGHT, getPosition().getX(), getPosition().getY());
				break;
			default:
				System.out.println("WTF NO DIRECTION?!");
				break;
			}
		}
		String toWrite = "Health: " + health + "    Score: " + score;
		int xSpacing = con.getWidth()/2 - toWrite.length()*16;
		Game.font.write(toWrite, (int)getX() + xSpacing, (int)getY() - 300, g, 1f);
	}

	@Override
	public void update(GameContainer con, int delta) {
		
		if(debug){
			setHealth(1000);
			setSpeed(3f);
		}
		
		if (Game.player.getHealth() <= 0) {
			Game.state = GameState.GAMEOVER;
			return;
		}
		if (Game.player.getKillstreak() >= 10) {
			Game.state = GameState.PAUSED;
		}
		
		Input input = con.getInput();
		float mouseX = getX() - con.getWidth()/2 + input.getMouseX();
		float mouseY = getY() - con.getHeight()/2 + input.getMouseY();

		boolean w = false, s = false, a = false, d = false;

		if (input.isKeyDown(Input.KEY_W)) {
			if (!Game.camera.isBlocked(Game.player.getPosition().getX(),
					Game.player.getPosition().getY() - Game.player.getSpeed()
							* delta * 0.1f)) {
				Vector2 pos = Game.player.getPosition();
				pos.setY(Game.player.getPosition().getY()
						- Game.player.getSpeed() * delta * 0.1f);
				Game.player.setPosition(pos);
			}
			w = true;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			if (!Game.camera.isBlocked(Game.player.getPosition().getX(),
					Game.player.getPosition().getY() + Game.player.getSpeed()
							* delta * 0.1f + 64)) {
				Game.player.getPosition().setY(
						Game.player.getPosition().getY()
								+ (Game.player.getSpeed() * delta * 0.1f));
			}
			s = true;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			if (!Game.camera.isBlocked(Game.player.getPosition().getX()
					- Game.player.getSpeed() * delta * 0.1f, Game.player
					.getPosition().getY())) {
				Game.player.getPosition().setX(
						Game.player.getPosition().getX()
								- (Game.player.getSpeed() * delta * 0.1f));
			}
			a = true;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			if (!Game.camera.isBlocked(Game.player.getPosition().getX()
					+ Game.player.getSpeed() * delta * 0.1f + 64, Game.player
					.getPosition().getY())) {
				Game.player.getPosition().setX(
						Game.player.getPosition().getX()
								+ (Game.player.getSpeed() * delta * 0.1f));
			}
			d = true;
		}
		if(input.isKeyPressed(Input.KEY_0)){
			System.out.println(getX());
			System.out.println("MOUSEX " + mouseX);
			//System.out.println(getX()+(getX() - MathHelper.getDistance(con.getWidth()/2, input.getMouseX())));
		}
		if(input.isKeyPressed(Input.KEY_1)){
			debug = !debug;
		}

		if (w || a || s || d) {
			Game.player.setWalking(true);
		} else {
			Game.player.setWalking(false);
		}

		setDirection(InGame.getTargetDirection(getX(), getY(), mouseX, mouseY));
		//Game.player.setDirection(getTargetDirection(con.getInput(), (int)Game.player.getPosition().getX(), (int)Game.player.getPosition().getY()));		
	}

	@Override
	public Image getImage() {
		return animationUP.getImage(0);
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public float getX(){
		return getPosition().getX();
	}
	
	public void setX(float x){
		getPosition().setX(x);
	}
	
	public float getY(){
		return getPosition().getY();
	}

	public void setY(float y){
		getPosition().setY(y);
	}
	
	@Override
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(int toAdd) {
		score += toAdd;
	}

	@Override
	public float getVisibility() {
		return visibility;
	}

	public void setVisibility(float visibility) {
		this.visibility = visibility;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public int getKillstreak() {
		return killstreak;
	}

	public void setKillstreak(int killstreak) {
		this.killstreak = killstreak;
	}

	@Override
	public boolean collidesWith(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}


}
