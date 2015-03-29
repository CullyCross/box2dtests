package com.box2d.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.box2d.game.screens.Box2dTestScreen;

public class Box2dTests extends Game {
	
	@Override
	public void create () {
        setScreen(new Box2dTestScreen());
	}
}
