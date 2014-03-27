package com.phr.main.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Component to hold Collision data for entities. This is
 * made up of a position 
 * @author Brandon
 *
 */
public class PositionComp implements Component {

	// Position and dimensions separated for convenience
	public Vector2 position;
	
	public PositionComp() {
		this(0, 0);
	}
	
	public PositionComp(float x, float y) {
		this.position = new Vector2(x, y);
	}
	
	@Override
	public void reset() {
		position.set(0, 0);
	}

}
