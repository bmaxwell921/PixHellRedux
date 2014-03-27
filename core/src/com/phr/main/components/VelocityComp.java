package com.phr.main.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Component to hold an entity's velocity
 * @author Brandon
 *
 */
public class VelocityComp implements Component {

	public Vector2 velocity;
	
	public VelocityComp() {
		this(0, 0);
	}
	
	public VelocityComp(int dx, int dy) {
		this.velocity = new Vector2(dx, dy);
	}
	
	@Override
	public void reset() {
		this.velocity.set(0, 0);
	}
}
