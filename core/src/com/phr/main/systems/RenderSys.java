package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.phr.main.PixHellGame;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.PositionComp;
import com.phr.main.components.TextureComp;
import com.phr.main.components.VelocityComp;
import com.phr.main.util.TextureUtil;

/**
 * System used to render entities to the screen
 * 
 * @author Brandon
 * 
 */
public class RenderSys extends EntityProcessingSystem {

	private final PixHellGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private ComponentMapper<PositionComp> pc;
	private ComponentMapper<VelocityComp> vc;
	private ComponentMapper<TextureComp> sc;
	private ComponentMapper<DimensionComp> dc;

	@SuppressWarnings("unchecked")
	public RenderSys(PixHellGame game, OrthographicCamera camera,
			SpriteBatch batch) {
		super(Filter.allComponents(PositionComp.class, VelocityComp.class,
				TextureComp.class, DimensionComp.class));
		this.game = game;
		this.camera = camera;
		this.batch = batch;
	}

	@Override
	public void initialize() {
		pc = world.getMapper(PositionComp.class);
		vc = world.getMapper(VelocityComp.class);
		sc = world.getMapper(TextureComp.class);
		dc = world.getMapper(DimensionComp.class);
	}

	@Override
	public void process(Entity e) {
		PositionComp pComp = pc.get(e);
		TextureComp sComp = sc.get(e);
		DimensionComp dComp = dc.get(e);

		TextureRegion tr = TextureUtil.getInstance().getTexture(sComp.texName);
		batch.setProjectionMatrix(camera.combined);

		float rotation = calcRotation(vc.get(e).velocity);
		
		/*
		 * Draw arguments:
		 *	batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
		 * OriginX and originY are used for where to rotate around. 
		 * (width / 2 and height / 2) makes us rotate about the center.
		 * Everything else is self explanatory  	
		 */
		
		batch.draw(tr, pComp.position.x, pComp.position.y, 
				dComp.width / 2, dComp.height / 2, dComp.width, dComp.height, 1f, 1f, rotation);

//		Gdx.app.log("RENDER", String.format(
//				"Drew entity at: (%f, %f) with texture: %s", pComp.position.x,
//				pComp.position.y, sComp.texName));
	}
	
	@Override
	public void end() {
//		Gdx.app.log("RENDER", "break\n");
	}

	// Uses the velocity to figure out which direction an entity is moving in
	// then it calculates the rotation of that direction from the up vector
	private float calcRotation(Vector2 velocity) {
		return 360 - (float) MathUtils.atan2(velocity.x, velocity.y) * MathUtils.radiansToDegrees;
	}
}
