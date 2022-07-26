package com.mygdx.mazegame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("MazeGame");
        config.setWindowedMode(MazeGame.V_WIDTH, MazeGame.V_HEIGHT);
        config.setWindowSizeLimits(MazeGame.V_WIDTH, MazeGame.V_HEIGHT, MazeGame.V_WIDTH, MazeGame.V_HEIGHT);
        new Lwjgl3Application(new MazeGame(), config);
    }
}
