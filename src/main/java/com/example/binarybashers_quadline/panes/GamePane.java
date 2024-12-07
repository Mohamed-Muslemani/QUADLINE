package com.example.binarybashers_quadline.panes;

import com.example.binarybashers_quadline.MainGame;
import com.example.binarybashers_quadline.Settings;
import com.example.binarybashers_quadline.scenes.WinScene;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;

public class GamePane extends HBox {
    private final AudioClip hoverSound;
    private final AudioClip clickSound;
    private final AudioClip fallingPieceSound;
    private final AudioClip winSound;
    private static MediaPlayer backgroundMusicPlayer;

    private VBox leftVBox;
    private VBox quadLine;
    private VBox players;
    private VBox buttons;
    private StackPane pieceAboveGrid = new StackPane();
    private Rectangle player1TurnRect = new Rectangle(10, 10);
    private Rectangle player2TurnRect = new Rectangle(10, 10);
    private HBox player1;
    private HBox player2;
    private HBox turnNum;
    private GridPane rightGridPane;
    private GridPane topGridPane;

    private PiecePane[][] board = new PiecePane[Settings.ROWS][Settings.COLUMNS];
    private Color LeftVBoxColor;
    private PiecePane pieceAboveBoard = new PiecePane(Settings.player1Color); // Piece above board
    private Text turnCountText = new Text(String.valueOf(Settings.turnCount));

