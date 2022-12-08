package org.example.juego;

public class Model {

    private final String titleApp;

    private int mines;

    public Model(){
        titleApp = "Minesweeper";

    }


    public int getMines(){
        return mines;
    }
    public String getTitleApp(){
        return titleApp;
    }



}
