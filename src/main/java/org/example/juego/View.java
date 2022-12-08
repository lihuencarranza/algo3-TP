package org.example.juego;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class View {
    private final Model model;
    private Controller controller;

    private List<Button> minesList;

    private Button[][] buttonsMatrix;
    private Stage stage;
    private Scene scene1;
    private Button startButton;
    private Button rulesButton;


    private Scene scene2;
    private GridPane grid;
    private Button smileButton;

    public View(Stage stage, Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        this.stage = stage;
        stage.setTitle(model.getTitleApp());
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

        startButton = setStartButton();
        rulesButton = setRulesButton();


        Label label = new Label(model.getTitleApp());

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
        b.setOnAction(e -> {
            switchScenes(scene2);
        });
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
        minesList = new ArrayList<Button>() ;
        buttonsMatrix = new Button[model.getRows()][model.getCols()];
        grid = new GridPane();
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
        for (int i = 0; i < model.getRows(); i++){
            for (int j=0; j < model.getCols(); j++){
                Button button = new Button();
                setButtonInGrid(button, i , j);
                buttonsMatrix[j][i] = button;
                if (controller.hasBomb(j, i))
                    minesList.add(button);
                grid.add(button, i, j);
            }
        }
        System.out.println(minesList.size());
    }
    private void setButtonInGrid(Button button, int row, int col){
        button.setMinWidth(35);
        button.setMinHeight(35);
        setButtonInGridEvents(button, row, col);
    }
    private void setButtonInGridEvents(Button button, int col, int row){

        button.setOnMouseClicked(event -> {
            switch (event.getButton()){
                case SECONDARY:
                    if(!controller.isFlaged(row,col)){
                        button.setGraphic(flagImage());
                        controller.setFlag(row,col);
                    }else{
                        button.setGraphic(null);
                        controller.removeFlag(row,col);
                    }
                    break;

                case PRIMARY:
                    if(!controller.isFlaged(row,col) && controller.hasBomb(row,col)) {
                        controller.click(row,col);
                        button.setGraphic(mineImage());
                        button.setDisable(true);
                    }
                        controller.click(row,col);
                        button.setGraphic(mineImage());
                        button.setDisable(true);
                        clickAllBombs();
                    break;


            }
            controller.game.board.printBoard();

        });
    }

    private void clickAllBombs(){
        for (Button b: minesList) {
            b.setGraphic(mineImage());
            b.setDisable(true);
        }
        smileButton.setGraphic(loserSmileImage());
    }
    private ImageView mineImage(){
        Image mineIcon = new Image("file:src/main/java/org/example/juego/resources/Mine.png");
        ImageView mineImgView = new ImageView(mineIcon);
        mineImgView.setFitWidth(19);
        mineImgView.setFitHeight(20);

        return mineImgView;
    }
    private ImageView flagImage(){
        Image flagIcon = new Image("file:src/main/java/org/example/juego/resources/redFlag.png");
        ImageView flagImgView = new ImageView(flagIcon);
        flagImgView.setFitWidth(19);
        flagImgView.setFitHeight(20);
        return flagImgView;
    }
    private ImageView loserSmileImage(){
        Image loserImg= new Image("file:src/main/java/org/example/juego/resources/cryFace.png");
        ImageView loserImgView = new ImageView(loserImg);
        loserImgView.setFitWidth(50);
        loserImgView.setFitHeight(50);
        return loserImgView;
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
            switchScenes(scene2);
            controller.startGame();
        });

    }

}
