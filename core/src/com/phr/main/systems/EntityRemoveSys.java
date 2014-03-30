package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.phr.main.PixHellGame;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.PlayerComp;
import com.phr.main.components.PositionComp;
import com.phr.main.util.EntityFactory;

public class EntityRemoveSys extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pcm;
	private ComponentMapper<DimensionComp> dcm;
	
	public EntityRemoveSys() {
		super(Filter.allComponents(PositionComp.class, DimensionComp.class).exclude(PlayerComp.class));
		
		// Bounds are bigger than the actual screen so new enemies aren't killed
	}
	
	@Override
	public void initialize() {
		pcm = world.getMapper(PositionComp.class);
		dcm = world.getMapper(DimensionComp.class);
	}

	@Override
	protected void process(Entity e) {
		PositionComp pc = pcm.get(e);
		DimensionComp dc = dcm.get(e);
		
		if (!inBounds(pc, dc)) {
			e.deleteFromWorld();
			
//			Gdx.app.log("Entity Remove", "Removed entity from the game");
		}
	}
	
	private boolean inBounds(PositionComp pc, DimensionComp dc) {
		return pc.position.x >= 0 && pc.position.x < PixHellGame.VIRTUAL_WIDTH - dc.width // x values
				&& pc.position.y >= -dc.height && pc.position.y <= PixHellGame.VIRTUAL_HEIGHT; // y values
	}
}
