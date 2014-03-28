package com.phr.main.components;

import com.artemis.Component;



public class SpawnComp implements Component {

	public float spawnRate;
	public float timeToSpawn;
	
	public SpawnComp() {
		spawnRate = 2;
		timeToSpawn = 0;
	}

	@Override
	public void reset() {
		spawnRate = 1;
		timeToSpawn = 0;
	}
}
