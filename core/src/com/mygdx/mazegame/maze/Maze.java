package com.mygdx.mazegame.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mazegame.MazeGame;

public class Maze {

    private final Texture empty_cell = new Texture("grey_rectangle.png");
    private final Texture wall_cell = new Texture("black_rectangle.png");
    private final Texture player_cell = new Texture("player_rectangle.png");
    private final Texture end_cell = new Texture("end_rectangle.png");
    private final Texture visited_cell = new Texture("visited_rectangle.png");

    private int[][] map = {
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 3},
            {1, 1, 1, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 0, 1},
            {2, 0, 0, 0, 0, 0, 0, 0, 1, 1},
    };

    private int player_x = 0;
    private int player_y = map.length - 1;

    private final float cell_size_x = (float) (MazeGame.V_WIDTH - 200) / map.length;
    private final float cell_size_y = (float) (MazeGame.V_HEIGHT) / map[0].length;

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                float x = (float) j * cell_size_x;
                float y = (float) (map.length - 1 - i) * cell_size_y;
                float w = cell_size_x - 1;
                float h = cell_size_y - 1;
                if (map[i][j] == 0) {
                    batch.draw(empty_cell, x, y, w, h);
                } else if (map[i][j] == 1) {
                    batch.draw(wall_cell, x, y, w, h);
                } else if (map[i][j] == 2) {
                    batch.draw(player_cell, x, y, w, h);
                } else if (map[i][j] == 3) {
                    batch.draw(end_cell, x, y, w, h);
                } else {
                    batch.draw(visited_cell, x, y, w, h);
                }
            }
        }
    }

    public void move() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            map[player_y][player_x] = -1;
            map[++player_y][player_x] = 2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            map[player_y][player_x] = -1;
            map[--player_y][player_x] = 2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            map[player_y][player_x] = -1;
            map[player_y][++player_x] = 2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            map[player_y][player_x] = -1;
            map[player_y][--player_x] = 2;
        }
    }

}
