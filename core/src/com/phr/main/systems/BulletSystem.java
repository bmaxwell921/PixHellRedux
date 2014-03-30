package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.phr.main.components.DamageComp;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.FireRateComp;
import com.phr.main.components.PlayerComp;
import com.phr.main.components.PositionComp;
import com.phr.main.components.VelocityComp;
import com.phr.main.util.EntityFactory;

public class BulletSystem extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pcm;
	private ComponentMapper<FireRateComp> frcm;
	private ComponentMapper<DimensionComp> dcm;
	private ComponentMapper<VelocityComp> vcm;
	private ComponentMapper<DamageComp> dmcm;
	
	private ComponentMapper<PlayerComp> plcm;

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
		dmcm = world.getMapper(DamageComp.class);
		plcm = world.getMapper(PlayerComp.class);
	}

	@Override
	protected void process(Entity e) {
		FireRateComp frc = frcm.get(e);

		frc.nextShot -= world.getDelta();
		if (frc.nextShot <= 0) {
			GroupManager gm = world.getManager(GroupManager.class);
			PositionComp pc = pcm.get(e);
			DimensionComp dc = dcm.get(e);
			VelocityComp vc = vcm.get(e);
			PlayerComp plc = plcm.getSafe(e);
			EntityFactory.createBullet(world, pc.position.x + dc.width / 2
					- EntityFactory.BULLET_SIZE / 2, pc.position.y + dc.height
					/ 2 - EntityFactory.BULLET_SIZE / 2, (plc == null) ? -1
					: 1, dmcm.get(e).damage,
					gm.getGroups(e).contains(EntityFactory.PLAYER, true));

			frc.nextShot = frc.fireRate;
		}
	}

}
