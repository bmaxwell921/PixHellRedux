package com.phr.main.components;

import com.artemis.Component;

public class FireRateComp implements Component {

	public float fireRate;
	public float nextShot;
	
	public FireRateComp() {
		this(0, 0);
	}
	
	public FireRateComp(float fireRate, float lastFired) {
		this.fireRate = fireRate;
		this.nextShot = lastFired;
	}
	
	@Override
	public void reset() {
		fireRate = 0;
		nextShot = 0;
	}
	
	
}
