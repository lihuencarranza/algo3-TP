package org.example.juego;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class View {

    private final Controller controller;


    private Button[][] buttonsMatrix;
    private final Stage stage;
    private Scene scene1;

    private Scene scene2;
    private Button smileButton;

    public View(Stage stage,  Controller controller){

        this.controller = controller;
        this.stage = stage;
        stage.setTitle("Minesweeper");
        stage.getIcons().add(new Image("file:src/main/java/org/example/juego/resources/icon.png"));
        stage.setResizable(false);

        scene1 = createInitialScene();
        scene2 = createGameScene();

        stage.setScene(scene1);

        stage.show();
    }
    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

    private Scene createInitialScene() {

        Button startButton = setStartButton();
        Button rulesButton = setRulesButton();


        Label label = new Label("hola");

        VBox vBox = new VBox(label, startButton, rulesButton);
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);
        scene1 = new Scene(vBox,350,425);
        return scene1;
    }
    private Button setStartButton(){
        Button b = new Button("start");
        b.setMinHeight(50);
        b.setMinWidth(100);
        b.setOnAction(e -> switchScenes(scene2));
        return b;
    }
    private Button setRulesButton(){
        Button b = new Button("rules");
        b.setMinHeight(50);
        b.setMinWidth(100);
        return b;
    }

    private Scene createGameScene() {

        controller.startGame();
        smileButton = new Button();
        setSmileButton(smileButton);
        buttonsMatrix = new Button[10][10];
        minesList = new Button[10];
        GridPane grid = new GridPane();
        setGrid(grid);

        HBox hBox = new HBox(smileButton);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox, grid);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        scene2 = new Scene(vBox);

        return scene2;
    }

    private void setGrid(GridPane grid){

        int h = 0;
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

        button.setMinWidth(35);
        button.setMinHeight(35);
        button.setCancelButton(true);
        button.setGraphic(blankImage(button));
        //blankImage(button);
        setButtonInGridEvents(button, row, col);
    }
    private void setButtonInGridEvents(Button button, int col, int row){
        button.setOnMouseClicked(event -> {
            switch (event.getButton()){
                case SECONDARY:
                    if(!controller.isFlaged(row,col)){
                        button.setGraphic(flagImage(button));
                        controller.setFlag(row,col);
                    }else{
                        button.setGraphic(null);
                        controller.removeFlag(row,col);
                    }
                    break;

                case PRIMARY:
                    if(!controller.isFlaged(row,col) && controller.hasBomb(row,col)) {
                        clickAllBombs(row,col);
                        endGame(false);
                    }else if (!controller.isFlaged(row,col) && controller.getNumber(row,col) == 0){
                        clickEmptyBoxes(row,col);
                    }else if(!controller.isFlaged(row,col)){
                        clickNumberBox(button,row,col);
                    }
                    break;
            }
            checkGame();
            controller.game.board.printBoard();

        });
    }
    private void checkGame(){
       if(controller.availableFlags == 0 && validateFlagInBomb()){
            endGame(true);
       }
    }
    public void endGame(boolean result){
        if(result){
            smileButton.setGraphic(starEyesFaceImage());
        }else {
            smileButton.setGraphic(loserSmileImage());}
    }
    private boolean validateFlagInBomb(){

        for (int j = 0; j < 10;j++){
            Button b = minesList[j];
            if(b.getGraphic() != flagImage(b)){
                return false;
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
                    b.setGraphic(mineImage(b));
                    b.setDisable(true);
                }

            }
        }
        b = buttonsMatrix[row][col];
        b.setGraphic(bloodImage(b));

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
            case 9 -> new Image("file:src/main/java/org/example/juego/resources/9.png");
            case 10 -> new Image("file:src/main/java/org/example/juego/resources/Mine.png");
            default -> null;
        };

        ImageView imgView = new ImageView(img);
        imgView.fitWidthProperty().bind(b.widthProperty());
        imgView.fitHeightProperty().bind(b.heightProperty());
        imgView.setPreserveRatio(true);
        return imgView;
    }
    private ImageView bloodImage(Button b){
        Image mineIcon = new Image("file:src/main/java/org/example/juego/resources/blood.png");
        ImageView mineImgView = new ImageView(mineIcon);
        mineImgView.fitWidthProperty().bind(b.widthProperty());
        mineImgView.fitHeightProperty().bind(b.heightProperty());
        mineImgView.setPreserveRatio(true);
        return mineImgView;
    }
    private ImageView mineImage(Button b){
        Image mineIcon = new Image("file:src/main/java/org/example/juego/resources/Mine.png");
        ImageView mineImgView = new ImageView(mineIcon);
        mineImgView.fitWidthProperty().bind(b.widthProperty());
        mineImgView.fitHeightProperty().bind(b.heightProperty());
        mineImgView.setPreserveRatio(true);
        return mineImgView;
    }
    private ImageView flagImage(Button b){
        Image flagIcon = new Image("file:src/main/java/org/example/juego/resources/redFlag.png");
        ImageView flagImgView = new ImageView(flagIcon);
        flagImgView.fitWidthProperty().bind(b.widthProperty());
        flagImgView.fitHeightProperty().bind(b.heightProperty());
        flagImgView.setPreserveRatio(true);
        return flagImgView;
    }
    private ImageView blankImage(Button b){
        Image blankIcon = new Image("file:src/main/java/org/example/juego/resources/blank.png");
        ImageView blankImgView = new ImageView(blankIcon);
        blankImgView.fitWidthProperty().bind(b.widthProperty());
        blankImgView.fitHeightProperty().bind(b.heightProperty());
        blankImgView.setPreserveRatio(true);
        return blankImgView;
    }
    private ImageView loserSmileImage(){
        Image loserImg= new Image("file:src/main/java/org/example/juego/resources/cryFace.png");
        ImageView loserImgView = new ImageView(loserImg);
        loserImgView.setFitWidth(50);
        loserImgView.setFitHeight(50);
        return loserImgView;
    }
    private ImageView starEyesFaceImage(){
        Image starEyesFaceImg= new Image("file:src/main/java/org/example/juego/resources/starEyes.png");
        ImageView starEyesFaceImgView = new ImageView(starEyesFaceImg);
        starEyesFaceImgView.setFitWidth(50);
        starEyesFaceImgView.setFitHeight(50);
        return starEyesFaceImgView;
    }
    private void setSmileButton(Button smileButton){
        smileButton.setMinWidth(50);
        smileButton.setMinHeight(50);
        Image smileIcon = new Image("file:src/main/java/org/example/juego/resources/smile.png");
        ImageView smileImgView = new ImageView(smileIcon);
        smileImgView.setFitHeight(50);
        smileImgView.setPreserveRatio(true);
        smileButton.setGraphic(smileImgView);

        smileButton.setOnMouseClicked(event -> {
            scene2 = createGameScene();
            controller.startGame();
            switchScenes(scene2);

        });

    }

}
