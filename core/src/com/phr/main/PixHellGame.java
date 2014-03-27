package com.phr.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.phr.main.screens.GameScreen;

public class PixHellGame extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
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
