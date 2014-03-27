package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.phr.main.components.PositionComp;
import com.phr.main.components.VelocityComp;

/**
 * System that updates entity positions
 * @author Brandon
 *
 */
public class MovementSys extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pc;
	private ComponentMapper<VelocityComp> vc;

	@SuppressWarnings("unchecked")
	public MovementSys() {
		super(Filter.allComponents(PositionComp.class, VelocityComp.class));
	}
	
	@Override
	public void initialize() {
		pc = world.getMapper(PositionComp.class);
		vc = world.getMapper(VelocityComp.class);
	}
	
	@Override
	public void process(Entity e) {
		PositionComp pComp = pc.get(e);
		VelocityComp vComp = vc.get(e);
		
		pComp.position.x += vComp.velocity.x * world.getDelta();
		pComp.position.y += vComp.velocity.y * world.getDelta();
	}
}
