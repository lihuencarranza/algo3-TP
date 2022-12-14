package org.example.juego;


public class Game{
    public enum State { PLAYING, LOST, WON }
    private State state;
    public final Board board;
    public final int flags = 10;


    public boolean hasBomb(int row, int col){
        return board.matrix[row][col].number == 10;
    }

    public void clickBox(int row, int col){
        board.clickBox(row,col);
    }

    public void setState(boolean b){
        board.unableAllBoxes();
        if (b) {
            state = State.WON;
        }else{
            state = State.LOST;
        }

    }

    public boolean getState(){
        return state == State.WON;
    }


    public Game(){
        board = new Board();
        state = State.PLAYING;
        board.enableBoxes();
    }






}
