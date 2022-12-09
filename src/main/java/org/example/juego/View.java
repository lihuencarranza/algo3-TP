package org.example.juego;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class View {

    private final Controller controller;
    private Button[][] buttonsMatrix;
    private final Stage stage;
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
        button.setGraphic(blankImage(button));
        setButtonInGridEvents(button, row, col);
    }
    private void setButtonInGridEvents(Button button, int col, int row){
        button.setOnMouseClicked(event -> {
            switch (event.getButton()){
                case SECONDARY:
                    if(!controller.isFlaged(row,col) && controller.availableFlags > 0){
                        button.setGraphic(flagImage(button));
                        controller.setFlag(row,col);
                        if(controller.availableFlags == 0)
                            checkGame();
                        setFlagQButton();

                    }else{
                        button.setGraphic(blankImage(button));
                        controller.removeFlag(row,col);
                        setFlagQButton();
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
            controller.endGame(true);
            wonMessageStage.show();
        }else {
            controller.endGame(false);
            smileButton.setGraphic(loserSmileImage());}
    }
    private boolean validateFlagInBomb(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                if (controller.hasBomb(i, j) && !controller.isFlaged(i, j)) {
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
            case 10 -> new Image("file:src/main/java/org/example/juego/resources/Mine.png");
            default -> null;
        };

        ImageView imgView = new ImageView(img);
        imgView.fitWidthProperty().bind(b.widthProperty());
        imgView.fitHeightProperty().bind(b.heightProperty());
        imgView.setPreserveRatio(true);
        return imgView;
    }
    private ImageView askImage(){
        Image rulesIcon = new Image("file:src/main/java/org/example/juego/resources/ask.png");
        ImageView rulesImgView = new ImageView(rulesIcon);
        rulesImgView.fitWidthProperty().bind(askButton.widthProperty());
        rulesImgView.fitHeightProperty().bind(askButton.heightProperty());
        return rulesImgView;
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
        loserImgView.fitWidthProperty().bind(smileButton.widthProperty());
        loserImgView.fitHeightProperty().bind(smileButton.heightProperty());
        loserImgView.setPreserveRatio(true);
        return loserImgView;
    }
    private ImageView starEyesFaceImage(){
        Image starEyesFaceImg= new Image("file:src/main/java/org/example/juego/resources/starEyes.png");
        ImageView starEyesFaceImgView = new ImageView(starEyesFaceImg);
        starEyesFaceImgView.fitWidthProperty().bind(smileButton.widthProperty());
        starEyesFaceImgView.fitHeightProperty().bind(smileButton.heightProperty());
        starEyesFaceImgView.setPreserveRatio(true);
        return starEyesFaceImgView;
    }
    private ImageView neutralSmile(){
        Image smileIcon = new Image("file:src/main/java/org/example/juego/resources/smile.png");
        ImageView smileImgView = new ImageView(smileIcon);
        smileImgView.fitWidthProperty().bind(smileButton.widthProperty());
        smileImgView.fitHeightProperty().bind(smileButton.heightProperty());
        smileImgView.setPreserveRatio(true);
        return smileImgView;
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

    private void setSmileButton(){
        smileButton.setMinSize(65,65);
        smileButton.setMaxSize(65,65);
        smileButton.setGraphic(neutralSmile());
        smileButton.setOnMouseClicked(event -> {
            scene1 = createGameScene();
            controller.startGame();
            switchScenes(scene1);

        });

    }
    private Button setAskButton(){
        askButton = new Button();
        askButton.setMinSize(65,65);
        askButton.setMaxSize(65,65);
        askButton.setGraphic(askImage());

        return askButton;
    }
    private void setFlagQButton(){
        flagsQButton.setMinSize(70,60);
        flagsQButton.setMaxSize(70,60);
        flagsQButton.setGraphic(flagQImage());
    }
}
