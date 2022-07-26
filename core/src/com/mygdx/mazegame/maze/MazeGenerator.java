package com.mygdx.mazegame.maze;


import java.util.Random;

public class MazeGenerator {

    private Maze maze;
    private int dimension;

    public MazeGenerator(Maze maze, int dim) {
        this.dimension = dim - 1;
        this.maze = maze;
    }

    public void createMaze() {
        CellType[][] newMap = new CellType[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    newMap[i][j] = CellType.EMPTY;
                } else {
                    newMap[i][j] = CellType.WALL;
                }
            }
        }

        newMap = kruskal(newMap);

        maze.setMap(newMap);
        maze.setPlaying(false);
    }

    private CellType[][] kruskal(CellType[][] maze) {
        Random random = new Random();
        int destroyedWallCount = 0;
        int emptyCellCount = countEmpty(maze);
        int[][] labels = fillLabels(maze);
        while (destroyedWallCount < emptyCellCount - 1) {
            int random_cell_x = random.nextInt(maze.length / 2 + 1) * 2;
            int random_cell_y = random.nextInt(maze[0].length / 2 + 1) * 2;

            int randomDir = random.nextInt(4);
            int random_wall_x = -1;
            int random_wall_y = -1;
            int random_neighbor_x = -1;
            int random_neighbor_y = -1;
            switch (randomDir) {
                case 0 -> {
                    random_wall_x = random_cell_x + 1;
                    random_wall_y = random_cell_y;
                    random_neighbor_x = random_cell_x + 2;
                    random_neighbor_y = random_cell_y;
                }
                case 1 -> {
                    random_wall_x = random_cell_x - 1;
                    random_wall_y = random_cell_y;
                    random_neighbor_x = random_cell_x - 2;
                    random_neighbor_y = random_cell_y;
                }
                case 2 -> {
                    random_wall_x = random_cell_x;
                    random_wall_y = random_cell_y + 1;
                    random_neighbor_x = random_cell_x;
                    random_neighbor_y = random_cell_y + 2;
                }
                case 3 -> {
                    random_wall_x = random_cell_x;
                    random_wall_y = random_cell_y - 1;
                    random_neighbor_x = random_cell_x;
                    random_neighbor_y = random_cell_y - 2;
                }
            }
            if (random_wall_x >= 0 && random_wall_x < maze.length
                    && random_wall_y >= 0 && random_wall_y < maze[0].length
                    && random_neighbor_x >= 0 && random_neighbor_x < maze.length
                    && random_neighbor_y >= 0 && random_neighbor_y < maze[0].length) {
                if (maze[random_wall_x][random_wall_y] == CellType.WALL) {
                    if (maze[random_neighbor_x][random_neighbor_y] == CellType.EMPTY) {
                        if (labels[random_cell_x][random_cell_y] != labels[random_neighbor_x][random_neighbor_y]) {
                            maze[random_wall_x][random_wall_y] = CellType.EMPTY;
                            destroyedWallCount++;
                            uniteLabels(labels[random_cell_x][random_cell_y], labels[random_neighbor_x][random_neighbor_y], labels);
                        }
                    }
                }
            }
        }
        return maze;
    }

    private int[][] uniteLabels(int to, int from, int[][] labels) {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                if (labels[i][j] == from)
                    labels[i][j] = to;
            }
        }
        return labels;
    }

    private int[][] fillLabels(CellType[][] maze) {
        int labelNumber = 0;
        int[][] labels = new int[maze.length][maze[0].length];
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                if (maze[i][j] == CellType.EMPTY) {
                    labels[i][j] = labelNumber++;
                }
            }
        }
        return labels;
    }

    private int countEmpty(CellType[][] maze) {
        int result = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == CellType.EMPTY)
                    result++;
            }
        }
        return result;
    }
}
