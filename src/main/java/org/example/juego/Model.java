package org.example.juego;

public class Model {

    private final String titleApp;
    private int rows;
    private int cols;
    private int mines;
    private int level;
    public Model(){
        titleApp = "Minesweeper";

    }
    public int getRows(){
        return rows;
    }

    public int getCols(){
        return mines;
    }

    public int getMines(){
        return mines;
    }
    public String getTitleApp(){
        return titleApp;
    }
    public int getLevel(){
        return level;
    }

    public void setLevel(int i){
        if (i == 1){
            rows = 10;
            cols = 10;
            mines = 10;
        }
    }


}
