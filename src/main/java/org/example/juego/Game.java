package org.example.juego;


import org.example.juego.Boxes.Box;

import java.util.Random;
import java.util.Scanner;

public class Game{

    public boolean gameOver = false;
    public Board board;
    public final int flags = 10;

    public Box usingBotModeBoxClicked(){
        board = new Board();
        Random random = new Random();


        int col = (int) random.nextInt(10);
        int row = (int) random.nextInt(10);

        gameOver = board.clickBox(row, col);

        return board.matrix[row][col];

    }
    public boolean hasBomb(int row, int col){
        return board.matrix[row][col].number == 10;
    }

    public void clickBox(int row, int col){
        board.matrix[row][col] = BoxFactory.createBox(BoxFactory.Type.CLICK, board.matrix[row][col].getNumber());
    }


    public Game(){
        board = new Board();
    }






}
