package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.phr.main.components.DamageComp;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.HealthComp;
import com.phr.main.components.PositionComp;
import com.phr.main.components.VelocityComp;
import com.phr.main.screens.GameScreen;
import com.phr.main.util.EntityFactory;

public class CollisionSys extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pcm;
	private ComponentMapper<VelocityComp> vcm;
	private ComponentMapper<DimensionComp> dcm;
	private ComponentMapper<DamageComp> dmcm;
	private ComponentMapper<HealthComp> hcm;

	private ArrayMap<String, Array<String>> collisionGroups;

	public CollisionSys() {
		super(Filter.allComponents(PositionComp.class, VelocityComp.class,
				DimensionComp.class, DamageComp.class));
	}

	@Override
	public void initialize() {
		collisionGroups = new ArrayMap<>();

		// Player bullets collide with enemies
		Array<String> playerBullet = new Array<>();
		playerBullet.add(EntityFactory.ENEMY);
		collisionGroups.put(EntityFactory.PLAYER_BULLET, playerBullet);

		// Enemies and enemy bullets collide with players
		Array<String> enemyBullet = new Array<>();
		enemyBullet.add(EntityFactory.PLAYER);
		collisionGroups.put(EntityFactory.ENEMY_BULLET, enemyBullet);
		collisionGroups.put(EntityFactory.ENEMY, enemyBullet);

		pcm = world.getMapper(PositionComp.class);
		vcm = world.getMapper(VelocityComp.class);
		dcm = world.getMapper(DimensionComp.class);
		dmcm = world.getMapper(DamageComp.class);
		hcm = world.getMapper(HealthComp.class);
	}

	@Override
	protected void process(Entity e) {
		PositionComp epc = pcm.get(e);
		VelocityComp evc = vcm.get(e);
		DimensionComp edc = dcm.get(e);
		GroupManager gm = world.getManager(GroupManager.class);
		String group = gm.getGroups(e).first();
		Array<String> collides = collisionGroups.get(group);

		// Must be a player, other groups will handle the collisions
		if (collides == null) {
			return;
		}
		Rectangle eRect = new Rectangle(epc.position.x, epc.position.y,
				edc.width, edc.height);
		Rectangle oRect = new Rectangle();
		for (String collideGroup : collides) {
			for (Entity o : gm.getEntities(collideGroup)) {
				PositionComp opc = pcm.get(o);
				VelocityComp ovc = vcm.get(o);
				DimensionComp odc = dcm.get(o);
				oRect.set(opc.position.x, opc.position.y, odc.width, odc.height);

				if (eRect.overlaps(oRect)) {
					DamageComp edmc = dmcm.get(e);
					DamageComp odmc = dmcm.get(o);

					HealthComp ehc = hcm.getSafe(e);
					HealthComp ohc = hcm.getSafe(o);

					if (ehc != null) {
						ehc.health -= odmc.damage;
						if (ehc.health <= 0) {
							e.deleteFromWorld();
							EntityFactory.createExplosion(world,
									epc.position.x, epc.position.y,
									evc.velocity.x, evc.velocity.y);
							world.deleteEntity(e);
							world.getManager(GroupManager.class).removeFromAllGroups(e);
//							Gdx.app.log("Collision", "Removing Entity");
						}
					} else {
						// If the thing doesn't have health, it's a bullet
						e.deleteFromWorld();
						world.deleteEntity(e);
						world.getManager(GroupManager.class).removeFromAllGroups(e);
//						Gdx.app.log("Collision", "Removing Entity");
					}

					if (ohc != null) {
						ohc.health -= edmc.damage;
						if (ohc.health <= 0) {
							o.deleteFromWorld();
							EntityFactory.createExplosion(world,
									opc.position.x, opc.position.y,
									ovc.velocity.x, ovc.velocity.y);
//							Gdx.app.log("Collision", "Removing Entity");
							world.deleteEntity(o);
							world.getManager(GroupManager.class).removeFromAllGroups(o);
						}
					} else {
						o.deleteFromWorld();
//						Gdx.app.log("Collision", "Removing Entity");
						world.deleteEntity(o);
						world.getManager(GroupManager.class).removeFromAllGroups(o);
					}
				}
			}
		}
	}
}
