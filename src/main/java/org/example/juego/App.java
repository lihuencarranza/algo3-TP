package org.example.juego;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private Parent createContent() {
        TextField input = new TextField();
        Text output = new Text();
        Button button = new Button("Press");
        button.setOnAction(e -> {
            output.setText(input.getText());
        });
        TilePane root = new TilePane(input, button, output);
        root.setPrefSize(300,300);
        return root;
    }

    public static void main(String[] args){
        launch();
    }
}
