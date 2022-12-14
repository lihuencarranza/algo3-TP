package org.example.juego;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {

    private final Controller controller;
    private Button[][] buttonsMatrix;
    private final Stage stage;
    private Stage stageRules;
    private Scene scene1;
    private final Stage wonMessageStage;
    private Button smileButton;
    private Button askButton;
    private Button flagsQButton;

    public View(Stage stage,  Controller controller){

        this.controller = controller;
        this.stage = stage;
        stage.setTitle("Minesweeper");
        stage.getIcons().add(new Image("file:src/main/java/org/example/juego/resources/icon.png"));
        stage.setResizable(false);

        scene1 = createGameScene();
        createRulesScene();

        wonMessageStage = createWonMessageStage();

        stage.setScene(scene1);
        stage.show();
    }
    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

    private Stage createWonMessageStage(){
        Stage s = new Stage();
        s.getIcons().add(new Image("file:src/main/java/org/example/juego/resources/icon.png"));
        s.setResizable(false);
        VBox vBox = new VBox();

        BackgroundSize backgroundSize = new BackgroundSize(200,
                100,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("file:src/main/java/org/example/juego/resources/youWon.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        vBox.setBackground(new Background(image));

        Scene wonMessageScene = new Scene(vBox, 200, 90);
        s.setScene(wonMessageScene);

        return s;
    }
    private void createRulesScene(){
        stageRules = new Stage();
        stageRules.getIcons().add(new Image("file:src/main/java/org/example/juego/resources/icon.png"));
        Label label = new Label(controller.getGameRules());
        label.setWrapText(true);
        stageRules.setTitle("How to play Minesweeper");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(label);
        borderPane.setPadding(new Insets(50,20,50,20));


        Scene sceneRules = new Scene(borderPane, 410, 300, Color.GRAY);

        stageRules.setScene(sceneRules);


    }
    private Scene createGameScene() {

        controller.startGame();
        smileButton = new Button();
        askButton = setAskButton();
        flagsQButton = new Button();
        setAskButton();
        setSmileButton();
        setFlagQButton();

        buttonsMatrix = new Button[10][10];
        GridPane grid = new GridPane();
        setGrid(grid);

        HBox hBox = new HBox(askButton, smileButton,flagsQButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(40);
        hBox.setPadding(new Insets(5,0,5,0));

        VBox vBox = new VBox(hBox, grid);
        vBox.setAlignment(Pos.CENTER);
        scene1 = new Scene(vBox);

        return scene1;
    }

    private void setGrid(GridPane grid){
        for (int i = 0; i < 10; i++){
            for (int j=0; j < 10; j++){
                Button button = new Button();
                setButtonInGrid(button, j , i);
                buttonsMatrix[i][j] = button;
                grid.add(button, j, i);
            }
        }

    }
    private void setButtonInGrid(Button button, int row, int col){
        button.setMinSize(35,35);
        button.setCancelButton(true);
        button.setGraphic(boxImage(button, "file:src/main/java/org/example/juego/resources/blank.png"));
        setButtonInGridEvents(button, row, col);
    }
    private void setButtonInGridEvents(Button button, int col, int row){
        button.setOnMouseClicked(event -> {
            switch (event.getButton()){
                case SECONDARY:
                    if(!controller.isFlagged(row,col) && controller.availableFlags > 0){
                        button.setGraphic(boxImage(button,"file:src/main/java/org/example/juego/resources/redFlag" +
                                ".png"));
                        controller.setFlag(row,col);
                        if(controller.availableFlags == 0)
                            checkGame();
                    }else{
                        button.setGraphic(boxImage(button, "file:src/main/java/org/example/juego/resources/blank.png"));
                        controller.removeFlag(row,col);
                    }
                    break;

                case PRIMARY:
                    boolean b = controller.isClickable(row,col);
                    boolean b2 =  controller.hasBomb(row,col);
                    if(controller.isClickable(row,col) && controller.hasBomb(row,col)) {
                        clickAllBombs(row,col);
                        endGame(false);
                    }else if (controller.isClickable(row,col) && controller.getNumber(row,col) == 0){
                        clickEmptyBoxes(row,col);
                    }else if(controller.isClickable(row,col) && !controller.hasBomb(row,col)){
                        clickNumberBox(button,row,col);
                    }
                    break;

                case BACK, FORWARD, MIDDLE, NONE:
                    break;
            }
            setFlagQButton();
        });

    }
    private void checkGame(){
        if(controller.availableFlags == 0 && validateFlagInBomb()){
            endGame(true);
        }
    }
    public void endGame(boolean result){
        if(result){
            smileButton.setGraphic(bigButtonImage(smileButton, "file:src/main/java/org/example/juego/resources/starEyes.png"));
            controller.endGame(true);
            wonMessageStage.show();
        }else {
            controller.endGame(false);
            smileButton.setGraphic(bigButtonImage(smileButton, "file:src/main/java/org/example/juego/resources/cryFace.png"));
        }
    }
    private boolean validateFlagInBomb(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                if (controller.hasBomb(i, j) && !controller.isFlagged(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int legalRow(int row){
        if(row < 0)
            return 0;
        else if(row >= 10)
            return 10-1;
        return row;
    }
    private int legalCol(int col){
        if(col < 0)
            return 0;
        else if(col >= 10)
            return 10-1;
        return col;
    }
    private void clickAllBombs(int row, int col){
        controller.click(row,col);
        Button b;
        for (int i = 0; i < 10; i++){
            for (int j=0; j < 10; j++) {
                b = buttonsMatrix[i][j];
                if (controller.hasBomb(i,j)){
                    b.setGraphic(boxImage(b, "file:src/main/java/org/example/juego/resources/Mine.png"));
                }

            }
        }
        b = buttonsMatrix[row][col];
        b.setGraphic(boxImage(b,"file:src/main/java/org/example/juego/resources/blood.png" ));

    }
    private void clickNumberBox(Button button, int row, int col){
        controller.click(row,col);
        button.setGraphic(numberImage(controller.getNumber(row,col), buttonsMatrix[row][col]));
        button.setDisable(true);
    }
    private void clickEmptyBoxes(int row, int col){
        row = legalRow(row);
        col = legalCol(col);

        if (buttonsMatrix[row][col].isDisabled() || controller.hasBomb(row,col))
            return;

        controller.click(row,col);
        Button b = buttonsMatrix[row][col];
        b.setDisable(true);

        int n = controller.getNumber(row,col);
        b.setGraphic(numberImage(n, buttonsMatrix[row][col]));
        if(n > 0){
            return;
        }

        clickEmptyBoxes(row-1,col-1);
        clickEmptyBoxes(row+1,col-1);
        clickEmptyBoxes(row+1,col+1);
        clickEmptyBoxes(row-1,col+1);
        clickEmptyBoxes(row+1,col);
        clickEmptyBoxes(row-1,col);
        clickEmptyBoxes(row,col-1);
        clickEmptyBoxes(row,col+1);
    }

    private ImageView boxImage(Button b, String string){
        Image image = new Image(string);
        ImageView imgView = new ImageView(image);
        imgView.fitWidthProperty().bind(b.widthProperty());
        imgView.fitHeightProperty().bind(b.heightProperty());
        imgView.setPreserveRatio(true);
        return imgView;
    }
    private ImageView numberImage(int i, Button b){
        Image img = switch (i) {
            case 0 -> new Image("file:src/main/java/org/example/juego/resources/0.png");
            case 1 -> new Image("file:src/main/java/org/example/juego/resources/1.png");
            case 2 -> new Image("file:src/main/java/org/example/juego/resources/2.png");
            case 3 -> new Image("file:src/main/java/org/example/juego/resources/3.png");
            case 4 -> new Image("file:src/main/java/org/example/juego/resources/4.png");
            case 5 -> new Image("file:src/main/java/org/example/juego/resources/5.png");
            case 6 -> new Image("file:src/main/java/org/example/juego/resources/6.png");
            case 7 -> new Image("file:src/main/java/org/example/juego/resources/7.png");
            case 8 -> new Image("file:src/main/java/org/example/juego/resources/8.png");
            default -> null;
        };

        ImageView imgView = new ImageView(img);
        imgView.fitWidthProperty().bind(b.widthProperty());
        imgView.fitHeightProperty().bind(b.heightProperty());
        imgView.setPreserveRatio(true);
        return imgView;
    }
    private ImageView bigButtonImage(Button button, String string){
        Image icon = new Image(string);
        ImageView imgView = new ImageView(icon);
        imgView.fitWidthProperty().bind(button.widthProperty());
        imgView.fitHeightProperty().bind(button.heightProperty());
        return imgView;
    }

    private void setSmileButton(){
        smileButton.setMinSize(65,65);
        smileButton.setMaxSize(65,65);
        smileButton.setGraphic(bigButtonImage(smileButton,"file:src/main/java/org/example/juego/resources/smile.png"));
        smileButton.setOnMouseClicked(event -> {
            scene1 = createGameScene();
            controller.startGame();
            switchScenes(scene1);

        });

    }
    private void setFlagQButton(){
        flagsQButton.setMinSize(70,60);
        flagsQButton.setMaxSize(70,60);
        flagsQButton.setGraphic(flagQImage());
    }
    private ImageView flagQImage(){
        Image img = switch (controller.availableFlags) {
            case 1 -> new Image("file:src/main/java/org/example/juego/resources/01.png");
            case 2 -> new Image("file:src/main/java/org/example/juego/resources/02.png");
            case 3 -> new Image("file:src/main/java/org/example/juego/resources/03.png");
            case 4 -> new Image("file:src/main/java/org/example/juego/resources/04.png");
            case 5 -> new Image("file:src/main/java/org/example/juego/resources/05.png");
            case 6 -> new Image("file:src/main/java/org/example/juego/resources/06.png");
            case 7 -> new Image("file:src/main/java/org/example/juego/resources/07.png");
            case 8 -> new Image("file:src/main/java/org/example/juego/resources/08.png");
            case 9 -> new Image("file:src/main/java/org/example/juego/resources/09.png");
            case 0 -> new Image("file:src/main/java/org/example/juego/resources/00.png");
            case 10 -> new Image("file:src/main/java/org/example/juego/resources/10.png");
            default -> null;
        };
        ImageView imgView = new ImageView(img);
        imgView.fitWidthProperty().bind(flagsQButton.widthProperty());
        imgView.fitHeightProperty().bind(flagsQButton.heightProperty());
        imgView.setPreserveRatio(true);
        return imgView;
    }
    private Button setAskButton(){
        askButton = new Button();
        askButton.setMinSize(65,65);
        askButton.setMaxSize(65,65);
        askButton.setGraphic(bigButtonImage(askButton, "file:src/main/java/org/example/juego/resources/ask.png"));
        askButton.setOnMouseClicked(e-> stageRules.show());
        return askButton;
    }


}