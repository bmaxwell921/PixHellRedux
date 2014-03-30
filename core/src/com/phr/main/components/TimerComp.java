package com.phr.main.components;

import com.artemis.Component;

// Have the spawnComp and fireRateComp extends from this?
public class TimerComp implements Component {

	public int rate;
	public int timeUntil;
	
	
	public TimerComp() {
		this.reset();
	}
	
	public void start(int rate) {
		this.rate = rate;
		this.timeUntil = this.rate;
	}
	
	@Override
	public void reset() {
		rate = 0;
		timeUntil = 0;
	}
}
