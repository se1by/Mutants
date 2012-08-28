package com.se1by.entity;

import org.newdawn.slick.Animation;

public interface Creature extends LivingEntity {
	public float getHealth();
	
	public void setHealth(float health);
	
	public float getVisibility();
	
	public Animation getAnimationUP();
	
	public Animation getAnimationDOWN();
	
	public Animation getAnimationLEFT();
	
	public Animation getAnimationRIGHT();
}
