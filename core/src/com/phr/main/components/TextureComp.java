package com.phr.main.components;

import com.artemis.Component;

/**
 * Component holding the name of a sprite in the texture atlas
 * @author Brandon
 *
 */
public class TextureComp implements Component {

	public String texName;
	
	public TextureComp() {
		this(null);
	}
	
	public TextureComp(String textName) {
		this.texName = textName;
	}
	
	@Override
	public void reset() {
		texName = null;
	}
}
