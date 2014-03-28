package com.phr.main.util;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.phr.main.PixHellGame;
import com.phr.main.components.DimensionComp;
import com.phr.main.components.FireRateComp;
import com.phr.main.components.PlayerComp;
import com.phr.main.components.PositionComp;
import com.phr.main.components.TextureComp;
import com.phr.main.components.VelocityComp;

/**
 * Factory class for creating entities
 * @author Brandon
 *
 */
public class EntityFactory {
	
	public static final int SHIP_SIZE = 50;
	public static final int BULLET_SIZE = 10;

	public static void createPlayer(World world, float x, float y) {
		Entity e = world.createEntity();
		
		PositionComp pc = world.createComponent(PositionComp.class);
		pc.position.set(x, y);
		e.addComponent(pc);
		
		VelocityComp vc = world.createComponent(VelocityComp.class);
		e.addComponent(vc);
		
		DimensionComp dc = world.createComponent(DimensionComp.class);
		dc.height = SHIP_SIZE;
		dc.width = SHIP_SIZE;
		e.addComponent(dc);
		
		TextureComp tc = world.createComponent(TextureComp.class);
		tc.texName = "Player2";
		e.addComponent(tc);
		
		FireRateComp frc = world.createComponent(FireRateComp.class);
		frc.fireRate = 1f;
		frc.nextShot = 0;
		e.addComponent(frc);
		
		PlayerComp plC = world.createComponent(PlayerComp.class);
		e.addComponent(plC);
		
		e.addToWorld();
	}
	
	public static void createBullet(World world, float x, float y, float dir) {
		Entity e = world.createEntity();
		
		PositionComp pc = world.createComponent(PositionComp.class);
		pc.position.set(x, y);
		e.addComponent(pc);
		
		VelocityComp vc = world.createComponent(VelocityComp.class);
		vc.velocity.set(0, 100 * dir);
		e.addComponent(vc);
		
		DimensionComp dc = world.createComponent(DimensionComp.class);
		dc.height = BULLET_SIZE;
		dc.width = BULLET_SIZE;
		e.addComponent(dc);
		
		TextureComp tc = world.createComponent(TextureComp.class);
		tc.texName = "Shot";
		e.addComponent(tc);
		
		e.addToWorld();
	}
	
	public static void createEnemy(World world) {
		Entity e = world.createEntity();
		
		PositionComp pc = world.createComponent(PositionComp.class);
		pc.position.set(MathUtils.random(0, PixHellGame.VIRTUAL_WIDTH - SHIP_SIZE), PixHellGame.VIRTUAL_HEIGHT);
		e.addComponent(pc);
		
		VelocityComp vc = world.createComponent(VelocityComp.class);
		vc.velocity.set(0, -75);
		e.addComponent(vc);
		
		DimensionComp dc = world.createComponent(DimensionComp.class);
		dc.height = SHIP_SIZE;
		dc.width = SHIP_SIZE;
		e.addComponent(dc);
		
		TextureComp tc = world.createComponent(TextureComp.class);
		tc.texName = "Enemy";
		e.addComponent(tc);
		
		FireRateComp frc = world.createComponent(FireRateComp.class);
		frc.fireRate = 2f;
		frc.nextShot = 0;
		e.addComponent(frc);
		
		e.addToWorld();
	}
}