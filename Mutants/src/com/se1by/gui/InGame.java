package com.se1by.gui;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.se1by.entity.Collision;
import com.se1by.entity.Creature;
import com.se1by.entity.Direction;
import com.se1by.entity.Entity;
import com.se1by.entity.Projectile;
import com.se1by.entity.enemy.Enemy;
import com.se1by.util.Vector2;
import com.se1by.Game.Game;
import com.se1by.Game.GameState;
import com.se1by.Level.Level1;

public class InGame {
	private static ArrayList<Entity> entities = new ArrayList<>();

	private static ArrayList<String> availableEntities = new ArrayList<>();
	private static ArrayList<Entity> toRemove = new ArrayList<>();
	private static boolean firstRun = true;

	public static void render(GameContainer con, Graphics g) {
		Game.camera.drawMap();
		Game.camera.translateGraphics();
		Game.player.render(con, g);

		for (Entity e : entities) {
			e.render(con, g);
		}
	}

	public static void update(GameContainer con, int delta) {
		if (firstRun) {
			Level1.init();
			availableEntities = Level1.getEntities();
			firstRun = false;
		}
		Game.player.update(con, delta);

		Input input = con.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			Game.state = GameState.PAUSED;
		}

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int x = input.getMouseX();
			int y = input.getMouseY();

			float alpha = 0;
			switch (Game.player.getDirection())
			{
			case DOWN:
				alpha = 180;
				break;
			case LEFT:
				alpha = 270;
				break;
			case RIGHT:
				alpha = 90;
				break;
			case UP:
				break;
			default:
				break;
			
			}
			Projectile projectile = new Projectile();
			Image tex = projectile.getImage();
			tex.setRotation(alpha);
			projectile.setImage(tex);
			projectile.setTargetPosition(new Vector2(x, y));
			projectile.getSound().play();
			entities.add(projectile);
		}

		Game.camera.centerOn(Game.player.getX(), Game.player.getY());
		generateEnemies();
		updateEntities(con, delta);
		removeEntities();
	}

	private static void updateEntities(GameContainer con, int delta) {
		for (Entity e : entities) {
			if (e instanceof Creature) {
				updateEnemies((Creature) e, delta);
			} else{
				e.update(con, delta);
			}
		}

	}

	public static void updateEnemies(Creature creature, int delta) {
		String result = canSeePlayer(creature);
		if (result.equals("none")) {
			return;
		} else if (result.contains("X")) {
			if (result.contains("&")) { // if leX is greater (this is to prevent
										// confusion with the math signs)
				creature.getPosition().setX(
						creature.getPosition().getX() - creature.getSpeed()
								* delta * 0.1f);
			} else {
				creature.getPosition().setX(
						creature.getPosition().getX() + creature.getSpeed()
								* delta * 0.1f);
			}
		} else if (result.contains("Y")) {
			if (result.contains("&")) {
				creature.getPosition().setY(
						creature.getPosition().getY() - creature.getSpeed()
								* delta * 0.1f);
			} else {
				creature.getPosition().setY(
						creature.getPosition().getY() + creature.getSpeed()
								* delta * 0.1f);
			}
		}
		if(Collision.collides(creature, Game.player)){
			Game.player.setHealth(Game.player.getHealth() - 0.5f * delta);
		}
	}

	private static String canSeePlayer(Creature creature) {
		if (!(Game.player.getPosition().distance(creature.getPosition()) <= creature
				.getVisibility())) {
			return "nope";
		}

		float leX = creature.getPosition().getX();
		float leY = creature.getPosition().getY();

		float pX = Game.player.getPosition().getX();
		float pY = Game.player.getPosition().getY();

		String canSeeX = "X";
		String canSeeY = "Y";

		float x = 0;
		float y = 0;

		if (leX > pX) {
			if ((leX - pX) <= creature.getVisibility()) {
				canSeeX += "&";
				x = leX - pX;
			}
		} else {
			if ((pX - leX) <= creature.getVisibility()) {
				canSeeX += "#";
				x = pX - leX;
			}
		}

		if (leY > pY) {
			if ((leY - pY) <= creature.getVisibility()) {
				canSeeY += "&";
				y = leY - pY;
			}
		} else {
			if ((pY - leY) <= creature.getVisibility()) {
				canSeeY += "#";
				y = pY - leY;
			}
		}

		if (x > y) {
			return canSeeX + x;
		} else if (y > x) {
			return canSeeY + y;
		} else {
			return "nope";
		}
	}

	public static void generateEnemies() {
		Random r = new Random();
		int i = r.nextInt(1000);
		if (i < 10) {
			Enemy le = null;
			String name = availableEntities.get(0);
			try {
				@SuppressWarnings("rawtypes")
				Class c = Class.forName("com.se1by.entity.enemy." + name);
				le = (Enemy) c.newInstance();
				le.setPosition(new Vector2(Game.player.getX(), Game.player.getY() + 5*64));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			le.setPosition(new Vector2(Game.player.getPosition().getX(),
					Game.player.getPosition().getY() - 5 * 64));
			entities.add(le);
			System.out.println("SPAWNED!");
		}
	}
	
	public static void removeEntity(Entity e){
		toRemove.add(e);
	}
	private static void removeEntities(){
		for(Entity e : toRemove){
			entities.remove(e);
		}
	}

	public static ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public static void setEntities(ArrayList<Entity> entities) {
		InGame.entities = entities;
	}
	public static Direction getTargetDirection(float sourceX, float sourceY, float targetX,float targetY) {
		float x = sourceX;
		float y = sourceY;

		float xDiff = targetX - x;
		float yDiff = targetY - y;
		float storX = xDiff;
		float storY = yDiff;

		if (xDiff < 0) {
			storX *= -1;
		}
		if (yDiff < 0) {
			storY *= -1;
		}

		if (storX > storY) {
			if (xDiff >= 0) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
		} else {
			System.out.println(yDiff);
			if (yDiff < 0) {
				return Direction.UP;
			} else {
				return Direction.DOWN;
			}
		}
	}

	public static ArrayList<Entity> getToRemove() {
		return toRemove;
	}

	public static void setToRemove(ArrayList<Entity> toRemove) {
		InGame.toRemove = toRemove;
	}
}
