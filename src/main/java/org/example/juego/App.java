package org.example.juego;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private Game game;
    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();
        View view = new View(stage, controller);


    }
    public static void main(String[] args){
        launch();
    }

}
