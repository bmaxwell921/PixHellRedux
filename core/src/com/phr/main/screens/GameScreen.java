package com.phr.main.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.phr.main.PixHellGame;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.PositionComp;
import com.phr.main.components.TextureComp;
import com.phr.main.components.VelocityComp;
import com.phr.main.systems.MovementSys;
import com.phr.main.systems.RenderSys;
import com.phr.main.util.TextureUtil;

public class GameScreen implements Screen {

	// The game
	private final PixHellGame game;

	// The camera
	private OrthographicCamera camera;

	private World world;

	public GameScreen(final PixHellGame game) {
		this.game = game;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		createWorld();

		TextureUtil.getInstance().setTextureAtlas(
				new TextureAtlas(Gdx.files
						.internal("gameImages.atlas")));

		createPlayer();
	}

	private void createWorld() {
		world = new World();
		world.setSystem(new MovementSys());
		world.setSystem(new RenderSys(game.batch, camera));

		world.initialize();
	}
	
	private void createPlayer() {
		Entity e = world.createEntity();
		PositionComp pc = world.createComponent(PositionComp.class);
		pc.position.set(190, 20);
		VelocityComp vc = world.createComponent(VelocityComp.class);
		DimensionComp dc = world.createComponent(DimensionComp.class);
		dc.height = 100;
		dc.width = 100;
		TextureComp tc = world.createComponent(TextureComp.class);
		tc.texName = "Player2";
		
		e.addComponent(pc);
		e.addComponent(vc);
		e.addComponent(dc);
		e.addComponent(tc);
		
		e.addToWorld();
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		world.setDelta(delta);
		world.process();
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		float centerX = width / 2f;
		float centerY = height / 2f;

		camera.position.set(centerX, centerY, 0);
		camera.viewportWidth = width;
		camera.viewportHeight = height;

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	}

}
