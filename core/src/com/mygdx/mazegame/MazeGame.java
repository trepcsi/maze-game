package com.mygdx.mazegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mazegame.screens.MazeScreen;

public class MazeGame extends Game {

	public static final int V_WIDTH = 1000;
	public static final int V_HEIGHT = 800;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MazeScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
