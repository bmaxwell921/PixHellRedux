package com.phr.main.screens;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.phr.main.PixHellGame;
import com.phr.main.components.SpawnComp;
import com.phr.main.systems.BulletSystem;
import com.phr.main.systems.CollisionSys;
import com.phr.main.systems.EnemySpawnSys;
import com.phr.main.systems.EntityRemoveSys;
import com.phr.main.systems.InputSystem;
import com.phr.main.systems.MovementSys;
import com.phr.main.systems.PlayerScreenCollisionSys;
import com.phr.main.systems.RemoveTimerSys;
import com.phr.main.systems.RenderSys;
import com.phr.main.util.EntityFactory;
import com.phr.main.util.TextureUtil;

public class GameScreen implements Screen {

	public static boolean DEBUGGING = false;
	
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
		TextureUtil.getInstance().loadTextures();
		EntityFactory.createPlayer(world, 190, 20);
	}

	private void createWorld() {
		world = new World();
		world.setSystem(new RenderSys(game, camera, game.batch));
		world.setSystem(new MovementSys());
		world.setSystem(new InputSystem());
		world.setSystem(new EnemySpawnSys());
		world.setSystem(new EntityRemoveSys());
		world.setSystem(new BulletSystem());
		world.setSystem(new CollisionSys());
		world.setSystem(new RemoveTimerSys());
		world.setSystem(new PlayerScreenCollisionSys());
		
		world.setManager(new GroupManager());
		
//		world.setSystem(new DebugRenderSys(camera));
		world.initialize();
		createEnemySpawner();
	}
	
	private void createEnemySpawner() {
		Entity e = world.createEntity();
		e.addComponent(world.createComponent(SpawnComp.class));
		e.addToWorld();
	}
	
	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glViewport((int) game.viewport.x, (int) game.viewport.y, 
				(int) game.viewport.width, (int) game.viewport.height); 
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.begin();
		world.setDelta(delta);
		world.process();
		game.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			DEBUGGING = !DEBUGGING;
			Gdx.app.log("DEBUG", "Toggled debugging to: " + DEBUGGING);
		}
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
