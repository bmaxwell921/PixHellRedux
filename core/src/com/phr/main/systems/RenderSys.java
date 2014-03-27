package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
		// Clear the screen
		Gdx.gl.glViewport((int) game.viewport.x, (int) game.viewport.y, 
				(int) game.viewport.width, (int) game.viewport.height); 
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		PositionComp pComp = pc.get(e);
		TextureComp sComp = sc.get(e);
		
		TextureRegion tr = TextureUtil.getInstance().getTexture(sComp.texName);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.draw(tr, pComp.position.x, pComp.position.y);
	}
}
