package com.phr.main.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
		
		// TODO how to scale to this resolution??
		camera.setToOrtho(false, PixHellGame.VIRTUAL_WIDTH, PixHellGame.VIRTUAL_HEIGHT);

		createWorld();

		TextureUtil.getInstance().setTextureAtlas(
				new TextureAtlas(Gdx.files
						.internal("gameImages.atlas")));

		createPlayer();
	}

	private void createWorld() {
		world = new World();
		world.setSystem(new MovementSys());
		world.setSystem(new RenderSys(game, camera, game.batch));

		world.initialize();
	}
	
	private void createPlayer() {
		Entity e = world.createEntity();
		PositionComp pc = world.createComponent(PositionComp.class);
		pc.position.set(PixHellGame.VIRTUAL_WIDTH / 2 - 50, 20);
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
		float aspectRatio = (float) width / (float) height;
		float scale = 1f;
		Vector2 crop = new Vector2();

        if(aspectRatio > PixHellGame.ASPECT_RATIO)
        {
            scale = (float)height/(float)PixHellGame.VIRTUAL_HEIGHT;
            crop.x = (width - PixHellGame.VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < PixHellGame.ASPECT_RATIO)
        {
            scale = (float)width/(float)PixHellGame.VIRTUAL_WIDTH;
            crop.y = (height - PixHellGame.VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)PixHellGame.VIRTUAL_WIDTH;
        }

        float w = (float)PixHellGame.VIRTUAL_WIDTH*scale;
        float h = (float)PixHellGame.VIRTUAL_HEIGHT*scale;
        game.viewport = new Rectangle(crop.x, crop.y, w, h);
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
