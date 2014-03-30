package com.phr.main.components;

import com.artemis.Component;

public class HealthComp implements Component {

	public int health;
	
	public HealthComp() {
		health = 0;
	}
	
	@Override
	public void reset() {
		health = 0;
	}

	
}
