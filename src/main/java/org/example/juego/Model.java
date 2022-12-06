package org.example.juego;

public class Model {

    private final String titleApp;
    private int level;
    public Model(){
        titleApp = "Minesweeper";
        level = 1;
    }

    public String getTitleApp(){
        return titleApp;
    }
    public int getLevel(){
        return level;
    }
}
