package com.mygdx.mazegame.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mazegame.MazeGame;

public class Maze {

    private final Texture empty_cell = new Texture("cells/grey_rectangle.png");
    private final Texture wall_cell = new Texture("cells/black_rectangle.png");
    private final Texture player_cell = new Texture("cells/player_rectangle.png");
    private final Texture end_cell = new Texture("cells/end_rectangle.png");
    private final Texture visited_cell = new Texture("cells/visited_rectangle.png");

    private CellType[][] map;

    private boolean playing = false;
    private int player_x;
    private int player_y;

    private float cell_size_x;// = (float) (MazeGame.V_WIDTH - 200) / map.length;
    private float cell_size_y;// = (float) (MazeGame.V_HEIGHT) / map[0].length;

    public void draw(SpriteBatch batch) {
        if (map == null) return;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                float x = (float) j * cell_size_x;
                float y = (float) (map.length - 1 - i) * cell_size_y;
                float w = cell_size_x - 1;
                float h = cell_size_y - 1;

                switch (map[i][j]) {
                    case EMPTY -> batch.draw(empty_cell, x, y, w, h);
                    case WALL -> batch.draw(wall_cell, x, y, w, h);
                    case PLAYER -> batch.draw(player_cell, x, y, w, h);
                    case FINISH -> batch.draw(end_cell, x, y, w, h);
                    case VISITED -> batch.draw(visited_cell, x, y, w, h);
                }
            }
        }
    }

    public void move() {
        if (!playing) return;

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (player_y + 1 <= map.length - 1) {
                if (map[player_y + 1][player_x] != CellType.WALL) {
                    map[player_y][player_x] = CellType.VISITED;
                    map[++player_y][player_x] = CellType.PLAYER;
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (player_y - 1 >= 0) {
                if (map[player_y - 1][player_x] != CellType.WALL) {
                    map[player_y][player_x] = CellType.VISITED;
                    map[--player_y][player_x] = CellType.PLAYER;
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (player_x + 1 <= map[0].length - 1) {
                if (map[player_y][player_x + 1] != CellType.WALL) {
                    map[player_y][player_x] = CellType.VISITED;
                    map[player_y][++player_x] = CellType.PLAYER;
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (player_x - 1 >= 0)
                if (map[player_y][player_x - 1] != CellType.WALL) {
                    map[player_y][player_x] = CellType.VISITED;
                    map[player_y][--player_x] = CellType.PLAYER;
                }
        }
    }

    public void setMap(CellType[][] map) {
        this.map = map;
        cell_size_x = (float) (MazeGame.V_WIDTH - 200) / map.length;
        cell_size_y = (float) (MazeGame.V_HEIGHT) / map[0].length;
    }

    public CellType[][] getMap() {
        return map;
    }

    public void setPlaying(boolean isPlaying) {
        this.playing = isPlaying;
    }

    public void startMazeGame() {
        playing = true;
        player_x = 0;
        player_y = map.length - 1;
        map[player_y][player_x] = CellType.PLAYER;
        map[0][map[0].length - 1] = CellType.FINISH;
    }
}
