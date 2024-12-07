// WinPane.java
package com.example.binarybashers_quadline.panes;

import com.example.binarybashers_quadline.MainGame;
import com.example.binarybashers_quadline.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WinPane extends BorderPane {
    private final AudioClip hoverSound;
    private final AudioClip clickSound;

    Text winText = new Text();

    public WinPane() {
        super();

        // Load the hover and click sounds
        hoverSound = new AudioClip(getClass().getResource("/menu_hover.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/menu-selection-102220/
        clickSound = new AudioClip(getClass().getResource("/menu_select.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/button-124476/

        VBox btVBox = new VBox(10);
        HBox btHBox = new HBox(10);
        HBox textHBox = new HBox();

        Button playAgainBt = new Button("Play Again");
        Button mainMenuBt = new Button("Main Menu");
        Button quitButton = new Button("Credits");

        BackgroundFill bgColor = new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgColor);

        Background titleBackgrond = new Background(new BackgroundFill(Color.rgb(29, 113, 186), new CornerRadii(20), null));

        Background buttonBackgrond = new Background(new BackgroundFill(Color.GRAY, new CornerRadii(20), null));
        Border buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), null));

        playAgainBt.setFont(Settings.mainFont);
        playAgainBt.setMinWidth(250);
        playAgainBt.setBackground(buttonBackgrond);
        playAgainBt.setBorder(buttonBorder);
        addHoverEffect(playAgainBt);
        addClickEffect(playAgainBt);

        mainMenuBt.setFont(Settings.mainFont);
        mainMenuBt.setMinWidth(125);
        mainMenuBt.setBackground(buttonBackgrond);
        mainMenuBt.setBorder(buttonBorder);
        addHoverEffect(mainMenuBt);
        addClickEffect(mainMenuBt);

        quitButton.setFont(Settings.mainFont);
        quitButton.setMinWidth(125);
        quitButton.setBackground(buttonBackgrond);
        quitButton.setBorder(buttonBorder);
        addHoverEffect(quitButton);
        addClickEffect(quitButton);

        btHBox.getChildren().addAll(mainMenuBt, quitButton);
        btVBox.getChildren().addAll(playAgainBt, btHBox);
        textHBox.getChildren().addAll(winText);

        if (!Settings.player1Turn && Settings.turnCount < 42) {
            winText.setText("Player 1 wins!");
            winText.setFill(Settings.player1Color);
        } else if (Settings.player1Turn && Settings.turnCount < 42) {
            winText.setText("Player 2 wins!");
            winText.setFill(Settings.player2Color);
        } else {
            winText.setText("It's a draw!");
            winText.setFill(Color.BLACK);
        }
        winText.setFont(Settings.mainFont);
        textHBox.setAlignment(Pos.CENTER);
        textHBox.setPadding(new Insets(10, 10, 10, 10));
        textHBox.setBackground(titleBackgrond);
        btVBox.setAlignment(Pos.CENTER);
        btHBox.setAlignment(Pos.CENTER);

        this.setTop(textHBox);
        this.setCenter(btVBox);
        this.setBackground(bg);

        playAgainBt.setOnAction(e -> {
            playClickSound();
            Settings.player1Turn = true;
            Settings.turnCount = 1;
            Settings.isGameOver = false;
            Settings.switchToGame();
            MainGame.secondStage.close();
        });
        mainMenuBt.setOnAction(e -> {
            playClickSound();
            Settings.isGameOver = false;
            Settings.switchToIntro();
            MainGame.secondStage.close();
        });
        quitButton.setOnAction(e -> {
            playClickSound();
            Settings.switchToCredits();
            MainGame.secondStage.close();
        });
    }

    private void addHoverEffect(Button button) {
        Background hoverBackground = new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(20), null));
        button.setOnMouseEntered(e -> {
            button.setBackground(hoverBackground);
            if (!Settings.isMuteSoundEffects()) {
                hoverSound.play();
            }
        });
        button.setOnMouseExited(e -> button.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(20), null))));
    }

    private void addClickEffect(Button button) {
        button.setOnAction(e -> {
            if (!Settings.isMuteSoundEffects()) {
                clickSound.play();
            }
        });
    }

    private void playClickSound() {
        if (!Settings.isMuteSoundEffects()) {
            clickSound.play();
        }
    }
}
