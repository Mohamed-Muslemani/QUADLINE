package com.example.binarybashers_quadline;

import com.example.binarybashers_quadline.panes.GamePane;
import com.example.binarybashers_quadline.scenes.CreditsScene;
import com.example.binarybashers_quadline.scenes.GameScene;
import com.example.binarybashers_quadline.scenes.IntroScene;
import com.example.binarybashers_quadline.scenes.SettingsScene;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.util.Properties;

public class Settings {
    // Constants for screen dimensions and file paths
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;
    public static final File SAVE_FILE = new File("save.txt");
    public static final File CONFIG_FILE = new File("config.properties");

    // Fonts used in the game
    public static final Font mainFont = Font.font("Impact", FontWeight.NORMAL, FontPosture.REGULAR, 20);
    public static final Font secondaryFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15);

    // Game settings
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static int turnCount = 1;
    public static boolean player1Turn = true;
    public static boolean loadGame = false;
    public static boolean isPieceTranslating = false;
    public static boolean isGameOver = false;
    public static boolean showColorSettings = false;

    // Colors for players and board
    public static Color player1Color = Color.rgb(178, 86, 144);
    public static Color player2Color = Color.rgb(113, 179, 121);
    public static Color defaultPieceColor = Color.WHITE;
    public static Color defaultStrokeColor = Color.BLACK;
    public static Color lastPlacedStrokeColor = Color.GOLD;
    public static Color boardColor = Color.rgb(237, 196, 0);
    private static Color gamePaneColor = Color.WHITE;

    // Audio settings
    private static boolean muteAudio = false;
    private static boolean muteSoundEffects = false;
    private static double backgroundMusicVolume = 0.5;

    // To track the previous scene
    private static Scene previousScene;

    // Static block to load settings on startup
    static {
        loadSettings();
    }

    // Getters and setters for the settings with necessary actions
    public static boolean isMuteAudio() {
        return muteAudio;
    }

    public static void setMuteAudio(boolean muteAudio) {
        Settings.muteAudio = muteAudio;
        updateBackgroundMusicVolume();
        saveSettings();
    }

    public static boolean isMuteSoundEffects() {
        return muteSoundEffects;
    }

    public static void setMuteSoundEffects(boolean muteSoundEffects) {
        Settings.muteSoundEffects = muteSoundEffects;
        saveSettings();
    }

    public static double getBackgroundMusicVolume() {
        return backgroundMusicVolume;
    }

    public static void setBackgroundMusicVolume(double volume) {
        backgroundMusicVolume = volume;
        updateBackgroundMusicVolume();
        saveSettings();
    }

    private static void updateBackgroundMusicVolume() {
        double volume = muteAudio ? 0 : backgroundMusicVolume;
        if (GamePane.getBackgroundMusicPlayer() != null) {
            GamePane.getBackgroundMusicPlayer().setVolume(volume);
        }
    }

    public static Color getGamePaneColor() {
        return gamePaneColor;
    }

    public static void setGamePaneColor(Color gamePaneColor) {
        Settings.gamePaneColor = gamePaneColor;
    }

    public static Color getPlayer1Color() {
        return player1Color;
    }

    public static void setPlayer1Color(Color color) {
        player1Color = color;
        saveSettings();
    }

    public static Color getPlayer2Color() {
        return player2Color;
    }

    public static void setPlayer2Color(Color color) {
        player2Color = color;
        saveSettings();
    }

    // Methods to switch between scenes
    public static void switchToCredits() {
        previousScene = MainGame.mainStage.getScene();
        MainGame.mainStage.setScene(new CreditsScene());
    }

    public static void switchToGame() {
        previousScene = MainGame.mainStage.getScene();
        MainGame.mainStage.setScene(new GameScene());
    }

    public static void switchToIntro() {
        previousScene = MainGame.mainStage.getScene();
        MainGame.mainStage.setScene(new IntroScene());
    }

    public static void switchToSettings() {
        previousScene = MainGame.mainStage.getScene();
        MainGame.mainStage.setScene(new SettingsScene());
    }

    public static void switchToPrevious() {
        if (previousScene != null) {
            MainGame.mainStage.setScene(previousScene);
        }
    }

    // Methods to save and load settings from a properties file
    public static void saveSettings() {
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE)) {
            Properties properties = new Properties();
            properties.setProperty("player1Color", colorToString(player1Color));
            properties.setProperty("player2Color", colorToString(player2Color));
            properties.setProperty("muteAudio", Boolean.toString(muteAudio));
            properties.setProperty("muteSoundEffects", Boolean.toString(muteSoundEffects));
            properties.setProperty("backgroundMusicVolume", Double.toString(backgroundMusicVolume));
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSettings() {
        if (CONFIG_FILE.exists()) {
            try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
                Properties properties = new Properties();
                properties.load(input);
                player1Color = stringToColor(properties.getProperty("player1Color", colorToString(player1Color)));
                player2Color = stringToColor(properties.getProperty("player2Color", colorToString(player2Color)));
                muteAudio = Boolean.parseBoolean(properties.getProperty("muteAudio", "false"));
                muteSoundEffects = Boolean.parseBoolean(properties.getProperty("muteSoundEffects", "false"));
                backgroundMusicVolume = Double.parseDouble(properties.getProperty("backgroundMusicVolume", "0.5"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper methods to convert color to string and vice versa
    private static String colorToString(Color color) {
        return color.toString();
    }

    private static Color stringToColor(String colorString) {
        return Color.valueOf(colorString);
    }
}
