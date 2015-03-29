package com.box2d.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.box2d.game.Box2dTests;
import com.box2d.game.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.APP_WIDTH;
        config.height = Constants.APP_HEIGHT;
		new LwjglApplication(new Box2dTests(), config);
	}
}
