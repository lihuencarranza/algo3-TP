package org.example.juego;

public class Controller {
    public Game game;

    public int availableFlags = 10;

    public Controller() {

    }

    void startGame(){

        game = new Game();

    }

    public boolean isVisible(int row, int col){return !game.board.matrix[row][col].visible;}
    public void setFlag(int row, int col) {
         game.board.setFlagBox(row, col);
         this.availableFlags--;
    }

    public boolean isFlaged(int row, int col){
        return game.board.matrix[row][col].flag;
    }

    public boolean isClickable(int row, int col){
        return game.board.matrix[row][col].isClickable();
    }

    public void removeFlag(int row, int col){
        game.board.unsetFlag(row, col);
        this.availableFlags++;
    }

    public int getNumber(int row, int col){
        return game.board.matrix[row][col].number;
    }

    public boolean hasBomb(int row, int col){return game.board.matrix[row][col].isABomb();}

    public void click(int row, int col){game.clickBox(row,col); }


    public void endGame(boolean b){
        game.setState(b);
    }


}
