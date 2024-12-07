package com.example.binarybashers_quadline.panes;

import com.example.binarybashers_quadline.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SettingsPane extends BorderPane {
    private final AudioClip hoverSound;
    private final AudioClip clickSound;

    public SettingsPane() {
        super();

        // Load the hover and click sounds
        hoverSound = new AudioClip(getClass().getResource("/menu_hover.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/menu-selection-102220/
        clickSound = new AudioClip(getClass().getResource("/menu_select.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/button-124476/

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        CheckBox muteAudioCheckBox = new CheckBox("Mute Audio");
        muteAudioCheckBox.setFont(Settings.mainFont);
        muteAudioCheckBox.setTextFill(Color.WHITE);
        muteAudioCheckBox.setSelected(Settings.isMuteAudio());
        muteAudioCheckBox.setOnAction(e -> {
            Settings.setMuteAudio(muteAudioCheckBox.isSelected());
        });

        CheckBox muteSoundEffectsCheckBox = new CheckBox("Mute Sound Effects");
        muteSoundEffectsCheckBox.setFont(Settings.mainFont);
        muteSoundEffectsCheckBox.setTextFill(Color.WHITE);
        muteSoundEffectsCheckBox.setSelected(Settings.isMuteSoundEffects());
        muteSoundEffectsCheckBox.setOnAction(e -> Settings.setMuteSoundEffects(muteSoundEffectsCheckBox.isSelected()));

        Text player1ColorText = new Text("Player 1 Color");
        player1ColorText.setFont(Settings.mainFont);
        player1ColorText.setFill(Color.WHITE);

        ColorPicker player1ColorPicker = new ColorPicker(Settings.getPlayer1Color());
        player1ColorPicker.setStyle("-fx-font: 16px 'Arial';");
        player1ColorPicker.setOnAction(e -> {
            Settings.setPlayer1Color(player1ColorPicker.getValue());
            Settings.saveSettings();
        });

        Text player2ColorText = new Text("Player 2 Color");
        player2ColorText.setFont(Settings.mainFont);
        player2ColorText.setFill(Color.WHITE);

        ColorPicker player2ColorPicker = new ColorPicker(Settings.getPlayer2Color());
        player2ColorPicker.setStyle("-fx-font: 16px 'Arial';");
        player2ColorPicker.setOnAction(e -> {
            Settings.setPlayer2Color(player2ColorPicker.getValue());
            Settings.saveSettings();
        });

        // Add volume slider for background music
        Text musicVolumeText = new Text("Music Volume");
        musicVolumeText.setFont(Settings.mainFont);
        musicVolumeText.setFill(Color.WHITE);

        Slider musicVolumeSlider = new Slider(0, 1, Settings.getBackgroundMusicVolume());
        musicVolumeSlider.setShowTickLabels(true);
        musicVolumeSlider.setShowTickMarks(true);
        musicVolumeSlider.setMajorTickUnit(0.1);
        musicVolumeSlider.setMinorTickCount(1);
        musicVolumeSlider.setBlockIncrement(0.1);
        musicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Settings.setBackgroundMusicVolume(newValue.doubleValue());
        });

        Button backButton = new Button("Back");
        backButton.setFont(Settings.mainFont);
        backButton.setMinWidth(200);
        backButton.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(20), null)));
        backButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), null)));
        addHoverEffect(backButton);
        addClickEffect(backButton, e -> Settings.switchToPrevious());

        layout.getChildren().addAll(
                muteAudioCheckBox,
                muteSoundEffectsCheckBox
        );
        if(Settings.showColorSettings) {
            layout.getChildren().addAll(
                    player1ColorText,
                    player1ColorPicker,
                    player2ColorText,
                    player2ColorPicker
            );
        }

        layout.getChildren().addAll(
                musicVolumeText,
                musicVolumeSlider,
                backButton
        );

        this.setCenter(layout);

        // Apply background color
        BackgroundFill bgColor = new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgColor);
        this.setBackground(bg);
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

    private void addClickEffect(Button button, EventHandler<ActionEvent> action) {
        button.setOnAction(e -> {
            if (!Settings.isMuteSoundEffects()) {
                clickSound.play();
            }
            action.handle(e);
        });
    }
}
