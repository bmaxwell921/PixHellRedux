package com.phr.main.components;

import com.artemis.Component;

public class DamageComp implements Component {

	public int damage;
	
	public DamageComp() {
		damage = 0;
	}
	
	@Override
	public void reset() {
		damage = 0;
	}
}
