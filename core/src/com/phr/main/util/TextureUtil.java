package com.phr.main.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ArrayMap;


/**
 * Class used to cache images
 * @author Brandon
 *
 */
public class TextureUtil {
	
	// Singleton Stuff
	private static TextureUtil instance;
	
	public static TextureUtil getInstance() {
		if (instance == null) {
			instance = new TextureUtil();
		}
		return instance;
	}
	
	private TextureUtil() {
		this.images = new ArrayMap<>();
	}
	
	private ArrayMap<String, TextureRegion> images;
	
	public void loadTextures() {
		images.put("Player2", new TextureRegion(new Texture(Gdx.files.internal("Player2.png"))));
		images.put("Shot", new TextureRegion(new Texture(Gdx.files.internal("Shot.png"))));
		images.put("Enemy", new TextureRegion(new Texture(Gdx.files.internal("Enemy.png"))));
		images.put("Explosion", new TextureRegion(new Texture(Gdx.files.internal("Explosion.png"))));
	}
	
	// Gets the texture associated with the given name
	public TextureRegion getTexture(String name) {		
		return images.get(name);
	}
}
