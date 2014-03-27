package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.managers.PlayerManager;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
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
		float dRate = 0;
		if (accel) {
			dRate = -Gdx.input.getAccelerometerX();
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			dRate = -1;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			dRate = 1;
		}
		VelocityComp vc = vcm.get(e);
		vc.velocity.x = dRate * MAX_SPEED;	
	}
}
