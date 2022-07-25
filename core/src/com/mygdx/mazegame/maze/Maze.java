package com.mygdx.mazegame.maze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mazegame.MazeGame;

public class Maze {

    private final Texture empty_cell = new Texture("grey_rectangle.png");
    private final Texture wall_cell = new Texture("black_rectangle.png");

    private int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
    };

    private final float cell_size_x = (float) (MazeGame.V_WIDTH - 200) / map.length;
    private final float cell_size_y = (float) (MazeGame.V_HEIGHT) / map[0].length;

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < map.length; i++) {
            for (int j = map[0].length-1; j >= 0; j--) {
                if (map[j][i] == 0) {
                    batch.draw(empty_cell, (float) i * cell_size_x, (float) j * cell_size_y, cell_size_x - 1, cell_size_y - 1);
                } else {
                    batch.draw(wall_cell, (float) i * cell_size_x, (float) j * cell_size_y, cell_size_x - 1, cell_size_y - 1);
                }
            }
        }
    }
}
