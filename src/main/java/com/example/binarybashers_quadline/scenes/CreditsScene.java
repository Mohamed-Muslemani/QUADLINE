package com.example.binarybashers_quadline.scenes;

import com.example.binarybashers_quadline.Settings;
import com.example.binarybashers_quadline.panes.CreditsPane;
import javafx.scene.Scene;

public class CreditsScene extends Scene {

    public CreditsScene() {

        super(new CreditsPane(), Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

    }

}
