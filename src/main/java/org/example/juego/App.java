package org.example.juego;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        View view = new View(stage, model);
        Controller controller = new Controller(model, view);
        controller.start();
    }
    public static void main(String[] args){
        launch();
    }

}
