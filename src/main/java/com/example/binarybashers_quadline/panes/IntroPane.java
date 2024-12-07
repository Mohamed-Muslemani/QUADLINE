// IntroPane.java
package com.example.binarybashers_quadline.panes;

import com.example.binarybashers_quadline.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;

public class IntroPane extends BorderPane {
    private final AudioClip hoverSound;
    private final AudioClip clickSound;

    public IntroPane() {
        super();

        // Load the hover and click sounds
        hoverSound = new AudioClip(getClass().getResource("/menu_hover.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/menu-selection-102220/
        clickSound = new AudioClip(getClass().getResource("/menu_select.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/button-124476/

        // Creating a Text node for the project name
        Text projectName = new Text("QuadLine");
        Text name = new Text("Binary Bashers");

        Text ruleTitle = new Text("Rules:");
        Text rule1 = new Text("1.\tClick on a piece in the row you would like to place your piece.");
        Text rule2 = new Text("2.\tYour piece will fall to the bottom of the row and it will be player2's turn.");
        Text rule3 = new Text("3.\tTry to get 4 pieces in a row upward, horizontally, or diagonally before your opponent.");
        Text rule4 = new Text("4.\tIf the board gets filled it's a draw");

        BackgroundFill bgColor = new BackgroundFill(Color.CORAL, new CornerRadii(20), Insets.EMPTY);
        Background bg = new Background(bgColor);

        ruleTitle.setFont(Settings.mainFont);
        rule1.setFont(Settings.secondaryFont);
        rule2.setFont(Settings.secondaryFont);
        rule3.setFont(Settings.secondaryFont);
        rule4.setFont(Settings.secondaryFont);

        projectName.setFont(Font.font("Impact", 50));
        projectName.setFill(Color.BLACK);
        name.setFont(Font.font("Impact", 30));
        name.setFill(Color.LIGHTGRAY);

        // Creating a VBox
        VBox vBox = new VBox(20);
        VBox ruleVBox = new VBox(10);
        // creating Hbox
        HBox buttonHbox = new HBox(10);
        // Creating the buttons
        Button button1 = new Button("Start");
        Button button2 = new Button("Settings");
        Button button3 = new Button("Load");
        Button button4 = new Button("Credits");
        buttonHbox.getChildren().addAll(button3, button4);
        // putting the buttons in the vbox
        vBox.getChildren().addAll(button1, button2, buttonHbox, ruleVBox);

        BorderPane.setMargin(vBox, new Insets(0, 250, 0, 250));

        buttonHbox.setAlignment(Pos.CENTER);

        // positioning the buttons
        this.setBottom(vBox);
        vBox.setAlignment(Pos.CENTER);

        ruleVBox.getChildren().addAll(ruleTitle, rule1, rule2, rule3, rule4);

        // vbox fo the projectname and the name
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(projectName, name);

        this.setLeft(vBox1);
        BorderPane.setMargin(vBox1, new Insets(200, 10, 10, 110));
        name.setTranslateX(50);

        //set the Background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/introImage.jpg"));

        BackgroundImage backgroundImage1 = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1024, 768, false, false, false, false));
        Background background = new Background(backgroundImage1);

        // fonts
        Font font = Font.font("Impact", 25);
        Color color = new Color(0, 0, 0, 0);

        ruleVBox.setBackground(bg);
        ruleVBox.setPadding(new Insets(10, 10, 10, 10));

        button1.setFont(font);
        Background buttonBackgrond = new Background(new BackgroundFill(Color.GRAY, new CornerRadii(20), null));
        Border buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), null));
        button1.setMinWidth(250);
        button1.setBackground(buttonBackgrond);
        button1.setBorder(buttonBorder);
        addHoverEffect(button1);

        button2.setFont(font);
        button2.setMinWidth(300);
        button2.setBackground(buttonBackgrond);
        button2.setBorder(buttonBorder);
        addHoverEffect(button2);

        button3.setFont(font);
        button3.setMinWidth(200);
        button3.setBackground(buttonBackgrond);
        button3.setBorder(buttonBorder);

        button4.setFont(font);
        button4.setMinWidth(200);
        button4.setBackground(buttonBackgrond);
        button4.setBorder(buttonBorder);

        this.setBackground(background);
        //Animation FAde
        FadeTransition fade = new FadeTransition(Duration.millis(2000), projectName);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        FadeTransition fadeName = new FadeTransition(Duration.millis(2000), name);
        fadeName.setFromValue(0);
        fadeName.setToValue(1);
        fadeName.play();

        FadeTransition fadeName1 = new FadeTransition(Duration.millis(2000), vBox);
        fadeName1.setFromValue(0);
        fadeName1.setToValue(1);
        fadeName1.play();

        // ANimation
        ScaleTransition scale = new ScaleTransition(Duration.millis(5000), projectName);
        scale.setByX(0.5);
        scale.setByY(0.5);
        scale.setCycleCount(-1);
        scale.play();

        // The hover
        addHoverEffect(button1);
        addHoverEffect(button2);
        addHoverEffect(button3);
        addHoverEffect(button4);

        button1.setOnAction(e -> {
            playClickSound();
            Settings.player1Turn = true;
            Settings.turnCount = 1;
            Settings.switchToGame();
        });

        button4.setOnAction(e -> {
            playClickSound();
            Settings.switchToCredits();
        });

        button3.setOnAction(e -> {
            playClickSound();
            Settings.loadGame = true;
            Settings.switchToGame();
        });

        button2.setOnAction(e -> {
            Settings.showColorSettings = true;
            playClickSound();
            Settings.switchToSettings();
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

    private void playClickSound() {
        if (!Settings.isMuteSoundEffects()) {
            clickSound.play();
        }
    }
}
