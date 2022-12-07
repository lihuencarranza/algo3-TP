package org.example.juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller {
    private Model model;
    public Game game;

    public int availableFlags = 10;

    public Controller(Model model) {
        this.model = model;
    }

    void startGame(){
        game = new Game();
    }


    public void setFlag(int row, int col) {
         game.board.setFlagBox(row, col);
         this.availableFlags--;
    }

    public boolean isFlaged(int row, int col){
        return game.board.matrix[row][col].flag;
    }

    public void removeFlag(int row, int col){
        game.board.unsetFlag(row, col);
        this.availableFlags++;
    }

    public int getNumber(int row, int col){
        return game.board.matrix[row][col].number;
    }

    public boolean hasBomb(int row, int col){
        return game.board.matrix[row][col].isABomb();
    }

    public void click(int row, int col){

    }

    public int[][] endGame(){
        return game.board.minesList;
    }


}
