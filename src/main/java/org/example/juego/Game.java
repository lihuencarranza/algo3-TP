package org.example.juego;


public class Game{
    public static enum State { PLAYING, LOST, WON };
    private State state;
    public Board board;
    public final int flags = 10;


    public boolean hasBomb(int row, int col){
        return board.matrix[row][col].number == 10;
    }

    public void clickBox(int row, int col){
        board.matrix[row][col] = BoxFactory.createClickedBox(board.matrix[row][col].getNumber());
    }

    public void setState(boolean b){
        if (b)
            state = State.WON;
        else{
            state = State.LOST;
            board.unableAllBoxes();
        }

    }

    public boolean getState(){
        return state == State.WON;
    }


    public Game(){
        board = new Board();
        state = State.PLAYING;
    }






}
