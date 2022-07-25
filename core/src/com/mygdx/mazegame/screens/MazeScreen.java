package com.mygdx.mazegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.mazegame.MazeGame;
import com.mygdx.mazegame.maze.Maze;

public class MazeScreen implements Screen {

    private MazeGame game;
    private OrthographicCamera camera;
    private FitViewport viewport;

    private Maze maze;

    public MazeScreen(MazeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MazeGame.V_WIDTH, MazeGame.V_HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        this.maze = new Maze();
    }

    @Override
    public void render(float dt) {
        update(dt);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(100/255f, 100/255f, 255/255f, 1);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        maze.draw(game.batch);
        game.batch.end();
    }

    private void update(float dt) {
        maze.move();
    }

    @Override
    public void show() {
        Gdx.gl.glViewport(0, 0, MazeGame.V_WIDTH, MazeGame.V_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
