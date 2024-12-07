package com.example.binarybashers_quadline.scenes;

import com.example.binarybashers_quadline.Settings;
import com.example.binarybashers_quadline.panes.GamePane;
import javafx.scene.Scene;

public class GameScene extends Scene {
    public GameScene() {
        super(new GamePane(), Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
    }
}
