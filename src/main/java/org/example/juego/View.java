package org.example.juego;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {
    private final Model model;
    private Stage stage1;
    private Scene scene1;
    private Button startButton;
    private Button rulesButton;
    private Stage stage2;
    private Scene scene2;
    private GridPane grid;
    private Button smileButton;

    /*private Button smileButton;
    private Button buttons[][];
    private GridPane grid;
    private Scene menu;
    private Scene s;
    private TilePane tilePane;*/

    public View(Stage stage, Model model){
        this.model = model;

        stage1 = createInitialStage();
        stage2 = createGameStage();


        stage1.show();
    }
    private Stage createInitialStage() {
        stage1 = new Stage();
        stage1.setTitle(model.getTitleApp());
        stage1.setResizable(false);
        stage1.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/595/595582.png"));

        startButton = new Button("start");
        startButton.setOnAction(e-> stage2.show());
        rulesButton = new Button("rules");
        smileButton = new Button();


        VBox vBox = new VBox(smileButton, startButton, rulesButton);
        scene1 = new Scene(vBox,300,300);
        stage1.setScene(scene1);
        return stage1;
    }
    private Stage createGameStage() {
        stage2 = new Stage();
        stage2.setTitle(model.getTitleApp());
        stage2.setResizable(false);
        stage2.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/595/595582.png"));

        grid = new GridPane();

        for (int i = 0; i < )


        return stage2;
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
