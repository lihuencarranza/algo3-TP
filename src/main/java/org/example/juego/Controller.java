package org.example.juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller {
    private Model model;
    private View view;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        /*view.recordListen(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        })
        */

    }
}
