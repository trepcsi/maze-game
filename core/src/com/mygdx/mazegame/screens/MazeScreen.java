package com.mygdx.mazegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.mazegame.MazeGame;
import com.mygdx.mazegame.controls.SideMenuBar;
import com.mygdx.mazegame.maze.Maze;
import com.mygdx.mazegame.maze.MazeGenerator;

public class MazeScreen implements Screen {

    private final MazeGame game;
    private final OrthographicCamera camera;
    private final FitViewport viewport;

    private final Maze maze;
    private final MazeGenerator mazeGenerator;

    private final SideMenuBar menu;
    public boolean generating = false;


    public MazeScreen(MazeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(MazeGame.V_WIDTH, MazeGame.V_HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        menu = new SideMenuBar(game.batch, this);
        this.maze = new Maze();
        this.mazeGenerator = new MazeGenerator(this, maze, 40); //n x n maze
    }

    @Override
    public void render(float dt) {
        update(dt);
        if (generating) {
            mazeGenerator.createMaze(dt);
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(100 / 255f, 100 / 255f, 255 / 255f, 1);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        maze.draw(game.batch);
        game.batch.end();
        game.batch.setProjectionMatrix(menu.stage.getCamera().combined);
        menu.stage.draw();
    }

    public void generateMaze() {
        if (!generating) {
            mazeGenerator.initMaze();
        }
        generating = true;
    }

    public void startMazeGame() {
        maze.startMazeGame();
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
        menu.dispose();
    }
}
