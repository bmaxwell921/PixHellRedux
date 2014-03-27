package com.phr.main.screens;

import com.artemis.World;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.phr.main.PixHellGame;
import com.phr.main.systems.BulletSystem;
import com.phr.main.systems.InputSystem;
import com.phr.main.systems.MovementSys;
import com.phr.main.systems.RenderSys;
import com.phr.main.util.EntityFactory;
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
		
		camera.setToOrtho(false, PixHellGame.VIRTUAL_WIDTH, PixHellGame.VIRTUAL_HEIGHT);

		createWorld();

		TextureUtil.getInstance().setTextureAtlas(
				new TextureAtlas(Gdx.files
						.internal("gameImages.atlas")));
		EntityFactory.createPlayer(world, 190, 20);
	}

	private void createWorld() {
		world = new World();
		world.setSystem(new RenderSys(game, camera, game.batch));
		world.setSystem(new MovementSys());
		world.setSystem(new InputSystem());
		
		// TODO why is it that when I add this system everything starts disappearing?
		world.setSystem(new BulletSystem());
		world.initialize();
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
