package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.managers.PlayerManager;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.math.MathUtils;
import com.phr.main.components.PlayerComp;
import com.phr.main.components.VelocityComp;

public class InputSystem extends EntityProcessingSystem {

	private static final int MAX_SPEED = 150;
	
	private ComponentMapper<VelocityComp> vcm;
	
	private boolean accel;
	
	@SuppressWarnings("unchecked")
	public InputSystem() {
		super(Filter.allComponents(VelocityComp.class, PlayerComp.class));
		accel = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);
	}
	
	@Override
	public void initialize() {
		vcm = world.getMapper(VelocityComp.class);
	}

	@Override
	protected void process(Entity e) {
		float xRate = 0;
		float yRate = 0;
		if (accel) {
			xRate = -Gdx.input.getAccelerometerX();
			yRate = -Gdx.input.getAccelerometerY();
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			xRate = -1;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			xRate = 1;
		} 
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			yRate = 1;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			yRate = -1;
		}
		
		// Round here to make the image rotation look nice
		xRate = MathUtils.round(xRate);
		yRate = MathUtils.round(yRate);
		VelocityComp vc = vcm.get(e);
		vc.velocity.x = xRate * MAX_SPEED;
		vc.velocity.y = yRate * MAX_SPEED;
	}
}
