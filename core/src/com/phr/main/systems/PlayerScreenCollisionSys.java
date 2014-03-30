package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.phr.main.PixHellGame;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.PlayerComp;
import com.phr.main.components.PositionComp;

public class PlayerScreenCollisionSys extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pcm;
	private ComponentMapper<DimensionComp> dcm;
	
	public PlayerScreenCollisionSys() {
		super(Filter.allComponents(PositionComp.class, DimensionComp.class, PlayerComp.class));
	}
	
	@Override
	public void initialize() {
		pcm = world.getMapper(PositionComp.class);
		dcm = world.getMapper(DimensionComp.class);
	}
	
	@Override
	public void process(Entity e) {
		// Check if the player hit the edge of the screen
		PositionComp pc = pcm.get(e);
		DimensionComp dc = dcm.get(e);
		
		if (pc.position.x <= 0) {
			pc.position.x = 0;
		}
		if (pc.position.x + dc.width > PixHellGame.VIRTUAL_WIDTH) {
			pc.position.x = PixHellGame.VIRTUAL_WIDTH - dc.width;
		}
		if (pc.position.y < PixHellGame.MIN_Y) {
			pc.position.y = PixHellGame.MIN_Y;
		}
		if (pc.position.y + dc.height > PixHellGame.MAX_Y) {
			pc.position.y = PixHellGame.MAX_Y - dc.height;
		}
	}
}
