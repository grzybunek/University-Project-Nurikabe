package controller;

import command.ChangeColorCommand;
import command.CommandRegistry;
import functionality.BoardValidator;
import functionality.ScoreHandler;
import functionality.Timer;
import game.Game;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Board;
import model.Cell;
import model.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NurikabeBoardController {

    private Game game;
    private CommandRegistry commandRegistry;

    @FXML
    private Label time;

    @FXML
    private CheckBox colorWhite;

    @FXML
    private CheckBox colorBlack;

    @FXML
    private CheckBox colorBlue;

    @FXML
    private CheckBox colorGreen;

    @FXML
    private CheckBox colorRed;

    @FXML
    private CheckBox colorAqua;

    @FXML
    private CheckBox colorPink;

    @FXML
    private CheckBox colorGreenyellow;

    @FXML
    private Button checkButton;

    @FXML
    private Label scoresText;

    private Color actualColor = Color.NONE;

    private Color previousColor;

    private HashMap<CheckBox, Color> checkBoxes = new HashMap<>();

    private ArrayList<BoardButton> buttons = new ArrayList<>();

    private static final int NUM_BUTTON_LINES = 10;
    private static final int BUTTONS_PER_LINE = 10;
    private double BUTTON_PADDING = 0;

    private Timer timer;

    @FXML
    GridPane buttonGrid;

    public void setGame(Game game) {
        System.out.println("Game set");
        this.game = game;
    }

    public void initialize() {

        buttonGrid.setPadding(new Insets(BUTTON_PADDING));
        buttonGrid.setHgap(BUTTON_PADDING);
        buttonGrid.setVgap(BUTTON_PADDING);

        checkBoxes.put(colorBlack, Color.BLACK);
        checkBoxes.put(colorBlue, Color.BLUE);
        checkBoxes.put(colorGreen, Color.GREEN);
        checkBoxes.put(colorRed, Color.RED);
        checkBoxes.put(colorWhite, Color.WHITE);
        checkBoxes.put(colorAqua, Color.AQUA);
        checkBoxes.put(colorPink, Color.PINK);
        checkBoxes.put(colorGreenyellow, Color.GREENYELLOW);

        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {

                BoardButton button = new BoardButton("   ", r, c);

                button.setOnAction(event -> {
                    BoardButton clickedButton = (BoardButton) event.getSource();
                    int row = clickedButton.getPositionRow();
                    int column = clickedButton.getPositionColumn();
                    previousColor = game.getUserBoard().getCellColor(row, column);
                    if (actualColor != Color.NONE && actualColor != previousColor && clickedButton.getText().equalsIgnoreCase("  ")) {
                        ChangeColorCommand changeColorCommand = new ChangeColorCommand(row, column, actualColor, previousColor, game);
                        commandRegistry.executeCommand(changeColorCommand);
                    }
                });
                buttonGrid.add(button, c, r);
                buttons.add(button);
            }
        }

        checkButton.setOnMousePressed((event) -> highlightIllegal());
        checkButton.setOnMouseReleased((event) -> unhighlightIllegal());

        timer = new Timer(this.time);
    }

    private void highlightIllegal() {
        BoardValidator validator = new BoardValidator(game.getExpectedBoard(), game.getUserBoard());
        if (validator.isSolved()) {
            game.markAsSolved();
            return;
        }

        Cell[][] diff = validator.getDiff();
        for (int y = 0; y < NUM_BUTTON_LINES; y++) {
            for (int x = 0; x < BUTTONS_PER_LINE; x++) {
                if (diff[y][x] != null) {
                    Button button = buttons.get(x + y * NUM_BUTTON_LINES);
                    if(game.getUserBoard().getCell(y, x).getColor()!=Color.SILVER)button.setStyle("-fx-background-color: red");
                }
            }
        }
    }

    private void unhighlightIllegal() {
        if (game.isSolved()) {
            long result = timer.stop();
            try {
                Stage endgamePopupStage = new Stage();
                endgamePopupStage.setTitle("Congratulations!");
                EndgamePopupController endgamePopupController = new EndgamePopupController(result, game.getBoardName());


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/EndgamePopupView.fxml"));
                fxmlLoader.setController(endgamePopupController);

                Scene endgamePopupScene = new Scene(fxmlLoader.load(), 400, 250);
                endgamePopupStage.setScene(endgamePopupScene);
                endgamePopupStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return;
        }

        for (int y = 0; y < NUM_BUTTON_LINES; y++) {
            for (int x = 0; x < BUTTONS_PER_LINE; x++) {
                Button button = buttons.get(x + y * NUM_BUTTON_LINES);

                Color color = game.getUserBoard().getCell(y, x).getColor();
                if(color.equals(Color.PINK) || color.equals(Color.AQUA) || color.equals(Color.GREENYELLOW)){
                    button.setStyle("-fx-background-color:" + color + ";" + "-fx-background-image:" + "url(palma.png)");
                }
                else{
                    button.setStyle("-fx-background-color:" + color);
                }
            }
        }
    }

    public void populateBoard() {
        Board userBoard = game.getUserBoard();

        buttons.forEach(button -> {
            Cell cell = userBoard.getCell(button.getPositionRow(), button.getPositionColumn());
            button.setText(cell.getIslandNumber());
            button.setStyle("-fx-background-color:" + cell.getColor());
            cell.addObserver(button);
        });
    }

    public void setDefaultColor(Color defaultColor){
        actualColor = defaultColor;
        for (CheckBox checkBox : checkBoxes.keySet()) {
            if (defaultColor == checkBoxes.get(checkBox)) {
                checkBox.setSelected(true);
                break;
            }
        }
    }


    @FXML
    public void handleColor(ActionEvent event) {
        for (CheckBox checkBox : checkBoxes.keySet()) {
            Color color = checkBoxes.get(checkBox);

            if (checkBox.isSelected() && actualColor != color) {
                actualColor = color;

                for (CheckBox checkBoxToDisable : checkBoxes.keySet()) {
                    if (checkBox != checkBoxToDisable) checkBoxToDisable.setSelected(false);
                }
                return;
            }
        }

        actualColor = Color.NONE;
    }

    @FXML
    void scoresSelected(Event event) {
        Tab source = (Tab) event.getSource();
        if (source.isSelected()){
            ScoreHandler scoreHandler = new ScoreHandler();
            scoresText.setText(scoreHandler.readScores());
        }
    }

    public void handleUndo(ActionEvent event) {
        commandRegistry.undo();
    }

    public void handleRedo(ActionEvent event) {
        commandRegistry.redo();
    }

    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

}