    public GamePane() {
        super();

        // Load the hover and click sounds
        hoverSound = new AudioClip(getClass().getResource("/menu_hover.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/menu-selection-102220/
        clickSound = new AudioClip(getClass().getResource("/menu_select.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/button-124476/
        fallingPieceSound = new AudioClip(getClass().getResource("/falling_piece.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/search/poker%20chips/
        winSound = new AudioClip(getClass().getResource("/win_effect.mp3").toExternalForm()); //Free-to-use https://pixabay.com/sound-effects/search/winner/

        // Load the background music
        URL backgroundMusicURL = getClass().getResource("/background_music.mp3");
        if (backgroundMusicURL != null) {
            Media backgroundMusic = new Media(backgroundMusicURL.toExternalForm());
            backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
            backgroundMusicPlayer.setVolume(Settings.getBackgroundMusicVolume());
            playBackgroundMusic();
        } else {
            System.err.println("Background music file not found!");
        }

        LeftVBoxColor = Color.CORAL;

        // Background for the root Hbox
        BackgroundFill bgColor = new BackgroundFill(Color.rgb(29, 113, 186), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgColor);

        // Background for the sideBar
        BackgroundFill bgcLeftVBox = new BackgroundFill(LeftVBoxColor, CornerRadii.EMPTY, Insets.EMPTY);
        Background bgcLVB = new Background(bgcLeftVBox);

        // Background for the board
        BackgroundFill boardColor = new BackgroundFill(Settings.boardColor, CornerRadii.EMPTY, Insets.EMPTY);
        Background bgBoard = new Background(boardColor);

        this.setBackground(bg); // Set background for root Hbox

        leftVBox = new VBox(200); // Spacing of 10
        leftVBox.setBackground(bgcLVB); // Set background for sidebar

        quadLine = new VBox(10);
        quadLine.setAlignment(Pos.CENTER); // Position game name center of its vbox
        quadLine.setTranslateY(50);

        players = new VBox(10); // Spacing of 10
        player1 = new HBox(10);
        player1.setAlignment(Pos.CENTER);
        player2 = new HBox(10);
        player2.setAlignment(Pos.CENTER);
        players.setAlignment(Pos.CENTER); // Position players text center of their vbox
        turnNum = new HBox(10);
        turnNum.setAlignment(Pos.CENTER);

        buttons = new VBox(10); // Spacing of 10
        buttons.setAlignment(Pos.CENTER); // Position buttons center of their vbox

        rightGridPane = new GridPane();
        rightGridPane.setBackground(bgBoard);

        // Create text for sidebar
        Text txtQuad = new Text("Quad");
        txtQuad.setFont(Settings.mainFont);

        Text txtLine = new Text("Line");
        txtLine.setFont(Settings.mainFont);

        Text txtPlayer1 = new Text("Player #1");
        txtPlayer1.setFont(Settings.mainFont);

        Text txtPlayer2 = new Text("Player #2");
        txtPlayer2.setFont(Settings.mainFont);

        Text txtTurn = new Text("Turn #: ");
        txtTurn.setFont(Settings.mainFont);

        turnCountText.setFont(Settings.mainFont);

        // Create buttons for sidebar
        Button btCredits = new Button("Credits");
        Button btSettings = new Button("Settings");
        Button btSave = new Button("Save");
        Button btQuit = new Button("Main Menu");

        Background buttonBackgrond = new Background(new BackgroundFill(Color.GRAY, new CornerRadii(20), null));
        Border buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), null));

        btCredits.setFont(Settings.mainFont);
        btCredits.setMinWidth(150);
        btCredits.setBackground(buttonBackgrond);
        btCredits.setBorder(buttonBorder);
        addHoverEffect(btCredits);
        addClickEffect(btCredits, e -> {
            playClickSound();
            stopBackgroundMusic();
            Settings.switchToCredits();
        });

        btSettings.setFont(Settings.mainFont);
        btSettings.setMinWidth(150);
        btSettings.setBackground(buttonBackgrond);
        btSettings.setBorder(buttonBorder);
        addHoverEffect(btSettings);
        addClickEffect(btSettings, e -> {
            Settings.showColorSettings = false;
            playClickSound();
            Settings.switchToSettings();
        });

        btSave.setFont(Settings.mainFont);
        btSave.setMinWidth(150);
        btSave.setBackground(buttonBackgrond);
        btSave.setBorder(buttonBorder);
        addHoverEffect(btSave);
        addClickEffect(btSave, e -> {
            playClickSound();
            saveGame();
        });

        btQuit.setFont(Settings.mainFont);
        btQuit.setMinWidth(150);
        btQuit.setBackground(buttonBackgrond);
        btQuit.setBorder(buttonBorder);
        addHoverEffect(btQuit);
        addClickEffect(btQuit, e -> {
            playClickSound();
            stopBackgroundMusic();
            Settings.switchToIntro();
        });

        // Add text to their vbox
        quadLine.getChildren().addAll(txtQuad, txtLine);
        player1.getChildren().addAll(player1TurnRect, txtPlayer1);
        player2.getChildren().addAll(player2TurnRect, txtPlayer2);
        turnNum.getChildren().addAll(txtTurn, turnCountText);
        players.getChildren().addAll(player1, player2, turnNum);
        buttons.getChildren().addAll(btCredits, btSettings, btSave, btQuit);

        // Add all vboxes to sidebar
        leftVBox.getChildren().addAll(quadLine, players, buttons);

        // expanding sidebar to fit screen
        leftVBox.setMinHeight(Settings.SCREEN_HEIGHT);
        leftVBox.setMinWidth(164);

        // adding space between piece
        rightGridPane.setMaxHeight(600);
        rightGridPane.setHgap(10);
        rightGridPane.setVgap(10);

        // fill the grid pane with transparent pieces to change their color
        topGridPane = new GridPane();
        for (int i = 0; i < Settings.COLUMNS - 1; i++) {
            PiecePane piecePane1 = new PiecePane(Color.TRANSPARENT, Color.TRANSPARENT);
            topGridPane.add(piecePane1, i, 0);
        }
        topGridPane.setHgap(10);
        topGridPane.add(pieceAboveBoard, 0, 0);

        // piecePane.setAlignment(Pos.TOP_LEFT); //Start position over first column

        StackPane.setAlignment(rightGridPane, Pos.BOTTOM_CENTER);

        topGridPane.setMouseTransparent(true);
        pieceAboveGrid.getChildren().addAll(rightGridPane, topGridPane); // Add Top piece and board in stackpane
        this.getChildren().addAll(leftVBox, pieceAboveGrid); // Add sidebar and grid to root hbox
        this.setSpacing(40); // add some spacing between them

        setupBoard(); // Call setup board function

        // load the game
        if ((Settings.loadGame) && (board[Settings.ROWS - 1][Settings.COLUMNS - 1] != null)) {
            Settings.loadGame = false;
            loadGame();
            if (!Settings.player1Turn) {
                pieceAboveBoard.setColor(Settings.player2Color);
            } else {
                pieceAboveBoard.setColor(Settings.player1Color);
            }
            turnCountText.setText(String.valueOf(Settings.turnCount));
        }

        // make sure turn square gets initialized with the right color
        if (Settings.player1Turn) {
            player1TurnRect.setFill(Settings.player1Color);
            player2TurnRect.setFill(Color.TRANSPARENT);
        } else {
            player2TurnRect.setFill(Settings.player2Color);
            player1TurnRect.setFill(Color.TRANSPARENT);
        }
    }

    public static MediaPlayer getBackgroundMusicPlayer() {
        return backgroundMusicPlayer;
    }

    private void playBackgroundMusic() {
        if (!Settings.isMuteAudio() && backgroundMusicPlayer != null) {
            backgroundMusicPlayer.play();
        }
    }

    private void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    // Setup the board
    private void setupBoard() {
        // loop through board
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int column = 0; column < Settings.COLUMNS; column++) {
                // fill board with default piece
                PiecePane piece = new PiecePane();
                board[row][column] = piece;

                // No clue why these are needed but the IDE gave as solution
                int finalColumn = column;

                // move top piece when a column is entered
                piece.setOnMouseEntered(e -> {
                    if (!Settings.isPieceTranslating) {
                        topGridPane.getChildren().remove(pieceAboveBoard);
                        topGridPane.add(pieceAboveBoard, finalColumn, 0);
                    }
                });
                // When a piece is clicked run addPiece method
                piece.setOnMouseClicked(e -> {
                    if (!Settings.isGameOver) {
                        addPiece(finalColumn);
                        Settings.turnCount++;
                        turnCountText.setText(String.valueOf(Settings.turnCount));
                    }
                });

                // add the piece to the gridPane
                rightGridPane.add(piece, column, row);
            }
        }
    }

    // Reset game method
    public void resetBoard() {
        // loop through board and set all pieces to default color and make it player ones turn
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int column = 0; column < Settings.COLUMNS; column++) {
                board[row][column].setColor(Settings.defaultPieceColor);
            }
        }
        Settings.player1Turn = true;
        Settings.turnCount = 1;
    }

    // reset the stroke of entire board
    public void resetStroke() {
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int column = 0; column < Settings.COLUMNS; column++) {
                board[row][column].getCircle().setStrokeWidth(1);
                board[row][column].setStroke(Settings.defaultStrokeColor);
            }
        }
    }

    // Add a piece method by taking the column clicked and looking first empty row
    public void addPiece(int column) {
        // rest after every click so only last move highlighted
        resetStroke();
        // Start from the bottom left of board
        for (int row = Settings.ROWS - 1; row >= 0; row--) {

            PiecePane piece = board[row][column]; // grab the current piece

            TranslateTransition transition = new TranslateTransition(Duration.millis(200), pieceAboveBoard);
            transition.setByY(piece.getLayoutY() + 2 * (piece.getRadius()) + 5);
            transition.setOnFinished(e -> {
                Settings.isPieceTranslating = false;
                pieceAboveBoard.setTranslateY(0);
                if (Settings.player1Turn) {
                    pieceAboveBoard.setColor(Settings.player1Color);
                } else {
                    pieceAboveBoard.setColor(Settings.player2Color);
                }
            });
            Settings.isPieceTranslating = true;
            transition.play();

            // if it is default color we change it
            if (piece.getColor().equals(Settings.defaultPieceColor)) {

                // if player1 turn change to their color and switch turns
                if (Settings.player1Turn) {
                    piece.setColor(Settings.player1Color);
                    player2TurnRect.setFill(Settings.player2Color);
                    player1TurnRect.setFill(Color.TRANSPARENT);
                    Settings.player1Turn = !Settings.player1Turn;

                    // highlight last move played by stroke
                    piece.setStroke(Settings.player2Color);
                    piece.getCircle().setStrokeWidth(2);
                    break;
                }
                // if player2 turn change to their color and switch turns
                else {
                    piece.setColor(Settings.player2Color);
                    player1TurnRect.setFill(Settings.player1Color);
                    player2TurnRect.setFill(Color.TRANSPARENT);
                    Settings.player1Turn = !Settings.player1Turn;

                    // highlight last move played by stroke
                    piece.setStroke(Settings.player1Color);
                    piece.getCircle().setStrokeWidth(2);
                    break;
                }
            }
        }
        playFallingPieceSound();
        checkWin();
        if (Settings.turnCount >= 42) {
            MainGame.secondStage.setScene(new WinScene());
            MainGame.secondStage.show();
            Settings.isGameOver = true;
        }
    }

    // Check win method
    public void checkWin() {
        int consecutiveCount = 0;
        Color currentColor;
        Color previousColor;

        // check vertical 4 in a row
        for (int column = 0; column < Settings.COLUMNS; column++) {
            consecutiveCount = 1;
            previousColor = Settings.defaultPieceColor;
            // start in bottom left
            for (int row = Settings.ROWS - 1; row >= 0; row--) {
                PiecePane piece = board[row][column]; // Get current piece

                if (piece.getColor() != Settings.defaultPieceColor) { // Check if it is default color
                    currentColor = piece.getColor(); // set the current color
                    if (currentColor.equals(previousColor)) {// if current color equal to previous one
                        consecutiveCount++; // increase counter
                    } else {
                        consecutiveCount = 1; // else reset counter
                    }
                    previousColor = piece.getColor(); // set the previous color
                } else { // if it is default color reset counter
                    consecutiveCount = 1;
                }

                if (consecutiveCount == 4) {
                    // Perform win action
                    for (int i = 0; i < 4; i++) {
                        board[row + i][column].setColor(piece.getColor().darker());
                    }
                    playWinSound();
                    stopBackgroundMusic(); // Stop the background music when win condition is met
                    MainGame.secondStage.setScene(new WinScene());
                    MainGame.secondStage.show();
                    Settings.isGameOver = true;
                    return; // Exit method
                }
            }
        }

        // Check horizontal 4 in a row
        for (int row = Settings.ROWS - 1; row >= 0; row--) {
            consecutiveCount = 0;
            previousColor = Settings.defaultPieceColor;

            for (int column = 0; column < Settings.COLUMNS; column++) {
                PiecePane piece = board[row][column]; // Get current piece

                if (piece.getColor() != Settings.defaultPieceColor) { // Check if it is default color
                    currentColor = piece.getColor(); // Set the current color

                    if (consecutiveCount == 0) {
                        consecutiveCount++;
                    } else if (currentColor.equals(previousColor)) { // If current color equal to previous one
                        consecutiveCount++; // Increase counter
                    } else {
                        consecutiveCount = 1; // Else reset counter
                    }

                    previousColor = currentColor; // Set the previous color

                } else { // If it is default color reset counter
                    consecutiveCount = 0;
                }

                if (consecutiveCount == 4) {
                    for (int i = 0; i < 4; i++) {
                        board[row][column - i].setColor(piece.getColor().darker());
                    }
                    playWinSound();
                    stopBackgroundMusic(); // Stop the background music when win condition is met
                    MainGame.secondStage.setScene(new WinScene());
                    MainGame.secondStage.show();
                    Settings.isGameOver = true;
                    return; // Exit method
                }
            }
        }

        // Check diagonal (\) 4 in a row
        // Loop through rows from bottom stopping 4 from the top
        for (int row = Settings.ROWS - 1; row >= 3; row--) {
            consecutiveCount = 1;
            // Looping through columns starting from 4th column
            for (int column = 3; column < Settings.COLUMNS; column++) {
                PiecePane piece = board[row][column]; // Get current piece

                if (piece.getColor() != Settings.defaultPieceColor) { // Check if it is default color
                    currentColor = piece.getColor(); // Set the current color

                    for (int i = 1; i < 4; i++) {
                        PiecePane nextPiece = board[row - i][column - i]; // get next piece
                        if (nextPiece.getColor().equals(currentColor)) {
                            // if they same color increase counter
                            consecutiveCount++;
                        } else {// else leave loop
                            consecutiveCount = 1;
                            break;
                        }
                    }

                    if (consecutiveCount == 4) {
                        for (int i = 0; i < 4; i++) {
                            board[row - i][column - i].setColor(Color.BLACK);
                        }
                        playWinSound();
                        stopBackgroundMusic(); // Stop the background music when win condition is met
                        MainGame.secondStage.setScene(new WinScene());
                        MainGame.secondStage.show();
                        Settings.isGameOver = true;
                        return; // Exit method
                    }
                }
            }
        }

        // Check diagonal (/) 4 in a row
        // Loop through rows from bottom stopping 4 from the top
        for (int row = Settings.ROWS - 1; row >= 3; row--) {
            consecutiveCount = 1;
            // Looping through columns stopping on 4th from end
            for (int column = 0; column < (Settings.COLUMNS - 3); column++) {
                PiecePane piece = board[row][column]; // Get current piece

                if (piece.getColor() != Settings.defaultPieceColor) { // Check if it is default color
                    currentColor = piece.getColor(); // Set the current color

                    for (int i = 1; i < 4; i++) {
                        PiecePane nextPiece = board[row - i][column + i]; // get next piece
                        if (nextPiece.getColor().equals(currentColor)) {
                            // if they same color increase counter
                            consecutiveCount++;
                        } else {// else leave loop
                            consecutiveCount = 1;
                            break;
                        }
                    }

                    if (consecutiveCount == 4) {
                        for (int i = 0; i < 4; i++) {
                            board[row - i][column + i].setColor(Color.BLACK);
                        }
                        playWinSound();
                        stopBackgroundMusic(); // Stop the background music when win condition is met
                        MainGame.secondStage.setScene(new WinScene());
                        MainGame.secondStage.show();
                        Settings.isGameOver = true;
                        return; // Exit method
                    }
                }
            }
        }
    }

    // Save game function FILE I/O
    public void saveGame() {
        try {
            // Buffering output since we writing multiple lines.
            BufferedWriter output = new BufferedWriter(new FileWriter(Settings.SAVE_FILE));

            output.write(String.valueOf(Settings.turnCount));
            output.write("\n");
            // loop through board and saving color to file
            for (int row = 0; row < Settings.ROWS; row++) {
                for (int column = 0; column < Settings.COLUMNS; column++) {
                    output.write(String.valueOf(board[row][column].getColor()));
                    output.write("\n");
                }
            }
            // Saving whose turn it is to file
            output.write(String.valueOf(Settings.player1Turn));
            output.flush();
            output.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // Load game method
    public void loadGame() {
        try {
            String line;
            int row = 99999;
            int column = 0;
            BufferedReader in = new BufferedReader(new FileReader(Settings.SAVE_FILE));
            while ((line = in.readLine()) != null) {
                if (row > Settings.ROWS) {
                    Settings.turnCount = Integer.parseInt(line);
                    row = 0;
                } else if (row < Settings.ROWS) {// If the row is less than max rows start reading colors
                    Color color = Color.valueOf(line);

                    if (color.equals(Settings.defaultPieceColor)) {
                        board[row][column].setColor(Settings.defaultPieceColor);
                    } else if (color.equals(Settings.player1Color)) {
                        board[row][column].setColor(Settings.player1Color);
                    } else if (color.equals(Settings.player2Color)) {
                        board[row][column].setColor(Settings.player2Color);
                    }

                    // Keep increasing the column until max columns are reached
                    if (column == (Settings.COLUMNS - 1)) {
                        row++; // increase row
                        column = -1; // reset column to -1
                    }
                    column++;
                } else { // Once board fill load turn variable
                    Settings.player1Turn = Boolean.parseBoolean(line);
                }
            }
            in.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
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

    private void playClickSound() {
        if (!Settings.isMuteSoundEffects()) {
            clickSound.play();
        }
    }

    private void playFallingPieceSound() {
        if (!Settings.isMuteSoundEffects()) {
            fallingPieceSound.play();
        }
    }

    private void playWinSound() {
        if (!Settings.isMuteSoundEffects()) {
            winSound.play();
        }
    }
}
