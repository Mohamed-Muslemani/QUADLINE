package com.example.binarybashers_quadline.scenes;

import com.example.binarybashers_quadline.Settings;
import com.example.binarybashers_quadline.panes.SettingsPane;
import javafx.scene.Scene;

public class SettingsScene extends Scene {
    public SettingsScene() {
        super(new SettingsPane(), Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
    }
}
