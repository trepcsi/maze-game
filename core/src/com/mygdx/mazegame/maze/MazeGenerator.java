package com.mygdx.mazegame.maze;


import java.util.Random;

public class MazeGenerator {

    private Maze maze;
    private int dimension;

    public MazeGenerator(Maze maze, int dim) {
        this.dimension = dim;
        this.maze = maze;
    }

    public void createMaze() {
        CellType empty = CellType.EMPTY;
        CellType wall = CellType.WALL;

        CellType[][] newMap = new CellType[20][20];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (new Random().nextInt() % 2 == 0) {
                    newMap[i][j] = empty;
                } else newMap[i][j] = wall;
            }
        }
        maze.setMap(newMap);
    }
}
