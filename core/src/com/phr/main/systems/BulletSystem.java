package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.FireRateComp;
import com.phr.main.components.PositionComp;
import com.phr.main.components.VelocityComp;
import com.phr.main.util.EntityFactory;

public class BulletSystem extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pcm;
	private ComponentMapper<FireRateComp> frcm;
	private ComponentMapper<DimensionComp> dcm;
	private ComponentMapper<VelocityComp> vcm;
	
	@SuppressWarnings("unchecked")
	public BulletSystem() {
		super(Filter.allComponents(PositionComp.class, FireRateComp.class));
	}
	
	@Override
	public void initialize() {
		pcm = world.getMapper(PositionComp.class);
		frcm = world.getMapper(FireRateComp.class);
		dcm = world.getMapper(DimensionComp.class);
		vcm = world.getMapper(VelocityComp.class);
	}

	@Override
	protected void process(Entity e) {
		FireRateComp frc = frcm.get(e);
		
		frc.nextShot -= world.getDelta();
		if (frc.nextShot <= 0) {
			PositionComp pc = pcm.get(e);
			DimensionComp dc = dcm.get(e);
			VelocityComp vc = vcm.get(e);
			EntityFactory.createBullet(world, pc.position.x + dc.width / 2 - 10, pc.position.y + dc.height / 2 - 10, vc.velocity.y < 0 ? -1 : 1);
			
			frc.nextShot = frc.fireRate;
		}
	}

}