package com.phr.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.phr.main.PixHellGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		packImages();
		config.title = "PixHellRedux";
		config.width = PixHellGame.WIDTH;
		config.height = PixHellGame.HEIGHT;
		new LwjglApplication(new PixHellGame(), config);
	}
	
	private static void packImages() {
		TexturePacker2.process("../images", "../android/assets", "gameImages");
	}
}
