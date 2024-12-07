package com.example.binarybashers_quadline;

import com.example.binarybashers_quadline.scenes.IntroScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainGame extends Application {
    public static Stage mainStage;
    public static Stage secondStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        mainStage.setTitle("QuadLine");
        mainStage.setScene(new IntroScene());
        mainStage.show();
    }
}
