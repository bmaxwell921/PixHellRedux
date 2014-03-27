package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.phr.main.PixHellGame;
import com.phr.main.components.PositionComp;
import com.phr.main.components.TextureComp;
import com.phr.main.util.TextureUtil;

/**
 * System used to render entities to the screen
 * @author Brandon
 *
 */
public class RenderSys extends EntityProcessingSystem {

	private final PixHellGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private ComponentMapper<PositionComp> pc;
	private ComponentMapper<TextureComp> sc;
	
	@SuppressWarnings("unchecked")
	public RenderSys(PixHellGame game, OrthographicCamera camera, SpriteBatch batch) {
		super(Filter.allComponents(PositionComp.class, TextureComp.class));
		this.game = game;
		this.camera = camera;
		this.batch = batch;
	}
	
	@Override
	public void initialize() {
		pc = world.getMapper(PositionComp.class);
		sc = world.getMapper(TextureComp.class);
	}

	
	@Override
	public void process(Entity e) {	
		PositionComp pComp = pc.get(e);
		TextureComp sComp = sc.get(e);
		
		Texture tr = TextureUtil.getInstance().getTexture(sComp.texName);
		batch.setProjectionMatrix(camera.combined);
		
		batch.draw(tr, pComp.position.x, pComp.position.y);
		
		Gdx.app.log("RENDER", String.format("Drew entity at: (%f, %f) with texture: %s", 
				pComp.position.x, pComp.position.y, sComp.texName));
	}
}
