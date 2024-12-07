package com.example.binarybashers_quadline.scenes;

import com.example.binarybashers_quadline.Settings;
import com.example.binarybashers_quadline.panes.IntroPane;
import javafx.scene.Scene;

public class IntroScene extends Scene {
    public IntroScene() {
        super(new IntroPane(), Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
    }
}
