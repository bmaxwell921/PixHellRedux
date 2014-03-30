package com.phr.main.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.phr.main.components.TimerComp;

public class RemoveTimerSys extends EntityProcessingSystem {

	private ComponentMapper<TimerComp> tcm;

	public RemoveTimerSys() {
		super(Filter.allComponents(TimerComp.class));
	}

	@Override
	public void initialize() {
		tcm = world.getMapper(TimerComp.class);
	}
	
	@Override
	protected void process(Entity e) {
		TimerComp tc = tcm.get(e);
		
		tc.timeUntil -= world.getDelta();
		
		if (tc.timeUntil <= 0) {
			e.deleteFromWorld();
		}
	}
}
