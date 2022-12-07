package org.example.juego;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private Game game;
    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(stage, model, controller);

    }
    public static void main(String[] args){
        launch();
    }

}
