package org.example.juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {
    private final Model model;
    private Controller controller;

    private Stage stage;
    private Scene scene1;
    private Button startButton;
    private Button rulesButton;

    private Scene scene2;
    private GridPane grid;
    private Button smileButton;

    /*private Button smileButton;
    private Button buttons[][];
    private GridPane grid;
    private Scene menu;
    private Scene s;
    private TilePane tilePane;*/

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
            controller.startGame();
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

    private void setButtonInGridEvents(Button button, int row, int col){

        button.setOnMouseClicked(event -> {
            //caso click derecho y no tiene bandera
            if (event.getButton() == MouseButton.SECONDARY && !controller.isFlaged(row, col)){
                Image flagIcon = new Image("file:src/main/java/org/example/juego/resources/redFlag.png");
                ImageView flagImgView = new ImageView(flagIcon);
                flagImgView.setFitWidth(19);
                flagImgView.setFitHeight(20);
                button.setGraphic(flagImgView);
                controller.setFlag(row, col);
                System.out.println("Flag: " + row + ", " + col);

            }/*else if(event.getButton() == MouseButton.SECONDARY){
                button.setGraphic(null);
                controller.setFlag(row, col);
            }*/
        });
    }
    private void setButtonInGrid(Button button, int row, int col){
        button.setMinWidth(35);
        button.setMinHeight(35);
        setButtonInGridEvents(button, row, col);

    }

    private void setGrid(GridPane grid){
        for (int i = 0; i < 10; i++){
            for (int j=0; j < 10; j++){
                Button button = new Button();
                setButtonInGrid(button, i , j);
                grid.add(button, i, j);
            }
        }
    }

    private Scene createGameScene() {

        smileButton = new Button();
        setSmileButton(smileButton);

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

    private Scene createRules(){
        return scene1;
    }

    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }





    /*public View(Stage stage, Model model){
        //stage.setTitle("Minesweeper");
        //menu = new Scene(createMenu());
        //stage.setScene(menu);
        //stage.show();
        //stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/595/595582.png"));
        //stage.setResizable(false);
        //s = new Scene(createContent());



    }

    private Parent createMenu() {
        Pane pane = new Pane();
        Button b = new Button();
        b.setOnAction(actionEvent ->
                s = new Scene(createContent()));
        return pane;
    }

    private Parent createContent() {
        smile = new Button();


        grid = new GridPane();
        for (int j = 0; j < 10; j++){
            for (int i= 0; i<10; i++){
                Button button = new Button();
                button.setMinWidth(35);
                button.setMinHeight(35);
                button.setOnAction(actionEvent ->  {
                    button.setDisable(true);
                });
                GridPane.setFillWidth(button, true);
                grid.add(button, i, j);
            }
        }


        return grid;
    }*/
}
