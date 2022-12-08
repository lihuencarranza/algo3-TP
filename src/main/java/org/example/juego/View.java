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

import java.util.*;

public class View {
    private final Model model;
    private Controller controller;

    private List<Button> buttonList;
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

    private ImageView numberImage(int i){
        Image img = switch (i) {
            case 1 -> new Image("file:src/main/java/org/example/juego/resources/1.png");
            case 2 -> new Image("file:src/main/java/org/example/juego/resources/2.png");
            case 3 -> new Image("file:src/main/java/org/example/juego/resources/3.png");
            case 4 -> new Image("file:src/main/java/org/example/juego/resources/4.png");
            case 5 -> new Image("file:src/main/java/org/example/juego/resources/5.png");
            case 6 -> new Image("file:src/main/java/org/example/juego/resources/6.png");
            case 7 -> new Image("file:src/main/java/org/example/juego/resources/7.png");
            case 8 -> new Image("file:src/main/java/org/example/juego/resources/8.png");
            case 9 -> new Image("file:src/main/java/org/example/juego/resources/9.png");
            default -> null;
        };

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(19);
        imgView.setFitHeight(20);
        return imgView;
    }

    private ImageView flagImage(){
        Image flagIcon = new Image("file:src/main/java/org/example/juego/resources/redFlag.png");
        ImageView flagImgView = new ImageView(flagIcon);
        flagImgView.setFitWidth(19);
        flagImgView.setFitHeight(20);
        return flagImgView;
    }

    private ImageView mineImage(){
        Image mineIcon = new Image("file:src/main/java/org/example/juego/resources/Mine.png");
        ImageView mineImgView = new ImageView(mineIcon);
        mineImgView.setFitWidth(19);
        mineImgView.setFitHeight(20);

        return mineImgView;
    }

    private ImageView setLoserSmile(){
        Image loserImg= new Image("file:src/main/java/org/example/juego/resources/cryFace.png");
        ImageView loserImgView = new ImageView(loserImg);
        loserImgView.setFitWidth(50);
        loserImgView.setFitHeight(50);
        return loserImgView;
    }

    private ImageView setWinSmile(){
        Image starImg= new Image("file:src/main/java/org/example/juego/resources/starEyes.png");
        ImageView starImgView = new ImageView(starImg);
        starImgView.setFitWidth(50);
        starImgView.setFitHeight(50);
        return starImgView;
    }
    private void clickBombs(){
        controller.endGame();


        for(Button b : buttonList){
            b.setGraphic(mineImage());
            b.setDisable(true);
        }
        smileButton.setGraphic(setLoserSmile());
    }
    private int permittedRow(int i){
        if(i < 0)
            return 0;
        else if(i > model.getRows())
            return model.getRows();
        return i;
    }
    private int permittedCol(int i){
        if(i < 0)
            return 0;
        else if(i > model.getCols())
            return model.getCols();
        return i;
    }

    private void clickEmptyBoxesAround(int row, int col){
        row = permittedRow(row);
        col = permittedCol(col);

        if (!controller.isClickable(row,col)){
            System.out.println("no es clickable");
            return;
        }
        System.out.println("es clickable");
        controller.click(row,col);
        Button b = new Button();
        b.setMinWidth(35);
        b.setMinHeight(35);
        b.setDisable(true);
        if (controller.getNumber(row,col) > 0){
            clickNumber(b, row, col);
            return;
        }
        grid.add(b,row,col);
        /*clickEmptyBoxesAround(row-1, col-1);
        clickEmptyBoxesAround(row-1, col+1);
        clickEmptyBoxesAround(row+1, col-1);
        clickEmptyBoxesAround(row+1, col+1);
        clickEmptyBoxesAround(row-1, col);
        clickEmptyBoxesAround(row+1, col);
        clickEmptyBoxesAround(row, col-1);
        clickEmptyBoxesAround(row, col+1);
        */return;
    }

    public void clickNumber(Button b, int row, int col){
        int i = controller.getNumber(row,col);
        b.setGraphic(numberImage(i));
        b.setDisable(true);
        controller.click(row, col);
    }
    public void disableButtons(){
        /*for (int i = 0; i < model.getRows(); i++){
            for (int j=0; j < model.getCols(); j++){
                if (!controller.hasBomb(i,j) && !controller.isFlaged(i,j) && !controller.isVisible(i, j)){
                    Button b = new Button();
                    b.setDisable(true);
                    grid.add(b, i, j);
                }
            }
        }*/

    }
    private void setButtonInGridEvents(Button button, int col, int row){

        button.setOnMouseClicked(event -> {
            switch (event.getButton()){
                case SECONDARY://#8
                    if (!controller.isFlaged(row, col)){
                        button.setGraphic(flagImage());
                        controller.setFlag(row, col);
                    }else if(controller.isFlaged(row, col)){
                        button.setGraphic(null);
                        controller.removeFlag(row,col);
                    }
                    break;

                case PRIMARY:
                    if (!controller.isFlaged(row, col) && controller.hasBomb(row,col)){
                        button.setGraphic(mineImage());
                        button.setDisable(true);
                        controller.click(row, col);
                        disableButtons();
                        clickBombs();

                    }else if(!controller.isFlaged(row,col) && controller.getNumber(row, col) == 0){
                        clickEmptyBoxesAround(row,col);
                    }if (!controller.isFlaged(row,col) && controller.getNumber(row, col) != 10){
                        clickNumber(button, row, col);
                    }
                    break;


            }
            controller.game.board.printBoard();




        });
    }
    private void setButtonInGrid(Button button, int row, int col){
        button.setMinWidth(35);
        button.setMinHeight(35);
        setButtonInGridEvents(button, row, col);

    }

    private void setGrid(GridPane grid){
        for (int i = 0; i < model.getRows(); i++){
            for (int j=0; j < model.getCols(); j++){
                Button button = new Button();
                setButtonInGrid(button, i , j);
                if (controller.hasBomb(j, i))
                    buttonList.add(button);
                grid.add(button, i, j);
            }
        }
    }

    private Scene createGameScene() {

        controller.startGame();
        smileButton = new Button();
        setSmileButton(smileButton);
        buttonList = new ArrayList<Button>() ;
        grid = new GridPane();
        setGrid(grid);

        //Label label = new Label("" + controller.availableFlags);
        //Pane pane = new Pane(label);
        HBox hBox = new HBox(smileButton);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox, grid);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        scene2 = new Scene(vBox);

        return scene2;
    }

    private Scene createRules(){return scene1;}


    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

}
