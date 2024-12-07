package com.example.binarybashers_quadline.panes;

// Jarrod Ferriss
// June 21, 2024

import com.example.binarybashers_quadline.MainGame;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class CreditsPane extends BorderPane {

    public CreditsPane() {
        super();

        // Set the background color of the pane to black
        BackgroundFill bg = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bgColor = new Background(bg);
        this.setBackground(bgColor);

        // Create and configure text elements for the credits
        Text txtQuadLine = new Text("QuadLine");
        txtQuadLine.setTextAlignment(TextAlignment.CENTER);
        txtQuadLine.setFont(Font.font("Impact", 60));
        txtQuadLine.setFill(Color.WHITE);


        Text txtCreditsIntro = new Text("Powered By:");
        txtCreditsIntro.setTextAlignment(TextAlignment.CENTER);
        txtCreditsIntro.setFont(Font.font("Impact", 40));
        txtCreditsIntro.setFill(Color.WHITE);


        Text txtMohamed = new Text("Mohamed Muslemani");
        txtMohamed.setTextAlignment(TextAlignment.CENTER);
        txtMohamed.setFont(Font.font("Impact", 30));
        txtMohamed.setFill(Color.WHITE);


        Text txtBashar = new Text("Bashar Herz");
        txtBashar.setTextAlignment(TextAlignment.CENTER);
        txtBashar.setFont(Font.font("Impact", 30));
        txtBashar.setFill(Color.WHITE);


        Text txtJarrod = new Text("Jarrod Ferriss");
        txtJarrod.setTextAlignment(TextAlignment.CENTER);
        txtJarrod.setFont(Font.font("Impact", 30));
        txtJarrod.setFill(Color.WHITE);


        Text txtThanks = new Text("Thanks for playing!");
        txtThanks.setTextAlignment(TextAlignment.CENTER);
        txtThanks.setFont(Font.font("Impact", 40));
        txtThanks.setFill(Color.WHITE);


        Text txtImgSrc = new Text("Image Source: https://pixabay.com/illustrations/connect-4-classic-game-fun-play-3532328/");
        txtImgSrc.setTextAlignment(TextAlignment.CENTER);
        txtImgSrc.setFont(Font.font("Impact", 20));
        txtImgSrc.setFill(Color.WHITE);

        Text textHoverSrc = new Text("Hover Button Sound: https://pixabay.com/sound-effects/menu-selection-102220/");
        textHoverSrc.setTextAlignment(TextAlignment.CENTER);
        textHoverSrc.setFont(Font.font("Impact", 20));
        textHoverSrc.setFill(Color.WHITE);

        Text txtSelectSrc = new Text("Select Button Sound: https://pixabay.com/sound-effects/button-124476/");
        txtSelectSrc.setTextAlignment(TextAlignment.CENTER);
        txtSelectSrc.setFont(Font.font("Impact", 20));
        txtSelectSrc.setFill(Color.WHITE);

        Text txtFallingSrc = new Text("Falling Piece Sound: https://pixabay.com/sound-effects/search/poker%20chips/");
        txtFallingSrc.setTextAlignment(TextAlignment.CENTER);
        txtFallingSrc.setFont(Font.font("Impact", 20));
        txtFallingSrc.setFill(Color.WHITE);

        Text txtWinSrc = new Text("Winning Sound: https://pixabay.com/sound-effects/search/winner/");
        txtWinSrc.setTextAlignment(TextAlignment.CENTER);
        txtWinSrc.setFont(Font.font("Impact", 20));
        txtWinSrc.setFill(Color.WHITE);

        Text txtBackgroundSrc = new Text("Background Music: https://pixabay.com/music/beautiful-plays-reflected-light-147979/");
        txtBackgroundSrc.setTextAlignment(TextAlignment.CENTER);
        txtBackgroundSrc.setFont(Font.font("Impact", 20));
        txtBackgroundSrc.setFill(Color.WHITE);





        // Add all text elements to the pane
        VBox tpVbox = new VBox(10);
        tpVbox.getChildren().addAll(txtThanks, txtQuadLine);
        this.setCenter(tpVbox);
        tpVbox.setAlignment(Pos.CENTER);

        VBox pdVbox = new VBox(10);
        pdVbox.getChildren().addAll(txtCreditsIntro, txtMohamed);
        this.setTop(pdVbox);
        pdVbox.setAlignment(Pos.CENTER);

        this.setLeft(txtBashar);
        this.setRight(txtJarrod);

        VBox citeVbox = new VBox(10);
        citeVbox.getChildren().addAll(txtImgSrc, txtFallingSrc, textHoverSrc, txtWinSrc, txtSelectSrc, txtBackgroundSrc);
        this.setBottom(citeVbox);
        citeVbox.setAlignment(Pos.CENTER);

        // Create and configure animations for each text element
        FadeTransition fadeThanks = new FadeTransition(Duration.millis(3000), tpVbox);
        fadeThanks.setFromValue(1);
        fadeThanks.setToValue(0);
        fadeThanks.setCycleCount(1);


        FadeTransition fadePowered = new FadeTransition(Duration.millis(1000), pdVbox);
        fadePowered.setFromValue(0);
        fadePowered.setToValue(1);
        fadePowered.setCycleCount(1);

        FadeTransition fadePowered2 = new FadeTransition(Duration.millis(1000), pdVbox);
        fadePowered2.setFromValue(1);
        fadePowered2.setToValue(0);
        fadePowered2.setCycleCount(1);

        FadeTransition fadeBashar = new FadeTransition(Duration.millis(1000), txtBashar);
        fadeBashar.setFromValue(0);
        fadeBashar.setToValue(1);
        fadeBashar.setCycleCount(1);

        FadeTransition fadeBashar2 = new FadeTransition(Duration.millis(1000), txtBashar);
        fadeBashar2.setFromValue(1);
        fadeBashar2.setToValue(0);
        fadeBashar2.setCycleCount(1);

        FadeTransition fadeJarrod = new FadeTransition(Duration.millis(1000), txtJarrod);
        fadeJarrod.setFromValue(0);
        fadeJarrod.setToValue(1);
        fadeJarrod.setCycleCount(1);

        FadeTransition fadeJarrod2 = new FadeTransition(Duration.millis(1000), txtJarrod);
        fadeJarrod2.setFromValue(1);
        fadeJarrod2.setToValue(0);
        fadeJarrod2.setCycleCount(1);

        FadeTransition fadeCite = new FadeTransition(Duration.millis(3000), citeVbox);
        fadeCite.setFromValue(0);
        fadeCite.setToValue(1);
        fadeCite.setCycleCount(1);

        TranslateTransition translateCite = new TranslateTransition(Duration.millis(3000), citeVbox);
        translateCite.setByY(-1024);

        // Sequence transition to play animations one after another
        SequentialTransition sequence = new SequentialTransition();
        sequence.getChildren().addAll(fadeThanks, fadePowered, fadeBashar, fadeJarrod, fadePowered2, fadeBashar2, fadeJarrod2, fadeCite, translateCite);
        sequence.play();

        sequence.setOnFinished(e -> {
            MainGame.mainStage.close();
        });

    }
}
