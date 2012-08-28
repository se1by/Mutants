package com.se1by.entity.enemy;

import com.se1by.entity.Creature;
import com.se1by.util.Vector2;

public interface Enemy extends Creature{
	public void setPosition(Vector2 vec);
}
