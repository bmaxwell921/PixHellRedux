package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.PositionComp;

public class DebugRenderSys extends EntityProcessingSystem {

	private ComponentMapper<PositionComp> pcm;
	private ComponentMapper<DimensionComp> dcm;
	
	private ShapeRenderer renderer;
	private OrthographicCamera camera;
	
	public DebugRenderSys(OrthographicCamera camera) {
		super(Filter.allComponents(PositionComp.class, DimensionComp.class));
		renderer = new ShapeRenderer();
		this.camera = camera;
	}
	
	@Override
	public void initialize() {
		pcm = world.getMapper(PositionComp.class);
		dcm = world.getMapper(DimensionComp.class);
	}

	@Override
	public void begin() {
		renderer.begin(ShapeType.Line);
	}
	
	@Override
	protected void process(Entity e) {
		renderer.setProjectionMatrix(camera.combined);
		
		PositionComp pc = pcm.get(e);
		DimensionComp dc = dcm.get(e);
		renderer.setColor(Color.RED);
		renderer.rect(pc.position.x, pc.position.y, dc.width, dc.height);
	}
	
	@Override
	public void end() {
		renderer.end();
	}
}
