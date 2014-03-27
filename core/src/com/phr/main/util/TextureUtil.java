package com.phr.main.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Disposable;


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
	
	// Instance stuff
	private TextureAtlas atlas;
	private ArrayMap<String, TextureRegion> images;
	
	public void setTextureAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}
	
	// Gets the texture associated with the given name
	public TextureRegion getTexture(String name) {
		if (!images.containsKey(name)) {
			images.put(name, atlas.findRegion(name));
		}
		
		return images.get(name);
	}
}
