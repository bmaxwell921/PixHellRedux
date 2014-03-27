package com.phr.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.phr.main.screens.GameScreen;

public class PixHellGame extends Game {

	public static final int VIRTUAL_WIDTH = 480;
	public static final int VIRTUAL_HEIGHT = 800;
	public static final float ASPECT_RATIO = ((float) VIRTUAL_WIDTH) / ((float) VIRTUAL_HEIGHT);
	public Rectangle viewport;
	
	// SB used for drawing sprites
	public SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
