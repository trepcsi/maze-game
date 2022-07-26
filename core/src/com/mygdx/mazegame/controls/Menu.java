package com.mygdx.mazegame.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mazegame.MazeGame;
import com.mygdx.mazegame.screens.MazeScreen;

public class Menu implements Disposable {

    public Stage stage;
    private final MazeScreen screen;

    public Menu(SpriteBatch sb, MazeScreen screen) {
        this.screen = screen;
        Viewport viewport = new FitViewport(MazeGame.V_WIDTH, MazeGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        createTable();
    }

    private void createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.top().right();

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton generateButton = new TextButton("Generate", uiSkin);
        generateButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.generateMaze();
            }
        });

        TextButton playButton = new TextButton("Play", uiSkin);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.startMazeGame();
            }
        });

        table.add(generateButton).size(100,50).right().padTop(50).padRight(50);
        table.row();
        table.add(playButton).size(100,50).right().padTop(50).padRight(50);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
