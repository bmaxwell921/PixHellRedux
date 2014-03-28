package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.phr.main.components.SpawnComp;
import com.phr.main.util.EntityFactory;

public class EnemySpawnSys extends EntityProcessingSystem {

	private ComponentMapper<SpawnComp> scm;
	
	public EnemySpawnSys() {
		super(Filter.allComponents(SpawnComp.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		scm = world.getMapper(SpawnComp.class);
	}

	@Override
	protected void process(Entity e) {
		SpawnComp sc = scm.get(e);
		
		sc.timeToSpawn -= world.getDelta();
		if (sc.timeToSpawn <= 0) {
			EntityFactory.createEnemy(world);
			sc.timeToSpawn = sc.spawnRate;
		}
	}

}
