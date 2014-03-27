package com.phr.main.components;

import com.artemis.Component;

/**
 * Component to hold an Entitie's dimensions
 * @author Brandon
 *
 */
public class DimensionComp implements Component {

	public int width;
	public int height;
	
	public DimensionComp() {
		this(0, 0);
	}
	
	public DimensionComp(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void reset() {
		this.width = 0;
		this.height = 0;
	}
}
